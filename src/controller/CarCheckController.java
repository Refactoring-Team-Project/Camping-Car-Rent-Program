package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.CarCheckModel;
import model.RepairListModel;
import view.CarCheckView;

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
			if (e.getSource() == carCheckView.btnRequest) {
				if (mainView.getCurrentRow() != -1) {
					if (carCheckView.DBResult.getModel().getValueAt(mainView.getCurrentRow(), 6).equals("Y")) {
						inputButtonEvent();
					} else if (carCheckView.DBResult.getModel().getValueAt(mainView.getCurrentRow(), 6).equals("N"))
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
		carCheckModel.selectedData(mainView.getConnection(),
				connectedView.DBResult.getModel().getValueAt(mainView.getCurrentRow(), 0));

		connectedView.inputFields[1].setText(Integer.toString(carCheckModel.getSelectedCarId()));
		connectedView.inputFields[1].setDisabledTextColor(Color.black);

		connectedView.inputFields[3].setText(Integer.toString(carCheckModel.getSelectedCompanyId()));
		connectedView.inputFields[3].setDisabledTextColor(Color.black);

		connectedView.inputFields[4].setText(Integer.toString(carCheckModel.getSelectedLicenseNo()));
		connectedView.inputFields[4].setDisabledTextColor(Color.black);
	}


}