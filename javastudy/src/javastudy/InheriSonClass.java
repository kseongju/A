package javastudy;

public class InheriSonClass extends InheriClass{

	public InheriSonClass(int speed) {
		super(speed);
		
	}
	//상위클래스의 속성과 기능을 물려받아서 활용할 수 있다 -상속
	
	
	//Override 재정의
		public void move() {
			super.move();
		System.out.println("무작정달리다");
	}
	
	public void move(int speed) {
		System.out.println(speed+"km/s로 움직이다");
	}
	
	
	
}
