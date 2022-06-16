package javastudy;

import java.util.ArrayList;

public class Student {
	
	
	//��������� �ʱ�ȭ ��Ű�� �ʾƵ� ������ �ڵ� �ʱ�ȭ�ȴ�.
	int studentID;
	String studentName;
	ArrayList<Subject> subjectlist; //subjectŸ������ ������ ��ü�� ArrayList�� ��´�.
	
	//�⺻ ������
	public Student() {
		
	}
	
	//�Ű������� �ִ� ������
	public Student(int studentID, String studentName) {
		this.studentID = studentID;
		this.studentName = studentName;
		subjectlist = new ArrayList<Subject>();
	}
	
	//addSubject�� ȣ���ؼ� �����̸��� ������ ��Ƽ� �ѱ�� �� ���� subject ��ü�ȿ� ��´�.
	public void addSubject(String name, int score) {
		Subject subject = new Subject();
		subject.setName(name);
		subject.setScorePoint(score);
		subjectlist.add(subject); //subject ��ü�� ArrayList�� ��´�.
	}
	
	public void showinfo() {
		
		//ArrayList�� ��� �����͸� ��� ������.
		for(Subject student : subjectlist) {
			System.out.println(studentName);
			System.out.println(student.getName());
			System.out.println(student.getScorePoint());
		}
		
		
		
	}
	
	
	
}
