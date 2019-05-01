package client;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicMenuUI;

import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.*;

public class Main extends JFrame implements ActionListener,WindowListener{
    Socket socket;
    DataOutputStream out = null;
    DataInputStream in=null;
    JList<String> jList=null;
    JPopupMenu popupMenu =null;
    String[] mes=null;
    int selectMM;
    String data;
    private JScrollPane jp;
	private JFrame jf = new JFrame("��Ŀ��");
	private JFrame jf2 = new JFrame("");
	//private JFrame jf3 = new Add();

               
     Main() throws IOException{
       // super();
        //this.setLayout(null);
       shift();           
       initPopMenu();
        try {

        }catch (Exception e){
        }
        try {
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        menu("С����");
        //this.setSize(475, 500);
        //this.setLocation(350, 150);       
        //this.setResizable(false);
        //this.addWindowListener(this);
        getAllMessage();
    }

     
     public void shift()
 	{
// 		jf0.setVisible(false);
 		//jf3.setVisible(false);
// 		jf4.setVisible(false);
// 		jf5.setVisible(false);
// 		jf6.setVisible(false);
 		
 		jf.setTitle("��Ŀ��");
 		Icon ic1 = new ImageIcon("D:\\java\\BB\\src\\�����̵����ݿ��\\����12.jpeg");
 		
 		
 		JLabel jl1 = new JLabel(ic1);
 		jl1.setBounds(0,0,ic1.getIconWidth(),ic1.getIconHeight());
 	    jf.add(jl1,BorderLayout.CENTER);
 		//jl1.setBounds(0, 0, this.getWidth(), this.getHeight());		
 	    jf.setSize(600, 500);		 
 	    JButton jb = new JButton("��ӭ������Ŀ�����");
 	    jb.setSize(40, 20);
 	    jb.setBackground(Color.white);
 	    jf.add(jb,BorderLayout.SOUTH);
 	    jf.setVisible(true);

 	   
 	
 		
 		jb.addActionListener(new ActionListener()
 				{
 					@Override
 					public void actionPerformed(ActionEvent e)
 					{
// 						
 												
 						jf.setVisible(false);
 						jf2.setVisible(true);
 						
 					}
 				});
 	}
 		/*button1.addActionListener(new ActionListener()          //�ȳ��б�
 				{
 					@Override
 					public void actionPerformed(ActionEvent e)
 					{
 						
 						JFrame jf0 = new Check();              //��ͼ���ˢ������
// 						jf0.setTitle("");
 						jf0.setVisible(true);                   //����������һ���ܺõİ취
 					}
 					
 				}
 				);
 		
 		
 		button2.addActionListener(new ActionListener()         //��ӳ���
 				{
 					@Override
 					public void actionPerformed(ActionEvent e)
 					{
 						jf3.setVisible(true);
 						
 					}
 				});
 		
 		button3.addActionListener(new ActionListener()        //��ѯ����
 				{
 					@Override
 					public void actionPerformed(ActionEvent e)
 					{
 						
 						JFrame jf4 = new Select();
 						jf4.setVisible(true);
 						
 					}
 				});
 		
 		
 		
 		button4.addActionListener(new ActionListener()        //���³���
 				{
 					@Override
 					public void actionPerformed(ActionEvent e)
 					{
 						JFrame jf5 = new Alter();
 						jf5.setVisible(true);
 						
 					}
 				});
         
 		button5.addActionListener(new ActionListener()        //ɾ������
 				{
 					@Override
 					public void actionPerformed(ActionEvent e)
 					{
 						
 						JFrame jf6 = new Delete();
 						jf6.setVisible(true);
 						
 					}
 				});
 		      
 	}
 		
 	*/
 	
 
    private void initPopMenu(){
        popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("�޸�")).addActionListener(this); //��Ӳ˵���Open
        popupMenu.add(new JMenuItem("ɾ��")).addActionListener(this);
    }

    
    private void maybeShowPopup(MouseEvent e) {
        //��ȡѡ�����ֵ
        if(e.getButton()==3){
            selectMM=jList.getSelectedIndex()+1;
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
    
    public static void main(String[] args)  throws IOException{
        new Main();
    }

    public void menu(String s) {

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        JButton add = createBtn("���");
        JButton check = createBtn("��ѯ");
        JButton quit = createBtn("�˳�");
        JButton shuaxin=createBtn("ˢ��");

        add.setLocation(50,350);
        check.setLocation(150,350);
        quit.setLocation(250,350);
        shuaxin.setLocation(350,350);
    }

    private JButton createBtn(String text) {
        JButton btn = new JButton(text);
       // btn.setUI(new BasicButtonUI());// �ָ������Ӿ�Ч��
        btn.setPreferredSize(new Dimension(100, 27));// ���ð�ť��С
        //btn.setContentAreaFilled(false);// ���ð�ť͸��
        //btn.setFont(new Font("����", Font.PLAIN, 15));// ��ť�ı���ʽ
        btn.setMargin(new Insets(2, 2, 2, 2));// ��ť������߿����
        btn.setBorderPainted(true);
        btn.setSize(60,40);
        btn.addActionListener(this);
        jf2.add(btn);
        return btn;
    }

    public synchronized void getAllMessage() throws IOException {
        socket = new Socket("localhost", 4331);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        out.writeUTF("check1");
        if(jList!=null) {
            jf2.remove(jp);
        }
        int  sum=Integer.valueOf(in.readUTF());
        System.out.println("sum:"+sum);
        if(sum>0) {
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

            jList = new JList(mes);
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
        else {
            mes=new String[1];
            mes[0]="���޳�����Ϣ";
            jList = new JList(mes);
        }
        //panel.add(jList);
        //panel.removeAll(); 
        jf2.setSize(475, 500);
 		JPanel panel = new JPanel();  
 		Icon ic2 = new ImageIcon("D:\\java\\BB\\src\\�����̵����ݿ��\\����11.jpg");
 		JLabel jl2;
 		jl2 = new JLabel(ic2);    
        jf2.getLayeredPane().add(jl2,BorderLayout.CENTER);
        jf2.getContentPane().setVisible(true);
        JLabel lab = new JLabel("                                                           ����˭�ڵ������ :"); 
       
	    
        jf2.setTitle("��Ŀ��");
        jf2.setLayout(new BorderLayout());
        jp=new JScrollPane(jList);
        jp.setBounds(140,80, 200, 250);
        jp.setBackground(new Color(236,238,238));
        jp.setBorder(BorderFactory.createTitledBorder("����     ����     ��ɫ   ����"));
         //jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       
        jf2.add(jp,BorderLayout.CENTER);
        jf2.add(lab,BorderLayout.NORTH);
        jf2.add(jl2,BorderLayout.CENTER);
      
        jList.setBounds(13, 25,174,30);
        jList.setBackground(new Color(238,238,238));      
        

        in.close();
        out.close();
        socket.close();
        jf2.setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent a) {
        if (a.getActionCommand() == "���") {
            System.out.println("add");
            try {
                add();
                Thread.sleep(50);
                getAllMessage();
            } catch (Exception error) {

            }

        } else if (a.getActionCommand() == "��ѯ") {
            System.out.println("check");
            try {
                new Check(this,"check",true);
            } catch (Exception error) {

            }
        } else if (a.getActionCommand() == "�޸�") {
            System.out.println("modify");
            try {
                try{
                    modify();
                    Thread.sleep(50);
                    getAllMessage();
                }catch (Exception error){
                    System.out.println(error);
                }

                getAllMessage();
            } catch (Exception error) {

            }
        } else if (a.getActionCommand() == "ɾ��") {
            System.out.println("delete");
            try {
                delete();
                Thread.sleep(50);
                getAllMessage();
            } catch (Exception error) {

            }
        }else if(a.getActionCommand()=="ˢ��"){

            try {
                getAllMessage();
            } catch (Exception error) {

            }
        }

        else if(a.getActionCommand()=="�˳�"){
            System.exit(0);
        }
    }



    public synchronized void add()throws IOException{
        socket = new Socket("localhost", 4331);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        new AddMessage(socket,this,"���������Ϣ",true);
        in.close();
        out.close();
        socket.close();
    }

    public synchronized void modify()throws IOException{
        socket = new Socket("localhost", 4331);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        new ModifyMessage(socket,this,"modify",true,data);
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
    /**
     * Invoked the first time a window is made visible.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Invoked when the user attempts to close the window
     * from the window's system menu.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("�ر�");
        System.exit(0);
    }

    /**
     * Invoked when a window has been closed as the result
     * of calling dispose on the window.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowClosed(WindowEvent e) {
        System.exit(0);

    }

    /**
     * Invoked when a window is changed from a normal to a
     * minimized state. For many platforms, a minimized window
     * is displayed as the icon specified in the window's
     * iconImage property.
     *
     * @param e the event to be processed
     * @see Frame#setIconImage
     */
    @Override
    public void windowIconified(WindowEvent e) {

    }

    /**
     * Invoked when a window is changed from a minimized
     * to a normal state.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    /**
     * Invoked when the Window is set to be the active Window. Only a Frame or
     * a Dialog can be the active Window. The native windowing system may
     * denote the active Window or its children with special decorations, such
     * as a highlighted title bar. The active Window is always either the
     * focused Window, or the first Frame or Dialog that is an owner of the
     * focused Window.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowActivated(WindowEvent e) {

    }

    /**
     * Invoked when a Window is no longer the active Window. Only a Frame or a
     * Dialog can be the active Window. The native windowing system may denote
     * the active Window or its children with special decorations, such as a
     * highlighted title bar. The active Window is always either the focused
     * Window, or the first Frame or Dialog that is an owner of the focused
     * Window.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
