package org.function.policies;

import org.function.CachePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnboundedCachePolicyTest {
    CachePolicy<Integer, Integer> cachePolicy;

    @BeforeEach
    void setUp() {
        cachePolicy = new UnboundedCachePolicy<>();
    }

    @Test
    public void cachePolicySavesAllEntries() {
        IntStream.range(0, 5)
                .forEach(entry -> assertEquals(entry, cachePolicy.computeIfAbsent(entry, Function.identity())));
        assertEquals("{0=0, 1=1, 2=2, 3=3, 4=4}", cachePolicy.toString());
    }
}