package javastudy;

public class StartClass {
	
	public int aa() {
		
		int c = 0;
		return c;
	}

	public static void main(String[] args) {
		

		
		//ClassFirst cs = new ClassFirst(); //��ü���� 
		//System.out.println("�ּҰ���"+cs);
		//int eng = cs.eng;
	//	int math = cs.math;
	//	String name = cs.name;
	//	int sum = cs.add(eng, math);
		
	//	System.out.println(name+"�� �������� :" + eng + " �������� :" + math);
	//	System.out.println("����"+ sum);
		
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
		
		//1. ��ü����
	//	StaticClass sc = new StaticClass();
		//������ ���� Ȯ���غ����� sc���� ����غ���.
	//	int k =sc.x;
	//	System.out.println(k);
	//	int z = StaticClass.y; //static�� Ŭ���� �̸�, ���� ���·� �θ���.
	//	System.out.println(z);
	//	int t =sc.y;
	//	System.out.println(t);
		
		//������ �迭�� 3���� �����. 0���� ����
		//int[] a = new int[3];
		//System.out.println(a); // �ּҰ��� ���´�. �迭�� �����Ǵ� ��ġ�� heap����
		
		
	//	a[0] = 1;
    //  a[1] = 2;
    //	a[2] = 3;
		
	//	int[] a = new int[] {1,2,3};
//		int[] a = {1,2,3,4,5,6,7,8,9,10};
	
//	    System.out.println("a�� ù��° �氪��"+a[0]);
//		System.out.println("a�� �ι�° �氪��"+a[1]);
//		System.out.println("a�� ����° �氪��"+a[2]);
		
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
		
		//1���� 10������ ���� �迭�� Ȱ���Ͽ� ���� ���Ͻÿ�.
		
	//	int[] a= {1,2,3,4,5,6,7,8,9,10};
	//	int sum=0;
	//	for(int i=0; i<a.length; i++) {
	//		sum+=a[i];
	//	}System.out.println("����"+ sum);
		
		//�迭�� Ȱ���ؼ� 1���� 100������ ���� ����Ͻÿ�
		
	//	int[] array = new int[100];
	//	int sum2=0;
		
	//	for(int i=0; i<array.length; i++) {
	//		array[i]= i+1;
	//		sum2 = sum2+array[i];
	//	}System.out.println("����"+sum2);

		
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
		customerLee.setCustomerName("�̼���");
		customerLee.bonusPoint = 1000;
		
		System.out.println(customerLee.showCustomerInfo());
		
		Customer customerkim = new VIPCustomer(10020, "������", 12345);
		customerkim.bonusPoint=1000;
		
		System.out.println(customerkim.showCustomerInfo());
		System.out.println("===== �������� ���ʽ� ����Ʈ ���=====");
		
		int price = 10000;
		int leePrice = customerLee.calcPrice(price);
		int kimPrice = customerkim.calcPrice(price);
		
		System.out.println(customerLee.getCustomerName() + " ���� " + leePrice + "�� �����ϼ̽��ϴ�.");
		System.out.println(customerLee.showCustomerInfo());
		System.out.println(customerkim.getCustomerName() + " ���� " + kimPrice + "�� �����ϼ̽��ϴ�.");
		
	}
	
	 
}
