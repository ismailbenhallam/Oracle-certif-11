package enums;

import java.io.Serializable;
import java.util.EnumSet;

public enum Pets implements Serializable {
    DOG("d"), CAT("c") {
        // We can override a method for a specific enum
        @Override
        public String getName() {
            return super.getName() + " cat";
        }
    };

    static final String CONSTANT = "CONSTANT";
    static String statique = "statique";
    private final String name;

    /*
        There are 3 predefined methods for all enums:
            - name() => "DOG" or "CAT" ...
            - ordinal() => 0 or 1 ...
            - values() => [DOG, CAT] (array)
     */

    Pets(String name) {
//        System.out.println(statique); //illegal;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static void main(String[] args) {
        var pets = Pets.values();
        for (var p : pets) {
            System.out.println(p.ordinal() + " " + p.getName());
        }

        // EnumSet
        EnumSet<Pets> set = EnumSet.allOf(Pets.class);
        set = EnumSet.of(CAT, DOG/*,...*/);
        set = EnumSet.complementOf(set);
    }

}
