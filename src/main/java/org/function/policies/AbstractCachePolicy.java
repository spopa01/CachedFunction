package org.function.policies;

import org.function.CachePolicy;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractCachePolicy<I, O> implements CachePolicy<I, O> {
    protected final Map<I, O> cache;

    protected AbstractCachePolicy(Map<I, O> cache) {
        this.cache = Objects.requireNonNull(cache);
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