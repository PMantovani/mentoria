package com.mantovani;

public class SudokuSolverException extends Exception {

    private String message = "Unsolvable game";

    public SudokuSolverException() {}

    public SudokuSolverException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
