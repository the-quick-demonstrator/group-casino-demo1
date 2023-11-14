package com.github.zipcodewilmington.casino.games.BingoGame;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

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
        final String randomLetter = Stream.of("BINGO".split("")).findAny().get();
        final Integer randomValue = ThreadLocalRandom.current().nextInt(1, 75);
        final String currentCallOutValue = randomLetter + randomValue;
        for (final PlayerInterface playerInterface : players) {
            final BingoPlayer player = (BingoPlayer) playerInterface;
            player.setCurrentCallOut(currentCallOutValue);
            player.play();
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
