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

public class CheckMessage extends JDialog implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField tName=new JTextField();
    JTextField tDest=new JTextField();
    JTextField tWeight=new JTextField();
    JRadioButton r1=new JRadioButton("¹·");
    JRadioButton r2=new JRadioButton("Ã¨");
    ButtonGroup r=new ButtonGroup();
    JLabel Name=new JLabel("Ãû×Ö:");
    JLabel destLabel=new JLabel("ÑÕÉ«:");
    JLabel weightLabel=new JLabel("ÄêÁä:");
    JButton bCreate=new JButton("Check");
    JButton bQuit=new JButton("Cancel");
    Socket socket;
    DataOutputStream out = null;
    DataInputStream in=null;
    Main frame=null;
    public CheckMessage(Socket socket, Main frame,String title,boolean b)throws IOException {
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
        this.setLocation(400, 200);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if( e.getActionCommand()=="Check")
        {
            String zhonglei=null;
            if(r1.isSelected()){
                zhonglei="¹·";
            }else if(r2.isSelected()){
                zhonglei="Ã¨";
            }
            String name=tName.getText();
            String color=tDest.getText();
            String age=tWeight.getText();
            if(name.length()==0)
                name="-1";
            if(color.length()==0)
                color="-1";
            if(age.length()==0)
                age="-1";
            String message=zhonglei+"#"+name+"#"+color+"#"+age;
            System.out.println(message);
            try {
                out.writeUTF("check,"+message);
                this.setVisible(false);
                new CheckResult(socket,frame,"result",true,message);
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
