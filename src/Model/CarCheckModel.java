package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Common.DbUtil;

public class CarCheckModel {
	int rentno;
	int carid;
	String explain_front;
	String explain_left;
	String explain_right;
	String explain_back;
	String repair_required;

	int selected_carid;
	int selected_compid;
	int selected_license_no;

	ResultSet rs;

	public ArrayList<Object[]> select(Connection conn) {
		String sql = "SELECT * FROM Car_Check c order by c.rentno";
		return DbUtil.getRows(conn, sql);
	}

	public void insert(Connection conn) {
		String sql = "INSERT INTO Car_Check VALUES(?, ?, ?, ?, ?, ?, ?)";

		String[] types = { "int", "int", "string", "string", "string", "string", "string" };
		Object[] values = { rentno, carid, explain_front, explain_left, explain_right, explain_back, repair_required };

		DbUtil.execute(conn, sql, types, values);

	}

	public void selectedData(Connection conn, Object object) {
		try {
			String sql = "select carid, compid, license_no from Car_Rent where rentno = " + object.toString() + ";";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				selected_carid = rs.getInt(1);
				selected_compid = rs.getInt(2);
				selected_license_no = rs.getInt(3);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public int getSelectedCarid() {
		return selected_carid;
	}

	public int getSelectedCompid() {
		return selected_compid;
	}

	public int getSelectedLicense_no() {
		return selected_license_no;
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

	public String getExplain_front() {
		return explain_front;
	}

	public void setExplain_front(String explain_front) {
		this.explain_front = explain_front;
	}

	public String getExplain_left() {
		return explain_left;
	}

	public void setExplain_left(String explain_left) {
		this.explain_left = explain_left;
	}

	public String getExplain_right() {
		return explain_right;
	}

	public void setExplain_right(String explain_right) {
		this.explain_right = explain_right;
	}

	public String getExplain_back() {
		return explain_back;
	}

	public void setExplain_back(String explain_back) {
		this.explain_back = explain_back;
	}

	public String getRepair_required() {
		return repair_required;
	}

	public void setRepair_required(String repair_required) {
		this.repair_required = repair_required;
	}

}
