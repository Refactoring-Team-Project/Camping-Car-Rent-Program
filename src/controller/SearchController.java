package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import common.AppManager;
import model.CampingCompanyModel;
import model.CarRentModel;
import model.RentCustomerModel;
import model.RepairShopModel;
import view.MainView;
import view.SearchView;

public class SearchController {
	MainView _mainView;
	SearchView _searchView;
	private CampingCompanyModel campCompModel;
	private RentCustomerModel rentCustomerModel;
	private CarRentModel carRentModel;
	private RepairShopModel repairShopModel;
	
	public SearchController() {
		this._mainView = AppManager.getInstance().getView();
		this._searchView = AppManager.getInstance().getSearchView();
		campCompModel = new CampingCompanyModel();
		rentCustomerModel = new RentCustomerModel();
		carRentModel = new CarRentModel();
		repairShopModel = new RepairShopModel();
		
		this._mainView.addSearch1Listener(new Search1ButtonListener());
		this._mainView.addAdminTop10CompanySearch(new Search2ButtonListener());
		this._mainView.addAdminBlackCusRankSearch(new Search3ButtonListener());
		this._mainView.addAdminRepairShopRankSearch(new Search4ButtonListener());
	}
	
	
	private class Search1ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _searchView.SearchInput("원하는 날짜를 입력해주세요");
			
			_mainView.changePanel(_searchView);
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = carRentModel.search1(_mainView.getConn(), input);
			Object column[] = { "RENT NO", "CAR ID", "LICENSE NO", "COMP ID", "RENT DATE", "RENTAL PERIOD", "CHARGE",
					"PAYMENT DEADLINE", "BILL HISTORY", "BILL HISTORY COST" };
			arr.add(0, column);
			if(arr.size() != 0) _searchView.searchDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_searchView.searchDefaultTable.addRow(arr.get(i));
			}
			System.out.println("search1");
			_mainView.revalidate();
			_mainView.repaint();
		}
		
	}
	
	private class Search2ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_searchView);
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = campCompModel.search2(_mainView.getConn());
			Object column[] = {"COMP ID", "COMP NAME", "TOTAL RENTAL COUNT"};
			arr.add(0, column);
			_searchView.searchDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_searchView.searchDefaultTable.addRow(arr.get(i));
			}
			System.out.println("search2");
			_mainView.revalidate();
			_mainView.repaint();
		}
		
	}
	
	private class Search3ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_searchView);
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = rentCustomerModel.search3(_mainView.getConn());
			Object column[] = {"NAME", "LICENSE NO", "TOTAL RENTAL COUNT", "REPAIR COUNT"};
			arr.add(0, column);
			_searchView.searchDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_searchView.searchDefaultTable.addRow(arr.get(i));
			}
			System.out.println("search3");
			_mainView.revalidate();
			_mainView.repaint();
		}
		
	}
	
	private class Search4ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_searchView);
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = repairShopModel.search4(_mainView.getConn());
			Object column[] = {"SHOP ID", "SHOP NAME", "INCOME"};
			arr.add(0, column);
			_searchView.searchDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_searchView.searchDefaultTable.addRow(arr.get(i));
			}
			System.out.println("search4");
			_mainView.revalidate();
			_mainView.repaint();
		}
		
	}
}
