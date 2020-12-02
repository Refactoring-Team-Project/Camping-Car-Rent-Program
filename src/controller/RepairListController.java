package controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.RepairListModel;
import view.MainView;
import view.RepairListView;

public class RepairListController extends Controller {

	private RepairListModel repairListModel;
	private RepairListView repairListView;

	@Override
	public void setMainView() {
		super.setMainView();
		this.mainView.addAdminButtonListener(Constants.REPAIRLIST, new mainButtonListener());
	}

	@Override
	public void initModel() {
		getDataModel = new RepairListModel();
		setDataModel = getDataModel;
		repairListModel = (RepairListModel) getDataModel;
	}

	@Override
	public void initConnectedView() {
		this.connectedView = AppManager.getInstance().getRepairListView();
		repairListView = (RepairListView) this.connectedView;
		connectedViewAddListener();
	}

	@Override
	public void setColumnName() {
		columnName = new Object[]{ "REPAIR NO", "CAR ID", "SHOP ID", "COMP ID", "LICENSE NO", "REPAIR DETAILS",
				"REPAIR DATE", "REPAIR COST", "PAYMENT DEADLINE", "REPAIRHISTORY" };
	}

	@Override
	public void setModelColumn(String column, String value) {
		switch(column) {
			case "repairno":
				repairListModel.setRepairno(Integer.parseInt(value));
				break;
			case "carid":
				repairListModel.setCarid(Integer.parseInt(value));
				break;
			case "shopid":
				repairListModel.setShopid(Integer.parseInt(value));
				break;
			case "compid":
				repairListModel.setCompid(Integer.parseInt(value));
				break;
			case "license_no":
				repairListModel.setLicense_no(Integer.parseInt(value));
				break;
			case "repairdetails":
				repairListModel.setRepairdetails(value);
				break;
			case "repairdate":
				repairListModel.setRepairdate(value);
				break;
			case "repaircost":
				repairListModel.setRepaircost(Integer.parseInt(value));
				break;
			case "paymentdeadline":
				repairListModel.setPaymentdeadline(value);
				break;
			case "repairhistory":
				repairListModel.setRepairhistory(value);
				break;
		}
	}


	@Override
	public void connectedViewButtonEvent(ActionEvent e) {
		try {
			if (e.getSource() == repairListView.btnDelete) {
				deleteButtonEvent();
			} else if (e.getSource() == repairListView.btnUpdate) {
				updateButtonEvent();
			}
		} catch (NullPointerException e2) {
			JOptionPane.showMessageDialog(null, "null");
		}

	}

}

