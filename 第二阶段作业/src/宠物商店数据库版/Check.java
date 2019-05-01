package 宠物商店数据库版;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Check extends JFrame
{
	private JTextField text = new JTextField();
	Function fuck = new Function();
	
	public Check()
	{
		
		
		 JTextArea jt = new JTextArea(10, 20);
	     jt.setLineWrap(true);// 如果内容过长。自动换行

	     JScrollPane scr = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scr.setBorder(BorderFactory.createTitledBorder("姓名    性别       种类      年龄 "));															
	     JLabel lab = new JLabel("看看谁在店里等你:");
	     this.setLayout(new GridLayout(2, 1));
	        
	     this.add(lab);
	     this.add(scr);
	        
	     this.setSize(300, 300);
	     this.setLocation(300, 200);
	     
//	     try {
//	    	 System.out.println("sgsrgsr");
//			fuck.renew();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	     
//	     jt.setText(fuck.getAllPets());
	     
	     
	     try {
			fuck.renew();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	     String strn = fuck.getAllPets();
//	     System.out.println(strn);
	     jt.setText(fuck.getAllPets());
	     jt.setEditable(false);
	     
	     
	     
	     
	     this.setVisible(true);		
		
		
		
		
	}
	
	
	
//	public static void main(String[] args)
//	{
//		new Second();
//		
//	}

}
