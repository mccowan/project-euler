package com.github.mccowan.euler;

import com.github.mccowan.euler.math.MoreMath;

/**
 * @author mccowan
 */
public class Problem7 {
    public static void main(String[] args) {
        System.out.println(MoreMath.primes().skip(10000).findFirst().get());
    }
}
