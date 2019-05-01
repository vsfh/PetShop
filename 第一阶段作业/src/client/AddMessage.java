package client;


import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AddMessage extends JDialog implements ActionListener {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    String cancel=null;
    JTextField tName=new JTextField();
    JTextField tSource=new JTextField();
    JTextField tDest=new JTextField();
    JTextField tWeight=new JTextField();

    Main frame=null;
    JLabel Name=new JLabel("Ãû×Ö");
    JRadioButton r1=new JRadioButton("¹·");
    JRadioButton r2=new JRadioButton("Ã¨");
    ButtonGroup r=new ButtonGroup();
    JLabel destLabel=new JLabel("ÑÕÉ«:");
    JLabel weightLabel=new JLabel("ÄêÁä:");

    JButton bCreate=new JButton("Create");
    JButton bQuit=new JButton("Cancel");

    public AddMessage(Socket socket, Main frame,String title,boolean b) throws IOException {
        super(frame,title,b);
        this.frame=frame;
        this.socket=socket;
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        bCreate.setUI(new BasicButtonUI());
        bQuit.setUI(new BasicButtonUI());
        bCreate.setContentAreaFilled(false);
        bQuit.setContentAreaFilled(false);
        bCreate.addActionListener(this);
        bQuit.addActionListener(this);

        this.setLayout(new GridLayout(5,2));
        r.add(r1);
        r.add(r2);
        this.add(r1);
        this.add(r2);
        r1.setSelected(true);
        this.add(Name);
        this.add(tName);
        this.add(destLabel);
        this.add(tDest);
        this.add(weightLabel);
        this.add(tWeight);
        this.add(bCreate);
        this.add(bQuit);
        this.setSize(400,250);
        this.setResizable(false);
        this.setLocation(400, 200);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if( e.getActionCommand()=="Create")
        {
            String name=tName.getText();
            String zhonglei=null;
            if(r1.isSelected()){
                zhonglei="¹·";
            }else if(r2.isSelected()){
                zhonglei="Ã¨";
            }
            String color=tDest.getText();
            String age=tWeight.getText();
            String message=zhonglei+" "+name+" "+color+" "+age;
            System.out.println(message);
            try {
                out.writeUTF("add,"+message);
                System.out.println("fenge");
                frame.getAllMessage();
            } catch (Exception error) {
                System.out.println(error);
            }
            this.setVisible(false);
        }
        else if(e.getActionCommand()=="Cancel")
        {

            this.setVisible(false);
        }
    }
}
