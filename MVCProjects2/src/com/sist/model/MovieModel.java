package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;

public class MovieModel  implements Model {
		@Override
		public String execute(HttpServletRequest request) {
			
	
			return "board/movie.jsp";
		}
}
