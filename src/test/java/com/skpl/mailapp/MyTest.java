package com.skpl.mailapp;

import java.util.HashSet;

public class MyTest {

    public static void main(String[] args) {
        String str1 = "1";
        String str2 = "2";
        String str3 = "3";
        String str4 = "4";
        HashSet<String> set = new HashSet<>();
        set.add(str1);
        set.add(str2);
        set.add(str3);
        set.add(str4);

        if(set.contains(new String("1"))) {
            System.out.println(1111);
        }
    }
}
