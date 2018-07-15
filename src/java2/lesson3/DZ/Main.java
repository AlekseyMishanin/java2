package java2.lesson3.DZ;

import java.util.*;

/*
1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
Посчитать сколько раз встречается каждое слово.

2.Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get()
искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
(в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
*/
public class Main {

    public static void main(String[] a){

        //Решение первой задачи. Для решения задачи выбрал ArrayList, хотя можно было проще решить при помощи HashMap,
        //где в качестве ключа используется "слово", а в качестве значения целое число, отражающее кол-во повторений слова.
        //Для решения первой задачи не использовал HashMap, так как данная структура данных использовалась для
        //решения второй задачи. Не хотел повторяться.

        ArrayList<String> arrStr = new ArrayList<>(20);
        arrStr.add("Москва");
        arrStr.add("Н.Новгород");
        arrStr.add("Самара");
        arrStr.add("Воронеж");
        arrStr.add("Владивосток");
        arrStr.add("Томск");
        arrStr.add("Иваново");
        arrStr.add("Пенза");
        arrStr.add("Самара");
        arrStr.add("Хабаровск");
        arrStr.add("Тюмень");
        arrStr.add("Владимир");
        arrStr.add("Москва");
        arrStr.add("Москва");
        arrStr.add("Тверь");
        arrStr.add("Владивосток");
        arrStr.add("Сызрать");
        arrStr.add("Владивосток");

        //выводим уникальные значения
        System.out.println("Уникальные элементы:");
        for (int i = 0; i<arrStr.size();i++) {
            int flag = 0;
            for (int j = 0; j < arrStr.size(); j++) {
                if (arrStr.get(i).equals(arrStr.get(j))) {flag++;}
            }
            if (flag==1){System.out.println(arrStr.get(i));}
        }

        //выводим значение элемента и кол-во повторений
        System.out.println("Количество элементов в массиве:");
        for (int i = 0; i<arrStr.size();i++) {
            int flag = 0;
            if(arrStr.indexOf(arrStr.get(i))>=i) {
                for (int j = i; j < arrStr.size(); j++) {
                    if (arrStr.get(i).equals(arrStr.get(j))) {
                        flag++;
                    }
                }
                System.out.println(arrStr.get(i) + " " + flag);
            }
        }


        //Решение второй задачи

        HashPhone pfone_manual = new HashPhone();
        pfone_manual.add("Иванов","9263569474");
        pfone_manual.add("Иванов","9263449875");
        pfone_manual.add("Иванов","9234469476");
        pfone_manual.add("Сидоров","9134469476");
        pfone_manual.add("Петров","9224469076");
        pfone_manual.add("Иванов","9254969476");
        pfone_manual.add("Петров","9784409476");
        pfone_manual.add("Волков","9894669076");

        pfone_manual.get("Сидоров");
        pfone_manual.get("Филиппов");

        pfone_manual.allPhone();

    }

    static class HashPhone{
        private HashMap<String,LinkedList<String>> pfone_manual; //телефонный справочник

        HashPhone(){
            pfone_manual= new HashMap<>(); //инициализируем переменную
        }

        public void add(String name,String pfone){
            LinkedList<String> list = pfone_manual.get(name);   //ищем в справочнике список телефонов по фамилии
            if(list==null){                                     //если списка с номерами телефонов не существует
                list= new LinkedList<>();                       //создаем список
                list.add(pfone);                                //добавляем в список первый номер телефона
                pfone_manual.put(name,list);                    //вставляем в HashMap пару ключ-значение
            } else {                                            //в противном случае
                list.add(pfone);                                //добавляев в существующий список новый номер телефона
            }
        }
        public  void get(String name){

            LinkedList<String> list = pfone_manual.get(name);
            if (list==null){
                System.out.println(name + " отсутствует в справочнике.");
            } else {
                System.out.print(name + ":");
                for (String str :list)
                {
                    System.out.print(" " + str);
                }
                System.out.println();
            }
        }
        public void allPhone(){
            Set<String> keys = pfone_manual.keySet();
            System.out.println("Содержимое телефооного справочника:");
            for (String key : keys){
                System.out.print(key + ":");
                LinkedList<String> list = pfone_manual.get(key);
                for (String pfone: list) {
                    System.out.print(" " + pfone);
                }
                System.out.println(";");
            }
        }
    }
}
