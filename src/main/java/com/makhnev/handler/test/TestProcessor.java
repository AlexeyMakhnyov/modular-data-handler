package com.makhnev.handler.test;

import com.makhnev.handler.api.Processor;
import com.makhnev.handler.exchange.ExchangeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProcessor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(TestProcessor.class);

    @Override
    public ExchangeContext process(ExchangeContext exchangeContext) {
        logger.info("Process message with key: " + exchangeContext.key() + " end value: " + exchangeContext.value());
        return new ExchangeContext(exchangeContext.key(), "newValue", exchangeContext.parameters());
    }
}
