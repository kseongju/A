package javastudy;

public class JavaClass {
	
	public int asd() {
		int sum = 0;
		
		for(int i = 1; i<=100; i++) {
			if(i%2==0) {
				sum+=i;
			}
		}
		return sum;
		
	}
	
	public static void main(String[] args) {
		
		JavaClass as=new JavaClass();
				int sum = as.asd();
				System.out.println("Â¦¼öÀÇ ÇÕÀº"+ sum);
	}
}

























