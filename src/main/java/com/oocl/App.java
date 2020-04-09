package com.oocl;

import java.io.*;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws IOException {
        AnswerGenerator answerGenerator = new RandomAnswerGenerator();
        GuessNumber guessNumber = new GuessNumber(answerGenerator);

        guessNumber.startGame();
    }
}
