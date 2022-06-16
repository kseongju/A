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
	
	public int insertfBoard(String title, String content, String writer, String memberip, int midx) { //자유게시판 게시글 작성
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
				pstmt.close();//sql종료
				conn.close(); //db접속종료
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return value;
	}
	
	public int insertnBoard(String title, String content, String writer, String memberip, int midx) { //공지 작성
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
				pstmt.close();//sql종료
				conn.close(); //db접속종료
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return value;
	}
	
	public ArrayList<BoardVo>fboardSelectAll(SearchCriteria scri){ //자유게시판 글목록
		ArrayList<BoardVo> alist =new ArrayList<BoardVo>(); //게시글을 담을 배열 만둘기
		ResultSet rs = null;
		
		//String sql="select*from Quiz_fboard where delyn='N'  order by writeday desc";
		
		String str=""; //검색기능(기본값)
		if(scri.getSearchType().equals("title")) {
			 str = "and title like ?";
			
		}else {
			str = "and writer like ?";
			
		}
		
		String sql = "select *from G_fboard where delyn='N'"+str+"order by writeday desc limit ?,?";//페이징
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%"); //검색어를 집어넣음.
			pstmt.setInt(2, (scri.getPage()-1)*15+1); //1에서
			pstmt.setInt(3, (scri.getPage()*15));//15까지만 한페이지에 출력
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo bv = new BoardVo();
				bv.setBidx(rs.getInt("fbidx")); //rs에 복사된 fbidx를 bv에 옮겨담는다.
				bv.setTitle(rs.getString("title"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setCcount(rs.getInt("fboard_c_count"));
				bv.setHit(rs.getInt("hit"));
			
				alist.add(bv); //각각의 bv객체를 alist에 추가한다.
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return alist;
	}
	
	public ArrayList<BoardVo>nboardSelectAll(SearchCriteria scri){ //공지사항 글목록
		ArrayList<BoardVo> alist =new ArrayList<BoardVo>(); //게시글을 담을 배열 만둘기
		ResultSet rs = null;
		
		String str=""; //검색기능(기본값)
		if(scri.getSearchType().equals("title")) {
			 str = "and title ?";
			
		}else {
			str = "and writer ?";
			
		}
		
		String sql = "select *from G_nboard where delyn='N'"+str+"order by writeday desc limit ?,?";//페이징
		
		//String sql="select*from Quiz_nboard where delyn='N' order by writeday desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%"); //검색어를 집어넣음.
			pstmt.setInt(2, (scri.getPage()-1)*15+1); //1에서
			pstmt.setInt(3, (scri.getPage()*15));//15까지만 한페이지에 출력
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo bv = new BoardVo();
				bv.setBidx(rs.getInt("nbidx")); //rs에 복사된 nbidx를 bv에 옮겨담는다.
				bv.setTitle(rs.getString("title"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setCcount(rs.getInt("nboard_c_count"));
				bv.setHit(rs.getInt("hit"));
			
				alist.add(bv); //각각의 bv객체를 alist에 추가한다.
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return alist;
	}
	
	public BoardVo fboardSelectOne(int bidx) { //자유게시판 게시글 내용보기
		BoardVo bv = null; 
		ResultSet rs =null;
		
		String sql="select*from G_fboard where fbidx=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { /*다음 값이 있으면*/
				bv = new BoardVo(); //객체생성
				bv.setBidx(rs.getInt("fbidx")); //필요한 값을 가져옴
				bv.setTitle(rs.getString("title"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setMidx(rs.getInt("midx")); //작성자만 삭제 수정버튼이 보이게 하기 위해 가져옴
				bv.setCcount(rs.getInt("fboard_c_count")); //댓글수
				bv.setHit(rs.getInt("hit")); //조회수
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return bv;
	}
	
	public BoardVo nboardSelectOne(int bidx) { //자유게시판 게시글 내용보기
		BoardVo bv = null; 
		ResultSet rs =null;
		
		String sql="select*from G_nboard where nbidx=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { /*다음 값이 있으면*/
				bv = new BoardVo(); //객체생성
				bv.setBidx(rs.getInt("nbidx")); //필요한 값을 가져옴
				bv.setTitle(rs.getString("title"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setCcount(rs.getInt("nboard_c_count")); //댓글수
				bv.setHit(rs.getInt("hit")); //조회수
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return bv;
	}
	
	public int fboardDelete(int bidx) {//삭제하기
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
	
	public int fboardmodify(String title, String content, String writer, int bidx) { //수정하기
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
	
	public int nboardmodify(String title, String content, String writer, int bidx) { //공지 수정하기
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
	
	public int nboardDelete(int bidx) {//공지사항 삭제하기
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
	
	
	
	public int boardTotal(SearchCriteria scri) { //게시글 총합을 구하는
		int cnt = 0;
		ResultSet rs=null;
		String str=""; //검색 안했을때
		if(scri.getSearchType().equals("title")) { //서치타입이 title이라면
			 str = "and title like ?"; //str의 내용은
			
		}else { //아니라면 
			str = "and writer like ?"; //이렇게 바꾼다.
			
		}
		
		String sql="select count(*) as cnt from G_fboard where delyn='N' "+str+"";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt =rs.getInt("cnt"); //count인 cnt를 cnt에 담는다.
				
				
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
	
	public int nboardTotal(SearchCriteria scri) { //게시글 총합을 구하는
		int cnt = 0;
		ResultSet rs=null;
		String str=""; //검색 안했을때
		if(scri.getSearchType().equals("title")) { //서치타입이 title이라면
			 str = "and title like ?"; //str의 내용은
			
		}else { //아니라면 
			str = "and writer like ?"; //이렇게 바꾼다.
			
		}
		
		String sql="select count(*) as cnt from G_nboard where delyn='N' "+str+"";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt =rs.getInt("cnt"); //count인 cnt를 cnt에 담는다.
				
				
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


	
	//댓글기능
	public int insertfcomment(String content, String writer, String memberip, int midx, int bidx) { //자유게시판 게시글 작성
		int value=0;
		
		
		String sql1="update G_fboard set FBOARD_C_COUNT = fboard_c_count+1 where fbidx=?";
		
		String sql2="insert into Reply(writer, content, memberip, midx, fbidx)"
				+ "values(?, ?, ?, ?, ?)";
		
			
		try {
			conn.setAutoCommit(false);//트랜잭션을 걸어서 sql구문이 하나만 적용되는 예외상황을 방지
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
				conn.rollback(); //오류발생시 롤백
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
	
	public ArrayList<BoardVo>Selectfcomment(int bidx){//댓글보여주기
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
				
			
				clist.add(abv); //각각의 cbv객체를 clist에 추가한다.
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return clist;
	}
	
	public int Fboardhit(int bidx) { //자유게시판 조회수
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
	
	public int Nboardhit(int bidx) { //자유게시판 조회수
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
	
	public ArrayList<BoardVo> nboardmain() { //메인페이지 최신글
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
					
					mnlist.add(bv); //mnlist에 bv담기
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return mnlist;
	}
	
	public ArrayList<BoardVo> fboardmain() { //메인페이지 최신글
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
					
					mflist.add(bv); //mflist에 bv담기
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return mflist;
	}
	
	public int insertncomment(String content, String writer, String memberip, int midx, int bidx) { //자유게시판 게시글 작성
		int value=0;
		
		
		String sql1="update G_nboard set NBOARD_C_COUNT = nboard_c_count+1 where nbidx=?";
		
		String sql2="insert into Reply(writer, content, memberip, midx, nbidx)"
				+ "values(?, ?, ?, ?, ?)";
		
			
		try {
			conn.setAutoCommit(false);//트랜잭션을 걸어서 sql구문이 하나만 적용되는 예외상황을 방지
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
				conn.rollback(); //오류발생시 롤백
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
	
	public ArrayList<BoardVo>Selectncomment(int bidx){//댓글보여주기
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
				
			
				nclist.add(abv); //각각의 cbv객체를 nclist에 추가한다.
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
