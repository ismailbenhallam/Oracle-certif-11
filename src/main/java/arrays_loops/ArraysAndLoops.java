package arrays_loops;

public class ArraysAndLoops {
    public static void main(String[] args) {

        /////////
        var array = new boolean[10];
        System.out.println(array[0]);

        /////////
        ko:
        while (true) {
            if (Math.random() < 0.1) {
                break ko;
            }
            System.out.println("Ko");
        }

        switch (4) {
        }

        if (false) ;
        else ;


        covariant();
    }

    private static void covariant() {
        // Covariant
        CharSequence[] charSequences = new StringBuilder[10];
        try {
            charSequences[0] = new StringBuffer(); // ArrayStoreException
        } catch (ArrayStoreException e) {
            System.out.println("Caught exception : " + e);
        }
    }

}
