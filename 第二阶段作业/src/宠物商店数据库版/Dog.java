package �����̵����ݿ��;
public class Dog extends Pet
{
	//protected String kind =new String("����");
	protected String kind;
	
	
	@Override
	public String getKind()
	{
		return kind;
	}
		
	public Dog(String kind, String name, String sex, int age)
	{
		super(kind,name,sex,age);
		this.kind  = "dog";
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
	
	
}}
