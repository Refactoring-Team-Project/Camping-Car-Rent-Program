package team_project;

public class CarRentModel {
	int rentno;
	int carid;
	int license_no;
	int compid;
	String rentdata;
	int rentalperiod;
	int charge;
	String paymentdeadline;
	String billhistory;
	String billhistorycost;
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
	public String getRentdata() {
		return rentdata;
	}
	public void setRentdata(String rentdata) {
		this.rentdata = rentdata;
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
