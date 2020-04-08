package com.oocl;

import java.util.HashMap;
import java.util.Random;

public class GuessNumber {
    private HashMap<Character, Integer> answer;
    final int maxNumber = 9999;
    final int answerLength = 4;

    public GuessNumber() {
        Random random = new Random();
        String answerString = String.format("%04d", maxNumber);
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

    public String guess(String input) {
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
