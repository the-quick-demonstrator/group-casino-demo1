package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.BingoGame.BingoBoard;
import org.junit.Test;

import java.util.List;

public class GetColumnDemo {
    @Test
    public void testGetColumn() {
        BingoBoard bb = new BingoBoard();
        List<String> values = bb.getColumn('B');
    }
}
