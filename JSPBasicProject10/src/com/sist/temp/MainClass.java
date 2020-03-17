package com.sist.temp;

import java.lang.reflect.Method;

public class MainClass {

	
		public static void main(String[] args) {
			try 
			{
				Class acls=Class.forName("com.sist.temp.A");  // reflection 리플렉션
/*				// !! Object 아님
 				A a=(A)acls.newInstance(); 
				a.display();*/
				
				//A클래스가 가진 모든 메소드 호출  =>  메소드 이름을 몰라도 찾아 올 수 있음
				Object obj=acls.newInstance();
				Method[] m=acls.getDeclaredMethods();
				
				//단, 번호는 임의로 줌 (0호출 됐다가 2가 호출되기도 함)
				m[0].invoke(obj, null);
	} catch (Exception ex) {}
			
			
			
		}
			
	
	
}
