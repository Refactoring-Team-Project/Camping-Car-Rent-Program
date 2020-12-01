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
		this._mainView.addAdminButtonListener(Constants.CARCHECK, new mainButtonListener());
	}

	@Override
	public void initModel() {
		dataModel = new RepairListModel();
		repairListModel = (RepairListModel) dataModel;

		dataModel = new CarCheckModel();
		carCheckModel = (CarCheckModel) dataModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getCarCheckView();
		carCheckView = (CarCheckView) this.thisView;
		this.thisView.addButtonListener(new ButtonListener());
		this.thisView.addMouseListener(new mainMouseListener());
	}

	@Override
	public void setColumnName() {
		column = new Object[]{"RENT NO", "CAR ID", "FRONT EX", "LEFT EX", "RIGHT EX", "BACK EX", "REPAIR REQUIRED"};
	}

	@Override
	public void setModelColumn(String column, String value) {
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

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == carCheckView.btnRequest) {
					if (_mainView.getCurRow() != -1) {
						if (carCheckView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 6).equals("Y")) {
							setModel();
							repairListModel.insert(_mainView.getConn());
							//	thisView.fieldReset();
						} else if (carCheckView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 6).equals("N"))
							JOptionPane.showMessageDialog(null, "데이터 선택이 잘못되었습니다.");
					} else
						JOptionPane.showMessageDialog(null, "요청할 데이터를 선택해 주세요.");

					thisView.fieldReset();
				}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null, "null");
			}
		}

	}

	public class mainMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			_mainView.setCurRow(thisView.DBResult.getSelectedRow());
			_mainView.setCurCol(thisView.DBResult.getSelectedColumn());

			carCheckModel.selectedData(_mainView.getConn(),
					carCheckView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));

			carCheckView.inputField[1].setText(Integer.toString(carCheckModel.getSelectedCarid()));
			carCheckView.inputField[1].setDisabledTextColor(Color.black);

			carCheckView.inputField[3].setText(Integer.toString(carCheckModel.getSelectedCompid()));
			carCheckView.inputField[3].setDisabledTextColor(Color.black);

			carCheckView.inputField[4].setText(Integer.toString(carCheckModel.getSelectedLicense_no()));
			carCheckView.inputField[4].setDisabledTextColor(Color.black);
		}
	}


}
