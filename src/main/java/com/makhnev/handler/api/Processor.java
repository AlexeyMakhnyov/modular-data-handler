package com.makhnev.handler.api;

import com.makhnev.handler.exchange.ExchangeContext;

public interface Processor {
    ExchangeContext process(ExchangeContext exchangeContext);
}
