package controller;

import common.AppManager;
import common.Constants;
import model.CampingCarModel;
import model.CarRentModel;
import view.CarRentListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class CarRentListController extends Controller{
	enum RENTABLE {
		ALLLIST,
		SEARCH1,
		SEARCH2,
		SEARCH3
	}
	RENTABLE currentList;
	String input;

	CarRentListView carRentListView;
	private CampingCarModel campCarModel;
	private CarRentModel carRentModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this._mainView.addUserButtonListener(Constants.RENTLIST, new mainButtonListener());
		this._mainView.addUserButtonListener(Constants.SEARCH1, new mainButtonListener());
		this._mainView.addUserButtonListener(Constants.SEARCH2, new mainButtonListener());
		this._mainView.addUserButtonListener(Constants.SEARCH3, new mainButtonListener());
	}

	@Override
	public void initModel() {
		dataModel = new CampingCarModel();
		campCarModel = (CampingCarModel) dataModel;

		updateModel = new CarRentModel();
		carRentModel = (CarRentModel) updateModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getCarRentListView();
		carRentListView = (CarRentListView) this.thisView;
		thisViewAddListener();
	}

	@Override
	public void setColumnName() {
		column = new Object[]{"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
	}

	@Override
	public void setModelColumn(String column, String value) {
		switch (column) {
			case "rentno":
				carRentModel.setRentno(Integer.parseInt(value));
				break;
			case "carid":
				carRentModel.setCarid(Integer.parseInt(value));
				break;
			case "license-no":
				carRentModel.setLicense_no(Integer.parseInt(value));
				break;
			case "compid":
				carRentModel.setCompid(Integer.parseInt(value));
				break;
			case "rent_date":
				carRentModel.setRentdate(value);
				break;
			case "rentalperiod":
				carRentModel.setRentalperiod(Integer.parseInt(value));
				break;
			case "charge":
				carRentModel.setCharge(Integer.parseInt(value));
				break;
			case "paymentdeadline":
				carRentModel.setPaymentdeadline(value);
				break;
			case "billhistory":
				carRentModel.setBillhistory(value);
				break;
			case "billhistorycost":
				carRentModel.setBillhistorycost(value);
				break;
		}
	}


	@Override
	public void thisViewButtonEvent(ActionEvent e) {
		try {
			if (e.getSource() == carRentListView.btnRent) {
				if (_mainView.getCurRow() != -1) {
					inputButtonEvent();
				} else
					JOptionPane.showMessageDialog(null, "대여할 차를 선택해주세요");
			}
		} catch (NullPointerException e2) {
			JOptionPane.showMessageDialog(null, "null");
		}
	}

	@Override
	public void mainViewButtonEvent(ActionEvent e) {
		if (e.getSource() == _mainView.btnUsers[Arrays.asList(Constants.USER_BUTTON_NAME).indexOf(Constants.RENTLIST)]) {
			currentList = RENTABLE.ALLLIST;
		}
		if (e.getSource() == _mainView.btnUsers[Arrays.asList(Constants.USER_BUTTON_NAME).indexOf(Constants.SEARCH1)]) {
			input = carRentListView.searchInput("원하는 최대 금액을 입력해주세요");
			currentList = RENTABLE.SEARCH1;
		}
		if (e.getSource() == _mainView.btnUsers[Arrays.asList(Constants.USER_BUTTON_NAME).indexOf(Constants.SEARCH2)]) {
			input = carRentListView.searchInput("원하는 최소 제조년도를 입력해주세요");
			currentList = RENTABLE.SEARCH2;
		}
		if (e.getSource() == _mainView.btnUsers[Arrays.asList(Constants.USER_BUTTON_NAME).indexOf(Constants.SEARCH3)]) {
			input = carRentListView.searchInput("원하는 최대 주행거리를 입력해주세요");
			currentList = RENTABLE.SEARCH3;
		}
		super.mainViewButtonEvent(e);
	}

	@Override
	public void getDataTableValues() {
		ArrayList<Object[]> arr;
		switch(currentList) {
			case ALLLIST:
				arr = campCarModel.selectRentAble(_mainView.getConn());
				break;
			case SEARCH1:
				arr = campCarModel.search1(_mainView.getConn(), input);
				break;
			case SEARCH2:
				arr = campCarModel.search2(_mainView.getConn(), input);
				break;
			case SEARCH3:
				arr = campCarModel.search3(_mainView.getConn(), input);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + currentList);
		}
		for (int i = 0; i < arr.size(); i++) {
			thisView.tableModel.addRow(arr.get(i));
		}
	}

	@Override
	public void thisViewMouseEvent(MouseEvent e) {
		super.thisViewMouseEvent(e);
		campCarModel.selectedData(_mainView.getConn(),
				thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));

		thisView.inputField[1].setText(Integer.toString(campCarModel.getSelectedCarid()));
		thisView.inputField[1].setDisabledTextColor(Color.black);

		thisView.inputField[3].setText(Integer.toString(campCarModel.getSelectedCompid()));
		thisView.inputField[3].setDisabledTextColor(Color.black);
	}

}
