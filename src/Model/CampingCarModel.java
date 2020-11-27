package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.DbUtil;

public class CampingCarModel {
	int carid;
	String carname;
	int carno;
	int seat;
	String manufacturer;
	int manu_year;
	int drivingdistance;
	int rentcost;
	int compid;
	String registdate;

	int selected_carid;
	int selected_compid;

	ResultSet rs;

	public ArrayList<Object[]> select(Connection conn) {
		String sql = "SELECT * FROM Camping_Car";
		return DbUtil.getRows(conn, sql);
	}

	public void insert(Connection conn) {
		String sql = "INSERT INTO Camping_Car VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		String[] types = { "int", "string", "int", "int", "string", "int", "int", "int", "int", "string" };
		Object[] values = { carid, carname, carno, seat, manufacturer, manu_year, drivingdistance, rentcost, compid,
				registdate };

		DbUtil.execute(conn, sql, types, values);

	}

	public void delete(Connection conn, Object object) {
		String sql = "DELETE FROM Camping_Car WHERE carid = " + object.toString() + ";";

		DbUtil.execute(conn, sql, null, null);

	}

	public void update(Connection conn, Object object) {

		String sql = "UPDATE Camping_Car SET carid=?,carname=?,carno=?,seat=?,manufacturer=?,manu_year=?,drivingdistance=?,rentcost=?,compid=?,registdate=? WHERE carid = "
				+ object.toString() + ";";

		String[] types = { "int", "string", "int", "int", "string", "int", "int", "int", "int", "string" };
		Object[] values = { carid, carname, carno, seat, manufacturer, manu_year, drivingdistance, rentcost, compid,
				registdate };

		DbUtil.execute(conn, sql, types, values);
	}

	public ArrayList<Object[]> selectRentAble(Connection conn) {
		String sql = "select c.carid, c.carname, c.carno, c.seat, c.manufacturer, c.manu_year, c.drivingdistance, c.rentcost, c.compid, c.registdate\r\n"
				+ "from Camping_Car c\r\n" + "where not exists (select 1 from Car_Rent r\r\n"
				+ "left join Car_Check cc on r.rentno = cc.rentno\r\n" + "where r.carid = c.carid\r\n"
				+ "and cc.rentno is null\r\n" + ")\r\n" + "order by carid;";
		return DbUtil.getRows(conn, sql);
	}

	public ArrayList<Object[]> search1(Connection conn, String maxPrice) {
		if (maxPrice != null) {
			String sql = "select * from Camping_car c where c.rentcost <= " + maxPrice + " and "
					+ "c.carid not in (select r.carid from Car_Rent r "
					+ "where r.rentno not in (select rentno from Car_Check)) order by carid;\n";

			return DbUtil.getRows(conn, sql);
		} else
			JOptionPane.showMessageDialog(null, "금액을 입력하세요");
		return null;
	}

	public ArrayList<Object[]> search2(Connection conn, String year) {
		if (year != null) {
			String sql = "select * from Camping_car c where c.manu_year >= " + year + " and "
					+ "c.carid not in (select r.carid from Car_Rent r "
					+ "where r.rentno not in (select rentno from Car_Check)) order by carid;\n";

			return DbUtil.getRows(conn, sql);
		} else
			JOptionPane.showMessageDialog(null, "년도를 입력하세요");
		return null;
	}

	public ArrayList<Object[]> search3(Connection conn, String distance) {
		if (distance != null) {
			String sql = "select * from Camping_car c where c.drivingdistance <= " + distance + " and "
					+ "c.carid not in (select r.carid from Car_Rent r "
					+ "where r.rentno not in (select rentno from Car_Check)) order by carid;\n";

			return DbUtil.getRows(conn, sql);
		} else
			JOptionPane.showMessageDialog(null, "거리를 입력하세요");
		return null;
	}

	public void selectedData(Connection conn, Object object) {
		try {
			String sql = "select carid, compid from Camping_Car where carid = " + object.toString() + ";";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				selected_carid = rs.getInt(1);
				selected_compid = rs.getInt(2);
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

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public int getCarno() {
		return carno;
	}

	public void setCarno(int carno) {
		this.carno = carno;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getManu_year() {
		return manu_year;
	}

	public void setManu_year(int manu_year) {
		this.manu_year = manu_year;
	}

	public int getDrivingdistance() {
		return drivingdistance;
	}

	public void setDrivingdistance(int drivingdistance) {
		this.drivingdistance = drivingdistance;
	}

	public int getRentcost() {
		return rentcost;
	}

	public void setRentcost(int rentcost) {
		this.rentcost = rentcost;
	}

	public int getCompid() {
		return compid;
	}

	public void setCompid(int compid) {
		this.compid = compid;
	}

	public String getRegistdate() {
		return registdate;
	}

	public void setRegistdate(String registdate) {
		this.registdate = registdate;
	}

}
