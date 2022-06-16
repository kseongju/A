package mystudy;

public class Company {

	private static Company cc = new Company();
	private Company() {}
	
	public static Company getCC() {
		if(cc==null) {
			cc=new Company();
		}
		return cc;
	}
	
}
