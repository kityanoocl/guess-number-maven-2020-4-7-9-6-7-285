package com.oocl;

import java.io.*;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws IOException {
        Game guessNumber = new GuessNumber(new RandomAnswerGenerator());

        guessNumber.startGame();
    }
}
