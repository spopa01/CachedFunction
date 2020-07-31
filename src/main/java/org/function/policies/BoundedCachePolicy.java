package org.function.policies;

import org.function.CachePolicy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import static org.function.policies.BoundedCachePolicy.EVICTION_POLICY.ACCESS_ORDER;

public class BoundedCachePolicy<I, O> implements CachePolicy<I, O> {
    private final Map<I, O> cache;

    public BoundedCachePolicy(int capacity, EVICTION_POLICY evictionPolicy) {
        final boolean accessOrder = evictionPolicy == ACCESS_ORDER;
        this.cache = new BoundedLinkedHashMap<>(capacity, accessOrder);
    }

    @Override
    public O computeIfAbsent(I input, Function<I, O> function) {
        return cache.computeIfAbsent(input, function);
    }

    @Override
    public String toString() {
        return cache.toString();
    }

    public enum EVICTION_POLICY {
        INSERTION_ORDER,
        ACCESS_ORDER,
    }

    private static class BoundedLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        private static final int DEFAULT_INITIAL_CAPACITY = 16;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private final int capacity;

        public BoundedLinkedHashMap(int capacity, boolean accessOrder) {
            super(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, accessOrder);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
}
