package javastudy;

class Animal{
	public void move() {
		System.out.println("������ �����Դϴ�.");
	}
}

class Human extends Animal{
	public void move() {
		System.out.println("����� �� �߷� �Ƚ��ϴ�.");
	}
}

class Tiger extends Animal{
	public void move() {
		System.out.println("ȣ���̰� �� �߷� �ݴϴ�.");
	}
}
	
class Eagle extends Animal{
	public void main() {
		System.out.println("�������� �ϴ��� ���ϴ�.");
	}
}

public class AnimalTest1 {
	
	public static void main(String[] args) {
		AnimalTest1 aTest = new AnimalTest1();
		aTest.moveAnimal(new Human());
		aTest.moveAnimal(new Tiger());
		aTest.moveAnimal(new Eagle());
		
		
		Animal ani = new Human();
		
		
		if (ani instanceof Human) {
		Human human = (Human)ani;
	
	System.out.println(human);
		}
		
		
		
	}

	public void moveAnimal(Animal anlmal) {
		anlmal.move();
	}

}