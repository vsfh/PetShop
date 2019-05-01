package 宠物商店数据库版;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
  

public class Delete extends JFrame 
{
	private JTextField name = new JTextField("");
    private JButton button = new JButton("删除宠物");
    private JButton button2 = new JButton("取消");
    Function fuck = new Function();
//    private static Function fuck;                             //单一实例化对象
    
    
    public Delete()
    {
        super("请输入您想要删除的宠物信息！");
  
        name.setBounds(40, 40, 200, 20);
        button.setBounds(260, 40, 100, 20);
        button2.setBounds(260, 100, 100, 20);
        
        this.setLayout(null);
        this.setBounds(200, 200, 400, 200);
          
        this.add(name);
        this.add(button);
        this.add(button2);
  
        button.addActionListener(new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent e)
        			{
        				
//        				try {
//							fuck.renew();
//						} catch (ClassNotFoundException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
        				
        				String xingming = name.getText().toString();
        				
        				
        				if (e.getSource() == button)
        	    		{
        					int i = 0;
        	    			try {
								i = fuck.deletePet(xingming);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        					if(i != -1)
        					{
        						JOptionPane.showMessageDialog(null,this, "删除成功！",JOptionPane.INFORMATION_MESSAGE);
        					}
        					else if(i == -1)
        					{
        						JOptionPane.showMessageDialog(null,this, "sorry,宠物店里面没有你想要删除的宠物",JOptionPane.INFORMATION_MESSAGE);
        					}
        	    		}
        			}
        		});
          
        button2.addActionListener(new ActionListener()
        		{
        			public void actionPerformed(ActionEvent e)
        			{
        				dispose();
        			}
        		});
        this.setVisible(false);
    }
    
    
}
