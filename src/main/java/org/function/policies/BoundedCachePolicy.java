package org.function.policies;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.function.policies.BoundedCachePolicy.EVICTION_POLICY.ACCESS_ORDER;

public class BoundedCachePolicy<I, O> extends AbstractCachePolicy<I, O> {
    public BoundedCachePolicy(int capacity, EVICTION_POLICY evictionPolicy) {
        super(new BoundedLinkedHashMap<>(capacity, evictionPolicy == ACCESS_ORDER));
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