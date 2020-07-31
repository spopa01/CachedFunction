package org.function.policies;

import org.function.CachePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoCachePolicyTest {
    CachePolicy<Integer, Integer> cachePolicy;

    @BeforeEach
    void setUp() {
        cachePolicy = new NoCachePolicy<>();
    }

    @Test
    public void cachePolicySavesNoEntries() {
        IntStream.range(0, 5)
                .forEach(entry -> assertEquals(entry, cachePolicy.computeIfAbsent(entry, Function.identity())));
        assertEquals("{}", cachePolicy.toString());
    }
}