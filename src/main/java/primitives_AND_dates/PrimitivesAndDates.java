package primitives_AND_dates;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.IntFunction;

public class PrimitivesAndDates {
    static void primitives() {
        /*
            Any operation of 2 numbers of a type minor than "int" produces and "int" for safety
            if one of them is bigger than int, then the result will be of this type
         */
        byte b1 = 1;
        byte b2 = 1;
        int b = b1 + b2;

        //switch applicable just for Strings, enums, < int..
        // & vs &&
        //
        // >> << >>>
        int x = 4 >>> 4;


        x = 101;
        if (false | true | false | ++x > 0) {
            System.out.println(x);
        }

        var c = 0;
        JACK:
        while (c < 8) {
            JILL:
            System.out.println(c);
            if (c > 3) break JACK;
            else c++;
        }

        long l = 'd';
        byte bfff = 4;
        short s = bfff;
        char ch = (char) bfff;

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
