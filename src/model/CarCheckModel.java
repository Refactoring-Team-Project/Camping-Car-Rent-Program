package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DbUtil;

public class CarCheckModel extends Model {
	int rentNo;
	int carId;
	String frontDetail;
	String leftDetail;
	String rigntDetail;
	String backDetail;
	String repairRequired;

	int selectedCarId;
	int selectedCompanyId;
	int selectedLicenseNo;

	ResultSet resultSet;

	public ArrayList<Object[]> select(Connection connection) {
		String sqlQuery = "SELECT * FROM Car_Check c order by c.rentno";
		return DbUtil.getRows(connection, sqlQuery);
	}

	public void insert(Connection connection) {
		String sqlQuery = "INSERT INTO Car_Check VALUES(?, ?, ?, ?, ?, ?, ?)";

		String[] dataTypes = { "int", "int", "string", "string", "string", "string", "string" };
		Object[] values = { rentNo, carId, frontDetail, leftDetail, rigntDetail, backDetail, repairRequired };

		DbUtil.execute(connection, sqlQuery, dataTypes, values);

	}

	public void selectedData(Connection connection, Object object) {
		try {
			String sqlQuery = "select carid, compid, license_no from Car_Rent where rentno = " + object.toString() + ";";
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);

			while (resultSet.next()) {
				selectedCarId = resultSet.getInt(1);
				selectedCompanyId = resultSet.getInt(2);
				selectedLicenseNo = resultSet.getInt(3);
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

	public int getSelectedLicenseNo() {
		return selectedLicenseNo;
	}

	public int getRentNo() {
		return rentNo;
	}

	public void setRentNo(int rentNo) {
		this.rentNo = rentNo;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getFrontDetail() {
		return frontDetail;
	}

	public void setFrontDetail(String frontDetail) {
		this.frontDetail = frontDetail;
	}

	public String getLeftDetail() {
		return leftDetail;
	}

	public void setLeftDetail(String leftDetail) {
		this.leftDetail = leftDetail;
	}

	public String getRigntDetail() {
		return rigntDetail;
	}

	public void setRigntDetail(String rigntDetail) {
		this.rigntDetail = rigntDetail;
	}

	public String getBackDetail() {
		return backDetail;
	}

	public void setBackDetail(String backDetail) {
		this.backDetail = backDetail;
	}

	public String getRepairRequired() {
		return repairRequired;
	}

	public void setRepairRequired(String repairRequired) {
		this.repairRequired = repairRequired;
	}

}
