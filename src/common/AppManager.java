package common;

import view.*;

public class AppManager {
	private static AppManager s_instance;
	private MainView mainView;
	private CampingCompanyView campingCompanyView;
	private RentCustomerView rentCustomerView;
	private CarCheckView carCheckView;
	private RepairShopView repairShopView;
	private RepairListView repairListView;
	private CampingCarView campingCarView;
	private RentCarView rentCarView;
	private SearchView searchView;
	private CarRentListView carRentListView;

	public MainView getMainView() {
		return mainView;
	}
	
	public CampingCarView getCampingCarView() {
		return campingCarView;
	}
	
	public RentCarView getRentCarView() {
		return rentCarView;
	}

	public CampingCompanyView getCampingCompanyView() {
		return campingCompanyView;
	} 

	public RentCustomerView getRentCustomerView() {
		return rentCustomerView;
	}

	public CarCheckView getCarCheckView() {
		return carCheckView;
	}

	public RepairShopView getRepairShopView() {
		return repairShopView;
	}

	public RepairListView getRepairListView() {
		return repairListView;
	}
	
	public SearchView getSearchView() {
		return searchView;
	}
	
	public CarRentListView getCarRentListView() {
		return carRentListView;
	}
	
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void setCampingCompanyView(CampingCompanyView campingCompanyView) {
		this.campingCompanyView = campingCompanyView;
	}

	public void setRentCustomerView(RentCustomerView rentCustomerView) {
		this.rentCustomerView = rentCustomerView;
	}

	public void setCarCheckView(CarCheckView carCheckView) {
		this.carCheckView = carCheckView;
	}

	public void setRepairShopView(RepairShopView repairShopView) {
		this.repairShopView = repairShopView;
	}

	public void setRepairListView(RepairListView repairListView) {
		this.repairListView = repairListView;
	}
	
	public void setCampingCarView(CampingCarView campingCarView) {
		this.campingCarView = campingCarView;
	}
	
	public void setRentCarView(RentCarView rentcarview) {
		this.rentCarView = rentcarview;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}
	
	public void setCarRentListView(CarRentListView carRentListView) {
		this.carRentListView = carRentListView;
	}
	
	public static AppManager getInstance() {
		if (s_instance == null)
			s_instance = new AppManager();
		return s_instance;

	}

}
