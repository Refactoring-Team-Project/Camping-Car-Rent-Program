package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DbUtil;

public class RentCustomerModel extends Model{
	int licenseNo;
	String customerName;
	String customerAddress;
	String customerContact;
	String customerEmail;

	public ArrayList<Object[]> select(Connection connection) {
		String sqlQuery = "SELECT * FROM Rent_Customer";
		return DbUtil.getRows(connection, sqlQuery);
	}

	public void insert(Connection connection) {
		String sqlQuery = "INSERT INTO Rent_Customer VALUES(?, ?, ?, ?, ?)";

		String[] dataTypes = { "int", "string", "string", "string", "string" };
		Object[] values = { licenseNo, customerName, customerAddress, customerContact, customerEmail };

		DbUtil.execute(connection, sqlQuery, dataTypes, values);

	}

	public void delete(Connection connection, Object object) {
		String sqlQuery = "DELETE FROM Rent_Customer WHERE license_no = " + object.toString() + ";";

		DbUtil.execute(connection, sqlQuery, null, null);

	}

	public void update(Connection connection, Object object) {
		String sqlQuery = "UPDATE Rent_Customer SET license_no=?,name=?,address=?,phone=?,email=? WHERE license_no = "
				+ object.toString() + ";";

		String[] dataTypes = { "int", "string", "string", "string", "string" };
		Object[] values = { licenseNo, customerName, customerAddress, customerContact, customerEmail };

		DbUtil.execute(connection, sqlQuery, dataTypes, values);
	}

	public ArrayList<Object[]> adminBlackCusRankSearch(Connection connection) {
		String sqlQuery = "select rc.name, cr.license_no, count(*) as total_count, count(case when cc.repair_required = 'Y' then 1 end) as repair_count\r\n"
				+ "from Car_Rent cr, Car_Check cc, Rent_Customer rc\r\n" + "where cr.rentno = cc.rentno\r\n"
				+ "and cr.license_no = rc.license_no\r\n" + "group by cr.license_no\r\n" + "order by 4 desc;";

		return DbUtil.getRows(connection, sqlQuery);

	}

	public int getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(int licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}
