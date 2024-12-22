package com.makhnev.handler.test;

import com.makhnev.handler.api.Reader;
import com.makhnev.handler.exchange.ExchangeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TestReader implements Reader {
    private static final Logger logger = LoggerFactory.getLogger(TestReader.class);

    @Override
    public ExchangeContext read() {
        logger.info("Read some message...");
        return new ExchangeContext("testKey", "testValue", Map.of());
    }
}
