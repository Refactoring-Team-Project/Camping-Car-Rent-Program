package model;

import common.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

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

	ResultSet rs;

	public ArrayList<Object[]> select(Connection conn) {

		String sql = "SELECT * FROM Repair_List";

		return DbUtil.getRows(conn, sql);

	}

	public void insert(Connection conn) {
		String sql = "INSERT INTO Repair_List(repairno, carid, shopid, compid, license_no, repairdetails, repairdate, repaircost, paymentdeadline, repairhistory) VALUES (?,?,?,?,?,?,?,?,?,?)";

		String[] types = {"int","int", "int", "int", "int", "string", "string" ,"int", "string", "string"};
		Object[] values = {repairno, carid, shopid, compid, license_no, repairdetails, repairdate, repaircost, paymentdeadline, repairhistory};

		DbUtil.execute(conn, sql, types, values);

	}

	public void delete(Connection conn, Object object) {

		String sql = "DELETE FROM Repair_List WHERE repairno = " + object.toString() + ";";
		DbUtil.execute(conn, sql, null, null);

	}

	public void update(Connection conn, Object object) {

		String sql = "UPDATE Repair_List SET repairno=?,carid=?,shopid=?,compid=?,license_no=?,repairdetails=?,repairdate=?,repaircost=?,paymentdeadline=?,repairhistory=? WHERE repairno = "
				+ object.toString() + ";";

		String[] types = {"int","int", "int", "int", "int", "string", "string" ,"int", "string", "string"};
		Object[] values = {repairno, carid, shopid, compid, license_no, repairdetails, repairdate, repaircost, paymentdeadline, repairhistory};

		DbUtil.execute(conn, sql, types, values);


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
