package arrays_loops;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Executors;

public class ArraysAndLoops {
    public static void main(String[] args) {

        /////////
        var array = new boolean[10];
        System.out.println(array[0]);

        /////////
        ko:
        while (true) {
            if (Math.random() < 0.5) {
                break ko;
            }
            System.out.println("Ko");
        }

        switch (4) {
            case 5:
                break;
        }

        if (false) ;
        else ;


        // Covariant
        CharSequence[] charSequences = new StringBuilder[10];
        charSequences[0] = new StringBuffer(); // ArrayStoreException
    }

}
