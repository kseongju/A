package mystudy;

public class CarFactory {
	
	private static CarFactory FT= new CarFactory(); 
	private CarFactory() {}
	
	public static CarFactory getFT() {
		if(FT==null) {
			FT=new CarFactory();
		}
		return FT;
	}
	
}
