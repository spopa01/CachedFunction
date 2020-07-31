package org.function;

import org.function.policies.AccessOrderBoundedCachePolicy;
import org.function.policies.InsertionOrderBoundedCachePolicy;
import org.function.policies.NoCachePolicy;
import org.function.policies.UnboundedCachePolicy;

import static java.util.Objects.requireNonNull;

public interface CachePolicyFactory {
    static <I, O> CachePolicy<I, O> build(CACHE_POLICY cachePolicy) {
        return build(cachePolicy, 256);
    }

    static <I, O> CachePolicy<I, O> build(CACHE_POLICY cachePolicy, int capacity) {
        return switch (requireNonNull(cachePolicy)) {
            case NO_CACHE -> new NoCachePolicy<>();
            case UNBOUNDED_CACHE -> new UnboundedCachePolicy<>();
            case INSERTION_ORDER_BOUNDED_CACHE -> new InsertionOrderBoundedCachePolicy<>(capacity);
            case ACCESS_ORDER_BOUNDED_CACHE -> new AccessOrderBoundedCachePolicy<>(capacity);
        };
    }

    enum CACHE_POLICY {
        NO_CACHE,
        UNBOUNDED_CACHE,
        INSERTION_ORDER_BOUNDED_CACHE,
        ACCESS_ORDER_BOUNDED_CACHE,
    }
}