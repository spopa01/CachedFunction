package org.function;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class CachedFunction<I, O> implements Function<I, O> {
    private final Function<I, O> function;
    private final CachePolicy<I, O> cachePolicy;

    public CachedFunction(CachePolicy<I, O> policy, Function<I, O> function) {
        this.cachePolicy = requireNonNull(policy);
        this.function = requireNonNull(function);
    }

    public static <I, O> Function<I, O> build(CachePolicy<I, O> policy, Function<I, O> function) {
        return new CachedFunction<>(policy, function);
    }

    @Override
    public O apply(I input) {
        return cachePolicy.computeIfAbsent(input, function);
    }
}