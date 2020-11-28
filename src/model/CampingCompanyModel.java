package model;

import common.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CampingCompanyModel {
	int compid;
	String compname;
	String address;
	String phone;
	String manager_name;
	String manager_email;
	
	ResultSet rs; 
	
	public ArrayList<Object[]> select(Connection conn) {
		String sql = "SELECT * FROM Camping_Company";
		return DbUtil.getRows(conn, sql);
	}
	
	public void insert(Connection conn) {
		String sql = "INSERT INTO Camping_Company(compid, compname, address, phone, manager_name, manager_email) VALUES(?, ?, ?, ?, ?, ?)";

		String[] types = {"int", "string", "string", "string", "string", "string"};
		Object[] values = {compid, compname, address, phone, manager_name, manager_email};

		DbUtil.execute(conn, sql, types, values);
	}
	
	public void delete(Connection conn, Object object) {
		String sql = "DELETE FROM Camping_Company WHERE compid = " + object.toString() + ";";
		DbUtil.execute(conn, sql, null, null);
	}

	public void update(Connection conn, Object object) {
		String sql = "UPDATE Camping_Company SET compid=?,compname=?,address=?,phone=?,manager_name=?,manager_email=? WHERE compid = "
				+ object.toString() + ";";

		String[] types = {"int", "string", "string", "string", "string", "string"};
		Object[] values = {compid, compname, address, phone, manager_name, manager_email};

		DbUtil.execute(conn, sql, types, values);
	}
	
	public ArrayList<Object[]> search2(Connection conn) {
		String sql = "select cc.compid, cc.compname, coalesce(count(*), 0) as rental_count\r\n" +
				"from Camping_Company cc\r\n" +
				"left join Car_Rent cr\r\n" +
				"on cc.compid = cr.compid\r\n" +
				"group by cc.compid, cc.compname\r\n" +
				"order by 3 desc\r\n" +
				"limit 0, 10;";
		return DbUtil.getRows(conn, sql);
	}
	
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
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
	public void setManager_email(String manager_mail) {
		this.manager_email = manager_mail;
	}
}