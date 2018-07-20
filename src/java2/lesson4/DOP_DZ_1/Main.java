package java2.lesson4.DOP_DZ_1;

/*
Создать окно с тремя полями и кнопкой (поля не активные)
при нажатии на копку появляется новое окно куда можно внести ФИО нажимаем кнопку ОК и окно скрывается
данные переходят в наше первое окно.
*/

import javax.swing.*;

public class Main {
    public static void main (String[] a){
        SwingUtilities.invokeLater(()->new SwingWindow());
    }
}
