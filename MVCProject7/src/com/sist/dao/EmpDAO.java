package com.sist.dao;
import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class EmpDAO {
	private static SqlSessionFactory ssf;
	static
	{
		// XML SAX Parsing
		try
		{
			Reader reader=Resources.getResourceAsReader("config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{ ex.printStackTrace();}
	}
	
	//emp-mapper의 <select>문을 실행해서 값을 채워준다는 것.
	public static List<EmpVO> empAllData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list=session.selectList("empAllData");
		session.close();
		
		return list;
	}
	
}
