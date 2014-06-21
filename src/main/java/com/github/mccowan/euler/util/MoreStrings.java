package com.github.mccowan.euler.util;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author mccowan
 */
public class MoreStrings {
    public static Stream<String> framesOf(final String source, final int frameWidth, final int step) {
        if (frameWidth > source.length()) throw new IllegalArgumentException();
        return StreamSupport.stream(new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    int startIndex = 0;

                    @Override
                    public boolean hasNext() {
                        return startIndex <= source.length() - frameWidth;
                    }

                    @Override
                    public String next() {
                        final String ret = source.substring(startIndex, startIndex + frameWidth);
                        startIndex += step;
                        return ret;
                    }
                };
            }
        }.spliterator(), false);
    }

    public static Stream<String> framesOf(final String source, final int frameWidth) {
        return framesOf(source, frameWidth, 1);
    }
}
