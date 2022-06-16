package mystudy;

public class Grandeur extends CarClass{

	@Override
	public void start() {
		System.out.println("Grandeur 시동을 켭니다");
	}
	@Override
		public void dirve() {
			System.out.println("Grandeur 달립니다");
		}
	@Override
		public void stop() {
			System.out.println("Grandeur 멈춥니다");
		}
	@Override
		public void turnoff() {
			System.out.println("Grandeur 시동을 끕니다");
		}
}
