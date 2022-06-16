package javastudy;

public class StartClass {
	
	public int aa() {
		
		int c = 0;
		return c;
	}

	public static void main(String[] args) {
		

		
		//ClassFirst cs = new ClassFirst(); //객체생성 
		//System.out.println("주소값은"+cs);
		//int eng = cs.eng;
	//	int math = cs.math;
	//	String name = cs.name;
	//	int sum = cs.add(eng, math);
		
	//	System.out.println(name+"의 영어점수 :" + eng + " 수학점수 :" + math);
	//	System.out.println("합은"+ sum);
		
	//	ConstructorClass cc = new ConstructorClass();
	//	int aa = cc.a;
	//	System.out.println(aa);
	//	String bb = cc.b;
	//	System.out.println(bb);
		
		
	//	PuClass p = new PuClass();
		//int x = p.a;
		//System.out.println(x);
		//int y = p.b;
		//System.out.println(y);
		//p.test();
	//	int x = p.getA();
	//	System.out.println(x);
	//	p.setA(100);
		
		//1. 객체생성
	//	StaticClass sc = new StaticClass();
		//생선된 것을 확인해볼려면 sc값을 출력해본다.
	//	int k =sc.x;
	//	System.out.println(k);
	//	int z = StaticClass.y; //static은 클래스 이름, 변수 형태롤 부른다.
	//	System.out.println(z);
	//	int t =sc.y;
	//	System.out.println(t);
		
		//숫자형 배열방 3개를 만든다. 0부터 시작
		//int[] a = new int[3];
		//System.out.println(a); // 주소값이 나온다. 배열이 생성되는 위치는 heap영역
		
		
	//	a[0] = 1;
    //  a[1] = 2;
    //	a[2] = 3;
		
	//	int[] a = new int[] {1,2,3};
//		int[] a = {1,2,3,4,5,6,7,8,9,10};
	
//	    System.out.println("a의 첫번째 방값은"+a[0]);
//		System.out.println("a의 두번째 방값은"+a[1]);
//		System.out.println("a의 세번째 방값은"+a[2]);
		
//		for(int i=0; i<a.length; i++) {
//			System.out.println(a[i]);
//   	}
		
	//	double[] date = new double[5];
	//	int size = 0;
		
	//	date[0] = 10.0; size++;
	//	date[1] = 20.0; size++;
	//	date[2] = 30.0; size++;
		
	//	for(int i =0; i < size; i++) {
	//		System.out.println(date[i]);
	//	}System.out.println(size);
		
		//1에서 10까지의 수를 배열을 활용하여 합을 구하시오.
		
	//	int[] a= {1,2,3,4,5,6,7,8,9,10};
	//	int sum=0;
	//	for(int i=0; i<a.length; i++) {
	//		sum+=a[i];
	//	}System.out.println("합은"+ sum);
		
		//배열을 활용해서 1에서 100까지의 합을 출력하시오
		
	//	int[] array = new int[100];
	//	int sum2=0;
		
	//	for(int i=0; i<array.length; i++) {
	//		array[i]= i+1;
	//		sum2 = sum2+array[i];
	//	}System.out.println("값은"+sum2);

		
	//	int[][] arr= {{1,2,3},{4,5,6}};
		 
	//	 for(int i =0; i<arr.length; i++) {
	//		 for(int j = 0; j<arr[i].length; j++) {
	//			 System.out.println(arr[i][j]);
	//		 }
	//		 System.out.println();
	//	 }
		 
	//	InheriSonClass is = new InheriSonClass(20);
	//		is.move();
	//		is.move(30);
		
		Customer customerLee = new Customer();
		customerLee.setCustomerID(10010);
		customerLee.setCustomerName("이순신");
		customerLee.bonusPoint = 1000;
		
		System.out.println(customerLee.showCustomerInfo());
		
		Customer customerkim = new VIPCustomer(10020, "김유신", 12345);
		customerkim.bonusPoint=1000;
		
		System.out.println(customerkim.showCustomerInfo());
		System.out.println("===== 할인율과 보너스 포인트 계산=====");
		
		int price = 10000;
		int leePrice = customerLee.calcPrice(price);
		int kimPrice = customerkim.calcPrice(price);
		
		System.out.println(customerLee.getCustomerName() + " 님이 " + leePrice + "원 지불하셨습니다.");
		System.out.println(customerLee.showCustomerInfo());
		System.out.println(customerkim.getCustomerName() + " 님이 " + kimPrice + "원 지불하셨습니다.");
		
	}
	
	 
}
