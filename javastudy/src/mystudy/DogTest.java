package mystudy;

public class DogTest {
	public static void main(String[] args) {
		Dog[] dog = new Dog[5];
		
		
		for(int i =0; i<dog.length; i++) {
			System.out.println(dog[i]);
		}
		
		for(Dog d : dog) {
			System.out.println(d.getName());
		}
		
	}
}
