package javastudy;

public class InheriSonClass extends InheriClass{

	public InheriSonClass(int speed) {
		super(speed);
		
	}
	//����Ŭ������ �Ӽ��� ����� �����޾Ƽ� Ȱ���� �� �ִ� -���
	
	
	//Override ������
		public void move() {
			super.move();
		System.out.println("�������޸���");
	}
	
	public void move(int speed) {
		System.out.println(speed+"km/s�� �����̴�");
	}
	
	
	
}
