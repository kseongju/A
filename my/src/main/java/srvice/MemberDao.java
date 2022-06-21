package srvice;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBconn.DBconn;
import domain.BoardVo;
import domain.MemberVo;

public class MemberDao {

	//���ᰴü
	private Connection conn;
	//������ü
	private PreparedStatement pstmt;
	
	public MemberDao() {
		DBconn db = new DBconn();
		this.conn = db.getConnection();
		
	}
	
	//ȸ������
	public int insertMember(String memberName, String userName, String memberID, String memberPwd, String memberPhone, String memberip, String memberemail){
		int value=0;
		//��ȭ�� ������ü ���� �� �ʱ�ȭ

	    String sql="insert into G_member(MEMBERNAME,USERNAME,MEMBERID,MEMBERPWD,MEMBERPHONE, MEMBERIP,MEMBEREMAIL)"
	    + "values(?,?,?,?,?,?,?)";
	    
	    
	    
	    try{

	    pstmt = conn.prepareStatement(sql); //?�� ���ֱ�
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
	
	public MemberVo memberLogin(String memberID, String memberPwd) { //�α���
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
				mv = new MemberVo(); //membervo���� ������
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
	
	public String memberfindid(String memberName, String memberPhone) { //IDã��
		String findid=null;
		ResultSet rs=null;
		
		String sql="select*from G_member where delyn='N' and membername=? and memberphone=?";
		try {
			pstmt = conn.prepareStatement(sql); //?�Է°�
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberPhone);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				findid = rs.getString("memberid"); //StringŸ���� memberID�� findid�� ��´�.
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		return findid;
		
	}
	
	public String memberfindpwd(String memberName,String memberID, String memberPhone) { //PWDã��
		String findpwd=null;
		ResultSet rs=null;
		
		String sql="select*from G_member where delyn='N' and membername=? and memberid=? and memberphone=?";
		try {
			pstmt = conn.prepareStatement(sql); //?�Է°�
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberID);
			pstmt.setString(3, memberPhone);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findpwd = rs.getString("memberpwd"); //StringŸ���� memberpwd�� findpwd�� ��´�.
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		return findpwd;
		
	}
	
	public MemberVo AccountSelect(int midx) {
		MemberVo mv = null; //������ ���� ������� ����.
		ResultSet rs = null;
		
		String sql="select*from G_member where delyn='N' and midx=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //�ش� ���� �ִٸ�
				mv = new MemberVo(); //��ü�� ����
				mv.setMidx(rs.getInt("midx"));//�� ������ �ʿ��� ���� ������
				mv.setUserName(rs.getString("username"));
				mv.setMemberID(rs.getString("memberid"));
				mv.setMemberPwd(rs.getString("memberpwd"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return mv;
	}
	
	public int memberAccount(String userName, String memberID, String memberPwd, int midx) { //�������� �����ϱ�
		int value=0; //������ ���� ��
		
		
		String sql="update G_member set userName=?, memberID=?, memberPwd=? where midx=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, userName);
			pstmt.setString(2, memberID); //2�̶�� �ؾ��ϴµ� 1�̶�� �س�...
			pstmt.setString(3, memberPwd);
			pstmt.setInt(4, midx);

			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	public ArrayList<MemberVo> memberSelectAll(){
		
		ArrayList<MemberVo> mlist = new ArrayList<MemberVo>();
		ResultSet rs= null;
		
		String sql="select*from G_member order by midx asc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMemberName(rs.getString("memberName"));
				mv.setMemberID(rs.getString("memberID"));
				mv.setUserName(rs.getString("userName"));
				mv.setMemberPhone(rs.getString("memberPhone"));
				mv.setCreationdate(rs.getString("creationdate"));
				mv.setDelyn(rs.getString("delyn"));
			
				mlist.add(mv); //������ bv��ü�� alist�� �߰��Ѵ�.
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return mlist;
	}
	
	
	
}