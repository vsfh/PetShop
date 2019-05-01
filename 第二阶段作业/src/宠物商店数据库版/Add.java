package 宠物商店数据库版;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class Add extends JFrame
{
	Function fuck = new Function();                            //将功能和界面结合起来
//	private static Function fuck;                 //单一实例化对象
	
	
	
	JLabel jLabel1,jLabel2,jLabel3,jLabel4;
	@SuppressWarnings("rawtypes")
	JComboBox kind;
	JRadioButton sex1,sex2;
	JTextField name,age;                            //文本框组件
    ButtonGroup jRadioGroup1;                       //单选框
    JPanel jpanel1,jpanel2,jpanel3,jpanel4,jpanel5;
    JButton ok,cancel;                                //创建按钮
	

//    private Object item;//缓存下拉框当前已选择的值
    
    
 
    
    public Add()   //throws IOException
    {
        
    	name = new JTextField(12);
    	age  = new JTextField(12);
    	
    	
	
		kind = new JComboBox();
		kind.addItem("dog");
		kind.addItem("cat");
		kind.setBounds(40, 150, 55, 20);
		
		jLabel1 = new JLabel("姓名");
		jLabel2 = new JLabel("性别");
		jLabel3 = new JLabel("种类");
		jLabel4 = new JLabel("年龄");
		
		
		sex1=new JRadioButton("male");
	    sex2=new JRadioButton("female");
		
	    jRadioGroup1=new ButtonGroup();
        jRadioGroup1.add(sex1);
        jRadioGroup1.add(sex2);          
 
        
        
        ok = new JButton("确定");
        
        cancel = new JButton("取消");
        jpanel3 = new JPanel();
        jpanel1 = new JPanel();
        jpanel2 = new JPanel();
        jpanel5 = new JPanel();
        jpanel4 = new JPanel();
        
        
        
        
        jpanel3.add(jLabel3);          //添加入 种类 单选框
        jpanel3.add(kind);
        
        jpanel1.add(jLabel1);          //添加入 姓名 文本框
        jpanel1.add(name);
        
        jpanel4.add(jLabel4);          //添加入  年龄 框
        jpanel4.add(age);
        
        jpanel2.add(jLabel2);          //添加入 性别  框
        jpanel2.add(sex1);
        jpanel2.add(sex2);
        
        jpanel5.add(ok);               //添加入  确定和取消
        jpanel5.add(cancel);
        
        
        this.setLayout(new GridLayout(5,1));
        
        
        this.add(jpanel1);
        this.add(jpanel2);
        this.add(jpanel3);
        this.add(jpanel4);
        this.add(jpanel5);
      
        this.setSize(400, 400);
        //this.pack();
        this.setVisible(true);
        this.setTitle("添加宠物界面");
        
        
        
        
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		final String url = "jdbc:mysql://localhost:3306/petlist?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
		final String user = "root";
		final String password = "123456";

        
        
        ok.addActionListener(new ActionListener()               //对确定按钮增加事件监听！！！
        		{
        			
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						
						String str = age.getText();
						int old = 0;
				        try {
				             old = Integer.valueOf(str);           //获取年龄
				        } catch (NumberFormatException e) {
				            e.printStackTrace();
				        }
				       
						
				        String xingming = name.getText().toString();     //获取姓名              
				    
				      
				        String zhonglei = (String) kind.getSelectedItem();
				        
				        
						
				        boolean isSelect1 = sex1.isSelected();
				        boolean isSelect2 = sex2.isSelected();
				        
						if(isSelect1)
						{
							String sql = "insert into pet_list (KIND,pet_name,sex,age) VALUES ('"+zhonglei+"','"+xingming+"','male' ,"+old+")";            //执行SQL语句！！！！
							String sql2= "select * from pet_list ;";
							try {
								Connection conn;
								conn = DriverManager.getConnection(url, user, password);
								Statement stmt = conn.createStatement();
								stmt.execute(sql);
								
								
								
								ResultSet rs = stmt.executeQuery(sql2);
								
								
								while (rs.next()) {			 
									
									int age;
									age = Integer.valueOf(rs.getInt(4));
									String str1 = new String("dog");
									String str2 = new String("cat");
									
									if(rs.getString(1).equals(str1))
									{
										fuck.addPet(rs.getString(1),rs.getString(2),rs.getString(3),age);
									}
									if(rs.getString(1).equals(str2))
									{
										fuck.addPet(rs.getString(1),rs.getString(2),rs.getString(3),age);
									}
								}
								
							
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
						
						
						if(isSelect2)
						{
							String sql = "insert into pet_list (KIND,pet_name,sex,age) VALUES ('"+zhonglei+"','"+xingming+"','female' ,"+old+")";            //执行SQL语句！！！！
							String sql2= "select * from pet_list ;";
							try {
								Connection conn;
								conn = DriverManager.getConnection(url, user, password);
								Statement stmt = conn.createStatement();
								
								stmt.execute(sql);
								
								ResultSet rs = stmt.executeQuery(sql2);
								
								while (rs.next()) {			 
									
									int age;
									age = Integer.valueOf(rs.getInt(4));
									String str1 = new String("dog");
									String str2 = new String("cat");
									
									if(rs.getString(1).equals(str1))
									{
										fuck.addPet(rs.getString(1),rs.getString(2),rs.getString(3),age);
									}
									if(rs.getString(1).equals(str2))
									{
										fuck.addPet(rs.getString(1),rs.getString(2),rs.getString(3),age);
									}
//									System.out.println("添加。。。。。");
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							fuck.addPet(zhonglei, xingming, "female", old);
							
						}
						
						JOptionPane.showMessageDialog(null,this, "添加成功！",JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
        		});    
        
        cancel.addActionListener(new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent e)
        			{
        				dispose();                                  //只关闭当前窗口！！！！！！！！！！！！！！！
        				
        				
        				
        			}
        		});
        
        this.setVisible(true);
    }
  
    public static void main(String[] args)
	{
    	new Add();
	}
    
}
