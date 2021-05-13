package org.stancu.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Formatter {

    static Random random = new Random();

    public static String formatDate() {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.of(2021, Math.abs(random.nextInt() % 12) + 1, Math.abs(random.nextInt() % 28) + 1);
        return dateFormat.format(date);
    }

    public static String formatTime() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH-mm-ss");
        LocalTime time = LocalTime.of(Math.abs(random.nextInt() % 23) + 1, Math.abs(random.nextInt() % 59) + 1,
                Math.abs(random.nextInt() % 59) + 1);
        return timeFormat.format(time);
    }
}
