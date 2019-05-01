package server;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ExportException;
import java.util.StringTokenizer;

class Server_thread extends Thread {
    Socket socket;


    String cancel = "cancel";
    ServerSocket server = null;
    Socket you = null;
    String bao=null;
    String instruct = null;
    String data;
    String value;
    DataOutputStream out = null;
    DataInputStream in = null;
    PetShop shop = null;

    Server_thread(Socket you, PetShop shop) throws IOException{


        this.shop = shop;

        in=new DataInputStream(you.getInputStream());
        out=new DataOutputStream(you.getOutputStream());


        /*try {
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException var3) {

        }*/

    }

    public void run(){
        while(true) {
            try {
                bao = in.readUTF();
            } catch (IOException e) {
            	System.out.println("error");
                break;
            }
            String[] arr = bao.split(",");
            instruct = arr[0];
            try {
                data = arr[1];

            } catch (Exception e) {
            	System.out.println("error");

            }
            try {
                value = arr[2];
            } catch (Exception e) {

            }
            System.out.println("instruct:" + instruct);
            switch (instruct) {
                case "add":
                    shop.add(data);
                    try {
                        shop.check1(out);
                    } catch (Exception e) {
                        System.out.println("bao=in.readUTF();");
                    	System.out.println("error");

                    }

                    break;
                case "check":
                    try {
                        shop.check(data, out);
                    } catch (Exception e) {
                    	System.out.println("error");

                    }

                    break;
                case "modify":
                    try {
                        shop.modify(value, data);
                    } catch (Exception e) {
                        System.out.println("error");
                    }
                    try {
                        shop.check1(out);
                    } catch (Exception e) {

                    }


                    break;
                case "delete":
                    try {
                        shop.delete(data, out);
                    } catch (Exception e) {
                        System.out.println(e);
                    	System.out.println("error");
                    }
                    try {
                        shop.check1(out);
                    } catch (Exception e) {
                    	System.out.println("error");

                    }
                    break;
                case "check1":
                    try {
                        shop.check1(out);
                        System.out.println("suc");
                    } catch (Exception e) {
                        System.out.println("error");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

