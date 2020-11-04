package team_project;


import java.awt.Color;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RentCarModel {
	int rentno;
	int carid;
	String explain_front;
	String explain_left;
	String explain_right;
	String explain_back;
	String repair_required;
	
	ResultSet rs;
	DefaultTableModel model;
	
	public ArrayList<Object[]> select(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		try {
			String sql = "SELECT * FROM Car_Rent";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("hhhhhhh");
			
			Object column[] = {"RENT NO", "CAR ID", "LICENSE NO", "COMP ID", "RENT DATE", "RENTAL PERIOD", "CHARGE", "PAYMENT DEADLINE", "BILL HISTORY", "BILL HISTORY COST"};
			arr.add(column);
			
			
        
            while(rs.next()) {
               Object[] data = {
                     rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getDate(8),rs.getString(9), rs.getInt(10)
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
        	String sql = "INSERT INTO Car_Check VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rentno);
			pstmt.setInt(2, carid);
			pstmt.setString(3, explain_front);
			pstmt.setString(4, explain_left);
			pstmt.setString(5,  explain_right);
			pstmt.setString(6,  explain_back);
			pstmt.setString(7,  repair_required);
			
			JOptionPane.showMessageDialog(null, "반환정보를 점검내역에 저장하였습니다.");
	         
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void setRentno(int rentno) {
		this.rentno = rentno;
	}
	
	public int getRentno() {
		return rentno;
	}
	
	public void setCarid(int carid) {
		this.carid = carid;
	}
	
	public int getCarid() {
		return carid;
	}

	public void setExplain_front(String explain_front) {
		this.explain_front = explain_front;
	}
	
	public String getExplain_front() {
		return explain_front;
	}
	
	public void setExplain_left(String explain_left) {
		this.explain_left = explain_left;
	}
	
	public String getExplain_left() {
		return explain_left;
	}
	
	public void setExplain_right(String explain_right) {
		this.explain_right = explain_right;
	}
	
	public String getExplain_right() {
		return explain_right;
	}
	
	public void setExplain_back(String explain_back) {
		this.explain_back = explain_back;
	}
	
	public String getExplain_back() {
		return explain_back;
	}
	
	public void setRepair_required(String repair_required) {
		this.repair_required = repair_required;
	}
	
	public String getRepair_required() {
		return repair_required;
	}
}