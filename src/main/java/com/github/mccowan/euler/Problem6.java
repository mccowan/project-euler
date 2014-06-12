package com.github.mccowan.euler;

/**
 * @author mccowan
 */
public class Problem6 {
    public static void main(String[] args) {
        final long sumOfSquares = Math.naturalsTo(100).reduce((i, j) -> i + j * j).get();
        final long sum = Math.naturalsTo(100).reduce((i, j) -> i + j).get();
        System.out.println(sum * sum - sumOfSquares);
    }
}
