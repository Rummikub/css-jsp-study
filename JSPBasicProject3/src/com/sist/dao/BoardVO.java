package com.sist.dao;
import java.util.*;
//lombok-1.18.12.jar를 가져와야 가능한 코딩
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	
	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private Date regdate;
	private int hit;
	private int group_id;
	private int group_step;
	private int group_tab;
	private int root;
	private int depth;
}
