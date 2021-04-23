package stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class GroupByFunctionCollector<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {
    private final Function<T, K> function;

    public GroupByFunctionCollector(Function<T, K> function) {
        this.function = function;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return () -> Collections.synchronizedMap(new HashMap<K, List<T>>());
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map, t) -> {
            K key = function.apply(t);
            if (map.containsKey(key)) {
                map.get(key).add(t);
            } else {
                var value = new ArrayList<T>();
                value.add(t);
                map.put(key, value);
            }
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (left, right) -> {
            left.putAll(right);
            return left;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
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
                .collect(new GroupByFunctionCollector<>(i -> i < 0 ? "Négatif" : i == 0 ? "Zero" : "Positif"));
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
