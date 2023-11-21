package com.iliailievyuliankremenskiood.taskmanagement.utils;

import java.time.format.DateTimeFormatter;

public class FormatterHelpers {
    public static DateTimeFormatter dateTimePattern() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }
}
