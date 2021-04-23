package collections;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Collect {

    public static void main(String[] args) throws Exception {
        // TODO: List.of.. ??
        List<Integer> immutableList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        // TODO: Is this better than a vector ?
        List<Object> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        // TODO: Load factor
        var strings = new HashSet<Integer>(10, 0.85f);

        // Immutable vs Unmodifiable Collections
        List<Integer> unmodifiableList = Collections.unmodifiableList(immutableList);

        // NOTE: If your program is modular, you can not access the inner list using Reflection
//        Field declaredField = unmodifiableList.getClass().getSuperclass().getDeclaredField("list");
//        declaredField.setAccessible(true);
//        List<Integer> immutableList2 = (List<Integer>) declaredField.get(unmodifiableList);
//        immutableList2.add(50);
//        System.out.println(immutableList2 == immutableList);

        // CopyOnWrite
        new CopyOnWriteArrayList<>(immutableList);


        var deque = new ArrayDeque<>();
//        deque.pollFirst();

        var set = new HashSet<String>();
        System.out.println(set.add(null));

        Collections.sort(new ArrayList(immutableList), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });


        // NOTE: Invariant
//        List<CharSequence> charSequences = new ArrayList<String>(); // Cannot compile, unlike Arrays covariant
    }

}
