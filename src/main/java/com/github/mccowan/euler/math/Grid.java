package com.github.mccowan.euler.math;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mccowan
 */
public class Grid<T> implements Iterable<Grid<T>.Point> {
    public enum Direction {
        N(0, 1), NE(1, 1),
        E(1, 0), SE(1, -1),
        S(0, -1), SW(-1, -1),
        W(-1, 0), NW(-1, 1);

        final int xUnitDelta, yUnitDelta;

        Direction(int xUnitDelta, int yUnitDelta) {
            this.xUnitDelta = xUnitDelta;
            this.yUnitDelta = yUnitDelta;
        }

        int yDelta(final int distance) {
            return distance * yUnitDelta;
        }

        int xDelta(final int distance) {
            return distance * xUnitDelta;
        }
    }

    private final int yWidth, xWidth;

    public static <T> Grid<T> of(final List<List<T>> rows) {
        return new Grid<>(rows);
    }

    public class Point {
        final int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("Point(%s,%s)=%s", x, y, value());
        }

        public T value() {
            return values.get(x).get(y);
        }

        Optional<Point> displace(final int xDelta, final int yDelta) {
            final int xResult = xDelta + x;
            final int yResult = yDelta + y;
            final Optional<Point> result;
            if (contains(xResult, yResult)) {
                result = Optional.of(new Point(xResult, yResult));
            } else {
                result = Optional.empty();
            }
            return result;
        }

        /**
         * Produces the points from this one
         */
        public Optional<List<Point>> collectionPointsTowards(final Direction direction, final int distance) {
            final Optional<Point> displace = displace(direction.xDelta(distance), direction.yDelta(distance));
            if (displace.isPresent()) {
                final ImmutableList.Builder<Point> b = ImmutableList.builder();
                b.add(this);
                for (int i = 1; i < distance; i++) {
                    b.add(displace(direction.xDelta(i), direction.yDelta(i)).get());
                }
                return Optional.of(b.add(displace.get()).build());
            } else {
                return Optional.empty();
            }
        }
    }

    final List<List<T>> values;
    final long size;

    Grid(List<List<T>> rows) {
        final Set<Integer> rowSizes =
                Stream.of(rows).map(List<List<T>>::size).distinct().collect(Collectors.toSet());
        if (rowSizes.size() != 1) {
            throw new IllegalArgumentException("Multiple row sizes: " + Joiner.on(", ").join(rowSizes));
        }
        this.xWidth = rowSizes.iterator().next();
        this.yWidth = rows.size();
        this.size = xWidth * yWidth;

        final ImmutableList.Builder<List<T>> builder = ImmutableList.builder();
        for (final List<T> row : rows) {
            builder.add(ImmutableList.copyOf(row));
        }
        this.values = builder.build();
    }

    public boolean contains(final int x, final int y) {
        return x < xWidth && y < yWidth && x >= 0 && y >= 0;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            long position = 0;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public Point next() {
                if (!hasNext()) throw new NoSuchElementException();
                final Point next = new Point((int) position % xWidth, (int) position / xWidth);
                position++;
                return next;
            }
        };
    }
}
