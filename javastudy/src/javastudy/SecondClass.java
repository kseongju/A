package javastudy;

public class SecondClass {

	public static void main(String[] args) {
		
		//������ ���� ����
		int a;
		//���� �ʱ�ȭ
		a = 3; //3�� a�� ������ �Ѵ�.
		a = 5;
		
		System.out.println(a);
		
		final int B; //������ ���� B�� ����
		B = 10;
		//B = 30;
		
		System.out.println(B);
		
		
		int c = 200;
		byte d = (byte)c;
		System.out.println(d);
		
		//���� a�� ���� 10�̰� b�� ���� 20�̴�.
		// ��� �����ڸ� ����ؼ� �ΰ��� ���� ���Ͻÿ�.
		
		int b;
		
		a = 10;
		b = 20;
		
		int sum1;
		
		sum1 = a+b;
		
		System.out.println(sum1);
		
		//���� c�� ���� 25�̰� ���� d�� ���� 5�̴�
		//���� d�� ������ c�� ������ �� �������� ���Ͻÿ�
		
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
