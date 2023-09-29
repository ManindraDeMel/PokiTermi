package com.pokitermi;

import java.io.File;

public class Main {
    /*
     * @author Yiming Lu
     */
    public static void main(String[] args) {
        try {
            Game.GameLogic.runGame(); // Start the game loop
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
