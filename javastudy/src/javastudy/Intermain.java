package javastudy;

public class Intermain {

	public static void main(String[] args) {
		
		//��ü����
		InterSonClass is = new InterSonClass();
		is.fly();
		is.run();
		
		InterClass inc = new InterSonClass();
		inc.fly();
		inc.run();
		System.out.println(inc.a);
		
		is.move();
		
		InterClass.dance();
		
}
		
}
