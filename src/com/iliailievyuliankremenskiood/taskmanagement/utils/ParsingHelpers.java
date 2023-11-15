package com.iliailievyuliankremenskiood.taskmanagement.utils;

import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;

public class ParsingHelpers {

    private static final String INVALID_NUMBER_MESSAGE = "Invalid value for %s. Should be an integer number.";

    public static <E extends Enum<E>> E parseEnum(String valueToParse, Class<E> type, String errorMessage) {
        try {
            return Enum.valueOf(type, valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }

    public static int parseInteger(String valueToParse, String parameterName) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new InvalidUserInputException(String.format(INVALID_NUMBER_MESSAGE, parameterName));
        }
    }
}
