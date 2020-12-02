package model;

import common.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CarRentModel extends Model {
	int rentNo;
	int carId;
	int licenseNo;
	int companyId;
	String rentDate;
	int rentalPeriod;
	int charge;
	String paymentDeadline;
	String billHistory;
	String billHistoryCost;

	public ArrayList<Object[]> select(Connection conn) {
		String sqlQuery = "SELECT * FROM Car_Rent";
		return DbUtil.getRows(conn, sqlQuery);
	}


	public void insert(Connection connection) {
		String sqlQuery = "INSERT INTO Car_Rent(rentno, carid, license_no, compid, rentdate, rentalperiod, charge, paymentdeadline, billhistory, billhistorycost) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		String[] dataTypes = { "int", "int", "int", "int", "string", "int", "int", "string", "string", "string" };
		Object[] values = { rentNo, carId, licenseNo, companyId, rentDate, rentalPeriod, charge, paymentDeadline, billHistory,
				billHistoryCost };

		DbUtil.execute(connection, sqlQuery, dataTypes, values);
	}
	
	public ArrayList<Object[]> adminNoReturnCarSearch(Connection connection, String day) {
		if(!day.isBlank() && (day.matches("\\d{4}-\\d{2}-\\d{2}"))) {
			  String sqlQuery = "select *\r\n" +
					"from Car_Rent r\r\n" +
					"where r.rentno not in (select c.rentno from Car_Check c) and date_format('"+day+"', '%Y-%m-%d') > date_add(r.rentdate, interval rentalperiod day);";

			  return DbUtil.getRows(connection, sqlQuery);
		} else JOptionPane.showMessageDialog(null, "날짜형식(yyyy-mm-dd)를 맞춰 입력해주십시오");
		return new ArrayList<Object[]>();
	}
	
	public int getRentNo() {
		return rentNo;
	}
	public void setRentNo(int rentNo) {
		this.rentNo = rentNo;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(int licenseNo) {
		this.licenseNo = licenseNo;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getRentDate() {
		return rentDate;
	}
	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}
	public int getRentalPeriod() {
		return rentalPeriod;
	}
	public void setRentalPeriod(int rentalPeriod) {
		this.rentalPeriod = rentalPeriod;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public String getPaymentDeadline() {
		return paymentDeadline;
	}
	public void setPaymentDeadline(String paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}
	public String getBillHistory() {
		return billHistory;
	}
	public void setBillHistory(String billHistory) {
		this.billHistory = billHistory;
	}
	public String getBillHistoryCost() {
		return billHistoryCost;
	}
	public void setBillHistoryCost(String billHistoryCost) {
		this.billHistoryCost = billHistoryCost;
	}
}
