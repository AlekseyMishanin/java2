package java2.lesson5;

/*
1. Необходимо написать два метода, которые делают следующее:
1) Создают одномерный длинный массив, например:

static final int size = 10000000;
static final int h = size / 2;
float[] arr = new float[size];

2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);

Отличие первого метода от второго:
Первый просто бежит по массиву и вычисляет значения.
Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

Пример деления одного массива на два:

System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

Пример обратной склейки:

System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

Примечание:
System.arraycopy() – копирует данные из одного массива в другой:
System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
По замерам времени:
Для первого метода надо считать время только на цикл расчета:

for (int i = 0; i < size; i++) {
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}

Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 */

import java.util.Arrays;

public class ThreadDemo {
    static final int SIZE = 10000000;
    //static final int H = SIZE / 2;

    public static void main(String[] args) {
        new ThreadDemo().arrayWork_1(SIZE, 1.0f);
        System.out.println();
        new ThreadDemo().arrayWork_2(SIZE, 1.0f,4);
    }

     public void arrayWork_1(int SIZE, float value){

        float[] arr = new float[SIZE];
        Arrays.fill(arr,value);                 //присваиваем элементам массива значение value
        long a = System.currentTimeMillis();    //засекаем время запуска цикла
        for (int i = 0; i < SIZE; i++) {
             arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        a = System.currentTimeMillis()-a;       //засекаем время завершения работы цикла
        System.out.println("Метод без потоков.");
        System.out.println("Обработка массива: " + a);
    }

    // countThread - количество потоков. Также данныя переменная равна кол-ву локальных массивов на которые будет разбит исходный массив
    public void arrayWork_2(int SIZE, float value, int countThread){

        final float[] arr = new float[SIZE];
        Arrays.fill(arr,value);                 //присваиваем элементам массива значение value
        long a,b,c,d;                           //обявляем переменные для фиксирования времени выполнения: разбивки массива, просчета и склейки.

        a = System.currentTimeMillis();         //засекаем время начала обработки массива
        Thread[] th = new Thread[countThread];
        float[][] localArr = new float[countThread][];  //определяем двухмерный массив, используемый для разбивки arr. Размерность двухмерного массива равна кол-ву потоков.
        for (int i = 0; i < countThread; i++) {         //внешний цикл для создания countThread потоков и локальных массивов
            final int INDEX = i;
            th[INDEX] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int length = SIZE/countThread;          //размер локального массива localArr[INDEX]
                    int scrPos = length*INDEX;              //начальная позиция для копирования данных из arr в localArr[INDEX]
                    localArr[INDEX] = new float[length];
                    System.arraycopy(arr,scrPos,localArr[INDEX],0,length); //копируем данные в localArr[INDEX]
                }
            });
            th[INDEX].start();
        }

        try{
                for (int i = 0; i<countThread; i++) th[i].join();
        } catch (InterruptedException e){e.printStackTrace();}
        
        b = System.currentTimeMillis();             //фиксируем время окончания разбивки и начала подсчета

        for (int i = 0; i < countThread; i++) {
            final int INDEX = i;
            th[INDEX] =new Thread(new Runnable() {
                @Override
                public void run() {
                    int length = SIZE/countThread;
                    for (int j = 0; j < length-1; j++) {
                        localArr[INDEX][j] = (float)(localArr[INDEX][j] * Math.sin(0.2f + (j+length*INDEX) / 5) * Math.cos(0.2f + (j+length*INDEX) / 5) * Math.cos(0.4f + (j+length*INDEX) / 2));

                    }
                }
            });
            th[INDEX].start();
        }

        try{
                for (int i = 0; i<countThread; i++) th[i].join();
        } catch (InterruptedException e){e.printStackTrace();}
        
        c = System.currentTimeMillis();         //фиксируем время окончания подсчета и начала склейки

        float[] arr1 = new float[SIZE];         //определяем новый массив в который будут копироваться данные локальных массивов
        for (int i = 0; i < countThread; i++) {
            final int INDEX = i;
            th[INDEX] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int length = SIZE/countThread;
                    int scrPos = length*INDEX;
                    System.arraycopy(localArr[INDEX], 0, arr1, scrPos, length);
                }
            });
            th[INDEX].start();
        }

        try{
             for (int i = 0; i<countThread; i++) th[i].join();
        } catch (InterruptedException e){e.printStackTrace();}
        
        d = System.currentTimeMillis();         //фиксируем время окончания склейки

        System.out.println("Метод с " + countThread + " потоком\\потоками.");
        System.out.println("Деление исходного массива на " + countThread + " локальных массива: " + (b-a));
        System.out.println("Обработка данных локальных массивов: " + (c-b));
        System.out.println("Склейка локальных массивов: " + (d-c));
        System.out.println("Всего затрачено времени на обработку массива: " + (d-a));
    }
}

