package com.oocl;

import java.util.*;

public class GuessNumber {
    private HashMap<Character, Integer> answer;
    final int maxNumber = 9999;
    final int answerLength = 4;

    public GuessNumber() {
        String answerString = "0000";

        while (!isInputValid(answerString))
        {
            Random random = new Random();
            answerString = String.format("%04d", random.nextInt(maxNumber));
        }
        putAnswerInMap(answerString);
    }

    public GuessNumber(String answer) {
        putAnswerInMap(answer);
    }

    private void putAnswerInMap(String answerString) {
        answer = new HashMap<Character, Integer>();
        for (int index = 0; index < answerLength; index++) {
            answer.put(new Character(answerString.charAt(index)), new Integer(index));
        }
    }

    public String getAnswer() {
        String answerString = "";
        for (Character character : answer.keySet()) {
            answerString += character;
        }
        return answerString;
    }

    public boolean isInputContainsNonInteger(String input) {
        for (int index = 0; index < answerLength; index++) {
            if (input.charAt(index) < '0' || input.charAt(index) > '9') {
                return true;
            }
        }

        return false;
    }

    public boolean isInputHasDuplicate(String input) {
        List<Character> characterList = new ArrayList<Character>();
        for (int index = 0; index < answerLength; index++) {
            if (characterList.contains(input.charAt(index))) {
                return true;
            }
            characterList.add(input.charAt(index));
        }
        return false;
    }

    public boolean isInputValid(String input) {
        if (input.length() != answerLength || isInputContainsNonInteger(input) || isInputHasDuplicate(input)) {
            return false;
        }
        return true;
    }

    public String guess(String input) {
        if (!isInputValid(input)) {
            return String.format("Wrong Input, Input again");
        }
        int correctNumberAndPlace = 0;
        int correctNumber = 0;
        for (int index = 0; index < answerLength; index++) {
            Character character = new Character(input.charAt(index));
            if (answer.containsKey(character)) {
                if (answer.get(character).intValue() == index) {
                    correctNumberAndPlace++;
                } else {
                    correctNumber++;
                }
            }
        }
        return String.format("%dA%dB", correctNumberAndPlace, correctNumber);
    }
}
