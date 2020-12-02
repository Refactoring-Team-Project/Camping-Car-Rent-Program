package model;

import common.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RepairListModel extends Model {
	int repairNo;
	int carId;
	int shopId;
	int compId;
	int licenseNo;
	String repairDetails;
	String repairDate;
	int repairCost;
	String paymentDeadline;
	String repairHistory;

	public ArrayList<Object[]> select(Connection connection) {

		String sqlQuery = "SELECT * FROM Repair_List";

		return DbUtil.getRows(connection, sqlQuery);

	}

	public void insert(Connection connection) {
		String sqlQuery = "INSERT INTO Repair_List(repairno, carid, shopid, compid, license_no, repairdetails, repairdate, repaircost, paymentdeadline, repairhistory) VALUES (?,?,?,?,?,?,?,?,?,?)";

		String[] dataTypes = {"int","int", "int", "int", "int", "string", "string" ,"int", "string", "string"};
		Object[] values = {repairNo, carId, shopId, compId, licenseNo, repairDetails, repairDate, repairCost, paymentDeadline, repairHistory};

		DbUtil.execute(connection, sqlQuery, dataTypes, values);

	}

	public void delete(Connection connection, Object object) {

		String sqlQuery = "DELETE FROM Repair_List WHERE repairno = " + object.toString() + ";";
		DbUtil.execute(connection, sqlQuery, null, null);

	}

	public void update(Connection connection, Object object) {

		String sqlQuery = "UPDATE Repair_List SET repairno=?,carid=?,shopid=?,compid=?,license_no=?,repairdetails=?,repairdate=?,repaircost=?,paymentdeadline=?,repairhistory=? WHERE repairno = "
				+ object.toString() + ";";

		String[] dataTypes = {"int","int", "int", "int", "int", "string", "string" ,"int", "string", "string"};
		Object[] values = {repairNo, carId, shopId, compId, licenseNo, repairDetails, repairDate, repairCost, paymentDeadline, repairHistory};

		DbUtil.execute(connection, sqlQuery, dataTypes, values);


	}

	public int getRepairNo() {
		return repairNo;
	}

	public void setRepairNo(int repairNo) {
		this.repairNo = repairNo;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public int getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(int licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getRepairDetails() {
		return repairDetails;
	}

	public void setRepairDetails(String repairDetails) {
		this.repairDetails = repairDetails;
	}

	public String getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}

	public int getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(int repairCost) {
		this.repairCost = repairCost;
	}

	public String getPaymentDeadline() {
		return paymentDeadline;
	}

	public void setPaymentDeadline(String paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}

	public String getRepairHistory() {
		return repairHistory;
	}

	public void setRepairHistory(String repairHistory) {
		this.repairHistory = repairHistory;
	}

}
