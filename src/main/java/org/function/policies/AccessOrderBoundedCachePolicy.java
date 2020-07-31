package org.function.policies;

public class AccessOrderBoundedCachePolicy<I, O> extends BoundedCachePolicy<I, O> {
    public AccessOrderBoundedCachePolicy(int capacity) {
        super(capacity, EVICTION_POLICY.ACCESS_ORDER);
    }
}