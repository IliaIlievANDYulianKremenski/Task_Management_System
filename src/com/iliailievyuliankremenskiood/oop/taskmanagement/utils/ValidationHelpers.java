package com.iliailievyuliankremenskiood.oop.taskmanagement.utils;

import com.iliailievyuliankremenskiood.oop.taskmanagement.exceptions.InvalidUserInputException;

public class ValidationHelpers {

    public static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new InvalidUserInputException(message);
        }
    }

    public static void validateStringLength(String string, int minLength, int maxLength, String message) {
        validateIntRange(string.length(), minLength, maxLength,message);
    }
}
