package com.github.zipcodewilmington.casino.games.BingoGame;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

public class BingoPlayer implements PlayerInterface {
    private final BingoBoard bingoBoard;
    private final CasinoAccount casinoAccount;
    private String currentCallOutValue;

    public BingoPlayer(CasinoAccount casinoAccount) {
        this.casinoAccount = casinoAccount;
        this.bingoBoard = new BingoBoard();
    }

    public BingoBoard getBingoBoard() {
        return bingoBoard;
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return casinoAccount;
    }

    @Override
    public Boolean play() {
        return bingoBoard.markBoard(currentCallOutValue);
    }

    public void setCurrentCallOut(String currentCallOutValue) {
        this.currentCallOutValue = currentCallOutValue;
    }
}
