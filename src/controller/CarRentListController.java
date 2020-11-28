package controller;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.CampingCarModel;
import model.CarRentModel;
import view.CarRentListView;
import view.MainView;

public class CarRentListController {
	MainView _mainView;
	CarRentListView _carRentListView;
	private CampingCarModel campCarModel;
	private CarRentModel carRentModel;

	public CarRentListController() {
		this._mainView = AppManager.getInstance().getView();
		this._carRentListView = AppManager.getInstance().getCarRentListView();
		campCarModel = new CampingCarModel();
		carRentModel = new CarRentModel();
		this._carRentListView.addButtonListener(new ButtonListener());
		this._carRentListView.addMouseListener(new CarRentListMouseListener());

		this._mainView.addUserButtonListener(Constants.RENTLIST, new CarRentListButtonListener());
		this._mainView.addUserButtonListener(Constants.SEARCH1, new UserSearch1ButtonListener());
		this._mainView.addUserButtonListener(Constants.SEARCH2,new UserSearch2ButtonListener());
		this._mainView.addUserButtonListener(Constants.SEARCH3,new UserSearch3ButtonListener());
	}



	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == _carRentListView.btnRent) {
				if (_mainView.getCurRow() != -1) {
					if (_carRentListView.carRentListInputField[0].getText().length() > 0) {
						carRentModel.setRentno(Integer.parseInt(_carRentListView.carRentListInputField[0].getText()));
					}
					if (_carRentListView.carRentListInputField[1].getText().length() > 0) {
						carRentModel.setCarid(Integer.parseInt(_carRentListView.carRentListInputField[1].getText()));
					}
					if (_carRentListView.carRentListInputField[2].getText().length() > 0) {
						carRentModel.setLicense_no(Integer.parseInt(_carRentListView.carRentListInputField[2].getText()));
					}
					if (_carRentListView.carRentListInputField[3].getText().length() > 0) {
						carRentModel.setCompid(Integer.parseInt(_carRentListView.carRentListInputField[3].getText()));
					}
					if (_carRentListView.carRentListInputField[4].getText().length() > 0) {
						carRentModel.setRentdate(_carRentListView.carRentListInputField[4].getText());
					}
					if (_carRentListView.carRentListInputField[5].getText().length() > 0) {
						carRentModel.setRentalperiod(Integer.parseInt(_carRentListView.carRentListInputField[5].getText()));
					}
					if (_carRentListView.carRentListInputField[6].getText().length() > 0) {
						carRentModel.setCharge(Integer.parseInt(_carRentListView.carRentListInputField[6].getText()));
					}
					if (_carRentListView.carRentListInputField[7].getText().length() > 0) {
						carRentModel.setPaymentdeadline(_carRentListView.carRentListInputField[7].getText());
					}
					if (_carRentListView.carRentListInputField[8].getText().length() > 0) {
						carRentModel.setBillhistory(_carRentListView.carRentListInputField[8].getText());
					}
					if (_carRentListView.carRentListInputField[9].getText().length() > 0) {
						carRentModel.setBillhistorycost(_carRentListView.carRentListInputField[9].getText());
					}

					carRentModel.insert(_mainView.getConn());

					_carRentListView.fieldReset();
				} else
					JOptionPane.showMessageDialog(null, "대여할 차를 선택해주세요");
			}
		}

	}

	private class CarRentListMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			_mainView.setCurRow(_carRentListView.carRentListDBResult.getSelectedRow());
			_mainView.setCurCol(_carRentListView.carRentListDBResult.getSelectedColumn());

			System.out.println(_carRentListView.carRentListDBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
			campCarModel.selectedData(_mainView.getConn(),
					_carRentListView.carRentListDBResult.getModel().getValueAt(_mainView.getCurRow(), 0));

			_carRentListView.carRentListInputField[1].setText(Integer.toString(campCarModel.getSelectedCarid()));
			_carRentListView.carRentListInputField[1].setDisabledTextColor(Color.black);

			_carRentListView.carRentListInputField[3].setText(Integer.toString(campCarModel.getSelectedCompid()));
			_carRentListView.carRentListInputField[3].setDisabledTextColor(Color.black);

		}

	}

	private class CarRentListButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_carRentListView);
			_carRentListView.fieldReset();

			ArrayList<Object[]> arr = campCarModel.selectRentAble(_mainView.getConn());
			Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
			arr.add(0, column);
			_carRentListView.carRentListDefaultTable.setDataVector(null, arr.get(0));

			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.carRentListDefaultTable.addRow(arr.get(i));
			}
			System.out.println("rentablelist");
			_mainView.revalidate();
			_mainView.repaint();
		}

	}

	private class UserSearch1ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _carRentListView.SearchInput("원하는 최대 금액을 입력해주세요");

			_mainView.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = campCarModel.search1(_mainView.getConn(), input);
			Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
			arr.add(0, column);

			_carRentListView.carRentListDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.carRentListDefaultTable.addRow(arr.get(i));
			}
			System.out.println("search1");
			_mainView.revalidate();
			_mainView.repaint();
		}

	}

	private class UserSearch2ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _carRentListView.SearchInput("원하는 최소 제조년도를 입력해주세요");

			_mainView.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = campCarModel.search2(_mainView.getConn(), input);
			Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
			arr.add(0, column);

			_carRentListView.carRentListDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.carRentListDefaultTable.addRow(arr.get(i));
			}
			System.out.println("search2");
			_mainView.revalidate();
			_mainView.repaint();
		}

	}

	private class UserSearch3ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _carRentListView.SearchInput("원하는 최대 주행거리를 입력해주세요");

			_mainView.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = campCarModel.search3(_mainView.getConn(), input);
			Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
			arr.add(0, column);
			_carRentListView.carRentListDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.carRentListDefaultTable.addRow(arr.get(i));
			}
			System.out.println("search3");
			_mainView.revalidate();
			_mainView.repaint();
		}

	}

}
