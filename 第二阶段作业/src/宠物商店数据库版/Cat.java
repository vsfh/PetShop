package �����̵����ݿ��;
public class Cat  extends Pet
{
	
	//protected String kind = new String("������");
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
