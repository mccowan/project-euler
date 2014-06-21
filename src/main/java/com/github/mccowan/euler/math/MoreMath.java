package com.github.mccowan.euler.math;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author mccowan
 */
public class MoreMath {
    public static Stream<Long> naturals() {
        return Stream.iterate(1L, i -> i + 1);
    }

    public static Stream<Long> primes() {
        final LazyPrimeGenerator generator = LazyPrimeGenerator.getInstance();
        return StreamSupport.stream(generator.spliterator(), false);
    }

    /**
     * Returns the factorization of the provided value.
     */
    public static Multiset<Long> primeFactors(final long value) {
        if (value == 0) return ImmutableMultiset.of();

        long divided = java.lang.Math.abs(value);
        final HashMultiset<Long> divisors = HashMultiset.create();
        final Iterator<Long> primes = MoreMath.primes().iterator();
        while (divided != 1) {
            final Long prime = primes.next();
            while (divided % prime == 0) {
                divided = divided / prime;
                divisors.add(prime);
            }
        }

        return divisors;
    }

    public static Set<Long> distinctPrimeFactors(final long value) {
        return primeFactors(value).elementSet();
    }
}
