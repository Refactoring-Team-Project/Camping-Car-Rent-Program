package team_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RepairListModel {
	int repairno;
	int carid;
	int shopid;
	int compid;
	int license_no;
	String repairdetails;
	String repairdate;
	int repaircost;
	String paymentdeadline;
	String repairhistory;

	String selected_reparino = null;
	String selected_carid = null;
	String selected_shopid = null;
	String selected_compid = null;
	String selected_license_no = null;

	ResultSet rs;
	DefaultTableModel model;

	public ArrayList<Object[]> select(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		try {
			String sql = "SELECT * FROM Repair_List";

			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			Object column[] = { "REPAIR NO", "CAR ID", "SHOP ID", "COMP ID", "LICENSE NO", "REPAIR DETAILS",
					"REPAIR DATE", "REPAIR COST", "PAYMENT DEADLINE", "REPAIRHISTORY" };
			arr.add(column);

			while (rs.next()) {
				Object[] data = { rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6),
						rs.getDate(7), rs.getInt(8), rs.getDate(9), rs.getString(10) };
				arr.add(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return arr;
	}

	public void insert(Connection conn) {
		try {
			String sql = "INSERT INTO Repair_List(repairno, carid, shopid, compid, license_no, repairdetails, repairdate, repaircost, paymentdeadline, repairhistory) VALUES (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, repairno);
			pstmt.setInt(2, carid);
			pstmt.setInt(3, shopid);
			pstmt.setInt(4, compid);
			pstmt.setInt(5, license_no);
			pstmt.setString(6, repairdetails);
			pstmt.setString(7, repairdate);
			pstmt.setInt(8, repaircost);
			pstmt.setString(9, paymentdeadline);
			pstmt.setString(10, repairhistory);

			JOptionPane.showMessageDialog(null, "추가되었습니다.");

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	public void delete(Connection conn, Object object) {
		try {
			String sql = "DELETE FROM Repair_List WHERE repairno = " + object.toString() + ";";

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);

			// System.out.println(sql);

			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "삭제되었습니다.");

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	public void update(Connection conn, Object object) {
		try {
			String sql = "UPDATE Repair_List SET repairno=?,carid=?,shopid=?,compid=?,license_no=?,repairdetails=?,repairdate=?,repaircost=?,paymentdeadline=?,repairhistory=? WHERE repairno = "
					+ object.toString() + ";";

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, repairno);
			pstmt.setInt(2, carid);
			pstmt.setInt(3, shopid);
			pstmt.setInt(4, compid);
			pstmt.setInt(5, license_no);
			pstmt.setString(6, repairdetails);
			pstmt.setString(7, repairdate);
			pstmt.setInt(8, repaircost);
			pstmt.setString(9, paymentdeadline);
			pstmt.setString(10, repairhistory);

			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "수정되었습니다.");

		} catch (SQLException e1) {

		}
	}

	public int getRepairno() {
		return repairno;
	}

	public void setRepairno(int repairno) {
		this.repairno = repairno;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public int getCompid() {
		return compid;
	}

	public void setCompid(int compid) {
		this.compid = compid;
	}

	public int getLicense_no() {
		return license_no;
	}

	public void setLicense_no(int license_no) {
		this.license_no = license_no;
	}

	public String getRepairdetails() {
		return repairdetails;
	}

	public void setRepairdetails(String repairdetails) {
		this.repairdetails = repairdetails;
	}

	public String getRepairdate() {
		return repairdate;
	}

	public void setRepairdate(String repairdate) {
		this.repairdate = repairdate;
	}

	public int getRepaircost() {
		return repaircost;
	}

	public void setRepaircost(int repaircost) {
		this.repaircost = repaircost;
	}

	public String getPaymentdeadline() {
		return paymentdeadline;
	}

	public void setPaymentdeadline(String paymentdeadline) {
		this.paymentdeadline = paymentdeadline;
	}

	public String getRepairhistory() {
		return repairhistory;
	}

	public void setRepairhistory(String repairhistory) {
		this.repairhistory = repairhistory;
	}

	public String getSelectedRepairno() {
		return selected_reparino;
	}

	public String getSelectedCarid() {
		return selected_carid;
	}

	public String getSelectedShopid() {
		return selected_shopid;
	}

	public String getSelectedCompid() {
		return selected_compid;
	}

	public String getSelectedLicense_no() {
		return selected_license_no;
	}
}
