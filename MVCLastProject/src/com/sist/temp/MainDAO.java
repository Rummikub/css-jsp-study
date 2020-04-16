package com.sist.temp;

import java.util.*;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import java.sql.*;

public class MainDAO {

	private Connection conn;
	//************************************************************
	private CallableStatement cs; // Procedure를 호출하는 Statement
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public MainDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection()
	{
			 try {
				 conn=DriverManager.getConnection(URL,"hr","happy");
			} catch (Exception e) {}	
	}
	
	public void disConnection()
	{
			try {
				 if(cs!=null) cs.close();
				 if(conn!=null)conn.close();
			} catch (Exception e) {}
	}
	
	public StudentVO studentInfoData(int no)
	{
		StudentVO vo=new StudentVO();
		try {
			getConnection();
			
		//***************************************************************	
			String sql="{CALL pro_test_select(?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
		//*************************************************************IN (사용자가 넣어줄 것)
			cs.setInt(1, no);
		//************************************************************OUT (오라클에서 가져올 것)
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			cs.registerOutParameter(3, OracleTypes.INTEGER);
			cs.registerOutParameter(4, OracleTypes.INTEGER);
			cs.registerOutParameter(5, OracleTypes.INTEGER);
		
		//실행 ********** 무조건 UPDATE다 ***********************************
			cs.executeUpdate();
		//**********************************값 가져올 때 번호(순서)를 잘 맞춰야 한다**
			String name=cs.getString(2);
			String kor=cs.getString(3); // int로 VO안만들었을 경우 그냥 String으로 받으면 되즹
			int math=cs.getInt(4);
			int eng=cs.getInt(5);
			
			vo.setName(name);
			vo.setKor(kor);
			vo.setMath(math);
			vo.setEng(eng);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
}
