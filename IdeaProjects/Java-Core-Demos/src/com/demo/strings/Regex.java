package com.demo.strings;

import java.util.Arrays;

public class Regex {

    public static void main(String[] args) {
        String message = "One 2 3 and you go free. Now again 123";
        //Get all the words!
        String strings[] = message.split(" ");
        // Get the non digits
        int words =0;
        for(String string : strings){
            if(!string.matches("\\d+")) {
                words++;
            }
        }
        System.out.println(words);
    }
}
