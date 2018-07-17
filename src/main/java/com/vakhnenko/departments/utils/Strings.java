package com.vakhnenko.departments.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vakhnenko.departments.entity.Constants.MAX_LENGTH_PASSWORD;
import static com.vakhnenko.departments.entity.Constants.MIN_LENGTH_PASSWORD;

public class Strings {

    // http://j4web.ru/java-regex/validatsiya-parolya-s-pomoshhyu-regulyarnogo-vyrazheniya.html
    // ((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])." +
            "{" + MIN_LENGTH_PASSWORD + "," + MAX_LENGTH_PASSWORD + "})";

    public Strings() {
    }

    public static String getRefererPath(String path) {
        String result = path.replaceAll("//", "");
        return result.substring(result.indexOf("/"));
    }

    public static boolean digitsAndCharactersOnly(String password) {
        //String result = password.replaceAll("\\w|\\d", "");
        //return (result.length() == 0) ? true : false;
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static String timeDateStering() {
        String result = " " + new Date().toString();
        return result.replaceAll("\\s|\\:|\\+", "-");
    }
}
