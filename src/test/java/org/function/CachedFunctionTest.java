package org.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.IntStream;

import static org.function.CachePolicyFactory.CACHE_POLICY.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CachedFunctionTest {
    Function<Integer, Integer> function;

    @BeforeEach
    void setUp() {
        function = CachedFunction.build(CachePolicyFactory.build(UNBOUNDED_CACHE), Function.identity());
    }

    @Test
    public void testCachedFunction() {
        IntStream.range(0, 5).forEach(entry -> assertEquals(entry, function.apply(entry)));
    }
}