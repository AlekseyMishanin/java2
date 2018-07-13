package java2.lesson2.DOP_DZ_1;

/*
Требуется реализовать enum DayOfWeek, который будет представлять дни недели.
С его помощью необходимо решить задачу определения кол-ва рабочих часов до конца недели по заднному текущему дню.

 Возвращает кол-во оставшихся рабочих часов до конца
 недели по заданному текущему дню. Считается, что
 текущий день ещё не начался, и рабочие часы за него
 должны учитываться.

public class DayOfWeekMain {

 public static void main(final String[] args) {
 System.out.println(getWorkingHours(DayOfWeek.MONDAY));
 }
 */

public class EnumDemo {

    public static void main(String[] a){

        for (DayOfWeek temp: DayOfWeek.values()) {
            System.out.println("День недели - " + temp + ". Осталось рабочих часов - " + DayOfWeek.getWorkingHours(temp));
        }
    }


}
