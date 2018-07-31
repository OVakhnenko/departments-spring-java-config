package com.vakhnenko.departments.utils;

import org.junit.Test;

import static com.vakhnenko.departments.utils.Strings.digitsAndCharactersOnly;
import static com.vakhnenko.departments.utils.Strings.getRefererPath;

public class StringsTests {
    @Test
    public void get_referer_path() {
        String result = getRefererPath("http://google.com/test");
        if (!"/test".equals(result)) {
            throw new AssertionError();
        }
    }

    @Test
    public void digits_and_characters_only() {
        if (!digitsAndCharactersOnly("aaa111AAA")) {
            throw new AssertionError();
        }
        if (digitsAndCharactersOnly("12345678")) {
            throw new AssertionError();
        }
        if (digitsAndCharactersOnly("abcdefg")) {
            throw new AssertionError();
        }
    }
}
