package com.pokitermi;

import Game.MapTest;

public class Main {

    public static void main(String[] args)  {
        try{
            MapTest.runMapTest();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}