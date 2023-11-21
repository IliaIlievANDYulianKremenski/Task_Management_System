package com.iliailievyuliankremenskiood.taskmanagement;

public class Main {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static void main(String[] args) {

    String string = "This is a String!";
    StringBuilder builder = new StringBuilder();
    String filter = "tri";
    int index = string.indexOf(filter);
        System.out.println(index);
     String firstPart = string.substring(0,index);
     String secondPart = ANSI_GREEN + string.substring(index,index + filter.length()) + ANSI_RESET;
     String thirdPart = string.substring(index + filter.length());
        System.out.println(firstPart+secondPart+thirdPart);

    }
}