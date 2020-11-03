package team_project;

public class Main {

	public static void main(String[] args) {

		// SimplePainterController ����
		MainView primary = new MainView();

		CampingCompanyView ccv = new CampingCompanyView();
		CampingCompanyController cccon = new CampingCompanyController();

		RentCustomerView rcv = new RentCustomerView();
		RentCustomerController rccon = new RentCustomerController();

		CarCheckView cchkv = new CarCheckView();
		CarCheckController cchkcon = new CarCheckController();
		
		SearchView sv = new SearchView();
		SearchController scon = new SearchController();
		
		CarRentListView crlv = new CarRentListView();
		CarRentListController crlcon = new CarRentListController();
	}

}
