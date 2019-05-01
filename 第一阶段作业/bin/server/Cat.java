package server;

import java.io.Serializable;

public class Cat implements Pet,Serializable {

	public String name;
	public String color;
	public String age;
	
	
	public Cat(String name, 
			String color, String age) {		// 通过构造设置属性
		this.setName(name) ;
		this.setColor(color) ;
		this.setAge(age) ;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}

	public String getAll(){ return name+" "+color+" "+age; }
	
	

}
