package �����̵����ݿ��;

import java.util.*;
import javax.swing.JOptionPane;

import java.io.FileWriter;  
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Function {

	private  ArrayList<Pet>  chongwu = new ArrayList<>();
	
	public Function() 
	{  
		
	}
	
	
	public void renew() throws SQLException , ClassNotFoundException          //�����ݿ��ж�������
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/petlist?serverTimezone=UTC&characterEncoding=utf-8";
		String user = "root";
		String password = "123456";
		Connection conn = DriverManager.getConnection(url, user, password);
		
		Statement stmt = conn.createStatement();
		
		String sql = "select * from pet_list";            //ִ��SQL��䣡������
		ResultSet rs = stmt.executeQuery(sql);
		

		while (rs.next())
		{			 
			int age;
			age = Integer.valueOf(rs.getInt(4));
			String str1 = new String("dog");
			String str2 = new String("cat");
			
			if(rs.getString(1).equals(str1))
			{
				chongwu.add(new Dog(rs.getString(1),rs.getString(2),rs.getString(3),age));
				
			}
			else if(rs.getString(1).equals(str2))
			{
				chongwu.add(new Cat(rs.getString(1),rs.getString(2),rs.getString(3),age));
			}
			
		}
		
		
	}
	
	
	
	
	public boolean addPet(String jb1,String jb2,String jb3,int jb4)                    //��ӳ���
	{
			
			ArrayList<String> kind1 = new ArrayList<>();
			kind1.add(jb1);
			
			ArrayList<String> name1 = new ArrayList<>();
			name1.add(jb2);
        
			ArrayList<String> sex1  = new ArrayList<>();
			sex1.add(jb3);
        
			ArrayList<Integer> age1 = new ArrayList<>();
        	age1.add(jb4);
        
		
		
        	
        
        	if(jb1.equals("dog"))
        	{
        		chongwu.add(new Dog(jb1,jb2,jb3,jb4));
        	}
        	else if(jb1.equals("cat"))
        	{
        		chongwu.add(new Cat(jb1,jb2,jb3,jb4));
        	}
        
         	
		return true;
		
	}
	
	
	
	
	public String searchPet(String mingzi) throws ClassNotFoundException , SQLException              //��ѯ����
	{
		
//		String fuck = new String();
		
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/petlist?serverTimezone=UTC&characterEncoding=utf-8";
		String user = "root";
		String password = "123456";
		Connection conn = DriverManager.getConnection(url, user, password);
		
		Statement stmt = conn.createStatement();
		
		String sql = "select * from pet_list where pet_name ='" + mingzi + "'";            //ִ��SQL��䣡������
		ResultSet rs = stmt.executeQuery(sql);
		
		
		int i;
		
		if(rs.next())
		{
			String str = " " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " ";
			JOptionPane.showMessageDialog(null,this, "�ҵ��ˣ�",JOptionPane.INFORMATION_MESSAGE);
			return str;

		}
		
		JOptionPane.showMessageDialog(null,this, "sorry,���������û����Ҫ�ҵĳ���",JOptionPane.INFORMATION_MESSAGE );
			
		return "ľ���ҵ��r���s�ߨt���q������";
	}
	
	
	
	
	
	
	
	public int updatePet(String kind,String name,String sex,int age) throws ClassNotFoundException , SQLException    //���³�����Ϣ
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/petlist?serverTimezone=UTC&characterEncoding=utf-8";
		String user = "root";
		String password = "123456";
		Connection conn = DriverManager.getConnection(url, user, password);
		
		Statement stmt = conn.createStatement();
		
		String sql = "select * from pet_list where pet_name ='" + name + "'";
		String sql2 = "delete from pet_list where pet_name = '" + name + "'";
		String sql3 = "insert into pet_list (KIND,pet_name,sex,age) VALUES ('" + kind + "','" + name +"','male' ," + age + ")";
		String sql4 = "insert into pet_list (KIND,pet_name,sex,age) VALUES ('" + kind + "','" + name +"','female' ," + age + ")";
		
		ResultSet  rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			stmt.execute(sql2);
			if(kind.equals("dog"))
        	{
				stmt.execute(sql3);
        	}
        	else if(kind.equals("cat"))
        	{
        		stmt.execute(sql4);
        	}
			return 1;
			
		}
		
		return -1;
	}
	
	
	
	
	
	
	
	public int deletePet(String name) throws ClassNotFoundException , SQLException       //ɾ������
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/petlist?serverTimezone=UTC&characterEncoding=utf-8";
		String user = "root";
		String password = "123456";
		Connection conn = DriverManager.getConnection(url, user, password);
		
		Statement stmt = conn.createStatement();
		
		String sql = "select * from pet_list where pet_name ='" + name + "'";
		String sql2 = "delete from pet_list where pet_name = '" + name + "'";
		
		ResultSet  rs = stmt.executeQuery(sql);


		
		if(rs.next())
		{
			stmt.execute(sql2);
			return 1;
			
		}
		
		return -1;
		
	}
	
	
	
	public String getAllPets()
	{
		
		StringBuilder sb = new StringBuilder();
		
		
		for(int i=0;i<chongwu.size();i++)
		{
			
			sb.append(chongwu.get(i));
			sb.append("\r\n");
			
		}
		
		return sb.toString();
		
		
	}
	
	
	
	
	
	
	
	
//	public void  upnew()                           //������д���ı�
//	{
//		int i = 0;
//		ArrayList<String> kind3 = new ArrayList<>();
//		ArrayList<String> name3 = new ArrayList<>();
//		ArrayList<String> sex3 = new ArrayList<>();
//		ArrayList<Integer> age3 = new ArrayList<>();
//		
//		for(i = 0;i<chongwu.size();i++)
//		{
//			kind3.add(chongwu.get(i).getKind());
//			name3.add(chongwu.get(i).getname());
//			sex3.add(chongwu.get(i).getsex());
//			age3.add(chongwu.get(i).getage());
//		}
//		
//		FileWriter fw = null;
//		try {
//			fw = new FileWriter("D:\\java\\�����̵�\\src\\PetShop\\������.txt",false);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}    //д����
//        
//		for(i = 0; i<chongwu.size();i++)
//		{
//			try {
//				fw.write(kind3.get(i)
//					+ "," 
//					+ name3.get(i)                  //.toString() 
//					+ "," 
//					+ sex3.get(i)                   //.toString() 
//					+ "," 
//					+ age3.get(i) 
//					+ "\r\n");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}    //windows�еĻ���Ϊ\r\n    unix��Ϊ\r�� 
//		}
//      
//        
//        try {
//			fw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException
//	{
//		Function fuck = new Function();
//		fuck.renew();
//		fuck.getAllPets();
//		
//	}
	
	
}
