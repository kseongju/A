package javastudy;

public class ThrowsTest {

	public static void main(String[] args) {
		
		try {
		ThrowsMethod th = new ThrowsMethod();
			int b = th.add(1,0);
			System.out.println("b����" + b);
			th.go();
			th.dance();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

class ThrowsMethod {
	
	public void go() throws Exception {
		throw new Exception();
		//System.out.println("����");
	}
	
	
	public void dance() {
		
		System.out.println("���� �ߴ�");
	}
	
	public int add(int a, int b) {
		int c = 0;
		
		try {
			c=a/b;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	
}
