package javastudy;

public class PuClass {
	
	 private int a;
	 
	 public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	private int b;
	
	public PuClass(){
		this.a=1;
		this.b=2;
	}
	
	public void test() {
		
		System.out.println(b);
	}
}
