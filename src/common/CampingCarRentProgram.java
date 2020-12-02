package common;

import controller.CampingCarController;
import controller.CampingCompanyController;
import controller.CarCheckController;
import controller.CarRentListController;
import controller.MainController;
import controller.RentCarController;
import controller.RentCustomerController;
import controller.RepairListController;
import controller.RepairShopController;
import controller.SearchController;
import view.CampingCarView;
import view.CampingCompanyView;
import view.CarCheckView;
import view.CarRentListView;
import view.MainView;
import view.RentCarView;
import view.RentCustomerView;
import view.RepairListView;
import view.RepairShopView;
import view.SearchView;

public class CampingCarRentProgram {

	public static void main(String[] args) {

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
