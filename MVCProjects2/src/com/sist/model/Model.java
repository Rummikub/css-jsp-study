package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public interface Model {

	// MVCProject1의 execute함수를 모은다
	public String execute(HttpServletRequest request);
	
	// ★메소드 추가는 안됨! 고정된 데이터만 사용 가능하다
		//	public void disp();
	
	
}
