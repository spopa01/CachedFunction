# CachedFunction
A replacement for java.util.function.Function when having to deal with expensive functions that take very long time to compute.

Simple usage:
```
Function<Integer, Integer> function =
    CachedFunction.build(
        CachePolicyFactory.build(ACCESS_ORDER_BOUNDED_CACHE, 256),
            x -> {
                //my very very expensive function
                return x;
            });

int result = function.apply(100_000_000);
```
![SEE ME](https://github.com/spopa01/CachedFunction/blob/master/Function%20Memoization.png)