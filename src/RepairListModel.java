

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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

	public void insert(Connection conn) {
		try {
			String sql = "INSERT INTO Repair_List(repairno, carid, shopid, compid, license_no, repairdetails, repairdate, repaircost, paymentdeadline, repairhistory) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

			System.out.println(sql);
			JOptionPane.showMessageDialog(null, "요청되었습니다.");

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
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

}
