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
		this.mainView.addUserButtonListener(Constants.RENTLIST, new mainButtonListener());
		this.mainView.addUserButtonListener(Constants.SEARCH1, new mainButtonListener());
		this.mainView.addUserButtonListener(Constants.SEARCH2, new mainButtonListener());
		this.mainView.addUserButtonListener(Constants.SEARCH3, new mainButtonListener());
	}

	@Override
	public void initModel() {
		getDataModel = new CampingCarModel();
		campCarModel = (CampingCarModel) getDataModel;

		setDataModel = new CarRentModel();
		carRentModel = (CarRentModel) setDataModel;
	}

	@Override
	public void initConnectedView() {
		this.connectedView = AppManager.getInstance().getCarRentListView();
		carRentListView = (CarRentListView) this.connectedView;
		connectedViewAddListener();
	}

	@Override
	public void setColumnName() {
		columnName = new Object[]{"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
	}

	@Override
	public void setModelEachColumn(String column, String value) {
		switch (column) {
			case "rentno":
				carRentModel.setRentNo(Integer.parseInt(value));
				break;
			case "carid":
				carRentModel.setCarId(Integer.parseInt(value));
				break;
			case "license-no":
				carRentModel.setLicenseNo(Integer.parseInt(value));
				break;
			case "compid":
				carRentModel.setCompanyId(Integer.parseInt(value));
				break;
			case "rent_date":
				carRentModel.setRentDate(value);
				break;
			case "rentalperiod":
				carRentModel.setRentalPeriod(Integer.parseInt(value));
				break;
			case "charge":
				carRentModel.setCharge(Integer.parseInt(value));
				break;
			case "paymentdeadline":
				carRentModel.setPaymentDeadline(value);
				break;
			case "billhistory":
				carRentModel.setBillHistory(value);
				break;
			case "billhistorycost":
				carRentModel.setBillHistoryCost(value);
				break;
		}
	}


	@Override
	public void connectedViewButtonEvent(ActionEvent e) {
		try {
			if (e.getSource() == carRentListView.btnRent) {
				if (mainView.getCurrentRow() != -1) {
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
		if (e.getSource() == mainView.btnOnUserPanel[Arrays.asList(Constants.USER_BUTTON_NAME).indexOf(Constants.RENTLIST)]) {
			currentList = RENTABLE.ALLLIST;
		}
		if (e.getSource() == mainView.btnOnUserPanel[Arrays.asList(Constants.USER_BUTTON_NAME).indexOf(Constants.SEARCH1)]) {
			input = carRentListView.searchInput("원하는 최대 금액을 입력해주세요");
			currentList = RENTABLE.SEARCH1;
		}
		if (e.getSource() == mainView.btnOnUserPanel[Arrays.asList(Constants.USER_BUTTON_NAME).indexOf(Constants.SEARCH2)]) {
			input = carRentListView.searchInput("원하는 최소 제조년도를 입력해주세요");
			currentList = RENTABLE.SEARCH2;
		}
		if (e.getSource() == mainView.btnOnUserPanel[Arrays.asList(Constants.USER_BUTTON_NAME).indexOf(Constants.SEARCH3)]) {
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
				arr = campCarModel.selectRentAble(mainView.getConnection());
				break;
			case SEARCH1:
				arr = campCarModel.userPriceSearch(mainView.getConnection(), input);
				break;
			case SEARCH2:
				arr = campCarModel.userManufacturingYearSearch(mainView.getConnection(), input);
				break;
			case SEARCH3:
				arr = campCarModel.userMileageSearch(mainView.getConnection(), input);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + currentList);
		}
		for (int i = 0; i < arr.size(); i++) {
			connectedView.tableModelOnScrollPane.addRow(arr.get(i));
		}
	}

	@Override
	public void connectedViewMouseEvent(MouseEvent e) {
		super.connectedViewMouseEvent(e);
		campCarModel.selectedData(mainView.getConnection(),
				connectedView.DBResult.getModel().getValueAt(mainView.getCurrentRow(), 0));

		connectedView.inputFields[1].setText(Integer.toString(campCarModel.getSelectedCarId()));
		connectedView.inputFields[1].setDisabledTextColor(Color.black);

		connectedView.inputFields[3].setText(Integer.toString(campCarModel.getSelectedCompanyId()));
		connectedView.inputFields[3].setDisabledTextColor(Color.black);
	}

}
