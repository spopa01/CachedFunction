package org.function;

import org.function.policies.AccessOrderBoundedCachePolicy;
import org.function.policies.InsertionOrderBoundedCachePolicy;
import org.function.policies.NoCachePolicy;
import org.function.policies.UnboundedCachePolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FluentCachePolicyFactoryTest {
    @Test
    public void testFluentCachePolicyFactory() {
        assertTrue(FluentCachePolicyFactory.noCache() instanceof NoCachePolicy);
        assertTrue(FluentCachePolicyFactory.unboundCache() instanceof UnboundedCachePolicy);
        assertTrue(FluentCachePolicyFactory.boundedCache(10).withInsertOrderEviction() instanceof InsertionOrderBoundedCachePolicy);
        assertTrue(FluentCachePolicyFactory.boundedCache(10).withAccessOrderEviction() instanceof AccessOrderBoundedCachePolicy);
    }
}