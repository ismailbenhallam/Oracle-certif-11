package nested_classes_AND_lambda_expressions;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.BiFunction;

public class Notes {
    /*
       4 types :
       - Static nested class
       - Member nested class
       - Local inner class
       - Anonymous inner class
     */

    private static class A {
        private final static int x = 5;

        private static final class B {

            public void n() {

                // This anonymous class in fact does implements Comparator interface
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return 0;
                    }
                };

                class L {

                }
            }

            private final class C {
                // Member nested class can have 'static' field (final only !)
                private final static int x = 5;

            }
        }

    }

    Math takeSystem1(System s) {
        return null;
    }

    static Math takeSystem2(Notes n, System s) {
        return null;
    }

    Math takeSystem3(Notes notes, System system) {
        return null;
    }

    public static void main(String[] args) {
        takeIt(Notes::takeSystem1);
        takeIt(Notes::takeSystem2);
        var notes = new Notes();
        takeIt(notes::takeSystem3);
    }


    static BigDecimal takeIt(BiFunction<Notes, System, Math> f) {
        return null;
    }

}
