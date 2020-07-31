package org.function.policies;

import java.util.HashMap;

public class UnboundedCachePolicy<I, O> extends AbstractCachePolicy<I, O> {
    public UnboundedCachePolicy() {
        super(new HashMap<>());
    }
}
