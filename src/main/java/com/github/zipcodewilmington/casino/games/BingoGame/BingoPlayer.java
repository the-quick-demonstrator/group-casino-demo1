package com.github.zipcodewilmington.casino.games.BingoGame;

public class BingoPlayer {
    private BingoBoard bingoBoard;

    public BingoPlayer() {
        this.bingoBoard = new BingoBoardGenerator().createRandomBingoBoard();
    }
}
