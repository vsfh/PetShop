package client;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.*;

public class Main extends JFrame implements ActionListener,WindowListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Socket socket;
    DataOutputStream out = null;
    DataInputStream in=null;

    JList<String> jList=null;
    JFrame frame;
    JScrollPane jscrolJanel;
    JPopupMenu popupMenu =null;
    Container contentpane;
    String[] mes=null;
    int selectMM;
    String data;
    JScrollPane jp;

     Main() throws IOException{
        super();
        this.setLayout(null);
        initPopMenu();
        try {
            socket = new Socket("localhost", 4331);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        }catch (Exception e){
        }
        try {
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        menu("小叮当");
        this.setSize(475, 500);
        this.setLocation(350, 150);
        out.writeUTF("check1");
        getAllMessage();
        this.setResizable(false);
        this.addWindowListener(this);
    }

    private void initPopMenu(){
        popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("修改")).addActionListener(this); //添加菜单项Open
        popupMenu.add(new JMenuItem("删除")).addActionListener(this);
    }

    public void getAllMessage() throws IOException {
        if(jList!=null) {
            this.remove(jp);
        }
        int sum=Integer.valueOf(in.readUTF());
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
        else {
            mes=new String[1];
            mes[0]="暂无宠物信息";
            jList = new JList<String>(mes);
        }
        //panel.add(jList);
        //panel.removeAll();
        jp=new JScrollPane(jList);
        jp.setBounds(40, 30, 200, 400);

        jp.setBackground(new Color(238,238,238));
        jp.setBorder(BorderFactory.createTitledBorder("种类     名字     颜色   年龄/岁"));
        jp.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        //jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(jp);
        //panel.setLayout(null);j
        //jList.setBounds(13, 25,174,360);
        jList.setBackground(new Color(238,238,238));


        this.setVisible(true);

    }
    private void maybeShowPopup(MouseEvent e) {
        //获取选择项的值
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


        JButton add = createBtn("添加");
        JButton check = createBtn("查询");
        JButton quit = createBtn("退出");
        JButton shuaxin=createBtn("刷新");

        add.setLocation(310,100);
        check.setLocation(310,160);
        quit.setLocation(310,220);
        shuaxin.setLocation(310,280);
    }

    private JButton createBtn(String text) {
        JButton btn = new JButton(text);
        //btn.setUI(new BasicButtonUI());// 恢复基本视觉效果
        btn.setPreferredSize(new Dimension(100, 27));// 设置按钮大小
        btn.setContentAreaFilled(false);// 设置按钮透明
        //btn.setFont(new Font("粗体", Font.PLAIN, 15));// 按钮文本样式
        btn.setMargin(new Insets(2, 2, 2, 2));// 按钮内容与边框距离
        btn.setBorderPainted(true);
        btn.setSize(60,40);
        btn.addActionListener(this);
        this.add(btn);
        return btn;
    }

    public void actionPerformed(ActionEvent a) {
        if (a.getActionCommand() == "添加") {
            System.out.println("add");
            try {
                new AddMessage(socket,this,"输入添加信息",true);

                out.writeUTF("check1");
                getAllMessage();
            } catch (Exception error) {

            }

        } else if (a.getActionCommand() == "查询") {
            System.out.println("check");
            try {
                new CheckMessage(socket,this,"check",true);

                out.writeUTF("check1");
                getAllMessage();
            } catch (Exception error) {

            }
        } else if (a.getActionCommand() == "修改") {
            System.out.println("modify");
            try {
                try{
                    new ModifyMessage(socket,this,"modify",true,data, data);
                }catch (Exception error){
                    System.out.println(error);
                }

                //getAllMessage();
            } catch (Exception error) {

            }
        } else if (a.getActionCommand() == "删除") {
            System.out.println("delete");
            try {

                out.writeUTF("delete,"+data);
                out.writeUTF("check1");
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
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
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
        System.out.println("关闭");
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
