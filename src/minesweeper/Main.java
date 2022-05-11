package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Узнаем сколько хочет мин
        System.out.println("How many mines do you want on the field?");
        int amountMines = scanner.nextInt();
        //Устанавливаем количество мин
        GenerateInnnerTable.setAmountMines(amountMines);
        //Делаем внутреннее поле для сравнения
        GenerateInnnerTable innerTable = new GenerateInnnerTable();

        //Внешнее поле
        GenerateTable personTable = new GenerateTable();
//        innerTable.printArea();
        personTable.printArea();

        // Класс для игровых действий
        PlayActions playActions = new PlayActions();
        while(true) {

            // Просим ввести поле для замены на звезду, повторяем, если поле равно цифре
            System.out.println("Set/unset mines marks or claim a cell as free: ");
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            String action = scanner.next();

            if (!playActions.checkCoordinates(x, y)) {
                continue;
            }

            switch(action) {
                case "mine":
                        //Меняем отмечанную ячейку на *
                        playActions.setMine(x, y);
                        //Печатаем поле еще раз
                        personTable.printArea();
                        playActions.checkWin();

                    break;
                case "free":
                    playActions.firstTurnCheck(x, y);
                    if (playActions.checkLose(x, y)) {
                        personTable.printArea();
                        break;
                    }
                    playActions.openCell(x, y,new boolean[9][9]);
                    personTable.printArea();
                    playActions.checkWin();
                    break;
            }
            //Смотрим на конец игры
            if (playActions.isEndOfGame()) {
                break;
            }
        }

    }
}
