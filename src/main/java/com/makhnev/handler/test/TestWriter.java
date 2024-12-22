package com.makhnev.handler.test;

import com.makhnev.handler.api.Writer;
import com.makhnev.handler.exchange.ExchangeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestWriter implements Writer {
    private static final Logger logger = LoggerFactory.getLogger(TestWriter.class);

    @Override
    public void write(ExchangeContext exchangeContext) {
        logger.info("Write message with key: "+ exchangeContext.key() + " end value: " + exchangeContext.value());
    }
}
