package java2.lesson2.DOP_DZ_1;

public enum DayOfWeek {
    MONDAY(true), TUESDAY(true), WEDNESDAY(true), THURSDAY(true), FRIDAY(true), SATURDAY(false), SUNDAY(false);

    private boolean working_day;        //true - рабочий день, false - выходной день
    final static int hours_of_work;     //продолжительность рабочего дня, час

    //статический блок, инициализирующий переменную перед загрузкой класса
    static {
        hours_of_work=8;
    }

    //конструктор, инициализирующий переменную, содержащую признак рабочего/выходного дня
    DayOfWeek(boolean working_day){
        this.working_day=working_day;
    }

    public boolean isWorking_day() {
        return working_day;
    }

    public static int getWorkingHours(DayOfWeek day){
        if(!day.isWorking_day()){                                   //если не рабочий день
            return 0;                                               //возвращаем 0
        } else{                                                     //иначе
            int count=0;                                            //объявляем переменную для подсчета общего кол-ва рабочих дней
            for (DayOfWeek temp: DayOfWeek.values()) {
                if (temp.isWorking_day()){count++;}                 //считаем общее кол-во рабочих дней
            }

            return (count-day.ordinal())*DayOfWeek.hours_of_work;   //возвращаем кол-во рабочих часов
        }
    }
}
