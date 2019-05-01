package 宠物商店数据库版;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
  

public class Select extends JFrame 
{
	private JTextField name = new JTextField("");
    private JTextField text = new JTextField();
    private JButton button = new JButton("开始查询");
    private JButton button2 = new JButton("取消");
    Function fuck = new Function();
//    private static Function fuck;                             //单一实例化对象
    
    
    public Select()
    {
        super("来找找小宝贝啊！");
  
        name.setBounds(40, 40, 200, 20);
        button.setBounds(260, 40, 100, 20);
        text.setBounds(40, 110, 300, 20);
        button2.setBounds(260, 200, 100, 20);
        
        this.setLayout(null);
        this.setBounds(200, 200, 400, 400);
          
        this.add(name);
        this.add(button);
        this.add(text);
        this.add(button2);
  
        button.addActionListener(new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent e)
        			{
        				
        				
        				try {
							fuck.renew();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        				
        				
        				String xingming = name.getText().toString();
        				
        				
        				if (e.getSource() == button)
        	    		{
        	    			try {
								text.setText(fuck.searchPet(xingming));
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
        	    			text.setEditable(false);
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
