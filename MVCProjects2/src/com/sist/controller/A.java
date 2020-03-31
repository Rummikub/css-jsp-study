package com.sist.controller;
import java.util.*;
public class A {
		
	// Map에 저장된다 ?  ==  주소가 하나다
	// 주소 하나를 가지고 여러번 사용 한다 : Singleton
	Map map=new HashMap();
		
		public A () 
		{
				map.put("b", new B());
		}
		
		
		public static void main(String[] args) {
			A a =new A();
			B b =(B)a.map.get("b");   // B b=new B();  Object라서 형변환필요
			B c =(B)a.map.get("b");   // B c= new B();
			
			
			System.out.println("b= "+b);
			System.out.println("c= "+c);
			
			
			/* Result)
			b= com.sist.controller.B@70dea4e
			c= com.sist.controller.B@70dea4e

			 */
		}
}


class B {
	
	public void display()
	{
		System.out.println("display:call....");
	}
}