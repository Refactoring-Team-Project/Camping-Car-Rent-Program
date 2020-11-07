package Common;

import Controller.CampingCarController;
import Controller.CampingCompanyController;
import Controller.CarCheckController;
import Controller.CarRentListController;
import Controller.MainController;
import Controller.RentCarController;
import Controller.RentCustomerController;
import Controller.RepairListController;
import Controller.RepairShopController;
import Controller.SearchController;
import View.CampingCarView;
import View.CampingCompanyView;
import View.CarCheckView;
import View.CarRentListView;
import View.MainView;
import View.RentCarView;
import View.RentCustomerView;
import View.RepairListView;
import View.RepairShopView;
import View.SearchView;

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
