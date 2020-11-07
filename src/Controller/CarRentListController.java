package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.CampingCarModel;
import Model.CarRentModel;
import View.CarRentListView;
import View.MainView;

public class CarRentListController {
	MainView _view;
	CarRentListView _carRentListView;
	private CampingCarModel campCarModel;
	private CarRentModel carRentModel;

	public CarRentListController() {
		this._view = AppManager.getInstance().getView();
		this._carRentListView = AppManager.getInstance().getCarRentListView();
		campCarModel = new CampingCarModel();
		carRentModel = new CarRentModel();
		this._carRentListView.addButtonListener(new ButtonListener());
		this._view.addCarRentListListener(new CarRentListButtonListener());
		this._view.addUserSearch1Listener(new UserSearch1ButtonListener());
		this._view.addUserSearch2Listener(new UserSearch2ButtonListener());
		this._view.addUserSearch3Listener(new UserSearch3ButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
				if (e.getSource() == _carRentListView.btnRent) {
					if(_view.getCurRow() != -1) {
						if (_carRentListView.tf[0].getText().length() > 0) {
							carRentModel.setRentno(Integer.parseInt(_carRentListView.tf[0].getText()));
						}
						if (_carRentListView.tf[1].getText().length() > 0) {
							carRentModel.setCarid(Integer.parseInt(_carRentListView.tf[1].getText()));
						}
						if (_carRentListView.tf[2].getText().length() > 0) {
							carRentModel.setLicense_no(Integer.parseInt(_carRentListView.tf[2].getText()));
						}
						if (_carRentListView.tf[3].getText().length() > 0) {
							carRentModel.setCompid(Integer.parseInt(_carRentListView.tf[3].getText()));
						}
						if (_carRentListView.tf[4].getText().length() > 0) {
							carRentModel.setRentdate(_carRentListView.tf[4].getText());
						}
						if (_carRentListView.tf[5].getText().length() > 0) {
							carRentModel.setRentalperiod(Integer.parseInt(_carRentListView.tf[5].getText()));
						}
						if (_carRentListView.tf[6].getText().length() > 0) {
							carRentModel.setCharge(Integer.parseInt(_carRentListView.tf[6].getText()));
						}
						if (_carRentListView.tf[7].getText().length() > 0) {
							carRentModel.setPaymentdeadline(_carRentListView.tf[7].getText());
						}
						if (_carRentListView.tf[8].getText().length() > 0) {
							carRentModel.setBillhistory(_carRentListView.tf[8].getText());
						}
						if (_carRentListView.tf[9].getText().length() > 0) {
							carRentModel.setBillhistorycost(_carRentListView.tf[9].getText());
						}					
		
						carRentModel.insert(_carRentListView.getConn());
		
						_carRentListView.fieldReset();
			        } else JOptionPane.showMessageDialog(null, "대여할 차를 선택해주세요");					
				} 
	}

	}

	private class CarRentListButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			ArrayList<Object[]> arr = campCarModel.selectRentAble(_carRentListView.getConn());
			_carRentListView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.model.addRow(arr.get(i));
			}
			System.out.println("rentablelist");
			_view.revalidate();
			_view.repaint();
		}

	}
	
	private class UserSearch1ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _carRentListView.SearchInput("원하는 최대 금액을 입력해주세요");

			_view.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = campCarModel.search1(_carRentListView.getConn(), input);
			if(arr.size() != 0) _carRentListView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.model.addRow(arr.get(i));
			}
			System.out.println("search1");
			_view.revalidate();
			_view.repaint();
		}
		
	}
	
	private class UserSearch2ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _carRentListView.SearchInput("원하는 최소 제조년도를 입력해주세요");
			
			_view.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = campCarModel.search2(_carRentListView.getConn(), input);
			_carRentListView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.model.addRow(arr.get(i));
			}
			System.out.println("search2");
			_view.revalidate();
			_view.repaint();
		}
		
	}
	
	private class UserSearch3ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _carRentListView.SearchInput("원하는 최대 주행거리를 입력해주세요");
			
			_view.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = campCarModel.search3(_carRentListView.getConn(), input);
			_carRentListView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.model.addRow(arr.get(i));
			}
			System.out.println("search3");
			_view.revalidate();
			_view.repaint();
		}
		
	}
	
}
