package javastudy;

//인터페이스를 상속받을때 implements 사용
public class InterSonClass implements InterClass{

	@Override
	public void fly() {
		System.out.println("하늘을 날아오르다");
		
	}

	public void run() {
		System.out.println("달리다");
		
	}

}
