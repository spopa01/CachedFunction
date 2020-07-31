package org.function.policies;

import org.function.CachePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccessOrderBoundedCachePolicyTest {
    CachePolicy<Integer, Integer> cachePolicy;

    @BeforeEach
    void setUp() {
        cachePolicy = new AccessOrderBoundedCachePolicy<>(3);
    }

    @Test
    public void cachePolicySavesAllButOnceCapacityHitEvictInAccessOrder() {
        //cache is first filled
        IntStream.range(0, 3)
                .forEach(entry -> assertEquals(entry, cachePolicy.computeIfAbsent(entry, Function.identity())));
        assertEquals("{0=0, 1=1, 2=2}", cachePolicy.toString());
        //cache capacity maintained and elements are evicted in access order (which here overlaps with insertion order)
        IntStream.range(3, 6)
                .forEach(entry -> assertEquals(entry, cachePolicy.computeIfAbsent(entry, Function.identity())));
        assertEquals("{3=3, 4=4, 5=5}", cachePolicy.toString());

        //no eviction but accessed element is placed at the of the eviction order (last to be removed)
        assertEquals(3, cachePolicy.computeIfAbsent(3, Function.identity()));
        assertEquals("{4=4, 5=5, 3=3}", cachePolicy.toString());

        //eviction happen in the expected order, with 3 as last element to be evicted
        assertEquals(0, cachePolicy.computeIfAbsent(0, Function.identity()));
        assertEquals("{5=5, 3=3, 0=0}", cachePolicy.toString());
        assertEquals(1, cachePolicy.computeIfAbsent(1, Function.identity()));
        assertEquals("{3=3, 0=0, 1=1}", cachePolicy.toString());
        assertEquals(2, cachePolicy.computeIfAbsent(2, Function.identity()));
        assertEquals("{0=0, 1=1, 2=2}", cachePolicy.toString());
    }
}