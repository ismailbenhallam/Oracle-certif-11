package collections;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Examples {
    private String name;

    public String getName() {
        return name;
    }

    public static void main(String[] args) throws Exception {

        synchronizedCollections();

//        mapLoadFactor();

//        immutableCollectionsVsUnmodifiable();

//        copyOnWrite();

//        deque();

//        mapMerge();

//        treeMap();

//        navigableMap();

//        comparators();

        // NOTE: Invariant
//        List<CharSequence> charSequences = new ArrayList<String>(); // Cannot compile, unlike Arrays => 'covariant'
    }

    private static void treeMap() {
        // Ex1
        var treeMap1 = new TreeMap<String, String>();
        treeMap1.put("A", "");
        treeMap1.put("D", "");
        treeMap1.put("B", "");
        treeMap1.put("E", "");
        treeMap1.put("C", "");
        treeMap1.keySet().forEach(System.out::print);
        System.out.println();

        // Ex2
        var treeMap2 = new TreeMap<Integer, String>();
        treeMap2.put(9, "");
        treeMap2.put(1, "");
        treeMap2.put(5, "");
        treeMap2.put(3, "");
        treeMap2.put(2, "");
        treeMap2.put(6, "");
        treeMap2.put(4, "");
        treeMap2.put(7, "");
        treeMap2.put(8, "");
        treeMap2.keySet().forEach(System.out::print);
        System.out.println();

        // Ex3
        class Point {
        }
        var treeMap3 = new TreeMap<Point, String>();
        /*
            Fails, because no comparator is provided in the constructor of the TreeMap
            and the class Point doesn't implement Comparable interface
         */
        try {
            treeMap3.put(new Point(), "");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void mapMerge() {
        var map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 1);

        map.merge("b", 1, (i1, i2) -> i1 + i2);
        map.merge("c", 3, (i1, i2) -> i1 + i2);

        System.out.println(map); // {a=1, b=2, c=3}
    }

    private static void deque() {
        var deque = new ArrayDeque<Integer>();
        /*
            return boolean or null / throw exception :
            offer/add
                offerFirst/addFirst
                offerLast/addLast
            peek/element !
                peekFirst/getFirst
                peekLast/getLast
            poll/remove
                pollFirst/removeFirst
                pollLast/removeLast

            push and pop
         */

        deque.offer(1);
        deque.offer(2);
        deque.offer(3);
        deque.offer(4);
        deque.offer(5);
        deque.push(0);

        deque.offerFirst(-1);
        deque.offer(6);

        System.out.println("-1 = " + deque.poll());
        System.out.println("There is no -1 now");

        System.out.println("0 =" + deque.element());

        deque.push(-1);
        System.out.println("-1 = " + deque.pop());

        System.out.println(deque); // 0 1 2 3 4 5 6
    }

    private static void navigableMap() {
        NavigableMap<String, String> navigableMap = new TreeMap<>();

        System.out.println(navigableMap.firstEntry());
        System.out.println(navigableMap.lastEntry());
        // ...
    }

    private static void copyOnWrite() {
        // CopyOnWrite..
        new CopyOnWriteArrayList<>();
        new CopyOnWriteArraySet<>();
    }

    private static void immutableCollectionsVsUnmodifiable() {
        // Immutable vs Unmodifiable Collections
        try {
            List<Integer> list = new ArrayList<>();
            list.add(9);

            /*
                "Collections.unmodifiableCollection" return a "wrapper" around your current Collection implementation,
                This collection is unmodifiable, it throws an "UnsupportedOperationException" if we try to modify it.
                But we can always modify the original one !!
             */
            List<Integer> unmodifiableList = Collections.unmodifiableList(list);

            /*
                Let's try to get the original collection and modify it ;)
                NOTE: If your program is modular, you can not access the inner collection using Reflection
             */
            Field declaredField = unmodifiableList.getClass().getSuperclass().getDeclaredField("list");
            declaredField.setAccessible(true);
            List<Integer> originalList = (List<Integer>) declaredField.get(unmodifiableList);

            System.out.println(originalList.get(0));
            System.out.println(originalList == list);
        } catch (Exception e) {
            System.out.println(e);
        }

        /*
            In the other hand, An immutableCollection is read-only
        */
        List<Integer> immutableList = List.of(10, 20, 30);
        System.out.println(immutableList.size());
        try {
            immutableList.add(5); // Read-only
        } catch (UnsupportedOperationException e) {
            System.out.println("As expected :" + e);
        }

    }

    private static void mapLoadFactor() {
        // TODO: Load factor ?
        var strings = new HashSet<Integer>(10, 0.85f);
    }

    private static void synchronizedCollections() {
        /*
            Collections.synchronizedCollection return a "wrapper" around your current List implementation,
            which means you don't copy data to another data structure and you keep underlying structure intact.

            See :
            https://stackoverflow.com/questions/14932034/in-java-vector-and-collections-synchronizedlist-are-all-synchronized-whats-th
            https://stackoverflow.com/questions/1386275/why-is-java-vector-and-stack-class-considered-obsolete-or-deprecated
         */
        List<Object> synchronizedList = Collections.synchronizedList(new ArrayList<>());
    }

    private static void comparators() {
        var list = new ArrayList<Integer>();

        Collections.sort(list);
//        Collections.sort(list, (o1, o2) -> o1 - o2);
        Collections.sort(list, Comparator.comparingInt(o -> o));
        Collections.sort(list, Comparator.naturalOrder());
        Collections.sort(list, Comparator.reverseOrder()/*.thenComparing(...)*/);

        var list2 = new ArrayList<Examples>();
        Collections.sort(list2,
                Comparator.<Examples>comparingInt(System::identityHashCode)
                        .thenComparing(Examples::getName)
        );
    }

}
