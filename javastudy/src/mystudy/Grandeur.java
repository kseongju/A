package mystudy;

public class Grandeur extends CarClass{

	@Override
	public void start() {
		System.out.println("Grandeur �õ��� �մϴ�");
	}
	@Override
		public void dirve() {
			System.out.println("Grandeur �޸��ϴ�");
		}
	@Override
		public void stop() {
			System.out.println("Grandeur ����ϴ�");
		}
	@Override
		public void turnoff() {
			System.out.println("Grandeur �õ��� ���ϴ�");
		}
}
