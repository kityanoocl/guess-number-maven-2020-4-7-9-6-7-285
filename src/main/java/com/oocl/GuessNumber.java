package com.oocl;

import java.io.IOException;
import java.util.*;

public class GuessNumber implements Game {
    final int MAX_GUESS_TRIAL_COUNT = 6;
    final int ANSWER_LENGTH = 4;
    final String DEFAULT_RESULT_STRING = "0A0B";
    final String WINNING_RESULT_STRING = "4A0B";
    final String WINNING_MESSAGE = "Congratulation! You win!";
    final String LOSE_MESSAGE = "Game Over! The answer is %s";
    final String WRONG_INPUT_MESSAGE = "Wrong Input, Input again";
    final String RESULT_FORMAT_STRING = "%dA%dB";
    final String INPUT_PROMPT = "You got %d chance(s) to guess: ";
    final String WELCOME_MESSAGE = "Welcome to Guess Number!\nGame Start!\n";
    final ConsoleInputReader consoleInputReader = new ConsoleInputReader();
    private int guessTrialCount = 0;
    private HashMap<Character, Integer> answer;
    private String result = DEFAULT_RESULT_STRING;

    public GuessNumber(AnswerGenerator answerGenerator) {
        answer = answerGenerator.generate();
    }

    public String getAnswer() {
        String answerString = "";
        for (Character character : answer.keySet()) {
            answerString += character;
        }
        return answerString;
    }

    public boolean isInputContainsNonIntegerOrDuplicate(String input) {
        for (Character character : input.toCharArray()) {
            if (!isDigit(character) || input.lastIndexOf(character) != input.indexOf(character)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDigit(Character character) {
        return Character.isDigit(character);
    }

    public boolean isInputValid(String input) {
        boolean isMatchLength = input.length() == ANSWER_LENGTH;
        return isMatchLength && !isInputContainsNonIntegerOrDuplicate(input);
    }

    public boolean isNumberCorrectAndInPlace(Character character, int index) {
        return answer.containsKey(character) && answer.get(character) == index;
    }

    public boolean isNumberCorrectButNotInPlace(Character character, int index) {
        return answer.containsKey(character) && answer.get(character) != index;
    }

    public String guess(String input) {
        if (!isInputValid(input)) {
            return WRONG_INPUT_MESSAGE;
        }

        int correctNumberAndInPlace = 0;
        int correctNumber = 0;
        guessTrialCount++;
        for (Character character : input.toCharArray()) {
            correctNumber += (isNumberCorrectButNotInPlace(character, input.indexOf(character))) ? 1 : 0;
            correctNumberAndInPlace += (isNumberCorrectAndInPlace(character, input.indexOf(character))) ? 1 : 0;
        }
        return String.format(RESULT_FORMAT_STRING, correctNumberAndInPlace, correctNumber);
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
