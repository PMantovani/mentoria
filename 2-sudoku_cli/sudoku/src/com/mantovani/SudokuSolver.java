package com.mantovani;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {

    public SudokuGame solve(SudokuGame game) throws SudokuSolverException {
        int nextRow = 0;
        int nextColumn = 0;
        boolean moveForward = true;

        while (nextRow < game.BOARD_SIZE) {
            moveForward = this.guessCell(game, nextRow, nextColumn, moveForward);
            if (moveForward) {
                nextRow = (nextColumn + 1) == game.BOARD_SIZE ? (nextRow + 1) : nextRow;
                nextColumn = (nextColumn + 1) == game.BOARD_SIZE ? 0 : (nextColumn + 1);
            } else {
                nextRow = nextColumn == 0 ? (nextRow - 1) : nextRow;
                nextColumn = nextColumn == 0 ? (game.BOARD_SIZE - 1) : (nextColumn - 1);
            }
        }
        return game;
    }

    private boolean guessCell(SudokuGame game, int row, int column, boolean movingForward) throws SudokuSolverException {
        // If row is negative, it means that backtracking went too far and no solution was found.
        if (row == -1) {
            throw new SudokuSolverException();
        }

        Cell cell = game.getCells().get(row).get(column);

        if (cell.isChangeable()) {
            Integer nextGuess = this.getNextAvailableNumber(game, row, column);
            cell.setValue(nextGuess);
        }


        return !this.shouldBacktrack(cell, movingForward);
    }

    private boolean shouldBacktrack(Cell cell, boolean wasMovingForward) {
        // We want to backtrack if we haven't found a feasible guess for the cell. Or if we were already backtracking
        // and this was not a changeable cell.
        return cell.getValue() == null || (!wasMovingForward && !cell.isChangeable());
    }

    private Integer getNextAvailableNumber(SudokuGame game, int row, int column) {
        Set<Integer> allNumbers = new HashSet<>();
        Set<Integer> filledNumbers = new HashSet<>();

        for (int i = 0; i < game.BOARD_SIZE; i++) {
            allNumbers.add(i + 1);
        }

        this.fillSetWithRowValues(filledNumbers, game, row, column);
        this.fillSetWithColumnValues(filledNumbers, game, row, column);
        this.fillSetWithBlockValues(filledNumbers, game, row, column);

        Cell currentCell = game.getCells().get(row).get(column);

        allNumbers.removeAll(filledNumbers);
        return allNumbers
                .stream()
                .sorted()
                .filter(i -> currentCell.getValue() == null || i > currentCell.getValue())
                .findFirst()
                .orElse(null);
    }

    private void fillSetWithRowValues(Set<Integer> filledNumbers, SudokuGame game, int row, int column) {
        game.getCells().get(row).forEach(cellInRow -> {
            if (cellInRow.getColumn() != column) {
                filledNumbers.add(cellInRow.getValue());
            }
        });
    }

    private void fillSetWithColumnValues(Set<Integer> filledNumbers, SudokuGame game, int row, int column) {
        game.getCells().forEach(rowToCheck -> {
            Cell cellInColumn = rowToCheck.get(column);
            if (cellInColumn.getRow() != row) {
                filledNumbers.add(cellInColumn.getValue());
            }
        });
    }

    private void fillSetWithBlockValues(Set<Integer> filledNumbers, SudokuGame game, int row, int column) {
        int minRowToCheck;
        int maxRowToCheck;
        int minColToCheck;
        int maxColToCheck;

        if (row <= 2) {
            minRowToCheck = 0;
            maxRowToCheck = 2;
        } else if (row <= 5) {
            minRowToCheck = 3;
            maxRowToCheck = 5;
        } else {
            minRowToCheck = 6;
            maxRowToCheck = 8;
        }

        if (column <= 2) {
            minColToCheck = 0;
            maxColToCheck = 2;
        } else if (column <= 5) {
            minColToCheck = 3;
            maxColToCheck = 5;
        } else {
            minColToCheck = 6;
            maxColToCheck = 8;
        }

        for (int i = minRowToCheck; i <= maxRowToCheck; i++) {
            for (int j = minColToCheck; j <= maxColToCheck; j++) {
                if (i != row || j != column) {
                    Integer value = game.getCells().get(i).get(j).getValue();
                    if (value != null) {
                        filledNumbers.add(value);
                    }
                }
            }
        }
    }
}
