package kys.web.main;

import java.sql.ResultSet;

/*
REVIEWNO  NOT NULL NUMBER         
TYPE               NUMBER         
NO        NOT NULL NUMBER         
MEMBERID  NOT NULL VARCHAR2(4000) 
TITLE     NOT NULL VARCHAR2(4000) 
PHOTO              VARCHAR2(4000) 
REGDATE   NOT NULL DATE           
POSTING            NUMBER         
LIKECOUNT          VARCHAR2(4000) 
GROUPID            VARCHAR2(4000) 
GROUPSTEP          NUMBER         
ROOT               NUMBER         
DEPTH     NOT NULL NUMBER         
HASHTAG            VARCHAR2(4000) 
GRAGE              NUMBER    
 */
public class ReviewDAO {
	//Review
		public void reviewInsert(ReviewVO vo) {
			try {
				getConnection();
				String sql = "INSERT INTO review VALUES((SELECT NVL(MAX(no)+1,1) FROM restaurant),name,tags,addr,tel,openhours,price,lat,lng,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, vo.getrNo());
				ps.setString(2, vo.getId());
				ps.setString(3, vo.getTitle());
				ps.setString(4, vo.getBubble());
				ps.setString(5, vo.getRegdate());
				ps.setString(6, vo.getContent());
				ps.setString(7, vo.getExpdate());
				ps.executeUpdate();
			} catch (Exception e) {
				System.out.println("reviewInsert():");
				e.printStackTrace();
			} finally {
				disConnection();
			}
		}

		public boolean hasReview(int rNo) {
			boolean bCheck = false;

			try {
				getConnection();
				String sql = "SELECT CASE WHEN EXISTS (SELECT 1 FROM review WHERE rNo = ?) THEN 1 ELSE 0 END FROM DUAL";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rNo);
				ResultSet rs = ps.executeQuery();
				rs.next();
				if(rs.getInt(1)==1) bCheck = true;
				rs.close();
			} catch (Exception e) {
				System.out.println("hasReview():");
				e.printStackTrace();
				return true;
			} finally {
				disConnection();
			}

			return bCheck;
		}

}
