package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CheckResult extends JDialog implements ActionListener{

    Socket socket;
    DataOutputStream out = null;
    DataInputStream in=null;
    Main frame=null;
    String[] mes=null;
    JList<String> jList=null;
    int sum;
    JPanel panel=null;
    JScrollPane jp;


    JPopupMenu popupMenu =null;
    String data;
    String message;
    public CheckResult( Main frame, String title, boolean b,String message)throws IOException {
        super(frame,title,b);
        this.message=message;
        this.frame=frame;

        this.setLayout(null);
        initPopMenu();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setSize(260, 280);
        this.setLocation(400, 170);
        this.setResizable(false);
        getAllMessage();




    }


    private void initPopMenu(){
        popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("修改")).addActionListener(this); //添加菜单项Open
        popupMenu.add(new JMenuItem("删除")).addActionListener(this);
    }

    private void maybeShowPopup(MouseEvent e) {
        //获取选择项的值
        if(e.getButton()==3){
            data=jList.getSelectedValue();
            popupMenu.show(e.getComponent(),e.getX(), e.getY());
        }
        else if(e.getButton()==2){
            popupMenu.setVisible(false);
        }
        else if(e.getButton()==1){
            popupMenu.setVisible(false);
        }

    }


    public synchronized void getAllMessage() throws IOException {
        socket = new Socket("localhost", 4331);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        out.writeUTF("check,"+message);
        if(jList!=null) {
            this.remove(jp);
        }
        sum=Integer.valueOf(in.readUTF());
        System.out.println("sum:"+sum);
        if(sum==0){
            mes=new String[1];
            mes[0]="暂无宠物信息";
            jList = new JList<String>(mes);

        }
        else {
            mes = new String[sum];
            String str = null;
            for (int s = 0; s < sum; s++) {
                str = in.readUTF();
                String[] arr = str.split("\\s+");
                //mes[s]=arr[1]+" "+arr[2]+"  "+arr[3];

                mes[s]=arr[0];
                for(int i=arr[0].length();i<4;i++) {
                    mes[s]+="   ";
                }
                mes[s] += arr[1];
                for (int i = arr[1].length(); i < 4; i++) {
                    mes[s] += "   ";
                }
                mes[s] += arr[2];
                for (int i = arr[2].length(); i < 3; i++) {
                    mes[s] += "   ";
                }
                mes[s] += arr[3];
            }
            System.out.println("sum:" + sum);

            jList = new JList<String>(mes);
            jList.addMouseListener(new MouseAdapter() {
                /**
                 * {@inheritDoc}
                 *
                 * @param e
                 */
                @Override
                public void mouseClicked(MouseEvent e) {
                    maybeShowPopup(e);
                }
            });



        }
        jp=new JScrollPane(jList);
        jp.setBounds(30, 30, 200, 200);

        jp.setBackground(new Color(238,238,238));
        jp.setBorder(BorderFactory.createTitledBorder("种类     名字     颜色   年龄"));
        jp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        //jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(jp);
        //panel.setLayout(null);j
        //jList.setBounds(13, 25,174,360);
        jList.setBackground(new Color(238,238,238));

        System.out.println("sum:"+sum);

        in.close();
        out.close();
        socket.close();
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent a) {
        if (a.getActionCommand() == "修改") {
            System.out.println("modify");
            try {
                try{

                    modify();

                    //out.writeUTF("check,"+message);
                }catch (Exception error){
                    System.out.println(error);
                }
                Thread.sleep(50);
                frame.getAllMessage();
                getAllMessage();
            } catch (Exception error) {

            }
        } else if (a.getActionCommand() == "删除") {
            System.out.println("delete");
            try {

                delete();
                Thread.sleep(50);
                frame.getAllMessage();

                getAllMessage();
            } catch (Exception error) {

            }
        }else if(a.getActionCommand()=="刷新"){

            try {
                out.writeUTF("check1");
                getAllMessage();
            } catch (Exception error) {

            }
        }

        else if(a.getActionCommand()=="退出"){
            System.exit(0);
        }
    }

    public synchronized void modify() throws IOException{
        socket = new Socket("localhost", 4331);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        new ModifyMessage(socket,frame,"modify",true,data);
        in.close();
        out.close();
        socket.close();

    }

    public synchronized void delete() throws IOException{
        socket = new Socket("localhost", 4331);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        out.writeUTF("delete,"+data);
        in.close();
        out.close();
        socket.close();
    }
}
