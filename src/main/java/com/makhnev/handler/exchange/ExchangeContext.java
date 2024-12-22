package com.makhnev.handler.exchange;

import java.util.Map;

public record ExchangeContext(
        String key,
        String value,
        Map<String, Object> parameters
) {
}
