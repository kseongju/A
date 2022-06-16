package mystudy;

import java.util.ArrayList;

public class CarTest {

	public static void main(String[] args) {

		ArrayList<CarClass> carList = new ArrayList<CarClass>();
		
		carList.add(new Sonata());
		carList.add(new Grandeur());
		carList.add(new Avante());
		carList.add(new Genesis());
		
		for(CarClass car : carList) {
			car.run();
			System.out.println("======================");
		}
	}
}
