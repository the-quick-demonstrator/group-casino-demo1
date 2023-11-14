package com.github.zipcodewilmington.utils;

import java.util.ArrayList;
import java.util.List;

public class ListTransposer<T> {
    private final List<List<T>> table;

    public ListTransposer(List<List<T>> table) {
        this.table = table;
    }

    public final List<List<T>> transpose() {
        List<List<T>> ret = new ArrayList<List<T>>();
        final int N = table.get(0).size();
        for (int i = 0; i < N; i++) {
            List<T> col = new ArrayList<T>();
            for (List<T> row : table) {
                col.add(row.get(i));
            }
            ret.add(col);
        }
        return ret;
    }
}
