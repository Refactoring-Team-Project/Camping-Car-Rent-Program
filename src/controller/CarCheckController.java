package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.CampingCompanyModel;
import model.CarCheckModel;
import model.RepairListModel;
import view.CarCheckView;
import view.MainView;

public class CarCheckController extends Controller {

	private CarCheckView carCheckView;
	private CarCheckModel carCheckModel;
	private RepairListModel repairListModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this.mainView.addAdminButtonListener(Constants.CARCHECK, new mainButtonListener());
	}

	@Override
	public void initModel() {
		getDataModel = new CarCheckModel();
		carCheckModel = (CarCheckModel) getDataModel;

		setDataModel = new RepairListModel();
		repairListModel = (RepairListModel) setDataModel;
	}

	@Override
	public void initConnectedView() {
		this.connectedView = AppManager.getInstance().getCarCheckView();
		carCheckView = (CarCheckView) this.connectedView;
		connectedViewAddListener();
	}

	@Override
	public void setColumnName() {
		columnName = new Object[]{"RENT NO", "CAR ID", "FRONT EX", "LEFT EX", "RIGHT EX", "BACK EX", "REPAIR REQUIRED"};
	}

	@Override
	public void setModelEachColumn(String column, String value) {
		switch (column) {
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
			if (e.getSource() == carCheckView.btnRequest) {
				if (mainView.getCurRow() != -1) {
					if (carCheckView.DBResult.getModel().getValueAt(mainView.getCurRow(), 6).equals("Y")) {
						inputButtonEvent();
					} else if (carCheckView.DBResult.getModel().getValueAt(mainView.getCurRow(), 6).equals("N"))
						JOptionPane.showMessageDialog(null, "데이터 선택이 잘못되었습니다.");
				} else
					JOptionPane.showMessageDialog(null, "요청할 데이터를 선택해 주세요.");
			}
		} catch (NullPointerException e2) {
			JOptionPane.showMessageDialog(null, "null");
		}
	}



	@Override
	public void connectedViewMouseEvent(MouseEvent e) {
		super.connectedViewMouseEvent(e);
		carCheckModel.selectedData(mainView.getConn(),
				connectedView.DBResult.getModel().getValueAt(mainView.getCurRow(), 0));

		connectedView.inputFields[1].setText(Integer.toString(carCheckModel.getSelectedCarid()));
		connectedView.inputFields[1].setDisabledTextColor(Color.black);

		connectedView.inputFields[3].setText(Integer.toString(carCheckModel.getSelectedCompid()));
		connectedView.inputFields[3].setDisabledTextColor(Color.black);

		connectedView.inputFields[4].setText(Integer.toString(carCheckModel.getSelectedLicense_no()));
		connectedView.inputFields[4].setDisabledTextColor(Color.black);
	}


}