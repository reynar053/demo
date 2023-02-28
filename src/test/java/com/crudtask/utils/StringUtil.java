package com.crudtask.utils;

import java.util.concurrent.ThreadLocalRandom;

public abstract class StringUtil {
    public static String getRndRepeatingNmbrs(int amountOfNumbers){
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 10);
        int factor = 1;
        for (int i = 1; i < amountOfNumbers; i++) {
            factor = factor * 10 + 1;
        }
//        System.out.println("Rnd number is: " + randomNumber + "  factor is: " + factor);
        return randomNumber > 0 ? Integer.toString(randomNumber * factor) : "00";
    }

    public static String getRndString() {
        return "s0o.difh65s#duo+fh";
    }
}
