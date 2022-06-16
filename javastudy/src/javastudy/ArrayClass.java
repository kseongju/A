package javastudy;



public class ArrayClass {

		
	public static void main(String[] args) {
		
//		ArrayList<String> alist = new ArrayList<String>();
//		alist.add(new String("안녕하세요"));
//		alist.add(new String("반갑습니다"));
//		alist.add(new String("수고하세요"));
		
		
//		for(int i = 0; i<alist.size(); i++) {
//			String str = alist.get(i);
//			System.out.println(str);
//		}
		
//		for(String str: alist) {
//			System.out.println(str);
//		}
		
	

	Student studentLee = new Student(1002,"Lee");
	studentLee.addSubject("국어", 100);   //ArrayList<subject>가 생성되어있는 메소드
	studentLee.addSubject("수학", 100);
	studentLee.addSubject("영어", 75);
	
	
	
	
	
	
	
	
	
	

	studentLee.showinfo();
		
	}
}
