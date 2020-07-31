package org.function.policies;

import org.function.CachePolicy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UnboundedCachePolicy<I, O> implements CachePolicy<I, O> {
    private final Map<I, O> cache;

    public UnboundedCachePolicy() {
        this.cache = new HashMap<>();
    }

    @Override
    public O computeIfAbsent(I input, Function<I, O> function) {
        return cache.computeIfAbsent(input, function);
    }

    @Override
    public String toString() {
        return cache.toString();
    }
}
