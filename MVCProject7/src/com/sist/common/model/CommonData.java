package com.sist.common.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonData {

	// 공통으로 뜨는 데이터 *여기서는 사이드바 를 모아두기
	public static void commonData(HttpServletRequest request, HttpServletResponse response)
	{
		//★
		request.setAttribute("side", "사이드 데이터");
	}
	
}
