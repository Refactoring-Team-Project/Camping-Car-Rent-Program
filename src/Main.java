

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

		RepairShopView rsv = new RepairShopView();
		RepairShopController rscon = new RepairShopController();

		RepairListView rlv = new RepairListView();
		RepairListController rlcon = new RepairListController();
	}

}
