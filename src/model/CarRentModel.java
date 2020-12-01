package model;

import common.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CarRentModel extends Model {
	int rentno;
	int carid;
	int license_no;
	int compid;
	String rentdate;
	int rentalperiod;
	int charge;
	String paymentdeadline;
	String billhistory;
	String billhistorycost;
	
	ResultSet rs;
	public ArrayList<Object[]> select(Connection conn) {
		String sql = "SELECT * FROM Car_Rent";
		return DbUtil.getRows(conn, sql);
	}


	public void insert(Connection conn) {
		String sql = "INSERT INTO Car_Rent(rentno, carid, license_no, compid, rentdate, rentalperiod, charge, paymentdeadline, billhistory, billhistorycost) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		String[] types = { "int", "int", "int", "int", "string", "int", "int", "string", "string", "string" };
		Object[] values = { rentno, carid, license_no, compid, rentdate, rentalperiod, charge, paymentdeadline, billhistory,
				billhistorycost };

		DbUtil.execute(conn, sql, types, values);
	}
	
	public ArrayList<Object[]> search1(Connection conn, String day) {
		if(!day.isBlank() && (day.matches("\\d{4}-\\d{2}-\\d{2}"))) {
			  String sql = "select *\r\n" +
					"from Car_Rent r\r\n" +
					"where r.rentno not in (select c.rentno from Car_Check c) and date_format('"+day+"', '%Y-%m-%d') > date_add(r.rentdate, interval rentalperiod day);";

			  return DbUtil.getRows(conn, sql);
		} else JOptionPane.showMessageDialog(null, "날짜형식(yyyy-mm-dd)를 맞춰 입력해주십시오");
		return new ArrayList<Object[]>();
	}
	
	public int getRentno() {
		return rentno;
	}
	public void setRentno(int rentno) {
		this.rentno = rentno;
	}
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public int getLicense_no() {
		return license_no;
	}
	public void setLicense_no(int license_no) {
		this.license_no = license_no;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getRentdate() {
		return rentdate;
	}
	public void setRentdate(String rentdata) {
		this.rentdate = rentdata;
	}
	public int getRentalperiod() {
		return rentalperiod;
	}
	public void setRentalperiod(int rentalperiod) {
		this.rentalperiod = rentalperiod;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public String getPaymentdeadline() {
		return paymentdeadline;
	}
	public void setPaymentdeadline(String paymentdeadline) {
		this.paymentdeadline = paymentdeadline;
	}
	public String getBillhistory() {
		return billhistory;
	}
	public void setBillhistory(String billhistory) {
		this.billhistory = billhistory;
	}
	public String getBillhistorycost() {
		return billhistorycost;
	}
	public void setBillhistorycost(String billhistorycost) {
		this.billhistorycost = billhistorycost;
	}
}
