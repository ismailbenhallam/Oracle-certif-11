package annotations;

import java.util.List;

public class SafeVarargsMethods {

    /*
        NOTE: The Generics and Varargs combination can lead to "Heap pollution".
        methods annotated @SafeVarargs should be final or private, to maintain type safety guarantee
     */
    @SafeVarargs
    public final void some(List<String>... values) {
        for (List<String> value : values) {
            value.forEach(System.out::println);
            System.out.println("---");
        }
    }

}
