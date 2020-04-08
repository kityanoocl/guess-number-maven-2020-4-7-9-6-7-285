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

    @Test
    public void should_check_input_with_answer() {
        GuessNumber guessNumber = new GuessNumber("1234");

        String result1 = guessNumber.guess("1567");
        String result2 = guessNumber.guess("2478");
        String result3 = guessNumber.guess("0324");
        String result4 = guessNumber.guess("5678");
        String result5 = guessNumber.guess("4321");
        String result6 = guessNumber.guess("1234");

        assertThat(result1, is("1A0B"));
        assertThat(result2, is("0A2B"));
        assertThat(result3, is("1A2B"));
        assertThat(result4, is("0A0B"));
        assertThat(result5, is("0A4B"));
        assertThat(result6, is("4A0B"));
    }
}
