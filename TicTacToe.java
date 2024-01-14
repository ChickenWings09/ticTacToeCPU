import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    private static char player;
    private static char board[][] = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private static char computer;

    public static void main(String[] args) {
        player();
        displayBoard();
        while (!gameIsOver()) {
            makeMove();
            displayBoard();
            if (gameIsOver()) {
                break;
            }
            computerMove();
            displayBoard();
        }
    }

    private static void player() {
        Scanner playerChoice = new Scanner(System.in);
        System.out.println("Would you like to play X or O?");
        player = playerChoice.next().charAt(0);
        if (player == 'X' || player == 'O') {
            computer = (player == 'X') ? 'O' : 'X';
        } else {
            System.out.println("Invalid choice. Please choose X or O.");
            player();
        }
    }

    private static void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("-----");
            }
        }
    }

    private static void makeMove() {
        Scanner playerMove = new Scanner(System.in);
        int row, column;
        do {
            System.out.println("Enter the row you would like to place your piece in (0, 1, or 2): ");
            row = playerMove.nextInt();
            System.out.println("Enter the column you would like to place your piece in (0, 1, or 2): ");
            column = playerMove.nextInt();
        } while (!isValidMove(row, column));
        board[row][column] = player;
    }

    private static void computerMove() {
        int[] bestMove = chooseBestMove();
        board[bestMove[0]][bestMove[1]] = computer;
    }

    private static int[] chooseBestMove() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    board[row][col] = computer;
                    if (computerWin()) {
                        board[row][col] = ' ';
                        return new int[]{row, col};
                    }
                    board[row][col] = ' ';
                }
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    board[row][col] = player;
                    if (playerWin()) {
                        board[row][col] = ' ';
                        return new int[]{row, col};
                    }
                    board[row][col] = ' ';
                }
            }
        }

        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));

        return new int[]{row, col};
    }

    private static boolean gameIsOver() {
        return isWin() || isBoardFull();
    }

    private static boolean isWin() {
        return checkTripleRows() || checkTripleColumns() || checkTripleDiagonals();
    }

    private static boolean checkTripleRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean checkTripleColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean checkTripleDiagonals() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ') ||
            (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' ')) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidMove(int row, int column) {
        if (row < 0 || row > 2 || column < 0 || column > 2 || board[row][column] != ' ') {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        return true;
    }

    private static boolean playerWin() {
        return playerTripleRows() || playerTripleColumns() || playerTripleDiagonals();
    }

    private static boolean playerTripleRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean playerTripleColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean playerTripleDiagonals() {
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    private static boolean computerWin() {
        return computerTripleRows() || computerTripleColumns() || computerTripleDiagonals();
    }

    private static boolean computerTripleRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == computer && board[i][1] == computer && board[i][2] == computer) {
                return true;
            }
        }
        return false;
    }

    private static boolean computerTripleColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == computer && board[1][i] == computer && board[2][i] == computer) {
                return true;
            }
        }
        return false;
    }

    private static boolean computerTripleDiagonals() {
        if ((board[0][0] == computer && board[1][1] == computer && board[2][2] == computer) ||
            (board[0][2] == computer && board[1][1] == computer && board[2][0] == computer)) {
            return true;
        }
        return false;
    }
}
