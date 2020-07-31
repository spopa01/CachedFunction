package org.function;

import static org.function.CachePolicyFactory.CACHE_POLICY.*;

public interface FluentCachePolicyFactory {
    static <I, O> CachePolicy<I, O> noCache() {
        return CachePolicyFactory.build(NO_CACHE);
    }

    static <I, O> CachePolicy<I, O> unboundCache() {
        return CachePolicyFactory.build(UNBOUNDED_CACHE);
    }

    static <I, O> FluentBoundedCachePolicyFactory<I, O> boundedCache(int capacity) {
        return new FluentBoundedCachePolicyFactory<>(capacity);
    }

    class FluentBoundedCachePolicyFactory<I, O> {
        private final int capacity;

        public FluentBoundedCachePolicyFactory(int capacity) {
            this.capacity = capacity;
        }

        public CachePolicy<I, O> withInsertOrderEviction() {
            return CachePolicyFactory.build(INSERTION_ORDER_BOUNDED_CACHE, capacity);
        }

        public CachePolicy<I, O> withAccessOrderEviction() {
            return CachePolicyFactory.build(ACCESS_ORDER_BOUNDED_CACHE, capacity);
        }
    }
}