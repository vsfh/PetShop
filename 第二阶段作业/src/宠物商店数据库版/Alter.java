package 宠物商店数据库版;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;


public class Alter extends JFrame
{
	Function fuck = new Function();                 //将功能和界面结合起来
//	private static Function fuck;                  //单一实例化对象
	
	
	
	JLabel jLabel1,jLabel2,jLabel3,jLabel4;
	JComboBox kind;
	JRadioButton sex1,sex2;
	JTextField name,age;                            //文本框组件
    ButtonGroup jRadioGroup1;                       //单选框
    JPanel jpanel1,jpanel2,jpanel3,jpanel4,jpanel5;
    JButton ok,cancel;                                //创建按钮
	

//    private Object item;//缓存下拉框当前已选择的值
    
    public Alter() //throws IOException
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
        this.setTitle("修改你的宠物界面");
        
        
     
        
        
        ok.addActionListener(new ActionListener()               //对确定按钮增加事件监听！！！
        		{
        			
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
				
						try {
							fuck.renew();
						} catch (ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						
						
						String str = age.getText();
						int old = 0;
				        try {
				             old = Integer.valueOf(str);                    //获取年龄
				        } catch (NumberFormatException e) {
				            e.printStackTrace();
				        }

				        String xingming = name.getText().toString();        //获取姓名              
				        String zhonglei = (String) kind.getSelectedItem();  //获取种类
				        
						
				        boolean isSelect1 = sex1.isSelected();
				        boolean isSelect2 = sex2.isSelected();
				              	        
				        	if(isSelect1)
				        	{
				        		int i =0;
								try {
									i = fuck.updatePet(zhonglei, xingming, "male", old);
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if(i == -1)
								{
									JOptionPane.showMessageDialog(null,this, "sorry,宠物店里面没有你要找的宠物",JOptionPane.INFORMATION_MESSAGE );
									dispose();
								}
								else
								{
									JOptionPane.showMessageDialog(null,this, "修改成功",JOptionPane.INFORMATION_MESSAGE );
									dispose();
									
									
								}
				        	
							}
				        	if(isSelect2)
				        	{
				        		int i =0;
								try {
									i = fuck.updatePet(zhonglei, xingming, "female", old);
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if(i == -1)
								{
									JOptionPane.showMessageDialog(null,this, "sorry,宠物店里面没有你要找的宠物",JOptionPane.INFORMATION_MESSAGE );
									dispose();
								}
								else
								{
									JOptionPane.showMessageDialog(null,this, "修改成功",JOptionPane.INFORMATION_MESSAGE );
									dispose();
									
								}
				        	
							}
				        	
				        }
//					}
        		});    
        
        cancel.addActionListener(new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent e)
        			{
        				dispose();                                  //只关闭当前窗口！！！！！！！！！！！！！！！
        			}
        		});
        
        this.setVisible(false);
    }
    
    
 
//    public static void main(String[] args) 
//    {
//    	new Fifth();
//    }
    
    
    
}


