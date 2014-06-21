package com.github.mccowan.euler;

import com.github.mccowan.euler.math.MoreMath;
import com.github.mccowan.euler.util.MoreStreams;

import java.util.Optional;

/**
 * @author mccowan
 */
public class Problem10 {
    public static void main(String[] args) {
        final Optional<Long> reduce =
                MoreStreams.takeUntil(MoreMath.primes(), i -> i < 2000000).reduce((a, b) -> a + b);
        System.out.println(reduce.get());
    }
}
