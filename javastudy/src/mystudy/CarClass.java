package mystudy;

public abstract class CarClass {
	
	
	public abstract void start();
	
	public abstract void dirve();
	
	public abstract void stop();
	
	public abstract void turnoff();
	
	public void washCar() {
		System.out.println("������ �մϴ�");
	}
	
	
	final public void run() {
		start();
		dirve();
		stop();
		turnoff();
		washCar();
	}
}
