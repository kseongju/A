package srvice;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBconn.DBconn;
import domain.BoardVo;
import domain.QuizCriteria;
import domain.SearchCriteria;

public class BoardDao {
	
	Connection conn;
	PreparedStatement pstmt;
	
	
	public BoardDao() {
		DBconn db = new DBconn();
		this.conn = db.getConnection();
		
	}
	
	public int insertfBoard(String title, String content, String writer, String memberip, int midx) { //�����Խ��� �Խñ� �ۼ�
		int value=0;
		
		String sql="insert into G_fboard(title, content, writer, memberip, midx)"
				+ "values(?, ?, ?, ?, ?)";
			
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, memberip);
			pstmt.setInt(5, midx);
			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();//sql����
				conn.close(); //db��������
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return value;
	}
	
	public int insertnBoard(String title, String content, String writer, String memberip, int midx) { //���� �ۼ�
		int value=0;
		
		String sql="insert into G_nboard(title, content, writer, memberip, midx)"
				+ "values(?, ?, ?, ?, ?)";
			
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, memberip);
			pstmt.setInt(5, midx);
			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();//sql����
				conn.close(); //db��������
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return value;
	}
	
	public ArrayList<BoardVo>fboardSelectAll(SearchCriteria scri){ //�����Խ��� �۸��
		ArrayList<BoardVo> alist =new ArrayList<BoardVo>(); //�Խñ��� ���� �迭 ���ѱ�
		ResultSet rs = null;
		
		//String sql="select*from Quiz_fboard where delyn='N'  order by writeday desc";
		
		String str=""; //�˻����(�⺻��)
		if(scri.getSearchType().equals("title")) {
			 str = "and title like ?";
			
		}else {
			str = "and writer like ?";
			
		}
		
		String sql = "select *from G_fboard where delyn='N'"+str+"order by writeday desc limit ?,?";//����¡
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%"); //�˻�� �������.
			pstmt.setInt(2, (scri.getPage()-1)*15+1); //1����
			pstmt.setInt(3, (scri.getPage()*15));//15������ ���������� ���
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo bv = new BoardVo();
				bv.setBidx(rs.getInt("fbidx")); //rs�� ����� fbidx�� bv�� �Űܴ�´�.
				bv.setTitle(rs.getString("title"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setCcount(rs.getInt("fboard_c_count"));
				bv.setHit(rs.getInt("hit"));
			
				alist.add(bv); //������ bv��ü�� alist�� �߰��Ѵ�.
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return alist;
	}
	
	public ArrayList<BoardVo>nboardSelectAll(SearchCriteria scri){ //�������� �۸��
		ArrayList<BoardVo> alist =new ArrayList<BoardVo>(); //�Խñ��� ���� �迭 ���ѱ�
		ResultSet rs = null;
		
		String str=""; //�˻����(�⺻��)
		if(scri.getSearchType().equals("title")) {
			 str = "and title ?";
			
		}else {
			str = "and writer ?";
			
		}
		
		String sql = "select *from G_nboard where delyn='N'"+str+"order by writeday desc limit ?,?";//����¡
		
		//String sql="select*from Quiz_nboard where delyn='N' order by writeday desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%"); //�˻�� �������.
			pstmt.setInt(2, (scri.getPage()-1)*15+1); //1����
			pstmt.setInt(3, (scri.getPage()*15));//15������ ���������� ���
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo bv = new BoardVo();
				bv.setBidx(rs.getInt("nbidx")); //rs�� ����� nbidx�� bv�� �Űܴ�´�.
				bv.setTitle(rs.getString("title"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setCcount(rs.getInt("nboard_c_count"));
				bv.setHit(rs.getInt("hit"));
			
				alist.add(bv); //������ bv��ü�� alist�� �߰��Ѵ�.
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return alist;
	}
	
	public BoardVo fboardSelectOne(int bidx) { //�����Խ��� �Խñ� ���뺸��
		BoardVo bv = null; 
		ResultSet rs =null;
		
		String sql="select*from G_fboard where fbidx=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { /*���� ���� ������*/
				bv = new BoardVo(); //��ü����
				bv.setBidx(rs.getInt("fbidx")); //�ʿ��� ���� ������
				bv.setTitle(rs.getString("title"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setMidx(rs.getInt("midx")); //�ۼ��ڸ� ���� ������ư�� ���̰� �ϱ� ���� ������
				bv.setCcount(rs.getInt("fboard_c_count")); //��ۼ�
				bv.setHit(rs.getInt("hit")); //��ȸ��
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return bv;
	}
	
	public BoardVo nboardSelectOne(int bidx) { //�����Խ��� �Խñ� ���뺸��
		BoardVo bv = null; 
		ResultSet rs =null;
		
		String sql="select*from G_nboard where nbidx=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { /*���� ���� ������*/
				bv = new BoardVo(); //��ü����
				bv.setBidx(rs.getInt("nbidx")); //�ʿ��� ���� ������
				bv.setTitle(rs.getString("title"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setCcount(rs.getInt("nboard_c_count")); //��ۼ�
				bv.setHit(rs.getInt("hit")); //��ȸ��
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return bv;
	}
	
	public int fboardDelete(int bidx) {//�����ϱ�
		int value=0;
		
		String sql="update G_fboard set delyn='Y' where fbidx=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	public int fboardmodify(String title, String content, String writer, int bidx) { //�����ϱ�
		int value =0;
		
		String sql="update G_fboard set title=?, content=?, writer=?, writeday=now() where fbidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setInt(4, bidx);
			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return value;
	}
	
	public int nboardmodify(String title, String content, String writer, int bidx) { //���� �����ϱ�
		int value =0;
		
		String sql="update G_nboard set title=?, content=?, writer=?, writeday=now() where nbidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setInt(4, bidx);
			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return value;
	}
	
	public int nboardDelete(int bidx) {//�������� �����ϱ�
		int value=0;
		
		String sql="update G_nboard set delyn='Y' where nbidx=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	
	
	public int boardTotal(SearchCriteria scri) { //�Խñ� ������ ���ϴ�
		int cnt = 0;
		ResultSet rs=null;
		String str=""; //�˻� ��������
		if(scri.getSearchType().equals("title")) { //��ġŸ���� title�̶��
			 str = "and title like ?"; //str�� ������
			
		}else { //�ƴ϶�� 
			str = "and writer like ?"; //�̷��� �ٲ۴�.
			
		}
		
		String sql="select count(*) as cnt from G_fboard where delyn='N' "+str+"";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt =rs.getInt("cnt"); //count�� cnt�� cnt�� ��´�.
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return cnt;
	}
	
	public int nboardTotal(SearchCriteria scri) { //�Խñ� ������ ���ϴ�
		int cnt = 0;
		ResultSet rs=null;
		String str=""; //�˻� ��������
		if(scri.getSearchType().equals("title")) { //��ġŸ���� title�̶��
			 str = "and title like ?"; //str�� ������
			
		}else { //�ƴ϶�� 
			str = "and writer like ?"; //�̷��� �ٲ۴�.
			
		}
		
		String sql="select count(*) as cnt from G_nboard where delyn='N' "+str+"";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt =rs.getInt("cnt"); //count�� cnt�� cnt�� ��´�.
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return cnt;
	}


	
	//��۱��
	public int insertfcomment(String content, String writer, String memberip, int midx, int bidx) { //�����Խ��� �Խñ� �ۼ�
		int value=0;
		
		
		String sql1="update G_fboard set FBOARD_C_COUNT = fboard_c_count+1 where fbidx=?";
		
		String sql2="insert into Reply(writer, content, memberip, midx, fbidx)"
				+ "values(?, ?, ?, ?, ?)";
		
			
		try {
			conn.setAutoCommit(false);//Ʈ������� �ɾ sql������ �ϳ��� ����Ǵ� ���ܻ�Ȳ�� ����
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, bidx);
			value = pstmt.executeUpdate();
			
			
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, writer);
			pstmt.setString(2, content);
			pstmt.setString(3, memberip);
			pstmt.setInt(4, midx);
			pstmt.setInt(5, bidx);
			value=pstmt.executeUpdate();
			
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback(); //�����߻��� �ѹ�
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return value;
	}
	
	public ArrayList<BoardVo>Selectfcomment(int bidx){//��ۺ����ֱ�
		ArrayList<BoardVo> clist = new ArrayList<BoardVo>();
		ResultSet rs=null;
		
		String sql="select *from Reply where delyn='N' and fbidx=? order by writeday asc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo abv = new BoardVo();
				abv.setWriter(rs.getString("writer"));
				abv.setWriteday(rs.getString("writeday"));
				abv.setContent(rs.getString("content"));
				abv.setMidx(rs.getInt("midx"));
				
			
				clist.add(abv); //������ cbv��ü�� clist�� �߰��Ѵ�.
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return clist;
	}
	
	public int Fboardhit(int bidx) { //�����Խ��� ��ȸ��
		int value=0;
		
		String sql="update G_fboard set hit = hit+1 where fbidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	public int Nboardhit(int bidx) { //�����Խ��� ��ȸ��
		int value=0;
		
		String sql="update G_nboard set hit = hit+1 where nbidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	public ArrayList<BoardVo> nboardmain() { //���������� �ֽű�
		ArrayList<BoardVo> mnlist = new ArrayList<BoardVo>();
		ResultSet rs =null;
		
		String sql="SELECT * FROM G_nboard WHERE delyn='N' ORDER BY writeday desc limit 1,5";
		
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVo bv = new BoardVo();
					bv.setBidx(rs.getInt("nbidx"));
					bv.setTitle(rs.getString("title"));
					bv.setWriter(rs.getString("writer"));
					bv.setWriteday(rs.getString("writeday"));
					bv.setCcount(rs.getInt("nboard_c_count"));
					bv.setHit(rs.getInt("hit"));
					
					mnlist.add(bv); //mnlist�� bv���
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return mnlist;
	}
	
	public ArrayList<BoardVo> fboardmain() { //���������� �ֽű�
		ArrayList<BoardVo> mflist = new ArrayList<BoardVo>();
		ResultSet rs =null;
		
		String sql="SELECT * FROM G_fboard WHERE delyn='N' ORDER BY writeday desc limit 1,10";
		
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVo bv = new BoardVo();
					bv.setBidx(rs.getInt("fbidx"));
					bv.setTitle(rs.getString("title"));
					bv.setWriter(rs.getString("writer"));
					bv.setWriteday(rs.getString("writeday"));
					bv.setCcount(rs.getInt("fboard_c_count"));
					bv.setHit(rs.getInt("hit"));
					
					mflist.add(bv); //mflist�� bv���
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return mflist;
	}
	
	public int insertncomment(String content, String writer, String memberip, int midx, int bidx) { //�����Խ��� �Խñ� �ۼ�
		int value=0;
		
		
		String sql1="update G_nboard set NBOARD_C_COUNT = nboard_c_count+1 where nbidx=?";
		
		String sql2="insert into Reply(writer, content, memberip, midx, nbidx)"
				+ "values(?, ?, ?, ?, ?)";
		
			
		try {
			conn.setAutoCommit(false);//Ʈ������� �ɾ sql������ �ϳ��� ����Ǵ� ���ܻ�Ȳ�� ����
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, bidx);
			value = pstmt.executeUpdate();
			
			
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, writer);
			pstmt.setString(2, content);
			pstmt.setString(3, memberip);
			pstmt.setInt(4, midx);
			pstmt.setInt(5, bidx);
			value=pstmt.executeUpdate();
			
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback(); //�����߻��� �ѹ�
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return value;
	}
	
	public ArrayList<BoardVo>Selectncomment(int bidx){//��ۺ����ֱ�
		ArrayList<BoardVo> nclist = new ArrayList<BoardVo>();
		ResultSet rs=null;
		
		String sql="select *from Reply where delyn='N' and nbidx=? order by writeday asc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo abv = new BoardVo();
				abv.setWriter(rs.getString("writer"));
				abv.setWriteday(rs.getString("writeday"));
				abv.setContent(rs.getString("content"));
				abv.setMidx(rs.getInt("midx"));
				
			
				nclist.add(abv); //������ cbv��ü�� nclist�� �߰��Ѵ�.
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return nclist;
	}
	
	
	public int fcommentDel(int bidx) {
		int value=0;
		
		
		return value;
	}
	
}
