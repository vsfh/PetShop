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

public class ModifyMessage extends JDialog implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Socket socket;
    DataInputStream in;
    DataOutputStream out;
    String bianhao;
    String cancel=null;
    String data=null;
    Main frame=null;

    JTextField tSource=new JTextField();
    JTextField tDest=new JTextField();
    JTextField tWeight=new JTextField();

    JLabel sourceLabel=new JLabel("Ãû×Ö:");
    JLabel destLabel=new JLabel("ÑÕÉ«:");
    JLabel weightLabel=new JLabel("ÄêÁä:");

    JButton bCreate=new JButton("Modify");
    JButton bQuit=new JButton("Cancel");

    public ModifyMessage(Socket socket, Main frame, String title, boolean b,String data, String bianhao)throws IOException {
        super(frame,title,b);
        this.frame=frame;
        this.socket=socket;
        this.data=data;
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        this.bianhao=bianhao;
        String[] arr = data.split("\\s+");
        tSource.setText(arr[1]);
        tDest.setText(arr[2]);
        tWeight.setText(arr[3]);
        bCreate.setUI(new BasicButtonUI());
        bQuit.setUI(new BasicButtonUI());
        bCreate.setContentAreaFilled(false);
        bQuit.setContentAreaFilled(false);
        bCreate.addActionListener(this);
        bQuit.addActionListener(this);
        this.setLayout(new GridLayout(4,2));
        this.add(sourceLabel);
        this.add(tSource);
        this.add(destLabel);
        this.add(tDest);
        this.add(weightLabel);
        this.add(tWeight);
        this.add(bCreate);
        this.add(bQuit);
        this.setSize(400,250);
        this.setLocation(400, 200);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if( e.getActionCommand()=="Modify")
        {
            String[] arr = data.split("\\s+");
            String name=tSource.getText();
            String color=tDest.getText();
            String age=tWeight.getText();
            String message=arr[0]+" "+name+" "+color+" "+age;
            System.out.println(message);
            try {
                out.writeUTF("modify,"+message+","+data);
                frame.getAllMessage();
            } catch (Exception error) {

            }
            this.setVisible(false);
        }
        else if(e.getActionCommand()=="Cancel")
        {
            this.setVisible(false);
        }
    }

}
