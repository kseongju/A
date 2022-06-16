package domain;

public class QuizCriteria {
	
	private int page;   // ������
	private int perPageNum;   //ȭ�鿡 ����Ʈ ��°���
	
	public QuizCriteria() {	
		this.setPage(1);
		this.setPerPageNum(1);		 
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <=1) { //page�� 1���� ������
			this.page = 1; //1�� �Ѵ�.
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
