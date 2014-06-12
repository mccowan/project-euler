package com.github.mccowan.euler;

/**
 * @author mccowan
 */
public class Problem7 {
    public static void main(String[] args) {
        System.out.println(Math.primes().skip(10000).findFirst().get());
    }
}
