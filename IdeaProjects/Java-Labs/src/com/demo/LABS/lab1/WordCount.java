package com.demo.LABS.lab1;

class WordCounter {
    public int countWords(String str) {
        if (str == null || str.trim().isEmpty()) return 0;

        String[] tokens = str.split("\\s+");
        int count = 0;
        for (String token : tokens) {
//            if (token.matches("[a-zA-Z]+")) {

                if (token.matches("[a-zA-Z]+")) {
                count++;
            }
            
        }
        return count;
    }
}


class WordCounterMain {
    public static void main(String[] args) {
        WordCounter wc = new WordCounter();
        String input = "Sum of 12 and 20 is 32";
        int wordCount = wc.countWords(input);
        System.out.println("Word count: " + wordCount);
    }
}

