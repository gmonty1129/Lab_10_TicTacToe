import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static int moveCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            resetBoard();
            playAgain = false;
            boolean gameOn = true;

            while (gameOn) {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (row and column: 1-3): ");
                int row, col;

                while (true) {
                    if (scanner.hasNextInt()) {
                        row = scanner.nextInt() - 1;
                        if (scanner.hasNextInt()) {
                            col = scanner.nextInt() - 1;
                            if (isValidMove(row, col)) {
                                break;
                            }
                        }
                    } else {
                        scanner.next();
                    }
                    System.out.println("Invalid move. Try again.");
                }

                board[row][col] = currentPlayer;
                moveCount++;

                if (moveCount >= 5 && checkWin(row, col)) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOn = false;
                } else if (moveCount == 9) {
                    printBoard();
                    System.out.println("It's a tie!");
                    gameOn = false;
                } else {
                    togglePlayer();
                }
            }

            System.out.println("Do you want to play again? (yes/no)");
            scanner.nextLine();
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                playAgain = true;
            }
        } while (playAgain);

        scanner.close();
    }

    private static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
        moveCount = 0;
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static boolean checkWin(int row, int col) {
        return (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) ||
                (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) ||
                (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private static void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}