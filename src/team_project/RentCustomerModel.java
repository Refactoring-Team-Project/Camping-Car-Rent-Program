package team_project;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RentCustomerModel {
	int license_no;
	String name;
	String address;
	String phone;
	String email;

	ResultSet rs;
	DefaultTableModel model;

	public ArrayList<Object[]> select(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		try {
			String sql = "SELECT * FROM Rent_Customer";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			Object column[] = { "LICENSE_NO", "NAME", "ADDRESS", "PHONE", "EMAIL" };
			arr.add(column);

			while (rs.next()) {
				Object[] data = { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5) };
				arr.add(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return arr;
	}

	public void insert(Connection conn) {
		try {
			String sql = "INSERT INTO Rent_Customer(license_no, name, address, phone, email) VALUES(?, ?, ?, ?, ?)";

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, license_no);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "추가되었습니다.");

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	public void delete(Connection conn, Object object) {
		try {
			String sql = "DELETE FROM Rent_Customer WHERE license_no = " + object.toString() + ";";

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
			String sql = "UPDATE Rent_Customer SET license_no=?,name=?,address=?,phone=?,email=? WHERE license_no = "
					+ object.toString() + ";";

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, license_no);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "수정되었습니다.");

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	public int getLicense_no() {
		return license_no;
	}

	public void setLicense_no(int license_no) {
		this.license_no = license_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
