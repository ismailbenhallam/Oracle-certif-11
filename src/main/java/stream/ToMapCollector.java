package stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToMapCollector<T, K, V> implements Collector<T, Map<K, V>, Map<K, V>> {
    private final Function<T, K> keyMapper;
    private final Function<T, V> valueMapper;

    public ToMapCollector(Function<T, K> keyMapper, Function<T, V> valueMapper) {
        this.keyMapper = keyMapper;
        this.valueMapper = valueMapper;
    }

    @Override
    public Supplier<Map<K, V>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, V>, T> accumulator() {
        return (map, t) -> map.put(keyMapper.apply(t), valueMapper.apply(t));
    }

    @Override
    public BinaryOperator<Map<K, V>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    @Override
    public Function<Map<K, V>, Map<K, V>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.IDENTITY_FINISH);
    }
}
