package 宠物商店数据库版;
public abstract class Pet 
{
	protected String kind;
	protected String name;
	protected String sex;
	protected int age;
	
	public  Pet(String kind,String name, String sex, int age)
	{
		this.kind = kind;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	
	public String getKind()
	{
		return kind;
	}
	
	
	public String getname()
	{
		return name;
	}
	
	public void setname(String name)
	{
		this.name = name;
	}
	
	public String getsex()
	{
		return sex;
	}
	
	public void setsex(String sex)
	{
		this.sex = sex;
	}
	
	public int getage()
	{
		return age;
	}
	
	public void setage(int age)
	{
		this.age = age;
	}
}
	