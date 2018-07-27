package java2.lesson6;

import java.net.Socket;

public class ClientTCP extends Thread{

    private Socket clientSocket;

    public ClientTCP(){
        try {
            this.clientSocket = new Socket("localhost", 8089);
            start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {

        ConnectionDemo con = new ConnectionDemo(clientSocket);
        try {
            con.join();
        }catch (InterruptedException e){e.printStackTrace();}
    }
    public static void main(String[] args) {
        new ClientTCP();
    }
}