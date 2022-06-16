package javastudy;

public class SecondClass {

	public static void main(String[] args) {
		
		//정수형 변수 선언
		int a;
		//변수 초기화
		a = 3; //3을 a에 대입을 한다.
		a = 5;
		
		System.out.println(a);
		
		final int B; //정수형 변수 B을 선언
		B = 10;
		//B = 30;
		
		System.out.println(B);
		
		
		int c = 200;
		byte d = (byte)c;
		System.out.println(d);
		
		//변수 a의 값은 10이고 b의 값은 20이다.
		// 산술 연산자를 사용해서 두값의 합을 구하시오.
		
		int b;
		
		a = 10;
		b = 20;
		
		int sum1;
		
		sum1 = a+b;
		
		System.out.println(sum1);
		
		//변수 c의 값은 25이고 변수 d의 값은 5이다
		//변수 d를 가지고 c를 나눴을 때 나머지를 구하시오
		
		c = 25;
		d = 5;
		
		int sum2;
		
		sum2 = c%d;
		
		System.out.println(sum2);
		
		int g;
		int h;
		
		h = 1;
		
		g = h++;
		
		System.out.println(g);
		
		g = ++h;
		
		System.out.println(g);
		
		int myAge = 27;
		boolean value = (myAge !=27);
		System.out.println(value);
		
		
		
		/*int num1 = 10;
		int num2 = 20;
		
		boolean flag = (num1 > 0) && (num2 >0);
		System.out.println(flag);
		
		flag = (num1 < 0) && (num2 > 0);  
		System.out.println(flag);         
		
		flag = (num1 < 0)|| (num2> 0);
		System.out.println(flag);*/
		
		int num1 = 5;
		int num2 = 12;
		
		int result = num1 & num2 ;
		
		System.out.println(result);
		
		
		
	}

}
