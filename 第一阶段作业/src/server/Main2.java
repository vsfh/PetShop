package server;

import java.net.*;
import java.io.*;
public class Main2 {
    public static void main(String[] args) {
        ServerSocket server=null;
        Socket you=null;
        String bao=null;
        DataOutputStream out=null;
        DataInputStream in=null;
        String data;
        try{
            server=new ServerSocket(4331);
        }
        catch(IOException e1)
        {
            System.out.println(e1.getMessage());
        }

        while(true)
        {
            try{
                you=server.accept();
                String mainpath = "D://test1.txt";

                ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream(mainpath));
                PetShop shop;
                try{
                    shop=(PetShop)objInputStream.readObject();
                }catch (Exception e){
                    shop=PetShop.getInstance();

                }
                objInputStream.close();
               // PetShop shop=new PetShop();

                System.out.println("success");

                new Server_thread(you,shop).start();
                System.out.println("created.");
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());

            }
        }//end of if

    }
}
