package javastudy;



public class ArrayClass {

		
	public static void main(String[] args) {
		
//		ArrayList<String> alist = new ArrayList<String>();
//		alist.add(new String("�ȳ��ϼ���"));
//		alist.add(new String("�ݰ����ϴ�"));
//		alist.add(new String("�����ϼ���"));
		
		
//		for(int i = 0; i<alist.size(); i++) {
//			String str = alist.get(i);
//			System.out.println(str);
//		}
		
//		for(String str: alist) {
//			System.out.println(str);
//		}
		
	

	Student studentLee = new Student(1002,"Lee");
	studentLee.addSubject("����", 100);   //ArrayList<subject>�� �����Ǿ��ִ� �޼ҵ�
	studentLee.addSubject("����", 100);
	studentLee.addSubject("����", 75);
	
	
	
	
	
	
	
	
	
	

	studentLee.showinfo();
		
	}
}
