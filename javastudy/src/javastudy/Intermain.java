package javastudy;

public class Intermain {

	public static void main(String[] args) {
		
		//°´Ã¼»ý¼º
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
