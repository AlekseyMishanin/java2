package java2.lesson2.DZ;

/*
1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или
текст вместо числа), должно быть брошено исключение MyArrayDataException, с детализацией в какой
именно ячейке лежат неверные данные.
3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException
и MyArrayDataException, и вывести результат расчета.
*/
public class ExceptionDemo {

    public static void main(String[] argc){

        ArrayVWork(new String[][]{{"dfdf"},{"jhj"}});
        ArrayVWork(new String[][]{
                {"1","2","3","4"},
                {"1","2","3","4"},
                {"1","2","3","4"},
                {"1","2","3","4"}
        });
        ArrayVWork(new String[][]{
                {"1","2","3","4"},
                {"1","2","3","4"},
                {"1","2","yes","4"},
                {"1","2","3","4"}
        });

    }

    public static void ArrayVWork(String[][] str)
    {
        try{
            //проверяем кол-во элементов в массиве str[]. Если !=4, то бросаем исключение
            if(str.length!=4){ throw new MySizeArrayException();}
            int sum=0;                                              //объявляем и инициализируем переменную для подсчета суммы всех элементов
            for (int i = 0; i<str.length;i++) {                     //запускаем цикл для прохода по внешнему массиву
                //запускаем цикл для прохода по вложенному массиву
                //в поле определения шага цикла, суммируем значения элементов
                for (int j = 0; j < str[i].length; sum += Integer.parseInt(str[i][j]),j++) {
                    //проверяем кол-во элементов в массиве str[i][]. Если !=4, то бросаем исключение
                    if (str[i].length != 4) { throw new MySizeArrayException(); }
                    //преобразуем String в char[] и проходим по каждому символу
                    for (char ch: str[i][j].toCharArray()) {
                        if(!Character.isDigit(ch)) {throw new MyArrayDataException(i,j);} //если символ не является цифрой, бросаем исключение
                    }
                }
            }
            System.out.println("Сумма значений всех элементов массива = " + sum);
        } catch (MySizeArrayException|MyArrayDataException e) {e.display();}
    }
}
