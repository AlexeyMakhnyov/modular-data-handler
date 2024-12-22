package com.makhnev.handler.engine;

import com.makhnev.handler.api.Processor;
import com.makhnev.handler.api.Reader;
import com.makhnev.handler.api.Writer;
import io.micrometer.observation.ObservationRegistry;

import java.util.Arrays;
import java.util.List;

public class JobBuilder {
    private Reader reader;
    private Writer writer;
    private List<Processor> processors;
    private ObservationRegistry observationRegistry;

    public static JobBuilder builder() {
        return new JobBuilder();
    }

    public JobBuilder reader(Reader reader) {
        this.reader = reader;
        return this;
    }

    public JobBuilder writer(Writer writer) {
        this.writer = writer;
        return this;
    }

    public JobBuilder processors(Processor... processors) {
        this.processors = Arrays.asList(processors);
        return this;
    }

    public JobBuilder observationRegistry(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
        return this;
    }

    public Job build() {
        return new Job(reader, processors, writer, observationRegistry);
    }
}
