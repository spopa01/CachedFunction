package org.function;

import org.function.policies.AccessOrderBoundedCachePolicy;
import org.function.policies.InsertionOrderBoundedCachePolicy;
import org.function.policies.NoCachePolicy;
import org.function.policies.UnboundedCachePolicy;
import org.junit.jupiter.api.Test;

import static org.function.CachePolicyFactory.CACHE_POLICY.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CachePolicyFactoryTest {
    @Test
    public void testCachePolices() {
        assertTrue(CachePolicyFactory.build(NO_CACHE) instanceof NoCachePolicy);
        assertTrue(CachePolicyFactory.build(UNBOUNDED_CACHE) instanceof UnboundedCachePolicy);
        assertTrue(CachePolicyFactory.build(INSERTION_ORDER_BOUNDED_CACHE) instanceof InsertionOrderBoundedCachePolicy);
        assertTrue(CachePolicyFactory.build(ACCESS_ORDER_BOUNDED_CACHE) instanceof AccessOrderBoundedCachePolicy);
    }
}