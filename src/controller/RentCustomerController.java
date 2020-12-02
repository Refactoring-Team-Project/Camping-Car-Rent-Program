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
		updateModel = dataModel;
		rentCustomerModel = (RentCustomerModel) dataModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getRentCustomerView();
		rentCustomerView = (RentCustomerView) this.thisView;
		thisViewAddListener();
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


	@Override
	public void thisViewButtonEvent(ActionEvent e) {
		try {
			if (e.getSource() == rentCustomerView.btnInput) {
				inputButtonEvent();
			} else if (e.getSource() == rentCustomerView.btnDelete) {
				deleteButtonEvent();
			} else if (e.getSource() == rentCustomerView.btnUpdate) {
				updateButtonEvent();
			}
		} catch (NullPointerException e2) {
			JOptionPane.showMessageDialog(null, "null");

		}
	}

}
