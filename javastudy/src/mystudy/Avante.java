package mystudy;

public  class Avante extends CarClass {
	
	@Override
	public void start() {
		System.out.println("Avante 시동을 켭니다");
	}
	@Override
		public void dirve() {
			System.out.println("Avante 달립니다");
		}
	@Override
		public void stop() {
			System.out.println("Avante 멈춥니다");
		}
	@Override
		public void turnoff() {
			System.out.println("Avante 시동을 끕니다");
		}

}
