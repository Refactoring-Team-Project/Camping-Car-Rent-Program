package team_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CampingCompanyModel {
	int compid;
	String compname;
	String address;
	String phone;
	String manager_name;
	String manager_email;
	
	ResultSet rs; 
	DefaultTableModel model;
	
	public ArrayList<Object[]> select(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		try {
		       String sql = "SELECT * FROM Camping_Company";
		       Statement stmt = conn.createStatement();
		       rs = stmt.executeQuery(sql);	       
		       
		       Object column[] = {"COMPID", "COMPNAME", "ADDRESS", "PHONE", "MANAGER NAME", "MANAGER EMAIL"};
		       arr.add(column);
     
		       while(rs.next()) {
		          Object[] data = {
		                rs.getInt(1),
		                rs.getString(2),
		                rs.getString(3),
		                rs.getString(4),
		                rs.getString(5),
		                rs.getString(6)
		                };
		          arr.add(data);
		       }
		       
		    } catch (SQLException e1) {
		       e1.printStackTrace();
		    }
		return arr;

	}
	
	public void insert(Connection conn) {
		try {
            String sql = "INSERT INTO Camping_Company(compid, compname, address, phone, manager_name, manager_email) VALUES(?, ?, ?, ?, ?, ?)";
           
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);    
            pstmt.setInt(1, compid);
            pstmt.setString(2, compname);
            pstmt.setString(3, address);
            pstmt.setString(4, phone);
            pstmt.setString(5, manager_name);
            pstmt.setString(6, manager_email);
            pstmt.executeUpdate();
         
            JOptionPane.showMessageDialog(null, "추가되었습니다.");
            
            
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getManager_email() {
		return manager_email;
	}
	public void setManager_email(String manager_mail) {
		this.manager_email = manager_mail;
	}
}
