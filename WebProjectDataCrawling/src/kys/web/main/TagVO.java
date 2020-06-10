package kys.web.main;

/*
TAGNAME          VARCHAR2(1000) 
TAGCODE NOT NULL NUMBER         
COUNT            NUMBER     
 */
public class TagVO {

	private String rname;
	private String tagname;
	private int tagcode;
	private int count;
	
	
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public int getTagcode() {
		return tagcode;
	}
	public void setTagcode(int tagcode) {
		this.tagcode = tagcode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
