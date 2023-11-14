package com.github.zipcodewilmington.casino.games.BingoGame;

import com.github.zipcodewilmington.utils.ListTransposer;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BingoBoard {
    private final Map<String, Boolean> bingoValues;

    public BingoBoard() {
        this.bingoValues = new LinkedHashMap<>();
        for (String letter : "BINGO".split("")) {
            final Integer randomValue = ThreadLocalRandom.current().nextInt(1, 75);
            final String bingoValue = letter + randomValue;
            bingoValues.put(bingoValue, false);
        }
    }

    public boolean isWinner() {
        final String[] bingoLetters = "BINGO".split("");
        for (int i = 0; i < bingoLetters.length; i++) {
            final String letter = bingoLetters[i];
            final Character currentBingoLetter = letter.charAt(0);
            final List<String> currentBingoColumn = getColumn(currentBingoLetter);
            final List<String> currentBingoRow = getRow(i);
            final Set<Boolean> rowValues = getValuesOfKeys(currentBingoRow);
            final Set<Boolean> columnValues = getValuesOfKeys(currentBingoColumn);
            final int numberOfUniqueRowValues = rowValues.size();
            final int numberOfUniqueColumnValues = columnValues.size();
            final boolean isWinningAtLeastOneColumn = numberOfUniqueColumnValues == 0;
            final boolean isWinningAtLeastOneRow = numberOfUniqueRowValues == 0;
            final boolean isWinning = isWinningAtLeastOneColumn || isWinningAtLeastOneRow;
            if(isWinning) {
                return true;
            }
        }
        return false;
    }


    public Set<Boolean> getValuesOfKeys(List<String> keys) {
        final Set<Boolean> values = new HashSet<>();
        for (String key : keys) {
            final boolean value = bingoValues.get(key);
            values.add(value);
        }
        return values;
    }

    public List<List<String>> getMatrix() {
        return Stream
                .of("BINGO".split(""))
                .map(letter -> getColumn(letter.charAt(0)))
                .collect(Collectors.toList());
    }

    public List<List<String>> invertMatrix() {
        return new ListTransposer(getMatrix()).transpose();
    }

    public List<String> getColumn(Character letter) {
        return bingoValues
                .keySet()
                .stream()
                .filter(value -> letter == value.charAt(0))
                .collect(Collectors.toList());
    }

    public List<String> getRow(int index) {
        final List<String> row = new ArrayList<>();
        for (List<String> column : getMatrix()) {
            row.add(column.get(index));
        }
        return row;
    }

    public boolean markBoard(String bingoValue) {
        boolean hasValue = bingoValues.containsKey(bingoValue);
        if (hasValue) {
            bingoValues.put(bingoValue, true);
            return true;
        }
        return false;
    }

}