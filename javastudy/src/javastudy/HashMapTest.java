package javastudy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HashMapTest {

	public static void main(String[] args) {
		
		//HashMap 사용법
		//key값과 value값으로 이루어짐
		
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(1, "홍길동");
		hm.put(2, "이순신");
		hm.put(3, "강감찬");
		hm.put(4, "김유신");
		hm.put(5, "이기순");
		
		String firstname = hm.get(1);
		System.out.println(firstname);
		
		
		//내부반복자	
		Iterator ir = hm.keySet().iterator();
		while(ir.hasNext()) { //다음값이 존재한다면 t u r e
			int key = (int) ir.next(); //다음 키 값이 존재하는지 존재하면 리턴;
			String nm = hm.get(key);
			System.out.println("이름은"+ nm);
		}
		
		
		ArrayList<String> alist = new ArrayList<String>();
		
		alist.add("김종우");
		alist.add("황정아");
		alist.add("손영진");
		
		HashMap<String, Object> hm2 = new HashMap<String, Object>();
		hm2.put("이름", alist);
		
		//해시맵에 담긴 alist를 통해서 손영진이라는 데이터를 출력하는 코드를 작성하시오.
		
		ArrayList<String> alist2 = (ArrayList<String>) hm2.get("이름");
		String name =alist.get(2);
		System.out.println("name은"+name);
		
	
		
		
		
		
	}

}
