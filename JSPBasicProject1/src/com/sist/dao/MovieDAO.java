package com.sist.dao;
//MyBatis로 DAO대체하기
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.sql.*;   

public class MovieDAO {
		private static SqlSessionFactory ssf;// XML전체를 읽어서 저장하는 역할
		static
		{
				try {
					Reader reader=Resources.getResourceAsReader("config.xml");
					ssf=new SqlSessionFactoryBuilder().build(reader);
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
		public static List<MovieVO> movieAllData()
		{
			return ssf.openSession().selectList("movieAllData");
		}
		
		public static List<MusicVO> musicAllData()
		{
			return ssf.openSession().selectList("musicAllData");
		}
		
}
		