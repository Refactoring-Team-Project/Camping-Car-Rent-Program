package team_project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RepairShopModel {
	int shopid;
	String shopname;
	String address;
	String phone;
	String manager_name;
	String manager_email;

	ResultSet rs;

	public ArrayList<Object[]> select(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		try {
			String sql = "SELECT * FROM Repairshop";

			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			Object column[] = { "SHOP ID", "SHOP NAME", "ADDRESS", "PHONE", "MANAGER NAME", "MANAGER EMAIL" };
			arr.add(column);

			while (rs.next()) {
				Object[] data = { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6) };
				arr.add(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return arr;
	}

	public void insert(Connection conn) {
		try {
			String sql = "INSERT INTO Repairshop(shopid, shopname, address, phone, manager_name, manager_email) VALUES(?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, shopid);
			pstmt.setString(2, shopname);
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

	public void delete(Connection conn, Object object) {
		try {
			String sql = "DELETE FROM RepairShop WHERE shopid = " + object.toString() + ";";

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
			String sql = "UPDATE Repairshop SET shopid=?,shopname=?,address=?,phone=?,manager_name=?,manager_email=? WHERE shopid = "
					+ object.toString() + ";";

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, shopid);
			pstmt.setString(2, shopname);
			pstmt.setString(3, address);
			pstmt.setString(4, phone);
			pstmt.setString(5, manager_name);
			pstmt.setString(6, manager_email);
			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "수정되었습니다.");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	public ArrayList<Object[]> search4(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		try {
			String sql = "select rs.shopid, rs.shopname, coalesce(sum(rl.repaircost), 0) as income\r\n"
					+ "from Repairshop rs\r\n" + "left join Repair_List rl\r\n" + "on rs.shopid = rl.shopid\r\n"
					+ "group by rs.shopid, rs.shopname\r\n" + "order by income desc;";

			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			Object column[] = { "SHOP ID", "SHOP NAME", "INCOME" };
			arr.add(column);

			while (rs.next()) {
				Object[] data = { rs.getInt(1), rs.getString(2), rs.getInt(3) };
				arr.add(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return arr;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
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

	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}

}