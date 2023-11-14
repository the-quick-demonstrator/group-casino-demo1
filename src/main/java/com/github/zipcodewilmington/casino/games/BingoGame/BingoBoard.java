package com.github.zipcodewilmington.casino.games.BingoGame;

import com.github.zipcodewilmington.utils.ListTransposer;
import jdk.internal.icu.text.UnicodeSet;

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
            for(int i=0; i<5; i++) {
                final Integer randomValue = ThreadLocalRandom.current().nextInt(1, 75);
                final String bingoValue = letter + randomValue;
                bingoValues.put(bingoValue, false);
            }
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
        final List<String> list = new ArrayList<>();
        for(String value : bingoValues.keySet()) {
            if(value.contains(letter.toString())) {
                list.add(value);
            }
        }
        return list;
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