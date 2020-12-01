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
		this._mainView.addAdminButtonListener(Constants.REPAIRLIST, new mainButtonListener());
	}

	@Override
	public void initModel() {
		dataModel = new RepairListModel();
		repairListModel = (RepairListModel) dataModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getRepairListView();
		repairListView = (RepairListView) this.thisView;
		this.thisView.addButtonListener(new ButtonListener());
		this.thisView.addMouseListener(new mainMouseListener());
	}

	@Override
	public void setColumnName() {
		column = new Object[]{ "REPAIR NO", "CAR ID", "SHOP ID", "COMP ID", "LICENSE NO", "REPAIR DETAILS",
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

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == repairListView.btnDelete) {
					if (_mainView.getCurRow() != -1) {
						repairListModel.delete(_mainView.getConn(), thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						thisView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				} else if (e.getSource() == repairListView.btnUpdate) {
					if (_mainView.getCurRow() != -1) { // 변경할 데이터를 선택한 것이 있다면
						setModel();
						repairListModel.update(_mainView.getConn(), thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						thisView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
					}
				}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null, "null");
			}

		}

	}

}

