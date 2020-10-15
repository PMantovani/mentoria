package com.mantovani;

public class Main {

    public static int main(String[] args) {
        SudokuGame game = new SudokuGame();
        SudokuSolver solver = new SudokuSolver();

        try {
            game.fillBoard(args[0]);
            solver.solve(game);
            game.printBoard();
            return 0;
        } catch (WrongInputException | SudokuSolverException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }
}
