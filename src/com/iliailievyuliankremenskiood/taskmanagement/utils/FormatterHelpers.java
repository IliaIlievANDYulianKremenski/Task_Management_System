package com.iliailievyuliankremenskiood.taskmanagement.utils;

import java.time.format.DateTimeFormatter;

public class FormatterHelpers {

    //TODO
    /* Talk with Yuli that he can move formatter method here, so it can be used
    in all the program.
     */

    public static DateTimeFormatter dateTimePattern() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }
}
