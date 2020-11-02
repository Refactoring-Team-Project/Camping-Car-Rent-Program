package team_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class CampingCarModel {
	int carid;
	String carname;
	int carno;
	int seat;
	String manufacurer;
	int manu_year;
	int drivingdistance;
	int rentcost;
	int compid;
	String registdate;
	
	

	

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
	public String getManufacurer() {
		return manufacurer;
	}
	public void setManufacurer(String manufacurer) {
		this.manufacurer = manufacurer;
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
