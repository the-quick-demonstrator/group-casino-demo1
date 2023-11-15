package com.github.zipcodewilmington.casino.games.BingoGame;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

// game has players have bingoboard generators creates bingo boards
public class BingoGame implements GameInterface {
    private final List<PlayerInterface> players;

    public BingoGame() {
        this.players = new ArrayList<>();
    }

    @Override
    public void run() {
        final String[] bingo = "BINGO".split("");
        while (!hasWinner()) {
            final IOConsole ioConsole = new IOConsole(AnsiColor.GREEN);
            final String randomLetter = bingo[new Random().nextInt(bingo.length)];
            final Integer randomValue = ThreadLocalRandom.current().nextInt(1, 75);
            final String currentCallOutValue = randomLetter + randomValue;
            new IOConsole(AnsiColor.YELLOW).println("The current call out value is [ %s ]", currentCallOutValue);
            for (final PlayerInterface playerInterface : players) {
                final BingoPlayer player = (BingoPlayer) playerInterface;
                player.setCurrentCallOut(currentCallOutValue);
                final boolean hasMarkedTheirBoard = player.play();
                if (hasMarkedTheirBoard) {
                    new IOConsole(AnsiColor.CYAN).println("The player has marked [ %s ] off their board.", currentCallOutValue);
                }
                ioConsole.println(player.getBingoBoard().toString());
            }
        }
    }

    private boolean hasWinner() {
        return players.stream().anyMatch(player -> ((BingoPlayer) player).getBingoBoard().isWinner());
    }

    @Override
    public void add(PlayerInterface player) {
        players.add(player);
    }

    @Override
    public void remove(PlayerInterface player) {
        players.remove(player);
    }
}
