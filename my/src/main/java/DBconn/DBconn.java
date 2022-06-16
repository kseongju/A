package DBconn;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconn {
	//접속정보
    String url="jdbc:mysql://127.0.0.1:3306/mysql?serverTimezone=UTC&characterEncoding=UTF-8";
    String user="root";
    String password="1234";
    
    public Connection getConnection() {
    	Connection conn = null;
    try {
    //드라이버
    Class.forName("com.mysql.cj.jdbc.Driver");
    //conn에 접속정보를 담아 반환
   conn = DriverManager.getConnection(url, user, password);
    }catch(Exception e) {
    	e.printStackTrace();
    }
    	return conn;
    }

}
