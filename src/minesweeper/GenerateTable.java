package minesweeper;

public class GenerateTable {

    static char[][] minesTable = new char[9][9];

    {
        fillTable();
    }

    private void fillTable() {
        for (int i = 0; i < minesTable.length; i++) {
            for (int j = 0; j < minesTable[0].length; j++) {
                minesTable[i][j] = '.';
            }
        }
    }

    public void printArea() {
        //Печатаем поле
        System.out.println(" |123456789|");
        System.out.println("-|---------|");

        for (int i = 0; i < minesTable.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < minesTable[0].length; j++) {
                System.out.print(minesTable[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }
}




