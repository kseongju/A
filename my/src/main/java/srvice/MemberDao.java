package srvice;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import DBconn.DBconn;
import domain.MemberVo;

public class MemberDao {

	//연결객체
	private Connection conn;
	//구문객체
	private PreparedStatement pstmt;
	
	public MemberDao() {
		DBconn db = new DBconn();
		this.conn = db.getConnection();
		
	}
	
	//회원생성
	public int insertMember(String memberName, String userName, String memberID, String memberPwd, String memberPhone, String memberip, String memberemail){
		int value=0;
		//진화된 구문객체 선언 및 초기화

	    String sql="insert into G_member(MEMBERNAME,USERNAME,MEMBERID,MEMBERPWD,MEMBERPHONE, MEMBERIP,MEMBEREMAIL)"
	    + "values(?,?,?,?,?,?,?)";
	    
	    
	    
	    try{

	    pstmt = conn.prepareStatement(sql); //?에 값넣기
	    pstmt.setString(1, memberName);
	    pstmt.setString(2, userName);
	    pstmt.setString(3, memberID);
	    pstmt.setString(4, memberPwd);
	    pstmt.setString(5, memberPhone);
	    pstmt.setString(6, memberip);
	    pstmt.setString(7, memberemail);
	 
	    value = pstmt.executeUpdate();
	    
	    	value = pstmt.executeUpdate(sql);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		return value;
	}
	
	public MemberVo memberLogin(String memberID, String memberPwd) { //로그인
		//int value=0;
		ResultSet rs = null;
		MemberVo mv = null;
		
		String sql="select*from G_member where delyn ='N' and memberid=? and memberpwd=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberID);
			pstmt.setString(2, memberPwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo(); //membervo에서 가져옴
				mv.setMidx(rs.getInt("midx"));
				mv.setMemberID(rs.getString("memberid"));
				mv.setUserName(rs.getString("username"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				
			rs.close();
			pstmt.close();
			conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
		return mv;
	}
	
	public String memberfindid(String memberName, String memberPhone) { //ID찾기
		String findid=null;
		ResultSet rs=null;
		
		String sql="select*from G_member where delyn='N' and membername=? and memberphone=?";
		try {
			pstmt = conn.prepareStatement(sql); //?입력값
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberPhone);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				findid = rs.getString("memberid"); //String타입인 memberID을 findid에 담는다.
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		return findid;
		
	}
	
	public String memberfindpwd(String memberName,String memberID, String memberPhone) { //PWD찾기
		String findpwd=null;
		ResultSet rs=null;
		
		String sql="select*from G_member where delyn='N' and membername=? and memberid=? and memberphone=?";
		try {
			pstmt = conn.prepareStatement(sql); //?입력값
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberID);
			pstmt.setString(3, memberPhone);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findpwd = rs.getString("memberpwd"); //String타입인 memberpwd을 findpwd에 담는다.
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		return findpwd;
		
	}
	
	public MemberVo AccountSelect(int midx) {
		MemberVo mv = null; //정보를 담을 빈공간을 만듬.
		ResultSet rs = null;
		
		String sql="select*from G_member where delyn='N' and midx=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //해당 값이 있다면
				mv = new MemberVo(); //객체를 생성
				mv.setMidx(rs.getInt("midx"));//그 곳에서 필요한 값을 가져옴
				mv.setUserName(rs.getString("username"));
				mv.setMemberID(rs.getString("memberid"));
				mv.setMemberPwd(rs.getString("memberpwd"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return mv;
	}
	
	public int memberAccount(String userName, String memberID, String memberPwd, int midx) { //계정정보 수정하기
		int value=0; //정보를 담을 곳
		
		
		String sql="update G_member set userName=?, memberID=?, memberPwd=? where midx=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, userName);
			pstmt.setString(2, memberID); //2이라고 해야하는데 1이라고 해놈...
			pstmt.setString(3, memberPwd);
			pstmt.setInt(4, midx);

			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	
	
}