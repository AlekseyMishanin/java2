package java2.lesson6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
Для каждого отдельного соединения сервер - клиент создается свой поток. Внутри каждого отдельного соединения
создаются два дополнительных потока для приема и отправки сообщений, чтобы избежать ситуации последовательного
приема и отправки сообщений
*/


public class ConnectionDemo extends Thread {

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private BufferedReader stdIn = null;

    public ConnectionDemo(Socket socket){

        this.socket=socket;
        this.stdIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e){e.printStackTrace();}
        start();
    }

    public void run(){


        Thread []t = new Thread[2];         //создаем массив под два потока: 1. отправка сообщения второму пользователю; 2.вывод на экран сообщения полученного от второго пользователя

        //поток для отправки сообщений
        t[0] =new Thread(new Runnable() {
            @Override
            public void run() {

                String str;
                try {
                    while (!socket.isClosed()&&!Thread.interrupted()) { //если сокет не закрыт и если поток не прерван
                        str = stdIn.readLine(); //пишем в переменную сообщение для второго пользователя
                        out.println(str); //отправляем сообщение
                        if(str.equals("Bye")) { //если отправленное сообщение - это команда завершения
                            if (!socket.isClosed()) {
                                socket.close(); //закрываем сокет
                                in.close();     //закрываем входной поток
                                out.close();    //закрываем выходной поток
                                t[1].interrupt(); //помечаем второй поток(прием сообщений от второго пользователя) как прерванный
                                return;
                            }
                        }
                    }
                } catch (Exception e){e.printStackTrace();
                    try {
                        in.close();
                        out.close();
                    } catch (Exception e1){e1.printStackTrace();}
                }
            }
        });
        t[0].start();

        //поток для приема сообщений
        t[1] =  new Thread(new Runnable() {
            @Override
            public void run() {

                String str;
                try {
                    while (!socket.isClosed()&&!Thread.interrupted()) {
                        str = in.readLine();
                        System.out.println(str);
                        if(str.equals("Bye")){
                            System.out.println(str);
                            if(!socket.isClosed()) {
                                socket.close();
                                in.close();
                                out.close();
                                t[0].interrupt();
                                return;
                            }
                        }
                    }
                } catch (Exception e){e.printStackTrace();
                    try {
                        in.close();
                        out.close();
                    } catch (Exception e1){e1.printStackTrace();}
                }
            }
        });
        t[1].start();

        try {
            t[0].join();
            t[1].join();
        }catch (InterruptedException e){e.printStackTrace();}
        try {
            in.close();
            out.close();
            if (!socket.isClosed()) {
                socket.close();
            }
        }catch (Exception e){e.printStackTrace();}

    }

}

