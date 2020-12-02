package controller;

import java.awt.event.*;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.RepairListModel;
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
	public void setModelEachColumn(String column, String value) {
		switch(column) {
			case "repairno":
				repairListModel.setRepairNo(Integer.parseInt(value));
				break;
			case "carid":
				repairListModel.setCarId(Integer.parseInt(value));
				break;
			case "shopid":
				repairListModel.setShopId(Integer.parseInt(value));
				break;
			case "compid":
				repairListModel.setCompId(Integer.parseInt(value));
				break;
			case "license_no":
				repairListModel.setLicenseNo(Integer.parseInt(value));
				break;
			case "repairdetails":
				repairListModel.setRepairDetails(value);
				break;
			case "repairdate":
				repairListModel.setRepairDate(value);
				break;
			case "repaircost":
				repairListModel.setRepairCost(Integer.parseInt(value));
				break;
			case "paymentdeadline":
				repairListModel.setPaymentDeadline(value);
				break;
			case "repairhistory":
				repairListModel.setRepairHistory(value);
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

