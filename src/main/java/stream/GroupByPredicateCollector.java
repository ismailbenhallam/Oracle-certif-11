package stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class GroupByPredicateCollector<T> implements Collector<T, Map<Boolean, List<T>>, Map<Boolean, List<T>>> {
    private final Predicate<T> predicate;

    public GroupByPredicateCollector(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public Supplier<Map<Boolean, List<T>>> supplier() {
        return () -> {
            var map = Collections.synchronizedMap(new HashMap<Boolean, List<T>>());
            map.put(true, new ArrayList<>());
            map.put(false, new ArrayList<>());
            return map;
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<T>>, T> accumulator() {
        return (map, t) -> {
            if (predicate.test(t))
                map.get(true).add(t);
            else
                map.get(false).add(t);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<T>>> combiner() {
        return (left, right) -> {
            left.putAll(right);
            return left;
        };
    }

    @Override
    public Function<Map<Boolean, List<T>>, Map<Boolean, List<T>>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
//        return EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED);
        return EnumSet.allOf(Characteristics.class);
    }

    public static void main(String[] args) {
        var map = List.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .stream()
                .parallel()
                .collect(new GroupByPredicateCollector<>(i -> i >= 0));
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
