package org.function;

import java.util.function.Function;

public interface CachePolicy<I, O> {
    O computeIfAbsent(I input, Function<I, O> function);
}