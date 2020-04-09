package com.oocl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GuessNumberTest {
    GuessNumber game;

    @Before
    public void setUp() throws Exception {
        // before method
        HashMap<Character, Integer> answer = new HashMap<Character, Integer>();
        answer.put('1', 0);
        answer.put('2', 1);
        answer.put('3', 2);
        answer.put('4', 3);
        AnswerGenerator answerGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(answer);
        game = new GuessNumber(answerGenerator);
    }

    @Test
    public void should_map_answer_string_to_has_map() {
        String result = game.getAnswer();

        assertThat(result, is("1234"));
    }

    @Test
    public void should_check_input_with_answer() {

        String result1 = game.guess("1567");
        String result2 = game.guess("2478");
        String result3 = game.guess("0324");
        String result4 = game.guess("5678");
        String result5 = game.guess("4321");
        String result6 = game.guess("1234");

        assertThat(result1, is("1A0B"));
        assertThat(result2, is("0A2B"));
        assertThat(result3, is("1A2B"));
        assertThat(result4, is("0A0B"));
        assertThat(result5, is("0A4B"));
        assertThat(result6, is("4A0B"));
    }

    @Test
    public void should_check_user_input_valid() {

        String result1 = game.guess("1123");
        String result2 = game.guess("12");

        assertThat(result1, is("Wrong Input, Input again"));
        assertThat(result2, is("Wrong Input, Input again"));
    }

    @Test
    public void should_generate_random_for_answer() {

        String answer = game.getAnswer();
        boolean result = game.isInputValid(answer);
        assertThat(result, is(true));
    }
}
