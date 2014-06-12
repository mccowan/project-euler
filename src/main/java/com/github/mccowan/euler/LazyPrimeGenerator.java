package com.github.mccowan.euler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author mccowan
 */
public class LazyPrimeGenerator implements Iterable<Long> {
    public static LazyPrimeGenerator getInstance() {
        return Instance.INSTANCE.primeGenerator;
    }

    private enum Instance {
        INSTANCE(new LazyPrimeGenerator());
        final LazyPrimeGenerator primeGenerator;

        Instance(LazyPrimeGenerator primeGenerator) {
            this.primeGenerator = primeGenerator;
        }
    }

    private final List<Long> primeCache = new ArrayList<>();

    {
        primeCache.add(2L); // A seed makes life much easier.
    }

    private void hydrateCacheToIndex(final long index) {
        long candidatePrime = largestPrimeInCache();
        candidatePrime += candidatePrime % 2 == 0 ? 1 : 2;
        while (index > primeCache.size() - 1) {
            isPrime(candidatePrime); // Updates cache
            candidatePrime += 2;
        }
    }

    synchronized boolean isPrime(final long primeCandidate) {
        if (primeCache.contains(primeCandidate)) {
            return true; // todo: lookup faster   
        } else if (primeCandidate < largestPrimeInCache()) {
            return false;
        }
        
        boolean isPrime = true;
        for (Long divisorCandidate : primeCache) { // Is this really OK?  How do I prove it?
            if (primeCandidate % divisorCandidate == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            primeCache.add(primeCandidate);
        }

        return isPrime;
    }

    private Long largestPrimeInCache() {
        return primeCache.get(primeCache.size() - 1);
    }

    public Iterator<Long> iterator() {
        return new Iterator<Long>() {
            int currentPrimeIndex = 0;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Long next() {
                hydrateCacheToIndex(currentPrimeIndex);
                return primeCache.get(currentPrimeIndex++);
            }
        };
    }
}
