package com.sist.vo;




/*
CATENO  NOT NULL NUMBER        
TITLE   NOT NULL VARCHAR2(200) 
SUBJECT NOT NULL VARCHAR2(200) 
POSTER  NOT NULL VARCHAR2(200) 
LINK    NOT NULL VARCHAR2(200) 
 */


public class CategoryVO {


		private int cateno;
		private String title;
		private String subject;
		private String poster;
		private String link;
		public int getCateno() {
			return cateno;
		}
		public void setCateno(int cateno) {
			this.cateno = cateno;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getPoster() {
			return poster;
		}
		public void setPoster(String poster) {
			this.poster = poster;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		
}
