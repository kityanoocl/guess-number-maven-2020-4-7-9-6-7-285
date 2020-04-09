package com.oocl;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}