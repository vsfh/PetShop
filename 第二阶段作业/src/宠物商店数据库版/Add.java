package �����̵����ݿ��;

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
	Function fuck = new Function();                            //�����ܺͽ���������
//	private static Function fuck;                 //��һʵ��������
	
	
	
	JLabel jLabel1,jLabel2,jLabel3,jLabel4;
	@SuppressWarnings("rawtypes")
	JComboBox kind;
	JRadioButton sex1,sex2;
	JTextField name,age;                            //�ı������
    ButtonGroup jRadioGroup1;                       //��ѡ��
    JPanel jpanel1,jpanel2,jpanel3,jpanel4,jpanel5;
    JButton ok,cancel;                                //������ť
	

//    private Object item;//����������ǰ��ѡ���ֵ
    
    
 
    
    public Add()   //throws IOException
    {
        
    	name = new JTextField(12);
    	age  = new JTextField(12);
    	
    	
	
		kind = new JComboBox();
		kind.addItem("dog");
		kind.addItem("cat");
		kind.setBounds(40, 150, 55, 20);
		
		jLabel1 = new JLabel("����");
		jLabel2 = new JLabel("�Ա�");
		jLabel3 = new JLabel("����");
		jLabel4 = new JLabel("����");
		
		
		sex1=new JRadioButton("male");
	    sex2=new JRadioButton("female");
		
	    jRadioGroup1=new ButtonGroup();
        jRadioGroup1.add(sex1);
        jRadioGroup1.add(sex2);          
 
        
        
        ok = new JButton("ȷ��");
        
        cancel = new JButton("ȡ��");
        jpanel3 = new JPanel();
        jpanel1 = new JPanel();
        jpanel2 = new JPanel();
        jpanel5 = new JPanel();
        jpanel4 = new JPanel();
        
        
        
        
        jpanel3.add(jLabel3);          //����� ���� ��ѡ��
        jpanel3.add(kind);
        
        jpanel1.add(jLabel1);          //����� ���� �ı���
        jpanel1.add(name);
        
        jpanel4.add(jLabel4);          //�����  ���� ��
        jpanel4.add(age);
        
        jpanel2.add(jLabel2);          //����� �Ա�  ��
        jpanel2.add(sex1);
        jpanel2.add(sex2);
        
        jpanel5.add(ok);               //�����  ȷ����ȡ��
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
        this.setTitle("��ӳ������");
        
        
        
        
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		final String url = "jdbc:mysql://localhost:3306/petlist?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
		final String user = "root";
		final String password = "123456";

        
        
        ok.addActionListener(new ActionListener()               //��ȷ����ť�����¼�����������
        		{
        			
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						
						String str = age.getText();
						int old = 0;
				        try {
				             old = Integer.valueOf(str);           //��ȡ����
				        } catch (NumberFormatException e) {
				            e.printStackTrace();
				        }
				       
						
				        String xingming = name.getText().toString();     //��ȡ����              
				    
				      
				        String zhonglei = (String) kind.getSelectedItem();
				        
				        
						
				        boolean isSelect1 = sex1.isSelected();
				        boolean isSelect2 = sex2.isSelected();
				        
						if(isSelect1)
						{
							String sql = "insert into pet_list (KIND,pet_name,sex,age) VALUES ('"+zhonglei+"','"+xingming+"','male' ,"+old+")";            //ִ��SQL��䣡������
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
							String sql = "insert into pet_list (KIND,pet_name,sex,age) VALUES ('"+zhonglei+"','"+xingming+"','female' ,"+old+")";            //ִ��SQL��䣡������
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
//									System.out.println("��ӡ���������");
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							fuck.addPet(zhonglei, xingming, "female", old);
							
						}
						
						JOptionPane.showMessageDialog(null,this, "��ӳɹ���",JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
        		});    
        
        cancel.addActionListener(new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent e)
        			{
        				dispose();                                  //ֻ�رյ�ǰ���ڣ�����������������������������
        				
        				
        				
        			}
        		});
        
        this.setVisible(true);
    }
  
    public static void main(String[] args)
	{
    	new Add();
	}
    
}
