package generics;

import java.util.ArrayList;
import java.util.List;

public class Examples {
    public static void main(String[] args) {
        covariant();

        invariant();

        wildcards();
        lowerBoundWildcard();
        upperBoundWildcard();
    }

    private static void covariant() {
        // Covariant
        CharSequence[] array = new StringBuilder[10];
        array[0] = new StringBuffer(); // ArrayStoreException !
    }

    private static void wildcards() {
        List<String> strings = new ArrayList<>();

        List<?> listOfUnknownType = strings; // This assignment is covariant
        // We cannot add elements to this list, except null values
//        listOfUnknownType.add("fff");

        Object o = listOfUnknownType.get(0);

    }

    private static void invariant() {
        // NOTE: Invariant
//        List<CharSequence> charSequences = new ArrayList<String>(); // Cannot compile, unlike Arrays covariant
        List<String> strings = new ArrayList<>();
        List values = strings;
        List<CharSequence> charSequences = values;
        charSequences.add(new StringBuilder());
        StringBuilder x1 = ((StringBuilder) values.get(0));
        String x2 = strings.get(0); // ClassCastException !
    }

    private static void upperBoundWildcard() {
        List<? extends CharSequence> strings;
        strings = new ArrayList<String>();
        strings = new ArrayList<StringBuffer>();

//        strings.add("sss"); // Forbidden
    }

    private static void lowerBoundWildcard() {
        // NOTE: contra-variant
        List<? super Integer> list;
        list = new ArrayList<Integer>();
        list = new ArrayList<Number>();

        list.add(5);
        Number number = null; // some number
//        strings.add(number); // Forbidden

//        Integer integer = list.get(0); // Forbidden
        Object object = list.get(0); // Forbidden
    }
}
