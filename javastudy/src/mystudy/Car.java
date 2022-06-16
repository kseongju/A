package mystudy;

public class Car {
	private static int serialNum=10000;
	public int CarNumber;
	public String Carname;
	
	public  Car() {
		serialNum++;
		CarNumber = serialNum;
	}

	public String getCarname() {
		return Carname;
	}
	
	public void setgetCarname(String name) {
		Carname = name;
	}
	
	public static int getSerialNum() {
		
		return serialNum;
	}
	public static void setSerialNum(int serialNum) {
		Car.serialNum = serialNum;
	}

	
}
