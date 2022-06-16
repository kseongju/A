package javastudy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HashMapTest {

	public static void main(String[] args) {
		
		//HashMap ����
		//key���� value������ �̷����
		
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(1, "ȫ�浿");
		hm.put(2, "�̼���");
		hm.put(3, "������");
		hm.put(4, "������");
		hm.put(5, "�̱��");
		
		String firstname = hm.get(1);
		System.out.println(firstname);
		
		
		//���ιݺ���	
		Iterator ir = hm.keySet().iterator();
		while(ir.hasNext()) { //�������� �����Ѵٸ� t u r e
			int key = (int) ir.next(); //���� Ű ���� �����ϴ��� �����ϸ� ����;
			String nm = hm.get(key);
			System.out.println("�̸���"+ nm);
		}
		
		
		ArrayList<String> alist = new ArrayList<String>();
		
		alist.add("������");
		alist.add("Ȳ����");
		alist.add("�տ���");
		
		HashMap<String, Object> hm2 = new HashMap<String, Object>();
		hm2.put("�̸�", alist);
		
		//�ؽøʿ� ��� alist�� ���ؼ� �տ����̶�� �����͸� ����ϴ� �ڵ带 �ۼ��Ͻÿ�.
		
		ArrayList<String> alist2 = (ArrayList<String>) hm2.get("�̸�");
		String name =alist.get(2);
		System.out.println("name��"+name);
		
	
		
		
		
		
	}

}
