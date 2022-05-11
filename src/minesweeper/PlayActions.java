package minesweeper;

import static minesweeper.GenerateInnnerTable.innerMinesTable;
import static minesweeper.GenerateTable.minesTable;


public class PlayActions {

    private boolean endOfGame = false;
    private boolean firstTurn = true;

    public boolean isEndOfGame() {
        return endOfGame;
    }

    public boolean checkCoordinates(int x, int y) {
        if (minesTable[y][x] == '.' || minesTable[y][x] == '*') {
            return true;
        } else {
            System.out.println("There is a number here!");
            return false;
        }
    }


    public void setMine(int row, int column) {
        if (minesTable[column][row] == '*') {
            minesTable[column][row] = '.';
        } else {
            minesTable[column][row] = '*';
        }
    }

    public void firstTurnCheck(int x, int y) {
        if (this.firstTurn && innerMinesTable[y][x] == 'X') {
            GenerateInnnerTable generateInnnerTable = new GenerateInnnerTable();
        }
        this.firstTurn = false;
    }


    public void openCell(int row, int column, boolean[][] visited) {
        if (!GenerateInnnerTable.isValid(row, column)) {
            return;
        }
        if (visited[row][column]) {
            return;
        } else {
            visited[row][column] = true;
        }


        int count = GenerateInnnerTable.getCnt(column, row);

        if (count != 0) {
            minesTable[column][row] = innerMinesTable[column][row];
            return;
        }

        minesTable[column][row] = innerMinesTable[column][row];
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                openCell(row + i, column + j, visited);
            }
        }
    }

    public boolean checkLose(int row, int column) {
        if (innerMinesTable[column][row] == 'X') {
            System.out.println("You stepped on a mine and failed!\n");
            for (int i = 0; i < minesTable.length; i++) {
                for (int j = 0; j < minesTable[0].length; j++) {
                    if (innerMinesTable[i][j] == 'X') {
                        minesTable[i][j] = innerMinesTable[i][j];
                    }
                }
            }
            endOfGame = true;
            return true;
        }
        return false;
    }

    public void checkWin() {
        int cntStar = 0;
        int cntHeadshot = 0;
        int cntDotsWin = 0;
        int cntDots = 0;
        for (int i = 0; i < minesTable.length; i++) {
            for (int j = 0; j < minesTable[0].length; j++) {
                if (minesTable[i][j] == '*') {
                    cntStar++;
                }
                if (innerMinesTable[i][j] == 'X' && minesTable[i][j] == '*') {
                    cntHeadshot++;
                }
                if (minesTable[i][j] == '.') {
                    cntDots++;
                }
                if (innerMinesTable[i][j] == 'X' && minesTable[i][j] == '.') {
                    cntDotsWin++;
                }
            }
        }

        if (cntHeadshot == cntStar && cntHeadshot == GenerateInnnerTable.amountMines || cntDots == GenerateInnnerTable.amountMines && cntDotsWin == GenerateInnnerTable.amountMines) {
            System.out.println("Congratulations! You found all the mines!");
            endOfGame = true;
        }
    }
}

