package com.github.mccowan.euler;

import com.github.mccowan.euler.math.Grid;
import com.github.mccowan.euler.util.MoreStreams;
import com.github.mccowan.euler.util.MoreStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mccowan
 */
public class Problem11 {
    private static final String INPUT = "08022297381500400075040507785212507791084949994017811857608717409843694804566200814931735579142993714067538830034913366552709523046011426924685601325671370236912231167151676389419236542240402866331380244732609903450244753353783684203517125032988128642367102638406759547066183864706726206802621220956394396308409166499421245558056673992697177878968314883489637221362309750076442045351400613397343133957817532822753167159403800462161409535692163905429635314755588824001754243629855786560048357189070544443744602158515417581980816805944769287392138652177704895540045208839735991607975732162626793327986688366887576220720346336746551232639353690442167338253911249472180846293240627636206936417230238834629969826759857404361620733529783190017431497148868116235705540170547183515469169233486143520189196748";

    public static void main(String[] args) {
        final Grid<Long> grid = composeGrid(MoreStrings.framesOf(INPUT, 2, 2).map(Long::valueOf), 20, 20);
        final long max = MoreStreams.fromIterator(grid.iterator())
                .map(Problem11::uniqueRaysFrom)
                .flatMap(i -> i.stream())
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .mapToLong(p -> p.stream().mapToLong(value -> value.value()).reduce((x, y) -> x * y).getAsLong()
                ).max().getAsLong();
        System.out.println(max);
    }

    static <T> List<Optional<List<Grid<Long>.Point>>> uniqueRaysFrom(final Grid<Long>.Point from) {
        final List<Optional<List<Grid<Long>.Point>>> paths = new ArrayList<>(4);
        paths.add(from.collectionPointsTowards(Grid.Direction.N, 3));
        paths.add(from.collectionPointsTowards(Grid.Direction.NE, 3));
        paths.add(from.collectionPointsTowards(Grid.Direction.E, 3));
        paths.add(from.collectionPointsTowards(Grid.Direction.SE, 3));
        return paths;
    }

    static <T> Grid<T> composeGrid(final Stream<T> s, final int x, final int y) {
        final List<T> pointElements = s.collect(Collectors.toList());
        final List<List<T>> rows = new ArrayList<>();
        for (int i = 0; i < pointElements.size() / x; i++) {
            rows.add(pointElements.subList(i * x, (i + 1) * x));
        }
        return Grid.<T>of(rows);
    }

}
