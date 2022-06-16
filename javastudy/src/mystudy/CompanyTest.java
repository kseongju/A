package mystudy;

public class CompanyTest {

	
	public static void main(String[] args) {
		Company myCompany1= Company.getCC();
		Company myCompany2= Company.getCC();
		System.out.println(myCompany1==myCompany2);
	}
}
