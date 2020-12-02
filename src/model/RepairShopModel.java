package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DbUtil;

public class RepairShopModel extends Model {
	int shopId;
	String shopName;
	String shopAddress;
	String shopContact;
	String shopManagerName;
	String shopManagerEmail;

	public ArrayList<Object[]> select(Connection connection) {
		String sqlQuery = "SELECT * FROM Repairshop";
		return DbUtil.getRows(connection, sqlQuery);

	}

	public void insert(Connection connection) {
		String sqlQuery = "INSERT INTO Repairshop VALUES(?, ?, ?, ?, ?, ?)";

		String[] dataTypes = { "int", "string", "string", "string", "string", "string" };
		Object[] values = { shopId, shopName, shopAddress, shopContact, shopManagerName, shopManagerEmail };

		DbUtil.execute(connection, sqlQuery, dataTypes, values);

	}

	public void delete(Connection connection, Object object) {
		String sqlQuery = "DELETE FROM RepairShop WHERE shopid = " + object.toString() + ";";

		DbUtil.execute(connection, sqlQuery, null, null);

	}

	public void update(Connection connection, Object object) {
		String sqlQuery = "UPDATE Repairshop SET shopid=?,shopname=?,address=?,phone=?,manager_name=?,manager_email=? WHERE shopid = "
				+ object.toString() + ";";

		String[] dataTypes = { "int", "string", "string", "string", "string", "string" };
		Object[] values = { shopId, shopName, shopAddress, shopContact, shopManagerName, shopManagerEmail };

		DbUtil.execute(connection, sqlQuery, dataTypes, values);

	}

	public ArrayList<Object[]> adminRepairShopRankSearch(Connection connection) {

		String sqlQuery = "select rs.shopid, rs.shopname, coalesce(sum(rl.repaircost), 0) as income\r\n"
				+ "from Repairshop rs\r\n" + "left join Repair_List rl\r\n" + "on rs.shopid = rl.shopid\r\n"
				+ "group by rs.shopid, rs.shopname\r\n" + "order by income desc;";

		return DbUtil.getRows(connection, sqlQuery);

	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopContact() {
		return shopContact;
	}

	public void setShopContact(String shopContact) {
		this.shopContact = shopContact;
	}

	public String getShopManagerName() {
		return shopManagerName;
	}

	public void setShopManagerName(String shopManagerName) {
		this.shopManagerName = shopManagerName;
	}

	public String getShopManagerEmail() {
		return shopManagerEmail;
	}

	public void setShopManagerEmail(String shopManagerEmail) {
		this.shopManagerEmail = shopManagerEmail;
	}

}
