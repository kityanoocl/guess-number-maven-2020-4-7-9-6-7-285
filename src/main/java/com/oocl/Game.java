package com.oocl;

import java.io.IOException;

public interface Game {
    boolean isWin();
    boolean isGameOver();
    void startGame() throws IOException;
}
