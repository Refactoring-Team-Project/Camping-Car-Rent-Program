package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.RentCustomerModel;
import view.MainView;
import view.RentCustomerView;

public class RentCustomerController extends Controller{

	private RentCustomerView rentCustomerView;
	private RentCustomerModel rentCustomerModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this._mainView.addAdminButtonListener(Constants.CUSTOMER, new mainButtonListener());
	}

	@Override
	public void initModel() {
		dataModel = new RentCustomerModel();
		rentCustomerModel = (RentCustomerModel) dataModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getRentCustomerView();
		rentCustomerView = (RentCustomerView) this.thisView;
		this.thisView.addButtonListener(new ButtonListener());
		this.thisView.addMouseListener(new mainMouseListener());
	}

	@Override
	public void setColumnName() {
		column = new Object[]{ "LICENSE_NO", "NAME", "ADDRESS", "PHONE", "EMAIL" };

	}

	@Override
	public void setModelColumn(String column, String value) {
		switch (column){
		case "license_no":
			rentCustomerModel.setLicense_no(Integer.parseInt(value));
			break;
		case "name":
			rentCustomerModel.setName(value);
			break;
		case "address":
			rentCustomerModel.setAddress(value);
			break;
		case "phone":
			rentCustomerModel.setPhone(value);
			break;
		case "email":
			rentCustomerModel.setEmail(value);
			break;
		}
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == rentCustomerView.btnInput) {
					setModel();
					rentCustomerModel.insert(_mainView.getConn());
					thisView.fieldReset();
				}

				else if (e.getSource() == rentCustomerView.btnDelete) {
					if (_mainView.getCurRow() != -1) {
						rentCustomerModel.delete(_mainView.getConn(),
								thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						thisView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");

				} else if (e.getSource() == rentCustomerView.btnUpdate) {
					if (_mainView.getCurRow() != -1) {
						setModel();
						rentCustomerModel.update(_mainView.getConn(),
								thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						thisView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");

				}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null, "null");

			}
		}
	}

}
