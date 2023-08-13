package Game;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public final class GameFunction {

    private static final String EMPTY_FIELD = " ";
    private static final String X_FIELD = "X";
    private static final String O_FIELD = "0";
    private static final String BACKUP_FIELD = "?";

    private final static Scanner scanner = new Scanner(System.in);

    class Saving {
        public static int[] saveState(String[][] board) {
            String[] tempBoardLine = new String[9];
            int[] boardLine = new int[9];
            int count = 0;
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col].isEmpty()) { // проверка на пустое значение
                        tempBoardLine[count++] = " "; // если значение пустое, заполняем пробелом
                    } else {
                        tempBoardLine[count++] = board[row][col];
                    }
                }
            }

            for (int i = 0; i < tempBoardLine.length; i++) {
                switch (tempBoardLine[i]) {
                    case " " -> boardLine[i] = 0;
                    case "X" -> boardLine[i] = 1;
                    case "0" -> boardLine[i] = 2;
                    case "?" -> boardLine[i] = 3;
                }
            }
            return boardLine;
        }

        public static int[] saveStateToFile(int[] boardNumber) {
            try (OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream("savingFile.txt"), StandardCharsets.UTF_8)) {
                for (int j : boardNumber) {
                    fileWriter.write(String.valueOf(j));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return boardNumber;
        }
    }

    class Recovery {
        private static int joinToOneNumber(int[] boardLine) {
            StringBuilder tempStringBuilder = new StringBuilder();
            for (int j : boardLine) {
                tempStringBuilder.append(j);
            }

            return Integer.parseInt(tempStringBuilder.toString());
        }


        private static int[] recoverState(int stateBoard) {
            int[] tempArray = new int[9];
            int lengthStateArray = tempArray.length;
            for (int i = lengthStateArray - 1; i > -1; i--) {
                tempArray[i] = stateBoard % 10;
                stateBoard /= 10;
            }
            return tempArray;
        }

        private static StringBuilder recoverSavingFile(int[] tempArray) {
            StringBuilder TempRecoveredBoard = new StringBuilder();
            try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream("savingFile.txt"), StandardCharsets.UTF_8)) {

                for (int i = 0; i < tempArray.length; i++) {
                    switch (fileReader.read()) {
                        case '0' -> TempRecoveredBoard.append(EMPTY_FIELD);
                        case '1' -> TempRecoveredBoard.append(X_FIELD);
                        case '2' -> TempRecoveredBoard.append(O_FIELD);
                        case '3' -> TempRecoveredBoard.append(BACKUP_FIELD);
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return TempRecoveredBoard;
        }

        private static String[][] RecoverBoard(StringBuilder TempRecoveredBoard) {
            String[][] recoveredBoard = new String[Game.ROW_COUNT][Game.COL_COUNT];
            int count = 0;
            char[] tempCharRecoveredBoard = TempRecoveredBoard.toString().toCharArray();
            for (int row = 0; row < Game.ROW_COUNT; row++) {
                for (int col = 0; col < Game.COL_COUNT; col++) {
                    if (count < tempCharRecoveredBoard.length) {
                        recoveredBoard[row][col] = String.valueOf(tempCharRecoveredBoard[count++]);
                    } else {
                        break;
                    }
                }
            }
            return recoveredBoard;
        }

        private static void writeRecoveredFieldToFile(String[][] recoverBoard)  {
            try (OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream("recoveringFile.txt"), StandardCharsets.UTF_8)) {
                int count = 0;
                for (int row = 0; row < Game.ROW_COUNT; row++) {
                    for (int col = 0; col < Game.COL_COUNT; col++) {
                        if (count < recoverBoard.length) {
                            fileWriter.write(recoverBoard[row][col]);
                        } else {
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void StartRecoverBoard(String[][] board) {
            int[] savingResult = Saving.saveStateToFile(Saving.saveState(board));
            String[][] result = Recovery.RecoverBoard(Recovery.recoverSavingFile(Recovery.recoverState(Recovery.joinToOneNumber(savingResult))));
            System.out.println("Сохраненное состояние: " + Arrays.toString(savingResult));
            System.out.println("Восстановленное состояние: " + Arrays.deepToString(result));

            System.out.println("Сохранение файла...");
            writeRecoveredFieldToFile(result);
        }
    }
}

