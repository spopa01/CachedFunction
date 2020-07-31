package org.function.policies;

import org.function.CachePolicy;

import java.util.Collections;
import java.util.function.Function;

public class NoCachePolicy<I, O> implements CachePolicy<I, O> {
    @Override
    public O computeIfAbsent(I input, Function<I, O> function) {
        return function.apply(input);
    }

    @Override
    public String toString() {
        return Collections.emptyMap().toString();
    }
}
