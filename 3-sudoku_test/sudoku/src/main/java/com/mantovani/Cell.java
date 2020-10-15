package com.mantovani;

public class Cell {

    private Integer value;
    private final int row;
    private final int column;
    private boolean changeable;

    public Cell(Integer value, int row, int column) {
        if (value == null) {
            this.changeable = true;
        } else {
            this.value = value;
            this.changeable = false;
        }
        this.row = row;
        this.column = column;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean isChangeable() {
        return this.changeable;
    }
}
