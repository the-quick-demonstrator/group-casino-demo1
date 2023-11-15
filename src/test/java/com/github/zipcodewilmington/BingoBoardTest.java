package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.games.BingoGame.BingoBoard;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BingoBoardTest {
    @Test
    public void testIsWinnerInColumn() {
        // given
        final Character letter = 'B';
        final BingoBoard bingoBoard = new BingoBoard();
        Assert.assertFalse(bingoBoard.isWinner());

        // when
        final List<String> bingoValuesToBeMarked = bingoBoard.getColumn(letter);
        bingoValuesToBeMarked.forEach(bingoBoard::markBoard);

        // then
        Assert.assertTrue(bingoBoard.isWinner());
    }

    @Test
    public void testGetColumn() {
        // given
        final int expectedLength = 5;
        final BingoBoard bingoBoard = new BingoBoard();

        // when
        for (Character bingoLetter : "BINGO".toCharArray()) {
            final List<String> currentColumn = bingoBoard.getColumn(bingoLetter);

            // then
            Assert.assertEquals(expectedLength, currentColumn.size());
        }
    }

    @Test
    public void testGetMatrix() {
        // given
        final int expectedLength = 5;
        final BingoBoard bingoBoard = new BingoBoard();

        // when
        final List<List<String>> matrix = bingoBoard.getMatrix();
        for(List<String> row : matrix){
            // then
            Assert.assertEquals(expectedLength, row.size());
        }
    }

    @Test
    public void testGetRow() {
        // given
        BingoBoard bingoBoard = new BingoBoard();

        // when
        for(int i=0; i<5; i++) {
            List<String> row = bingoBoard.getRow(i);
            for (Character bingoLetter : "BINGO".toCharArray()) {
                // then
                Assert.assertTrue(row.toString().contains(bingoLetter.toString()));
            }
        }
    }
}
