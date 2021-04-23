package primitives_AND_dates;

import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

public class PrimitivesAndDates {
    static void primitives() {
        /*
            Any operation of 2 numbers of a type minor than "int" produces and "int" for safety
            if one of them is bigger than int, then the result will be of this type
         */
        byte b1 = 1;
        byte b2 = 12;
        int b = b1 + b2;

        // >> << >>>
        int x = 4 >>> 4;


        // & vs &&
        x = 101;
        if (false | true | false | ++x > 0) {
            System.out.println(x);
        }

        var i = 0;
        JACK:
        while (i < 8) {
            JILL:
            System.out.println(i);
            if (i > 3) break JACK;
            else i++;
        }
    }

    private static void primitivesCompatibility() {
        byte b = 1;
        short s = 1;
        char c = 1;
        int i = 1;
        float f = 1;
        double d = 1;

        s = b;

        i = b;
        i = c;
        i = s;

        f = b;
        f = s;
        f = c;
        f = i;

        d = b;
        d = s;
        d = c;
        d = i;
        d = f;
    }

    private static void wrappers() {
        Integer i = 9;
    }

    static void date() {
        var timestamp = Instant.from(ZonedDateTime.now());
        ZoneId casablanca = ZoneId.of("Africa/Casablanca");
        var dateTime = ZonedDateTime.of(LocalDateTime.now(), casablanca);
        System.out.println(dateTime);
        System.out.println(dateTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles")));
        System.out.println(LocalDateTime.parse("2021-04-16T17:39:01"));
        System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(new Locale("ru")).format(LocalDateTime.now()));
        System.out.println(Duration.between(LocalDateTime.now(), LocalDateTime.now().minusMinutes(75)).toMinutesPart());

        // Locales
        var maroc = new Locale("ar", "MA");
        var france = new Locale("fr", "FR");
        var usa = new Locale("en", "US");
        var locales = List.of(maroc, france, usa);
        System.out.println("\n10 in different currency formatters: ");
        locales.stream().map(NumberFormat::getCurrencyInstance).forEach(nf -> System.out.println(" - " + nf.format(10)));
    }

    public static void main(String[] args) {
        primitives();
        date();
    }


}
