package 宠物商店数据库版;
import java.awt.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
public class Frame extends JFrame
{
	private JFrame jf = new JFrame("夏目家");
	private JFrame jf2 = new JFrame("");
	private JFrame jf3 = new Add();
//	private JFrame jf4 = new Forth();
//	private JFrame jf5 = new Fift//
//	private JFrame jf6 = new Sixth();
//	private JFrame jf0 = new Second();
               
	public void shift()
	{
//		jf0.setVisible(false);
		jf3.setVisible(false);
//		jf4.setVisible(false);
//		jf5.setVisible(false);
//		jf6.setVisible(false);
		jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           // 关闭该界面时会结束程序！！！！！！		
		jf.setTitle("夏目家");
		Icon ic1 = new ImageIcon("D:\\java\\BB\\src\\宠物商店数据库版\\封面12.jpeg");
		Icon ic2 = new ImageIcon("D:\\java\\BB\\src\\宠物商店数据库版\\封面11.jpg");
		
		JLabel jl1 = new JLabel(ic1);
		jl1.setBounds(0,0,ic1.getIconWidth(),ic1.getIconHeight());
	    jf.add(jl1,BorderLayout.CENTER);
		//jl1.setBounds(0, 0, this.getWidth(), this.getHeight());		
	    jf.setSize(600, 500);		 
	    JButton jb = new JButton("欢迎光临夏目宠物店");
	    jb.setSize(40, 20);
	    jb.setBackground(Color.white);
	    jf.add(jb,BorderLayout.SOUTH);
	    jf.setVisible(true);

	    
		JLabel jl2;
		JPanel panel = new JPanel();
	    JButton button1 = new JButton("查看宠物");
		JButton button2 = new JButton("添加宠物");
		JButton button3 = new JButton("查询宠物");
		JButton button4 = new JButton("修改宠物");
		JButton button5 = new JButton("删除宠物");	    
		jl2 = new JLabel(ic2);
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);

		jf2.setTitle("夏目家");
		jf2.setVisible(false);
		jf2.setSize(1200,650);		
		jf2.setLayout(new BorderLayout());
		jf2.add(jl2,BorderLayout.CENTER);
		jf2.add(panel, BorderLayout.SOUTH);
		
						
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
		 
		button1.addActionListener(new ActionListener()          //萌宠列表
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						
						JFrame jf0 = new Check();              //试图解决刷新问题
//						jf0.setTitle("");
						jf0.setVisible(true);                   //并发现这是一个很好的办法
					}
					
				}
				);
		
		
		button2.addActionListener(new ActionListener()         //添加宠物
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						jf3.setVisible(true);
						
					}
				});
		
		button3.addActionListener(new ActionListener()        //查询宠物
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						
						JFrame jf4 = new Select();
						jf4.setVisible(true);
						
					}
				});
		
		
		
		button4.addActionListener(new ActionListener()        //更新宠物
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						JFrame jf5 = new Alter();
						jf5.setVisible(true);
						
					}
				});
        
		button5.addActionListener(new ActionListener()        //删除宠物
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						
						JFrame jf6 = new Delete();
						jf6.setVisible(true);
						
					}
				});
		      
	}
		
	
	
}