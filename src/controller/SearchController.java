package controller;

import common.AppManager;
import common.Constants;
import model.CampingCompanyModel;
import model.CarRentModel;
import model.RentCustomerModel;
import model.RepairShopModel;
import view.SearchView;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchController extends Controller {
	enum SEARCH {
		SEARCH1,
		SEARCH2,
		SEARCH3,
		SEARCH4
	}
	SEARCH currentSearch;
	Object columns[][];
	String input;

	private SearchView searchView;
	private CampingCompanyModel campCompModel;
	private RentCustomerModel rentCustomerModel;
	private CarRentModel carRentModel;
	private RepairShopModel repairShopModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this.mainView.addAdminButtonListener(Constants.SEARCH1, new mainButtonListener());
		this.mainView.addAdminButtonListener(Constants.SEARCH2, new mainButtonListener());
		this.mainView.addAdminButtonListener(Constants.SEARCH3, new mainButtonListener());
		this.mainView.addAdminButtonListener(Constants.SEARCH4, new mainButtonListener());
	}

	@Override
	public void initModel() {
		campCompModel = new CampingCompanyModel();
		rentCustomerModel = new RentCustomerModel();
		carRentModel = new CarRentModel();
		repairShopModel = new RepairShopModel();
	}

	@Override
	public void initConnectedView() {
		this.connectedView = AppManager.getInstance().getSearchView();
		searchView = (SearchView) this.connectedView;
	}

	@Override
	public void setColumnName() {
		columns = new Object[][] {
				{ "RENT NO", "CAR ID", "LICENSE NO", "COMP ID", "RENT DATE", "RENTAL PERIOD", "CHARGE",
						"PAYMENT DEADLINE", "BILL HISTORY", "BILL HISTORY COST" },
				{"COMP ID", "COMP NAME", "TOTAL RENTAL COUNT"},
				{"NAME", "LICENSE NO", "TOTAL RENTAL COUNT", "REPAIR COUNT"},
				{"SHOP ID", "SHOP NAME", "INCOME"}
		};
		columnName = columns[0];
	}

	@Override
	public void setModelEachColumn(String column, String value) { }

	@Override
	public void mainViewButtonEvent(ActionEvent e) {
		if (e.getSource() == mainView.btnOnAdminPanel[Arrays.asList(Constants.ADMIN_BUTTON_NAME).indexOf(Constants.SEARCH1)]) {
			input = searchView.searchInput("원하는 날짜를 입력해주세요");
			currentSearch = SEARCH.SEARCH1;
		}
		if (e.getSource() == mainView.btnOnAdminPanel[Arrays.asList(Constants.ADMIN_BUTTON_NAME).indexOf(Constants.SEARCH2)]) {
			currentSearch = SEARCH.SEARCH2;
		}
		if (e.getSource() == mainView.btnOnAdminPanel[Arrays.asList(Constants.ADMIN_BUTTON_NAME).indexOf(Constants.SEARCH3)]) {
			currentSearch = SEARCH.SEARCH3;
		}
		if (e.getSource() == mainView.btnOnAdminPanel[Arrays.asList(Constants.ADMIN_BUTTON_NAME).indexOf(Constants.SEARCH4)]) {
			currentSearch = SEARCH.SEARCH4;
		}

		columnName = columns[currentSearch.ordinal()];
		super.mainViewButtonEvent(e);
	}

	@Override
	public void getDataTableValues() {
		ArrayList<Object[]> arr;
		switch(currentSearch) {
			case SEARCH1:
				arr = carRentModel.search1(mainView.getConnection(), input);
				break;
			case SEARCH2:
				arr = campCompModel.search2(mainView.getConnection());
				break;
			case SEARCH3:
				arr = rentCustomerModel.search3(mainView.getConnection());
				break;
			case SEARCH4:
				arr = repairShopModel.search4(mainView.getConnection());
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + currentSearch);
		}
		for (int i = 0; i < arr.size(); i++) {
			connectedView.tableModelOnScrollPane.addRow(arr.get(i));
		}
	}
}