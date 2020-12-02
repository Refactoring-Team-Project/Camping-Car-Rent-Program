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
		this._mainView.addAdminButtonListener(Constants.CAMPCAR, new mainButtonListener());
	}
	@Override
	public void initModel() {
		dataModel = new CampingCarModel();
		updateModel = dataModel;
		campingCarModel = (CampingCarModel) dataModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getCampingCarView();
		campinCarView = (CampingCarView) this.thisView;
		thisViewAddListener();
	}

	@Override
	public void setColumnName() {
		column = new Object[]{"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
	}

	@Override
	public void setModelColumn(String column, String value) {
		switch(column) {
			case "carid":
				campingCarModel.setCarid(Integer.parseInt(value));
				break;
			case "carname":
				campingCarModel.setCarname(value);
				break;
			case "carno":
				campingCarModel.setCarno(Integer.parseInt(value));
				break;
			case "seat":
				campingCarModel.setSeat(Integer.parseInt(value));
				break;
			case "manufacturer":
				campingCarModel.setManufacturer(value);
				break;
			case "manu_year":
				campingCarModel.setManu_year(Integer.parseInt(value));
				break;
			case "drivingdistance":
				campingCarModel.setDrivingdistance(Integer.parseInt(value));
				break;
			case "rentcost":
				campingCarModel.setRentcost(Integer.parseInt(value));
				break;
			case "compid":
				campingCarModel.setCompid(Integer.parseInt(value));
				break;
			case "registdate":
				campingCarModel.setRegistdate(value);
				break;
		}

	}

	@Override
	public void thisViewButtonEvent(ActionEvent e) {
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
