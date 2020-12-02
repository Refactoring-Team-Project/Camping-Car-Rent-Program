package controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.CampingCarModel;
import view.CampingCarView;
import view.MainView;

public class CampingCarController extends Controller {

	private CampingCarView campinCarView;
	private CampingCarModel campingCarModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this.mainView.addAdminButtonListener(Constants.CAMPCAR, new mainButtonListener());
	}
	@Override
	public void initModel() {
		getDataModel = new CampingCarModel();
		setDataModel = getDataModel;
		campingCarModel = (CampingCarModel) getDataModel;
	}

	@Override
	public void initConnectedView() {
		this.connectedView = AppManager.getInstance().getCampingCarView();
		campinCarView = (CampingCarView) this.connectedView;
		connectedViewAddListener();
	}

	@Override
	public void setColumnName() {
		columnName = new Object[]{"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
	}

	@Override
	public void setModelEachColumn(String column, String value) {
		switch(column) {
			case "carid":
				campingCarModel.setCarId(Integer.parseInt(value));
				break;
			case "carname":
				campingCarModel.setCarName(value);
				break;
			case "carno":
				campingCarModel.setCarNo(Integer.parseInt(value));
				break;
			case "seat":
				campingCarModel.setCarSeatNum(Integer.parseInt(value));
				break;
			case "manufacturer":
				campingCarModel.setCarManufacturer(value);
				break;
			case "manu_year":
				campingCarModel.setCarManufacturingYear(Integer.parseInt(value));
				break;
			case "drivingdistance":
				campingCarModel.setDrivingDistance(Integer.parseInt(value));
				break;
			case "rentcost":
				campingCarModel.setCarRentCost(Integer.parseInt(value));
				break;
			case "compid":
				campingCarModel.setCompanyId(Integer.parseInt(value));
				break;
			case "registdate":
				campingCarModel.setCarRegistrationDate(value);
				break;
		}

	}

	@Override
	public void connectedViewButtonEvent(ActionEvent e) {
		try {
			if (e.getSource() == campinCarView.btnInput) {
				inputButtonEvent();
			} else if (e.getSource() == campinCarView.btnDelete) {
				deleteButtonEvent();
			} else if (e.getSource() == campinCarView.btnUpdate) {
				updateButtonEvent();
			}
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "null");
		}
	}


}
