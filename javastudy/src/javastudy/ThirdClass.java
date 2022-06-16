package javastudy;

import java.util.Scanner;

public class ThirdClass {

	public static void main(String[] args) {
		
		//gender 변수값이 F이면 여성입니다라고 표현하고 그렇지 않으면 남성입니다라고 표현하시오
		
		/*char gender = 'F'; //String gender = "F";라고 써도 됨
				if(gender=='F') {
					System.out.println("여성입니다.");
				}
				else {
					System.out.println("남성입니다.");
				}
				
		String gender2 = "F"; //문자열을 비교할 때 equals사용
			if(gender2.equals("F")) { 
				System.out.println("여성입니다.");
			}
			else {
				System.out.println("남성입니다.");
			}
			
			int math =100;
			int age = 10;
			
			if(math >=90) {
				if(age==10); {
					System.out.println("매우우수");
				}     if(age==11); {
					System.out.println("그냥우수");
				}
				System.out.println("우수");
			}
			else if(math >=80) {
				System.out.println("보통");
			}
			else if(math >=70) {
				System.out.println("미흡");
			}
			else if(math >=60) {
				System.out.println("큰일");
			}
			else if(math <=60) {
				System.out.println("재시험");
			}*/
			
			//anner sc = new Scanner(System.in);
		//ing input = sc.nextLine();
			
			// (input.equals("안녕"));{
			//ystem.out.println("맞습니다.");
			//{
				//ystem.out.println("틀립니다.");
				//
			
//
			// 영어점수 eng가 90점이면 우수 80점이면 보통70점이 미흡 나머지는 재시험이라고 출력하시오.
			
			/*int eng = 80;
			
			switch(eng) {
			
			case 90:
					System.out.println("우수");
					break;
			case 80:
			        System.out.println("보통");
			        break;
			case 70:
					System.out.println("미흡");
					break;
			default :	
					System.out.println("재시험");
				
			}*/
			
			// 문자형 변수 medal에 금이면 매우 잘함 은이면 잘함 동이면 보통이라고 출력하는 구문을 작성하시오.
			
			/*char medal = '은';
			
			switch(medal) {
			
			case '금':
					System.out.println("매우 잘함");
					break;
			case '은':
				   System.out.println("잘함");
				   break;
			case '동':
				   System.out.println("보통");
				   break;
			default :
				   System.out.println("고생하셨습니다");
			}*/
			
			String medal = "은";
			
			if(medal.equals("금")) {
				System.out.println("매우잘함");
				
			}
			
			else if(medal.equals("은")) {
				System.out.println("잘함");
				
			}
			
			else if(medal.equals("동")) {
				System.out.println("보통");
				
			}
			
			else {
				System.out.println("고생하셨습니다");
				
			}
			
			
			
			
	}
	
}
