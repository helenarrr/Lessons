package Game;

import java.util.*;

public class Game {
    private final static String CELL_STATE_X = "X";
    private final static String CELL_STATE_O = "0";
    private final static String CELL_STATE_EMPTY = " ";

    protected final static int ROW_COUNT = 3;
    protected final static int COL_COUNT = 3;

    private final static String GAME_STATE_X_WON = "Х победили!";
    private final static String GAME_STATE_O_WON = "0 победили!";
    private final static String GAME_STATE_DRAW = "Ничья";
    private final static String GAME_STATE_NOT_FINISHED = "Игра не закончена";

    private final static Scanner scanner = new Scanner(System.in);
    private final static Random random = new Random();


    class Board {
        public static String[][] createBoard() {
            String[][] board = new String[ROW_COUNT][COL_COUNT];

            for (int row = 0; row < ROW_COUNT; row++) {
                for (int col = 0; col < COL_COUNT; col++) {
                    board[row][col] = CELL_STATE_EMPTY;
                }
            }
            return board;
        }

        public static void printBoard(String[][] board) {
            System.out.println("---------");
            for (int row = 0; row < ROW_COUNT; row++) {
                String line = "| ";
                for (int col = 0; col < COL_COUNT; col++) {
                    line += board[row][col] + " ";
                }
                line += "|";

                System.out.println(line);
            }
            System.out.println("---------");
        }
    }

    class Human {
        public static void makePlayerTurn(String[][] board) {
            int[] coordinates = inputCellCoordinates(board);
            board[coordinates[0]][coordinates[1]] = CELL_STATE_X;
        }

        public static int[] inputCellCoordinates(String[][] board) {
            System.out.println("Введите 2 числа (ряд и колонку) от 0 до 2 через пробел (0-2):");

            do {
                String[] input = scanner.nextLine().split(" ");

                int row = Integer.parseInt(input[0]);
                int col = Integer.parseInt(input[1]);

                if ((row < 0) || (row >= ROW_COUNT) || (col < 0) || (col >= COL_COUNT)) {
                    System.out.println("Некорректное значение! Введите 2 числа (ряд и колонку) от 0 до 2 через пробел:");
                } else if (!Objects.equals(board[row][col], CELL_STATE_EMPTY)) {
                    System.out.println("Данная ячейка уже занята");
                } else {
                    return new int[]{row, col};
                }
            } while (true);
        }
    }

    class Bot {
        public static void makeBotTurn(String[][] board) {
            System.out.println("Ход бота");

            int[] coordinates = getRandomEmptyCellCoordinates(board);
            board[coordinates[0]][coordinates[1]] = CELL_STATE_O;
        }

        public static int[] getRandomEmptyCellCoordinates(String[][] board) {
            do {
                int row = random.nextInt(ROW_COUNT);
                int col = random.nextInt(COL_COUNT);

                if (Objects.equals(board[row][col], CELL_STATE_EMPTY)) {
                    return new int[]{row, col};
                }
            } while (true);
        }
    }


    class GameInterface {

        public static void startGameRound() {
            System.out.println("Начало нового раунда:");

            String[][] board = Board.createBoard();
            Board.printBoard(board);
            startGameLoop(board);

        }

        public static void startGameLoop(String[][] board) {
            boolean playerTurn = true;

            do {
                if (playerTurn) {
                    Human.makePlayerTurn(board);
                    Board.printBoard(board);
                } else {
                    Bot.makeBotTurn(board);
                    Board.printBoard(board);
                }
                GameFunction.Recovery.StartRecoverBoard(board);


                playerTurn = !playerTurn;

                System.out.println();

                String gameState = checkGameState(board);
                if (!Objects.equals(gameState, GAME_STATE_NOT_FINISHED)) {
                    System.out.println(gameState);
                    return;
                }
            } while (true);
        }

        public static String checkGameState(String[][] board) {
            ArrayList<Integer> sums = new ArrayList<>();

            // iterate rows
            for (int row = 0; row < ROW_COUNT; row++) {
                int rowSum = 0;
                for (int col = 0; col < COL_COUNT; col++) {
                    rowSum += calculateNumValue(board[row][col]);
                }
                sums.add(rowSum);
            }

            // iterate columns
            for (int col = 0; col < COL_COUNT; col++) {
                int colSum = 0;
                for (int row = 0; row < ROW_COUNT; row++) {
                    colSum += calculateNumValue(board[row][col]);
                }
                sums.add(colSum);
            }

            // diagonal from top-left to bottom-right
            int leftDiagonalSum = 0;
            for (int i = 0; i < ROW_COUNT; i++) {
                leftDiagonalSum += calculateNumValue(board[i][i]);
            }
            sums.add(leftDiagonalSum);

            // diagonal from top-right to bottom-left
            int rightDiagonalSum = 0;
            for (int i = 0; i < ROW_COUNT; i++) {
                rightDiagonalSum += calculateNumValue(board[i][(ROW_COUNT - 1) - i]);
            }
            sums.add(rightDiagonalSum);

            if (sums.contains(3)) {
                return GAME_STATE_X_WON;
            } else if (sums.contains(-3)) {
                return GAME_STATE_O_WON;
            } else if (areAllCellsTaken(board)) {
                return GAME_STATE_DRAW;
            } else {
                return GAME_STATE_NOT_FINISHED;
            }
        }

        // X - 1, O - (-1), empty - 0
        // используется для проверки победителя
        private static int calculateNumValue(String cellState) {
            if (Objects.equals(cellState, CELL_STATE_X)) {
                return 1;
            } else if (Objects.equals(cellState, CELL_STATE_O)) {
                return -1;
            } else {
                return 0;
            }
        }

        public static boolean areAllCellsTaken(String[][] board) {
            for (int row = 0; row < ROW_COUNT; row++) {
                for (int col = 0; col < COL_COUNT; col++) {
                    if (Objects.equals(board[row][col], CELL_STATE_EMPTY)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}