package com.sist.dao;

public class MovieBean {
/*
MNO         NUMBER(4)      
TITLE       VARCHAR2(1000) 
POSTER      VARCHAR2(2000) 
SCORE       NUMBER(4,2)    
GENRE       VARCHAR2(100)  
REGDATE     VARCHAR2(100)  
TIME        VARCHAR2(10)   
GRADE       VARCHAR2(100)  
DIRECTOR    VARCHAR2(200)  
ACTOR       VARCHAR2(200)  
STORY       CLOB           
TYPE        NUMBER   
 */
	
		private int mno;
		private String title;
		private String poster;
		private int type;
		private String genre;
		
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public int getMno() {
			return mno;
		}
		public void setMno(int mno) {
			this.mno = mno;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getPoster() {
			return poster;
		}
		public void setPoster(String poster) {
			this.poster = poster;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		
}
