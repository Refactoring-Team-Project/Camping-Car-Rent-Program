package team_project;

//import src.team_project.MainController;

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
	}

}
