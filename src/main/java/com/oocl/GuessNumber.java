package com.oocl;

import java.io.IOException;
import java.util.*;

public class GuessNumber implements Game {
    private static final String ANSWER_STRING_DELIMITER = "";
    private static final int MAX_GUESS_TRIAL_COUNT = 6;
    private static final int ANSWER_LENGTH = 4;
    private static final String DEFAULT_RESULT_STRING = "0A0B";
    private static final String WINNING_RESULT_STRING = "4A0B";
    private static final String WINNING_MESSAGE = "Congratulation! You win!";
    private static final String LOSE_MESSAGE = "Game Over! The answer is %s";
    private static final String WRONG_INPUT_MESSAGE = "Wrong Input, Input again";
    private static final String RESULT_FORMAT_STRING = "%dA%dB";
    private static final String INPUT_PROMPT = "You got %d chance(s) to guess: ";
    private static final String WELCOME_MESSAGE = "Welcome to Guess Number!\nGame Start!\n";
    private static final int CORRECT_NUMBER_NOT_IN_PLACE_POINT = 1;
    private static final int CORRECT_NUMBER_IN_PLACE_POINT = 1;
    private static final int INCORRECT_POINT = 0;
    private final ConsoleInputReader consoleInputReader = new ConsoleInputReader();
    private int guessTrialCount = 0;
    private final HashMap<Character, Integer> answer;
    private String result = DEFAULT_RESULT_STRING;

    public GuessNumber(AnswerGenerator answerGenerator) {
        answer = answerGenerator.generate();
    }

    public String getAnswer() {
        StringJoiner answerString = new StringJoiner(ANSWER_STRING_DELIMITER);
        answer.keySet().forEach(character -> answerString.add(character.toString()));
        return answerString.toString();
    }

    private boolean isGuessStringOnlyContainsInteger(String guessString) {
        return guessString.chars().allMatch(character -> isDigit((char) character));
    }

    private boolean isGuessStringHasNoDuplicate(String guessString) {
        return guessString.chars().allMatch(character -> guessString.lastIndexOf(character) == guessString.indexOf(character));
    }

    private boolean isDigit(Character character) {
        return Character.isDigit(character);
    }

    private boolean isGuessStringValid(String guessString) {
        boolean isMatchLength = guessString.length() == ANSWER_LENGTH;
        return isMatchLength && isGuessStringOnlyContainsInteger(guessString) && isGuessStringHasNoDuplicate(guessString);
    }

    private boolean isNumberCorrectAndInPlace(Character character, int index) {
        return answer.containsKey(character) && answer.get(character) == index;
    }

    private int getPointForNumberCorrectAndInPlace(Character character, int index) {
        if (isNumberCorrectAndInPlace(character, index)) {
            return CORRECT_NUMBER_IN_PLACE_POINT;
        }
        return INCORRECT_POINT;
    }

    private boolean isNumberCorrectButNotInPlace(Character character, int index) {
        return answer.containsKey(character) && answer.get(character) != index;
    }

    private int getPointForNumberCorrectAndNotInPlace(Character character, int index) {
        if (isNumberCorrectButNotInPlace(character, index)) {
            return CORRECT_NUMBER_NOT_IN_PLACE_POINT;
        }
        return INCORRECT_POINT;
    }

    private String guess(String guessString) {
        if (!isGuessStringValid(guessString)) {
            return WRONG_INPUT_MESSAGE;
        }

        guessTrialCount++;
        int correctNumberAndNotInPlace = guessString.chars().map(character -> getPointForNumberCorrectAndNotInPlace((char) character, guessString.indexOf(character))).sum();
        int correctNumberAndInPlace = guessString.chars().map(character -> getPointForNumberCorrectAndInPlace((char) character, guessString.indexOf(character))).sum();

        return String.format(RESULT_FORMAT_STRING, correctNumberAndInPlace, correctNumberAndNotInPlace);
    }

    public boolean isWin() {
        return result.equals(WINNING_RESULT_STRING);
    }

    public boolean isGameOver() {
        return guessTrialCount == MAX_GUESS_TRIAL_COUNT;
    }

    public void startGame() throws IOException {
        System.out.println(WELCOME_MESSAGE);

        while (!isWin() && !isGameOver()) {
            System.out.print(String.format(INPUT_PROMPT, MAX_GUESS_TRIAL_COUNT - guessTrialCount));
            result = guess(consoleInputReader.getInput());
            System.out.println(result);
        }

        if (isWin()) {
            System.out.println(WINNING_MESSAGE);
        } else if (isGameOver()) {
            System.out.println(String.format(LOSE_MESSAGE, this.getAnswer()));
        }
    }
}
