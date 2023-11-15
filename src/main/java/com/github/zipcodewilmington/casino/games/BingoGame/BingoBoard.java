package com.github.zipcodewilmington.casino.games.BingoGame;

import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.ListTransposer;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// multidimensional structure - String[][] / List<List<String>>
// index    0    1    2    3    4
//   0      [B14, I13, N22, G12, O15],
//   1      [B14, I13, N22, G12, O15],
//   2      [B14, I13, N22, G12, O15],


// list structure - String[] / List<String>
// index    0    1    2    3    4
// value   [B14, I13, N22, G12, O15]

// map structure - Map<String, Boolean>
//        [        key,value      index
//                (B14,true),   - 0
//                (I13,true),   - 1
//                (N22,false),  - 2
//                (G12,true),   - 3
//                (O15,true)    - 5
//        ]
public class BingoBoard {
    private final Map<String, Boolean> bingoValues;

    public BingoBoard() {
        this.bingoValues = new HashMap<>();
        final String[] bingoLetters = "BINGO".split("");
        for (String letter : bingoLetters) {
            for (int i = 0; i < 5; i++) {
                String bingoValue;
                do {
                    final Integer randomValue = ThreadLocalRandom.current().nextInt(10, 75);
                    bingoValue = letter + randomValue;
                } while (bingoValues.keySet().contains(bingoValue));
                bingoValues.put(bingoValue, false);
            }
        }
    }

    public boolean isWinner() {
        final String[] bingoLetters = "BINGO".split("");
        for (int currentBingoRowIndex = 0; currentBingoRowIndex < bingoLetters.length; currentBingoRowIndex++) {
            final String letter = bingoLetters[currentBingoRowIndex];

            // column evaluation
            final Character currentBingoLetter = letter.charAt(0);
            final List<String> currentBingoColumn = getColumn(currentBingoLetter);
            final List<Boolean> columnValues = getValuesOfKeys(currentBingoColumn);
            final int numberOfUniqueColumnValues = new HashSet<>(columnValues).size();
            final boolean hasCellsMarkedColumn = columnValues.get(0);
            final boolean isHomogenousColumn = numberOfUniqueColumnValues == 1;
            final boolean isWinningAtLeastOneColumn = isHomogenousColumn && hasCellsMarkedColumn;

            // row evaluation
            final List<String> currentBingoRow = getRow(currentBingoRowIndex);
            final List<Boolean> rowValues = getValuesOfKeys(currentBingoRow);
            final int numberOfUniqueRowValues = new HashSet<>(rowValues).size();
            final boolean hasCellsMarkedRow = rowValues.get(0);
            final boolean isHomogenousRow = numberOfUniqueRowValues == 1;
            final boolean isWinningAtLeastOneRow = isHomogenousRow && hasCellsMarkedRow;

            // conclusion
            final boolean isWinning = isWinningAtLeastOneColumn || isWinningAtLeastOneRow;
            if (isWinning) {
                return true;
            }
        }
        return false;
    }


    public List<Boolean> getValuesOfKeys(List<String> keys) {
        final List<Boolean> values = new ArrayList<>();
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

    public List<String> getColumn(Character letter) {
        final List<String> list = new ArrayList<>();
        for (String value : bingoValues.keySet()) {
            if (value.contains(letter.toString().toUpperCase())) {
                list.add(value);
            }
        }
        return list;
    }

    public List<String> getRow(int index) {
        final List<String> row = new ArrayList<>();
        final List<List<String>> matrix = getMatrix();
        for (final List<String> column : matrix) {
            final String bingoValue = column.get(index);
            row.add(bingoValue);
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

    @Override
    public String toString() {
        final List<List<String>> columns = getMatrix();
        final List<List<String>> rows = new ListTransposer<>(columns).transpose();
        final StringJoiner rowString = new StringJoiner("||");
        for (final List<String> row : rows) {
            for (String key : row) {
                final boolean value = bingoValues.get(key);
                String displayValue = key + "," + value;
                displayValue = value ? AnsiColor.RED.getColor() + displayValue : displayValue;
                displayValue += AnsiColor.GREEN.getColor();
                rowString.add(displayValue);
            }
            rowString.add("\n");
        }
        return "||" + rowString;
    }
}