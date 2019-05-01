package 宠物商店数据库版;
public class Cat  extends Pet
{
	
	//protected String kind = new String("喵星人");
	protected String kind;
	
	@Override
	public String getKind()
	{
		return kind;
	}
	
	public Cat(String kind, String name, String sex, int age)
	{
		super(kind,name,sex,age);
		this.kind = "cat";
	}
	
	
	public void eat()
	{
		
	}
	
	public void howl()
	{
		
	}
	
	public void show()
	{
		System.out.println(this.toString());
	}
	
	public void run()
	{
		
	}
	
	@Override
	public String toString()
	{
		return " "+ name + "    " + sex + "    " + kind+"       "+age + "    " ; 
	}
	
	
}
