package team_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CampingCarModel {
	int carid;
	String carname;
	int carno;
	int seat;
	String manufacturer;
	int manu_year;
	int drivingdistance;
	int rentcost;
	int compid;
	String registdate;

	ResultSet rs;
	DefaultTableModel model;
	
	public ArrayList<Object[]> select(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		
		try {
			String sql = "SELECT * FROM Camping_Car";
            
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
            arr.add(column);
            
            while (rs.next()) {
            	Object[] data = {rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getDate(10)};
            	arr.add(data);
            }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return arr;
	}

	public void insert(Connection conn) {
		try {
			String sql = "INSERT INTO Camping_Car VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
			PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, carid);
            pstmt.setString(2, carname);
            pstmt.setInt(3, carno);
            pstmt.setInt(4, seat);
            pstmt.setString(5,  manufacturer);
            pstmt.setInt(6,  manu_year);
            pstmt.setInt(7,  drivingdistance);
            pstmt.setInt(8,  rentcost);
            pstmt.setInt(9,  compid);
            pstmt.setString(10,  registdate);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "추가되었습니다.");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	public void delete(Connection conn, Object object) {
		try {
			String sql = "DELETE FROM Camping_Car WHERE carid = " + object.toString() + ";";
            
			PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
         
            JOptionPane.showMessageDialog(null, "삭제되었습니다.");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	public void update(Connection conn, Object object) {
		try {
			String sql ="UPDATE Camping_Car SET carid=?,carname=?,carno=?,seat=?,manufacturer=?,manu_year=?,drivingdistance=?,rentcost=?,compid=?,registdate=? WHERE carid = " + object.toString() + ";";
			PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, carid);
            pstmt.setString(2, carname);
            pstmt.setInt(3, carno);
            pstmt.setInt(4, seat);
            pstmt.setString(5,  manufacturer);
            pstmt.setInt(6,  manu_year);
            pstmt.setInt(7,  drivingdistance);
            pstmt.setInt(8,  rentcost);
            pstmt.setInt(9,  compid);
            pstmt.setString(10,  registdate);
            pstmt.executeUpdate();
         
            JOptionPane.showMessageDialog(null, "변경되었습니다.");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	

	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public int getCarno() {
		return carno;
	}
	public void setCarno(int carno) {
		this.carno = carno;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public String getManufacurer() {
		return manufacturer;
	}
	public void setManufacurer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getManu_year() {
		return manu_year;
	}
	public void setManu_year(int manu_year) {
		this.manu_year = manu_year;
	}
	public int getDrivingdistance() {
		return drivingdistance;
	}
	public void setDrivingdistance(int drivingdistance) {
		this.drivingdistance = drivingdistance;
	}
	public int getRentcost() {
		return rentcost;
	}
	public void setRentcost(int rentcost) {
		this.rentcost = rentcost;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getRegistdate() {
		return registdate;
	}
	public void setRegistdate(String registdate) {
		this.registdate = registdate;
	}
	
	
}
