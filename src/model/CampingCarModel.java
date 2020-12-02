package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.DbUtil;

public class CampingCarModel extends Model {
	int carId;
	String carName;
	int carNo;
	int carSeatNum;
	String carManufacturer;
	int carManufacturingYear;
	int drivingDistance;
	int carRentCost;
	int companyId;
	String carRegistrationDate;

	int selectedCarId;
	int selectedCompanyId;

	ResultSet resultSet;

	public ArrayList<Object[]> select(Connection connection) {
		String sqlQuery = "SELECT * FROM Camping_Car";
		return DbUtil.getRows(connection, sqlQuery);
	}

	public void insert(Connection connection) {
		String sqlQuery = "INSERT INTO Camping_Car VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		String[] dataTypes = { "int", "string", "int", "int", "string", "int", "int", "int", "int", "string" };
		Object[] values = { carId, carName, carNo, carSeatNum, carManufacturer, carManufacturingYear, drivingDistance, carRentCost, companyId, carRegistrationDate };

		DbUtil.execute(connection, sqlQuery, dataTypes, values);

	}

	public void delete(Connection connection, Object object) {
		String sqlQuery = "DELETE FROM Camping_Car WHERE carid = " + object.toString() + ";";

		DbUtil.execute(connection, sqlQuery, null, null);

	}

	public void update(Connection connection, Object object) {

		String sqlQuery = "UPDATE Camping_Car SET carid=?,carname=?,carno=?,seat=?,manufacturer=?,manu_year=?,drivingdistance=?,rentcost=?,compid=?,registdate=? WHERE carid = "
				+ object.toString() + ";";

		String[] dataTypes = { "int", "string", "int", "int", "string", "int", "int", "int", "int", "string" };
		Object[] values = { carId, carName, carNo, carSeatNum, carManufacturer, carManufacturingYear, drivingDistance, carRentCost, companyId, carRegistrationDate };


		DbUtil.execute(connection, sqlQuery, dataTypes, values);
	}

	public ArrayList<Object[]> selectRentAble(Connection connection) {
		String sqlQuery = "select c.carid, c.carname, c.carno, c.seat, c.manufacturer, c.manu_year, c.drivingdistance, c.rentcost, c.compid, c.registdate\r\n"
				+ "from Camping_Car c\r\n" + "where not exists (select 1 from Car_Rent r\r\n"
				+ "left join Car_Check cc on r.rentno = cc.rentno\r\n" + "where r.carid = c.carid\r\n"
				+ "and cc.rentno is null\r\n" + ")\r\n" + "order by carid;";
		return DbUtil.getRows(connection, sqlQuery);
	}

	public ArrayList<Object[]> userPriceSearch(Connection connection, String maxPrice) {
		if (!maxPrice.isBlank()) {
			String sqlQuery = "select * from Camping_car c where c.rentcost <= " + maxPrice + " and "
					+ "c.carid not in (select r.carid from Car_Rent r "
					+ "where r.rentno not in (select rentno from Car_Check)) order by carid;\n";

			return DbUtil.getRows(connection, sqlQuery);
		} else
			JOptionPane.showMessageDialog(null, "금액을 입력하세요");
		return new ArrayList<Object[]>();
	}

	public ArrayList<Object[]> userManufacturingYearSearch(Connection connection, String manufactureYear) {
		if (!manufactureYear.isBlank()) {
			String sqlQuery = "select * from Camping_car c where c.manu_year >= " + manufactureYear + " and "
					+ "c.carid not in (select r.carid from Car_Rent r "
					+ "where r.rentno not in (select rentno from Car_Check)) order by carid;\n";

			return DbUtil.getRows(connection, sqlQuery);
		} else
			JOptionPane.showMessageDialog(null, "년도를 입력하세요");
		return new ArrayList<Object[]>();
	}

	public ArrayList<Object[]> userMileageSearch(Connection connection, String distance) {
		if (!distance.isBlank()) {
			String sqlQuery = "select * from Camping_car c where c.drivingdistance <= " + distance + " and "
					+ "c.carid not in (select r.carid from Car_Rent r "
					+ "where r.rentno not in (select rentno from Car_Check)) order by carid;\n";

			return DbUtil.getRows(connection, sqlQuery);
		} else
			JOptionPane.showMessageDialog(null, "거리를 입력하세요");
		return new ArrayList<Object[]>();
	}

	public void selectedData(Connection connection, Object object) {
		try {
			String sqlQuery = "select carid, compid from Camping_Car where carid = " + object.toString() + ";";
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);

			while (resultSet.next()) {
				selectedCarId = resultSet.getInt(1);
				selectedCompanyId = resultSet.getInt(2);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}


	public int getSelectedCarId() {
		return selectedCarId;
	}

	public int getSelectedCompanyId() {
		return selectedCompanyId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getCarNo() {
		return carNo;
	}

	public void setCarNo(int carNo) {
		this.carNo = carNo;
	}

	public int getCarSeatNum() {
		return carSeatNum;
	}

	public void setCarSeatNum(int carSeatNum) {
		this.carSeatNum = carSeatNum;
	}

	public String getCarManufacturer() {
		return carManufacturer;
	}

	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}

	public int getCarManufacturingYear() {
		return carManufacturingYear;
	}

	public void setCarManufacturingYear(int carManufacturingYear) {
		this.carManufacturingYear = carManufacturingYear;
	}

	public int getDrivingDistance() {
		return drivingDistance;
	}

	public void setDrivingDistance(int drivingDistance) {
		this.drivingDistance = drivingDistance;
	}

	public int getCarRentCost() {
		return carRentCost;
	}

	public void setCarRentCost(int carRentCost) {
		this.carRentCost = carRentCost;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCarRegistrationDate() {
		return carRegistrationDate;
	}

	public void setCarRegistrationDate(String carRegistrationDate) {
		this.carRegistrationDate = carRegistrationDate;
	}

}
