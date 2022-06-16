package mystudy;

public  class Sonata extends CarClass {

	
	@Override
public void start() {
	System.out.println("Sonata 시동을 켭니다");
}
	@Override
	public void dirve() {
		System.out.println("Sonata 달립니다");
	}
	@Override
	public void stop() {
		System.out.println("Sonata 멈춥니다");
	}
	@Override
	public void turnoff() {
		System.out.println("Sonata 시동을 끕니다");
	}
}
