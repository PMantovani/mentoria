package com.mantovani;

public class Main {

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        SudokuSolver solver = new SudokuSolver();

        try {
            game.fillBoard(args[0]);
            solver.solve(game);
            game.printBoard();
        } catch (WrongInputException | SudokuSolverException e) {
            System.err.println(e.getMessage());
        }
    }
}
