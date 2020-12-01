package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionModel extends Model {

	 Connection conn;
	 
	  String Driver = "com.mysql.cj.jdbc.Driver";
	  String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	  String userid = "madang";
	  String pwd = "madang";
	  
	  public DatabaseConnectionModel() {
		  connDB();
	  }
	  
	  public void connDB() {
	      try { // 드라이버 로드
	         Class.forName(Driver);
	         System.out.println("드라이버 로드 완료");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }

	      try { // 데이터베이스 연결
	         conn = DriverManager.getConnection(url, userid, pwd);
	         System.out.println("데이터베이스 연결 완료");
	      } catch (SQLException e1) {
	         e1.printStackTrace();
	      }
	  }
	      
	  public void closeDB() {
	      try { // 데이터베이스 연결 종료
	         if (conn != null) {
	            System.out.println("데이터베이스 연결 종료");
	         }
	      } catch (Exception e3) {
	         e3.printStackTrace();
	      }
	   }
	  
	  public Connection getConn() {
	      return conn;
	   }


}
