package minesweeper;

import java.util.Random;


public class GenerateInnnerTable {
    //Основное игровое поле
    static char[][] innerMinesTable = new char[9][9];
    // Генерируем начальное поле, состоящее из '.' и 'X'
    static int amountMines = 0;


    public GenerateInnnerTable() {
        generateTable();
        replaceDotsWithX();
        replaceDotsWithNumbers();
        replceDotsWithSlashed();
    }

    public static void setAmountMines(int amountMines) {
        GenerateInnnerTable.amountMines = amountMines;
    }

    private void generateTable() {

        //Заполняем точками
        for (int i = 0; i < innerMinesTable.length; i++) {
            for (int j = 0; j < innerMinesTable[0].length; j++) {
                innerMinesTable[i][j] = '.';
            }
        }
    }

    private void replaceDotsWithX() {
        Random random = new Random();
        int cntX;
        //Меняем '.' на 'X' пока количество мин в поле не будет равно количеству задыных пользователем мин
        do {
            cntX = 0;
            int randomLine = random.nextInt(9);
            int randomColumn = random.nextInt(9);
            if (innerMinesTable[randomLine][randomColumn] != 'X') {
                innerMinesTable[randomLine][randomColumn] = 'X';
            }

            for (char[] chars : innerMinesTable) {
                for (int j = 0; j < innerMinesTable[0].length; j++) {
                    if (chars[j] == 'X') {
                        cntX++;
                    }
                }
            }
        } while (amountMines > cntX);

    }

    private void replceDotsWithSlashed() {
        for (int i = 0; i < innerMinesTable.length; i++) {
            for (int j = 0; j < innerMinesTable[0].length; j++) {
                if (innerMinesTable[i][j] == '.') {
                    innerMinesTable[i][j] = '/';
                }
            }
        }
    }

    public void printArea() {
        //Печатаем поле
        System.out.println(" |123456789|");
        System.out.println("-|---------|");

        for (int i = 0; i < innerMinesTable.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < innerMinesTable[0].length; j++) {
                System.out.print(innerMinesTable[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }

    // Меняем точки на цифры, если рядом есть бомба
    static boolean isValid(int row, int column) {
        return row >= 0 && row  < innerMinesTable.length && column  >= 0 && column  < innerMinesTable[0].length;
    }

    static int getCnt(int row, int column) {
        int cntX = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isValid(row + i, column + j)) {
                    if (innerMinesTable[row + i][column + j] == 'X') {
                        cntX++;
                    }
                }
            }
        }
        return cntX;
    }

    private void replaceDotsWithNumbers() {
        for (int row = 0; row < innerMinesTable.length; row++) {
            for (int column = 0; column < innerMinesTable[0].length; column++) {
                if (innerMinesTable[row][column] != 'X') {
                    int cntX = getCnt(row, column);
                    if (cntX > 0) {
                        innerMinesTable[row][column] = Character.forDigit(cntX, 10);
                        cntX = 0;
                    }
                }
            }
        }
    }
}


