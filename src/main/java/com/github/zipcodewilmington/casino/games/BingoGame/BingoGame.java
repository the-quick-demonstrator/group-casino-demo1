package com.github.zipcodewilmington.casino.games.BingoGame;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.List;
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
        final IOConsole ioConsole = new IOConsole(AnsiColor.GREEN);
        final String randomLetter = Stream.of("BINGO".split("")).findAny().get();
        final Integer randomValue = ThreadLocalRandom.current().nextInt(1, 75);
        final String currentCallOutValue = randomLetter + randomValue;
        ioConsole.println("The current call out value is [ %s ]", currentCallOutValue);
        for (final PlayerInterface playerInterface : players) {
            final BingoPlayer player = (BingoPlayer) playerInterface;
            player.setCurrentCallOut(currentCallOutValue);
            final boolean hasMarkedTheirBoard = player.play();
            if(hasMarkedTheirBoard) {
                ioConsole.println("The player has marked [ %s ] off their board.");
            }
            ioConsole.println(player.getBingoBoard().toString());
        }
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
