package com.github.mccowan.euler;

import com.github.mccowan.euler.math.MoreMath;

/**
 * @author mccowan
 */
public class Problem6 {
    public static void main(String[] args) {
        final long sumOfSquares = MoreMath.naturals().limit(100).reduce((i, j) -> i + j * j).get();
        final long sum = MoreMath.naturals().limit(100).reduce((i, j) -> i + j).get();
        System.out.println(sum * sum - sumOfSquares);
    }
}
