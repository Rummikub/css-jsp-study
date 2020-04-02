package com.sist.temp;
import java.lang.reflect.Method;
//annotation은 일종의 index야
import java.util.*;

class A
{
	@RequestMapping("a.do")
	public void aaa()
	{
		System.out.println("A:aaa Call");
	}
	@RequestMapping("b.do")
	public void bbb()
	{
		System.out.println("A:bbb Call");
	}
	@RequestMapping("c.do")
	public void ccc()
	{
		System.out.println("A:ccc Call");
	}
	@RequestMapping("d.do")
	public void ddd()
	{
		System.out.println("A:ddd Call");
	}
	@RequestMapping("e.do")
	public void eee()
	{
		System.out.println("A:eee Call");
	}
}
public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
			Scanner scan=new Scanner(System.in);
			//사용자가 입력하면 a.do => aaa 호출 b.do=> bbb호출
			System.out.println("URI주소 입력:");
			String uri=scan.next();
		/*	
			A a=new A();
			if(uri.equals("a.do"))
				a.aaa();
			if(uri.equals("b.do"))
				a.bbb();
			if(uri.equals("c.do"))
				a.ccc();
			if(uri.equals("d.do"))
				a.ddd();
			if(uri.equals("e.do"))
				a.eee();
			*/
			
			try
			{
				//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
				//1.메모리할당 ~ singleton   	A a=new A();
				Class clsName=Class.forName("com.sist.temp.A"); //clsName -클래스의 모든 정보를 갖고 있음
				Object obj=clsName.newInstance();
				
				//2. 메소드를 배열로 잡음, 이름만 불러와라  --> 빈 공간에 들어오니까 순서가 맞지 않음/ 번호로 호출 x
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods)
				{
					//annotation이 여러개일 수 있잖아. 그중에 requestmapping만 가져와
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					
					//3. uri주소로 메소드 호출하기 
					if(rm.value().equals(uri))
					{
						//invoke()메소드를 호출하는 함수   
						
						m.invoke(obj);
						// X ==> m.invoke(obj, " ");
						//	O ==> m.invoke(obj, null);
						
						
					}
					//System.out.println(m.getName());
				}
			}catch (Exception ex) {}
	}

}
