package com.frontad.frontadvertisement;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void regex_slicing_isCorrect(){
        String link = "https://www.youtube.com/watch?v=uv09yQOTEJ4&ab_channel=%EB%83%A5%EC%8B%A0TV";
        List<String> a = Arrays.asList(link.split("v="));
        assertEquals("uv09yQOTEJ4",a.get(1).split("&")[0]);
    }

//https://www.youtube.com/watch?v=uv09yQOTEJ4&ab_channel=%EB%83%A5%EC%8B%A0TV

}