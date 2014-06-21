package com.github.mccowan.euler;

import com.github.mccowan.euler.math.MoreMath;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * @author mccowan
 */
public class Problem5 {
    public static void main(String[] args) {
        final HashMultiset<Long> factors = HashMultiset.create();
        for (int i = 0; i <= 20; i++) {
            for (final Multiset.Entry<Long> primeCardinality : MoreMath.primeFactors(i).entrySet()) {
                final int minimum = primeCardinality.getCount();
                final long prime = primeCardinality.getElement();
                if (minimum > factors.count(prime)) {
                    factors.setCount(prime, minimum);
                }
            }
        }

        System.out.println(factors.stream().reduce((i, j) -> i * j));
    }
}
