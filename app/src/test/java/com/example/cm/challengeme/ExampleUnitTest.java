package com.example.cm.challengeme;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private String name;

    @Before
    public void initialize() {
        name = "AIT ERRAMI";
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void nameIsCorrect() {
        assertEquals("AIT ERRAMI", name);
    }
}