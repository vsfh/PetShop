package �����̵����ݿ��;
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
	private JFrame jf = new JFrame("��Ŀ��");
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
		jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           // �رոý���ʱ��������򣡣���������		
		jf.setTitle("��Ŀ��");
		Icon ic1 = new ImageIcon("D:\\java\\BB\\src\\�����̵����ݿ��\\����12.jpeg");
		Icon ic2 = new ImageIcon("D:\\java\\BB\\src\\�����̵����ݿ��\\����11.jpg");
		
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

	    
		JLabel jl2;
		JPanel panel = new JPanel();
	    JButton button1 = new JButton("�鿴����");
		JButton button2 = new JButton("��ӳ���");
		JButton button3 = new JButton("��ѯ����");
		JButton button4 = new JButton("�޸ĳ���");
		JButton button5 = new JButton("ɾ������");	    
		jl2 = new JLabel(ic2);
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);

		jf2.setTitle("��Ŀ��");
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
		 
		button1.addActionListener(new ActionListener()          //�ȳ��б�
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						
						JFrame jf0 = new Check();              //��ͼ���ˢ������
//						jf0.setTitle("");
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
		
	
	
}