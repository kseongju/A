package mystudy;


public class Student {
	
	protected int Average=0;
	
	public Student(int scoreA, int scoreB, int scoreC) {}
	
	public int getAverage() {
		
		return Average;
	}

	public static void main(String[] args) {

	
	int scoreA = 10;
	int scoreB = 20;
	int scoreC = 30;
	
	Student s = new Student(scoreA, scoreB, scoreC);
	System.out.println("��� ������"+s.getAverage()+"�Դϴ�");
	
	
	
	}
	
}
