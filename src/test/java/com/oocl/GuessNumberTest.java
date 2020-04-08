package com.oocl;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GuessNumberTest {
    @Test
    public void should_map_answer_string_to_has_map() {
        GuessNumber guessNumber = new GuessNumber("1234");

        String result = guessNumber.getAnswer();
        assertThat(result, is("1234"));
    }
}
