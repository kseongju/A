package javastudy;

public interface InterClass {
	
	//�̿ϼ��� �޼��常 ��밡�� 
	public void fly(); //abstract ������

	//���
	int a = 1;//final ������
	
	public void run();
	
	//����Ʈ�޼ҵ�
	default void move() {
		System.out.println("�̵��ϴ�");
		gogo();
	}
	
	//�����޼ҵ�
	public static void dance() {
		System.out.println("���� �ߴ�");
	}
	
	private void gogo() {
		System.out.println("����");
	}
	
	
}
