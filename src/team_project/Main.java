package team_project;

public class Main {

	public static void main(String[] args) {

		// SimplePainterController ����
		MainView primary = new MainView();
		MainController mc = new MainController();

		CampingCompanyView ccv = new CampingCompanyView();
		CampingCompanyController cccon = new CampingCompanyController();

		RentCustomerView rcv = new RentCustomerView();
		RentCustomerController rccon = new RentCustomerController();

		CarCheckView cchkv = new CarCheckView();
		CarCheckController cchkcon = new CarCheckController();

		RepairShopView rsv = new RepairShopView();
		RepairShopController rscon = new RepairShopController();

		RepairListView rlv = new RepairListView();
		RepairListController rlcon = new RepairListController();

		CampingCarView campCarView = new CampingCarView();
		CampingCarController campCarCont = new CampingCarController();
		
		RentCarView rentCarView = new RentCarView();
		RentCarController rentCarCont = new RentCarController();
		
		SearchView sv = new SearchView();
		SearchController scon = new SearchController();
		
		CarRentListView crlv = new CarRentListView();
		CarRentListController crlcon = new CarRentListController();
	}

}
