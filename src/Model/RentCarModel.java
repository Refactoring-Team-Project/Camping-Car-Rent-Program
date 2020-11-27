package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import Common.DbUtil;

public class RentCarModel {
	int rentno;
	int carid;
	String explain_front = null;
	String explain_left;
	String explain_right;
	String explain_back;
	String repair_required;

	ResultSet rs;

	public ArrayList<Object[]> select(Connection conn) {
		String sql = "SELECT * FROM Car_Rent";
		return DbUtil.getRows(conn, sql);
	}

	public void insert(Connection conn) {
		String sql = "INSERT INTO Car_Check VALUES(?, ?, ?, ?, ?, ?, ?)";

		String[] types = { "int", "int", "string", "string", "string", "string", "string" };
		Object[] values = { rentno, carid, explain_front, explain_left, explain_right, explain_back, repair_required };

		DbUtil.execute(conn, sql, types, values);

	}

	public void setRentno(int rentno) {
		this.rentno = rentno;
	}

	public int getRentno() {
		return rentno;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public int getCarid() {
		return carid;
	}

	public void setExplain_front(String explain_front) {
		this.explain_front = explain_front;
	}

	public String getExplain_front() {
		return explain_front;
	}

	public void setExplain_left(String explain_left) {
		this.explain_left = explain_left;
	}

	public String getExplain_left() {
		return explain_left;
	}

	public void setExplain_right(String explain_right) {
		this.explain_right = explain_right;
	}

	public String getExplain_right() {
		return explain_right;
	}

	public void setExplain_back(String explain_back) {
		this.explain_back = explain_back;
	}

	public String getExplain_back() {
		return explain_back;
	}

	public void setRepair_required(String repair_required) {
		this.repair_required = repair_required;
	}

	public String getRepair_required() {
		return repair_required;
	}
}