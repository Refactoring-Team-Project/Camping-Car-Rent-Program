package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class CarCheckModel {
	int carid;
	String explain_front;
	String explain_left;
	String explain_right;
	String explain_back;
	String repair_required;

	String selected_carid = null;
	String selected_compid = null;
	String selected_license_no = null;

	ResultSet rs;
	DefaultTableModel model;

	public ArrayList<Object[]> select(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		try {
			String sql = "SELECT * FROM Car_Check c order by c.rentno";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			Object column[] = { "RENT NO", "CAR ID", "FRONT EX", "LEFT EX", "RIGHT EX", "BACK EX", "REPAIR REQUIRED" };
			arr.add(column);

			while (rs.next()) {
				Object[] data = { rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), };
				arr.add(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return arr;
	}

	public void selectedData(Connection conn, Object object) {
		try {
			String sql = "select carid, compid, license_no from Car_Rent where rentno = " + object.toString() + ";";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				selected_carid = Integer.toString(rs.getInt(1));
				selected_compid = Integer.toString(rs.getInt(2));
				selected_license_no = Integer.toString(rs.getInt(3));
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public String getSelectedCarid() {
		return selected_carid;
	}

	public String getSelectedCompid() {
		return selected_compid;
	}

	public String getSelectedLicense_no() {
		return selected_license_no;
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

