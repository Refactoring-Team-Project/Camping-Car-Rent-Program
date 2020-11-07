package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.CampingCompanyModel;
import Model.CarRentModel;
import Model.RentCustomerModel;
import Model.RepairShopModel;
import View.MainView;
import View.SearchView;

public class SearchController {
	MainView _view;
	SearchView _searchView;
	private CampingCompanyModel campCompModel;
	private RentCustomerModel rentCustomerModel;
	private CarRentModel carRentModel;
	private RepairShopModel repairShopModel;
	
	public SearchController() {
		this._view = AppManager.getInstance().getView();
		this._searchView = AppManager.getInstance().getSearchView();
		campCompModel = new CampingCompanyModel();
		rentCustomerModel = new RentCustomerModel();
		carRentModel = new CarRentModel();
		repairShopModel = new RepairShopModel();
		
		this._view.addSearch1Listener(new Search1ButtonListener());
		this._view.addSearch2Listener(new Search2ButtonListener());
		this._view.addSearch3Listener(new Search3ButtonListener());
		this._view.addSearch4Listener(new Search4ButtonListener());
	}
	
	
	private class Search1ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _searchView.SearchInput("원하는 날짜를 입력해주세요");
			
			_view.changePanel(_searchView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = carRentModel.search1(_searchView.getConn(), input);
			if(arr.size() != 0) _searchView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_searchView.model.addRow(arr.get(i));
			}
			System.out.println("search1");
			_view.revalidate();
			_view.repaint();
		}
		
	}
	
	private class Search2ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_searchView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = campCompModel.search2(_searchView.getConn());
			_searchView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_searchView.model.addRow(arr.get(i));
			}
			System.out.println("search2");
			_view.revalidate();
			_view.repaint();
		}
		
	}
	
	private class Search3ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_searchView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = rentCustomerModel.search3(_searchView.getConn());
			_searchView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_searchView.model.addRow(arr.get(i));
			}
			System.out.println("search3");
			_view.revalidate();
			_view.repaint();
		}
		
	}
	
	private class Search4ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_searchView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = repairShopModel.search4(_searchView.getConn());
			_searchView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_searchView.model.addRow(arr.get(i));
			}
			System.out.println("search4");
			_view.revalidate();
			_view.repaint();
		}
		
	}
}
