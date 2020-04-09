package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ConsoleInputReaderTest {
    @Test
    public void should_return_input_when_get_input() {
        ConsoleInputReader reader = new ConsoleInputReader();
        String expected = "1234";
        InputStream in = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in);
        String input = reader.getInput();
        Assert.assertEquals(expected, input);
    }
}