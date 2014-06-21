package com.github.mccowan.euler.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author mccowan
 */
public class MoreStreams {
    public static <T> Stream<T> fromIterator(final Iterator<T> i) {
        final Iterable<T> iterable = new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return i;
            }
        };

        return StreamSupport.stream(iterable.spliterator(), false);
    }
    
    public static <T> Stream<T> takeUntil(final Stream<T> stream, final Predicate<T> p) {
        final PeekingIterator<T> backingIterator = Iterators.peekingIterator(stream.iterator());

        return fromIterator(new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return backingIterator.hasNext() && p.test(backingIterator.peek());
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return backingIterator.next();
            }
        });
    }
}
