package javastudy;

import java.util.ArrayList;

public class Student {
	
	
	//멤버변수는 초기화 시키지 않아도 생성시 자동 초기화된다.
	int studentID;
	String studentName;
	ArrayList<Subject> subjectlist; //subject타입으로 생성된 객체를 ArrayList에 담는다.
	
	//기본 생성자
	public Student() {
		
	}
	
	//매개변수가 있는 생성자
	public Student(int studentID, String studentName) {
		this.studentID = studentID;
		this.studentName = studentName;
		subjectlist = new ArrayList<Subject>();
	}
	
	//addSubject를 호출해서 과목이름과 점수를 담아서 넘기면 그 값을 subject 객체안에 담는다.
	public void addSubject(String name, int score) {
		Subject subject = new Subject();
		subject.setName(name);
		subject.setScorePoint(score);
		subjectlist.add(subject); //subject 객체를 ArrayList에 담는다.
	}
	
	public void showinfo() {
		
		//ArrayList에 담긴 데이터를 모두 꺼낸다.
		for(Subject student : subjectlist) {
			System.out.println(studentName);
			System.out.println(student.getName());
			System.out.println(student.getScorePoint());
		}
		
		
		
	}
	
	
	
}
