package com.oocl;

import java.util.*;

public class GuessNumber {
    private HashMap<Character, Integer> answer;
    final int maxNumber = 9999;
    final int maxGuessTrialCount = 6;
    final int answerLength = 4;
    private int guessTrialCount;
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
        while (!isInputValid(answerString))
        {
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
        if (answer.get(character) == index)
        {
            return 1;
        }
        return 0;
    }

    public int isNumberCorrectButNotInPlace(Character character, int index) {
        if (answer.get(character) != index)
        {
            return 1;
        }
        return 0;
    }

    public String guess(String input) {
        if (isInputValid(input)) {
            if (guessTrialCount == maxGuessTrialCount) {
                return "Game Over";
            }
            int correctNumberAndInPlace = 0;
            int correctNumber = 0;
            for (int index = 0; index < answerLength; index++) {
                if (answer.containsKey(input.charAt(index))) {
                    correctNumber += isNumberCorrectButNotInPlace(input.charAt(index), index);
                    correctNumberAndInPlace += isNumberCorrectAndInPlace(input.charAt(index), index);
                }
            }
            if (correctNumberAndInPlace != answerLength) {
                guessTrialCount++;
            }
            return String.format("%dA%dB", correctNumberAndInPlace, correctNumber);
        } else {
            return "Wrong Input, Input again";
        }
    }
}
