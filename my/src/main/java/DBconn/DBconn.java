package DBconn;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconn {
	
	String url="jdbc:mysql://127.0.0.1:3306/mysql?serverTimezone=UTC&characterEncoding=UTF-8";
	String user="root";
	String password = "1234";		
    
    public Connection getConnection() {
    	Connection conn = null;
    try {
    //����̹�
    	Class.forName("com.mysql.cj.jdbc.Driver");
    //conn�� ���������� ��� ��ȯ
   conn = DriverManager.getConnection(url, user, password);
   System.out.println("����");
    }catch(Exception e) {
    	e.printStackTrace();
    	
    }
    	return conn;
    }

}
