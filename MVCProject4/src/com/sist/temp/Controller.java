package com.sist.temp;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/* 
 		[ Annotation 얹는 위치 &  구분 ]
  		
  		
  		@  --> Type , 클래스 구분  (@Controller ,@Repository,@Service,@Component)
													//Model		// DAO			// Manager //일반 Class
		public class A						
		{
				@ --> Field, 메모리 주소 전송 (@Autowired ; 자동 메모리 할당, 주소 호출 
																			Map에  key값을 주는 코딩이 사라짐)
																(@RequestMapping
				private B b;
				public void setB(@ B b)  --> Parameter (@Resource ; 매개변수 자동 호출)
				{
						this.b=b;
				}
				@ --> Constructor : 생성자
				public A()
				{
				
				}
				@ --> Method
				public void display()
				{
						public void aaa(String a, int b){}
						public void bbb(String a) {}
						public void ccc(String a, double d){}
				}
		}
								입력) a ~ aaa , b ~ bbb , c~ ccc   --- 사용자에게 char input 받는다 치자
								호출)   메서드는 자동호출 안됨, if문 사용해서 불러왔었음
								A aa=new A();
								if(input=='a')
									aa.aaa("",10);
								else if(input=='b')
									aa.bbb("");
								else if(input=='c')
									aa.ccc("",10.5);
	
 * 
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Controller {

}
