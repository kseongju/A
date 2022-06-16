package javastudy;

public class ClassFirst extends Object {

	
	int t = 0;
		//생성자
		public ClassFirst() {
			super();
			this.t = 1;
		}
	
		//상수
		final int kor = 90;
		//변수
		int eng = 90;
		int math = 80;
		String name ="홍길동";
		//메소드
		public int add(int a, int b) {
			int c = a+b;
			
			return c;
	
			
		}

}
