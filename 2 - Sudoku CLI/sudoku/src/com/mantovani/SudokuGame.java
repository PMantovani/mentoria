package com.mantovani;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuGame {

    public final int BOARD_SIZE = 9;
    private List<List<Cell>> cells;

    public List<List<Cell>> getCells() {
        return this.cells;
    }

    public void fillBoard(String input) throws WrongInputException {
        if (input == null || input.length() != BOARD_SIZE * BOARD_SIZE) {
            throw new WrongInputException("Input does not contain the correct length");
        }

        this.cells = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            this.cells.add(new ArrayList<>());

            for (int j = 0; j < BOARD_SIZE; j++) {
                List<Cell> row = this.cells.get(i);

                char inputChar = input.charAt((BOARD_SIZE * i) + j);
                try {
                    Integer value = (inputChar == 'x' || inputChar == 'X') ? null : Integer.parseInt(String.valueOf(inputChar));

                    Cell cell = new Cell(value, i, j);
                    row.add(cell);
                } catch (NumberFormatException e) {
                    throw new WrongInputException("Unparsable character in input: " + e.getMessage());
                }
            }
        }

        // After filling in the board, we need to double check for duplicate entries.
        if (this.containsDuplicatedValues()) {
            throw new WrongInputException("Input contains duplicated values");
        }
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(this.cells.get(i).get(j).getValue());
                if (j != 0 && j != 8 && (j+1) % 3 == 0) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i != 0 && i != 8 && (i+1) % 3 == 0) {
                System.out.println("---+---+---");
            }
        }
    }

    private boolean containsDuplicatedValues() {

        if (this.checkRowDuplicates() || this.checkColumnDuplicated()) {
            return true;
        }

        // Check block
        int[] startBlockPositions = {0, 3, 6};
        for (int startRowPosition : startBlockPositions) {
            for (int startColumnPosition : startBlockPositions) {
                boolean hasDuplicates = this.checkBlockDuplicates(startRowPosition, startColumnPosition);
                if (hasDuplicates) {
                    return true;
                }
            }
        }

        return false;

    }

    private boolean checkRowDuplicates() {
        Set<Integer> filledValues = new HashSet<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            filledValues.clear();
            for (int j = 0; j < BOARD_SIZE; j++) {
                Integer value = this.cells.get(i).get(j).getValue();
                if (value != null) {
                    if (filledValues.contains(value)) {
                        return true;
                    } else {
                        filledValues.add(value);
                    }
                }
            }
        }
        return false;
    }

    private boolean checkColumnDuplicated() {
        Set<Integer> filledValues = new HashSet<>();

        for (int j = 0; j < BOARD_SIZE; j++) {
            filledValues.clear();
            for (int i = 0; i < BOARD_SIZE; i++) {
                Integer value = this.cells.get(i).get(j).getValue();
                if (value != null) {
                    if (filledValues.contains(value)) {
                        return true;
                    } else {
                        filledValues.add(value);
                    }
                }
            }
        }
        return false;
    }

    private boolean checkBlockDuplicates(int startRow, int startColumn) {
        Set<Integer> filledValues = new HashSet<>();

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startColumn; j < startColumn + 3; j++) {
                Integer value = this.cells.get(i).get(j).getValue();
                if (value != null) {
                    if (filledValues.contains(value)) {
                        return true;
                    } else {
                        filledValues.add(value);
                    }
                }
            }
        }
        return false;
    }

}
