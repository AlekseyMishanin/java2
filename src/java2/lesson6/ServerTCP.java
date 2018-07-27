package java2.lesson6;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

public class ServerTCP extends Thread {

    ServerSocket serverSocket = null;
    public ServerTCP(){
        try{
            serverSocket = new ServerSocket(8089);
            System.out.println("Starting the server");

            start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){

        try{
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println(Calendar.getInstance().getTime() + " connection accepted from " + clientSocket.getInetAddress().getHostAddress());
                ConnectionDemo con = new ConnectionDemo(clientSocket);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServerTCP();
    }
}
