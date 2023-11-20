package com.iliailievyuliankremenskiood.taskmanagement.utils;

import com.iliailievyuliankremenskiood.taskmanagement.exceptions.InvalidUserInputException;

import java.util.List;

public class ValidationHelpers {

    /*<-------Constant(s)------->*/

    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d, Received: %d";
    public static final String INVALID_ARGUMENTS_TASK_WITH_ASSIGNEE = "Invalid number of arguments. Expected: %d or %d, Received: %d";


    /*<-------Behavioural Method(s)------->*/

    public static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new InvalidUserInputException(message);
        }
    }

    public static void validateStringLength(String string, int minLength, int maxLength, String message) {
        validateIntRange(string.length(), minLength, maxLength,message);
    }

    public static void validateArgumentsCount(List<String> list, int expectedArgumentsCount) {
        if (list.size() < expectedArgumentsCount || list.size() > expectedArgumentsCount) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedArgumentsCount, list.size()));
        }
    }
    public static void validateTaskWithAssigneeCount(List<String> list, int argumentsWithAssignee, int argumentsWithoutAssignee) {
        if (list.size() < argumentsWithoutAssignee || list.size() > argumentsWithAssignee) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, argumentsWithoutAssignee,argumentsWithAssignee, list.size()));
        }
    }

}
