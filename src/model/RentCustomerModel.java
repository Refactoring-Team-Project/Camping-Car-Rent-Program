package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DbUtil;

public class RentCustomerModel extends Model{
	int license_no;
	String name;
	String address;
	String phone;
	String email;

	ResultSet rs;

	public ArrayList<Object[]> select(Connection conn) {
		String sql = "SELECT * FROM Rent_Customer";
		return DbUtil.getRows(conn, sql);
	}

	public void insert(Connection conn) {
		String sql = "INSERT INTO Rent_Customer VALUES(?, ?, ?, ?, ?)";

		String[] types = { "int", "string", "string", "string", "string" };
		Object[] values = { license_no, name, address, phone, email };

		DbUtil.execute(conn, sql, types, values);

	}

	public void delete(Connection conn, Object object) {
		String sql = "DELETE FROM Rent_Customer WHERE license_no = " + object.toString() + ";";

		DbUtil.execute(conn, sql, null, null);

	}

	public void update(Connection conn, Object object) {
		String sql = "UPDATE Rent_Customer SET license_no=?,name=?,address=?,phone=?,email=? WHERE license_no = "
				+ object.toString() + ";";

		String[] types = { "int", "string", "string", "string", "string" };
		Object[] values = { license_no, name, address, phone, email };

		DbUtil.execute(conn, sql, types, values);
	}

	public ArrayList<Object[]> search3(Connection conn) {
		String sql = "select rc.name, cr.license_no, count(*) as total_count, count(case when cc.repair_required = 'Y' then 1 end) as repair_count\r\n"
				+ "from Car_Rent cr, Car_Check cc, Rent_Customer rc\r\n" + "where cr.rentno = cc.rentno\r\n"
				+ "and cr.license_no = rc.license_no\r\n" + "group by cr.license_no\r\n" + "order by 4 desc;";

		return DbUtil.getRows(conn, sql);

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
