package javastudy;

public interface InterClass {
	
	//미완성된 메서드만 사용가능 
	public void fly(); //abstract 생략됨

	//상수
	int a = 1;//final 생략됨
	
	public void run();
	
	//디폴트메소드
	default void move() {
		System.out.println("이동하다");
		gogo();
	}
	
	//정적메소드
	public static void dance() {
		System.out.println("춤을 추다");
	}
	
	private void gogo() {
		System.out.println("고고고");
	}
	
	
}
