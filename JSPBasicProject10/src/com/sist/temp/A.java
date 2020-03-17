package com.sist.temp;

// annotation : 둘중 원하는 메소드를 (내용을) 찾을 때 식별할 수 있게 구분해주는 역할
//@Retention(RUNTIME)
//@Target(METHOD)
/*public @interface RequestMapping {
		public String value();
}*/
public class A {
		
	public void display()
	{
		 System.out.println("A.. 0 Call..");
	}
	
	public void display2()
	{
		 System.out.println("A.. 2Call..");
	}
}
