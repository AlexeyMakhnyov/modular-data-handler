package com.makhnev.handler;

import com.makhnev.handler.engine.JobBuilder;
import com.makhnev.handler.test.TestProcessor;
import com.makhnev.handler.test.TestReader;
import com.makhnev.handler.test.TestWriter;
import io.micrometer.observation.ObservationRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class Runner {
    ThreadPoolTaskScheduler threadPoolTaskScheduler;
    ObservationRegistry observationRegistry;

    public Runner(ThreadPoolTaskScheduler threadPoolTaskScheduler, ObservationRegistry observationRegistry) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
        this.observationRegistry = observationRegistry;
    }

    @PostConstruct
    public void run() {
        final var job = JobBuilder.builder()
                .reader(new TestReader())
                .processors(new TestProcessor())
                .writer(new TestWriter())
                .observationRegistry(observationRegistry)
                .build();
        threadPoolTaskScheduler.execute(job::run);
    }
}
