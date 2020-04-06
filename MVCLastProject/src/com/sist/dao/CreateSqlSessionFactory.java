package com.sist.dao;




// 귀찮으니까 sqlsession을 한번에




import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.*;
public class CreateSqlSessionFactory {
		private static SqlSessionFactory ssf;
		static
		{
			try
			{
				Reader reader = Resources.getResourceAsReader("config.xml");
				ssf=new SqlSessionFactoryBuilder().build(reader);
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
		//Getter
		public static SqlSessionFactory getSsf() {
			return ssf;
		}

		
}
