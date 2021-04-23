package stream;

import java.util.*;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Examples {
    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        // takeWhile
//        System.out.println(new Random().ints().parallel().peek(System.out::println).takeWhile(i -> i < 0).count());

        // Short-circuit terminal oprations
//        System.out.println(new Random().ints().parallel().peek(System.out::println).anyMatch(i -> i < 0));

//        predefinedFunctionalInterfacesMethods();

//        flatMap();

        reduce();


        // Collectors
        summaryStatistics();

//        collectors();
    }

    private static void summaryStatistics() {
        System.out.println(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .summaryStatistics());

        var random = new Random();
        System.out.println(Stream.generate(() -> new Point(random.nextInt(), random.nextInt()))
                .limit(40)
                .collect(Collectors.summarizingInt(p -> p.x)));
    }


    private static void reduce() {
        var names = Stream.of("Ismaïl", "BENHALLAM")
                .reduce(
                        (s1, s2) -> s1 + ", " + s2
                )
                .orElse("");
//                .collect(Collectors.joining(", "));
        System.out.println(names);
    }


    private static void collectors() {
        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .collect(Collectors.partitioningBy((Integer i) ->  i % 2 == 0 )
//                .collect(Collectors.groupingBy(i -> i % 2 == 0 ? "Odd" : "Even"))
                .collect(HashMap<Boolean, Integer>::new, (map, i) -> {
                    if (i % 2 == 0)
                        map.put(true, i);
                    else
                        map.put(false, i);
                }, HashMap::putAll);

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .collect(Collectors.partitioningBy())
                .collect(new OddAndEvenCollector())
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }

    private static void predefinedFunctionalInterfacesMethods() {
        var predicate = Predicate.<String>isEqual("Ko");
        var identity = Function.identity();
    }

    private static void flatMap() {
        // FlatMap
        var list1 = List.of(1, 2, 3);
        var list2 = List.of(4, 5, 6);
        var list3 = List.of(7, 8, 9);
        Stream.of(list1, list2, list3)
                .distinct()
                .flatMap(l -> l.stream())
                .forEach(System.out::println);
    }
}
