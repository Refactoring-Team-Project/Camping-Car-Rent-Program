package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionModel extends Model {

	 Connection connection;
	 
	  String Driver = "com.mysql.cj.jdbc.Driver";
	  String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	  String userId = "madang";
	  String password = "madang";
	  
	  public DatabaseConnectionModel() {
		  connectionDB();
	  }
	  
	  public void connectionDB() {
	      try { // 드라이버 로드
	         Class.forName(Driver);
	         System.out.println("드라이버 로드 완료");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }

	      try { // 데이터베이스 연결
	         connection = DriverManager.getConnection(url, userId, password);
	         System.out.println("데이터베이스 연결 완료");
	      } catch (SQLException e1) {
	         e1.printStackTrace();
	      }
	  }
	      
	  public void closeDB() {
	      try { // 데이터베이스 연결 종료
	         if (connection != null) {
	            System.out.println("데이터베이스 연결 종료");
	         }
	      } catch (Exception e3) {
	         e3.printStackTrace();
	      }
	   }
	  
	  public Connection getConnection() {
	      return connection;
	   }


}
