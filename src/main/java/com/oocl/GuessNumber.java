package com.oocl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GuessNumber {
    private HashMap<Character, Integer> answer;
    final int maxNumber = 9999;
    final int maxGuessTrialCount = 6;
    final int answerLength = 4;
    private int guessTrialCount;
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public GuessNumber() {
        String answerString = getRandomAnswerString();
        putAnswerInMap(answerString);
        this.guessTrialCount = 0;
    }

    public GuessNumber(String answerString) {
        if (!isInputValid(answerString)) {
            answerString = getRandomAnswerString();
        }
        putAnswerInMap(answerString);
        this.guessTrialCount = 0;
    }

    private String getRandomAnswerString() {
        String answerString = "0000";
        while (!isInputValid(answerString)) {
            Random random = new Random();
            answerString = String.format("%04d", random.nextInt(maxNumber));
        }
        return answerString;
    }

    private void putAnswerInMap(String answerString) {
        answer = new HashMap<Character, Integer>();
        for (int index = 0; index < answerLength; index++) {
            answer.put(answerString.charAt(index), index);
        }
    }

    public String getAnswer() {
        String answerString = "";
        for (Character character : answer.keySet()) {
            answerString += character;
        }
        return answerString;
    }

    public boolean isInputContainsNonIntegerOrDuplicate(String input) {
        List<Character> characterList = new ArrayList<Character>();
        for (int index = 0; index < answerLength; index++) {
            if (isNonDigit(input.charAt(index)) || characterList.contains(input.charAt(index))) {
                return true;
            }
            characterList.add(input.charAt(index));
        }

        return false;
    }

    private boolean isNonDigit(Character character) {
        return character < '0' || character > '9';
    }


    public boolean isInputValid(String input) {
        return input.length() == answerLength && !isInputContainsNonIntegerOrDuplicate(input);
    }

    public int isNumberCorrectAndInPlace(Character character, int index) {
        if (answer.get(character) == index) {
            return 1;
        }
        return 0;
    }

    public int isNumberCorrectButNotInPlace(Character character, int index) {
        if (answer.get(character) != index) {
            return 1;
        }
        return 0;
    }

    public String guess(String input) {
        if (isInputValid(input)) {
            int correctNumberAndInPlace = 0;
            int correctNumber = 0;
            guessTrialCount++;
            for (int index = 0; index < answerLength; index++) {
                if (answer.containsKey(input.charAt(index))) {
                    correctNumber += isNumberCorrectButNotInPlace(input.charAt(index), index);
                    correctNumberAndInPlace += isNumberCorrectAndInPlace(input.charAt(index), index);
                }
            }
            return String.format("%dA%dB", correctNumberAndInPlace, correctNumber);
        } else {
            return "Wrong Input, Input again";
        }
    }

    public boolean isWin(String result) {
        return result.equals("4A0B");
    }

    public boolean isGameOver() {
        return guessTrialCount == maxGuessTrialCount;
    }

    public void startGame() throws IOException {
        System.out.println("Welcome to Guess Number!");
        System.out.println("Game Start!");
        String result = "0A0B";
        while (!isWin(result) && !isGameOver()) {
            String prompt = String.format("You got %d chance(s) to guess: ", maxGuessTrialCount - guessTrialCount);
            System.out.print(prompt);
            result = guess(reader.readLine());
            System.out.println(result);
        }

        if (isWin(result)) {
            System.out.println("Congratulation! You win!");
        } else if (isGameOver()) {
            System.out.println("Game Over!");
        }
    }
}
