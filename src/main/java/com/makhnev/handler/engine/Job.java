package com.makhnev.handler.engine;

import com.makhnev.handler.api.Processor;
import com.makhnev.handler.api.Reader;
import com.makhnev.handler.api.Writer;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Job {
    private static final Logger logger = LoggerFactory.getLogger(Job.class);

    private final Reader reader;
    private final List<Processor> processors;
    private final Writer writer;
    private final ObservationRegistry observationRegistry;

    public Job(Reader reader, List<Processor> processors, Writer writer, ObservationRegistry observationRegistry) {
        this.reader = reader;
        this.processors = processors;
        this.writer = writer;
        this.observationRegistry = observationRegistry;
    }

    public void run() {
        final var observation = Observation.createNotStarted("name", observationRegistry)
                .contextualName("run-name");

        AtomicBoolean isCanceled = new AtomicBoolean(false);
        logger.info("Run job...");
        while (!isCanceled.get()) {
            observation.observe(() -> {
                var exchangeContext = reader.read();
                if (exchangeContext == null) {
                    isCanceled.set(true);
                    return;
                }
                for (Processor processor : processors) {
                    exchangeContext = processor.process(exchangeContext);
                    if (exchangeContext == null) {
                        return;
                    }
                }
                writer.write(exchangeContext);
            });
        }
        logger.info("Stop job...");
    }
}
