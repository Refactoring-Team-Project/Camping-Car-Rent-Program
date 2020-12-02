package model;

import common.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CampingCompanyModel extends Model {
	int companyId;
	String companyName;
	String companyAddress;
	String companyContact;
	String companyManagerName;
	String companyManagerEmail;

	public ArrayList<Object[]> select(Connection connection) {
		String sqlQuery = "SELECT * FROM Camping_Company";
		return DbUtil.getRows(connection, sqlQuery);
	}
	
	public void insert(Connection connection) {
		String sqlQuery = "INSERT INTO Camping_Company(compid, compname, address, phone, manager_name, manager_email) VALUES(?, ?, ?, ?, ?, ?)";

		String[] dataTypes = {"int", "string", "string", "string", "string", "string"};
		Object[] values = {companyId, companyName, companyAddress, companyContact, companyManagerName, companyManagerEmail};

		DbUtil.execute(connection, sqlQuery, dataTypes, values);
	}
	
	public void delete(Connection connection, Object object) {
		String sqlQuery = "DELETE FROM Camping_Company WHERE compid = " + object.toString() + ";";
		DbUtil.execute(connection, sqlQuery, null, null);
	}

	public void update(Connection connection, Object object) {
		String sqlQuery = "UPDATE Camping_Company SET compid=?,compname=?,address=?,phone=?,manager_name=?,manager_email=? WHERE compid = "
				+ object.toString() + ";";

		String[] dataTypes = {"int", "string", "string", "string", "string", "string"};
		Object[] values = {companyId, companyName, companyAddress, companyContact, companyManagerName, companyManagerEmail};

		DbUtil.execute(connection, sqlQuery, dataTypes, values);
	}
	
	public ArrayList<Object[]> adminTop10CompanySearch(Connection connection) {
		String sqlQuery = "select cc.compid, cc.compname, coalesce(count(*), 0) as rental_count\r\n" +
				"from Camping_Company cc\r\n" +
				"left join Car_Rent cr\r\n" +
				"on cc.compid = cr.compid\r\n" +
				"group by cc.compid, cc.compname\r\n" +
				"order by 3 desc\r\n" +
				"limit 0, 10;";
		return DbUtil.getRows(connection, sqlQuery);
	}
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyContact() {
		return companyContact;
	}
	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}
	public String getCompanyManagerName() {
		return companyManagerName;
	}
	public void setCompanyManagerName(String companyManagerName) {
		this.companyManagerName = companyManagerName;
	}
	public String getCompanyManagerEmail() {
		return companyManagerEmail;
	}
	public void setCompanyManagerEmail(String companyManagerEmail) {
		this.companyManagerEmail = companyManagerEmail;
	}
}