package com.github.zipcodewilmington.casino.games.BingoGame;

import java.util.concurrent.ThreadLocalRandom;

public class BingoBoardGenerator {
    private int minValue = 1;
    private int maxValue = 75;


    public BingoBoard createRandomBingoBoard() {
        final Integer[][] rows = new Integer[5][5];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = createRandomBingoRow();
        }
        return new BingoBoard(rows);
    }

    public Integer[] createRandomBingoRow() {
        final Integer[] row = new Integer[5];
        for (int i = 0; i < row.length; i++) {
            final int randomValue = ThreadLocalRandom.current().nextInt(minValue, maxValue);
            row[i] = randomValue;
        }
        return row;
    }
}
