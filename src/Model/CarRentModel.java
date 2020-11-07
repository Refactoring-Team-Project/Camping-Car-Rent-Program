package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CarRentModel {
	int rentno;
	int carid;
	int license_no;
	int compid;
	String rentdate;
	int rentalperiod;
	int charge;
	String paymentdeadline;
	String billhistory;
	String billhistorycost;
	
	ResultSet rs;
	
	public void insert(Connection conn) {
		try {
            String sql = "INSERT INTO Car_Rent(rentno, carid, license_no, compid, rentdate, rentalperiod, charge, paymentdeadline, billhistory, billhistorycost) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
           
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);    
            pstmt.setInt(1, rentno);
            pstmt.setInt(2, carid);
            pstmt.setInt(3, license_no);
            pstmt.setInt(4, compid);
            pstmt.setString(5, rentdate);
            pstmt.setInt(6, rentalperiod);
            pstmt.setInt(7, charge);
            pstmt.setString(8, paymentdeadline);
            pstmt.setString(9, billhistory);
            pstmt.setString(10, billhistorycost);
            pstmt.executeUpdate();
         
            JOptionPane.showMessageDialog(null, "대여완료");
            
            
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
	}
	
	public ArrayList<Object[]> search1(Connection conn, String day) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		if(day!=null && (day.matches("\\d{4}-\\d{2}-\\d{2}"))) {
	         try {
	            System.out.println(day);
	              String sql = "select *\r\n" + 
	                    "from Car_Rent r\r\n" + 
	                    "where r.rentno not in (select c.rentno from Car_Check c) and date_format('"+day+"', '%Y-%m-%d') > date_add(r.rentdate, interval rentalperiod day);";

	              Statement stmt = conn.createStatement();
	              rs = stmt.executeQuery(sql);

	              Object column[] = {"RENT NO", "CAR ID", "LICENSE NO", "COMP ID", "RENT DATE", "RENTAL PERIOD", "CHARGE", "PAYMENT DEADLINE", "BILL HISTORY", "BILL HISTORY COST"};
	              arr.add(column);
	          
	              while(rs.next()) {
	                 Object[] data = {
	                       rs.getInt(1),
	                       rs.getInt(2),
	                         rs.getInt(3),
	                         rs.getInt(4),
	                         rs.getDate(5),
	                         rs.getInt(6),
	                         rs.getInt(7),
	                         rs.getDate(8),
	                         rs.getString(9),
	                         rs.getInt(10)
	                 };
	                 arr.add(data);
	              }
	              
	            } catch (SQLException e1) {
	                JOptionPane.showMessageDialog(null, e1.getMessage());
	            }
		 } else JOptionPane.showMessageDialog(null, "날짜형식(yyyy-mm-dd)를 맞춰 입력해주십시오");
		return arr;
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
	public int getLicense_no() {
		return license_no;
	}
	public void setLicense_no(int license_no) {
		this.license_no = license_no;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getRentdate() {
		return rentdate;
	}
	public void setRentdate(String rentdata) {
		this.rentdate = rentdata;
	}
	public int getRentalperiod() {
		return rentalperiod;
	}
	public void setRentalperiod(int rentalperiod) {
		this.rentalperiod = rentalperiod;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public String getPaymentdeadline() {
		return paymentdeadline;
	}
	public void setPaymentdeadline(String paymentdeadline) {
		this.paymentdeadline = paymentdeadline;
	}
	public String getBillhistory() {
		return billhistory;
	}
	public void setBillhistory(String billhistory) {
		this.billhistory = billhistory;
	}
	public String getBillhistorycost() {
		return billhistorycost;
	}
	public void setBillhistorycost(String billhistorycost) {
		this.billhistorycost = billhistorycost;
	}
}
