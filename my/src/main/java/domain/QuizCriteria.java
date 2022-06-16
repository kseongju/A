package domain;

public class QuizCriteria {
	
	private int page;   // 페이지
	private int perPageNum;   //화면에 리스트 출력개수
	
	public QuizCriteria() {	
		this.setPage(1);
		this.setPerPageNum(1);		 
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <=1) { //page가 1보다 적으면
			this.page = 1; //1로 한다.
			return;
		}	
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum <=0 || perPageNum >100) {
			this.perPageNum = 1;
			return;
		}
		
		this.perPageNum = perPageNum;
	}

}
