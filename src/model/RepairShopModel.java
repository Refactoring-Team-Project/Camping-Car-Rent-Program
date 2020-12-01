package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DbUtil;

public class RepairShopModel extends Model {
	int shopid;
	String shopname;
	String address;
	String phone;
	String manager_name;
	String manager_email;

	ResultSet rs;

	public ArrayList<Object[]> select(Connection conn) {
		String sql = "SELECT * FROM Repairshop";
		return DbUtil.getRows(conn, sql);

	}

	public void insert(Connection conn) {
		String sql = "INSERT INTO Repairshop VALUES(?, ?, ?, ?, ?, ?)";

		String[] types = { "int", "string", "string", "string", "string", "string" };
		Object[] values = { shopid, shopname, address, phone, manager_name, manager_email };

		DbUtil.execute(conn, sql, types, values);

	}

	public void delete(Connection conn, Object object) {
		String sql = "DELETE FROM RepairShop WHERE shopid = " + object.toString() + ";";

		DbUtil.execute(conn, sql, null, null);

	}

	public void update(Connection conn, Object object) {
		String sql = "UPDATE Repairshop SET shopid=?,shopname=?,address=?,phone=?,manager_name=?,manager_email=? WHERE shopid = "
				+ object.toString() + ";";

		String[] types = { "int", "string", "string", "string", "string", "string" };
		Object[] values = { shopid, shopname, address, phone, manager_name, manager_email };

		DbUtil.execute(conn, sql, types, values);

	}

	public ArrayList<Object[]> search4(Connection conn) {

		String sql = "select rs.shopid, rs.shopname, coalesce(sum(rl.repaircost), 0) as income\r\n"
				+ "from Repairshop rs\r\n" + "left join Repair_List rl\r\n" + "on rs.shopid = rl.shopid\r\n"
				+ "group by rs.shopid, rs.shopname\r\n" + "order by income desc;";

		return DbUtil.getRows(conn, sql);

	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
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

	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}

}
