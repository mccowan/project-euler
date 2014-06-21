package com.github.mccowan.euler;

import com.google.common.base.Joiner;

/**
 * @author mccowan
 */
public class Problem9 {

    public static void main(String[] args) {
        for (int a = 1; a < 998; a++) {
            for (int b = 1; b < a; b++) {
                final int c = 1000 - a - b;
                if (a*a + b*b == c*c) {
                    System.out.println(Joiner.on(", ").join(a, b, c));
                    System.out.println(a * b * c);
                }
            }
        }
    }
}
