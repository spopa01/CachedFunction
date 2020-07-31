package org.function.policies;

public class InsertionOrderBoundedCachePolicy<I, O> extends BoundedCachePolicy<I, O> {
    public InsertionOrderBoundedCachePolicy(int capacity) {
        super(capacity, EVICTION_POLICY.INSERTION_ORDER);
    }
}