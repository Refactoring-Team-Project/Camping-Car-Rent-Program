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
		this.mainView.addAdminButtonListener(Constants.CUSTOMER, new mainButtonListener());
	}

	@Override
	public void initModel() {
		getDataModel = new RentCustomerModel();
		setDataModel = getDataModel;
		rentCustomerModel = (RentCustomerModel) getDataModel;
	}

	@Override
	public void initConnectedView() {
		this.connectedView = AppManager.getInstance().getRentCustomerView();
		rentCustomerView = (RentCustomerView) this.connectedView;
		connectedViewAddListener();
	}

	@Override
	public void setColumnName() {
		columnName = new Object[]{ "LICENSE_NO", "NAME", "ADDRESS", "PHONE", "EMAIL" };

	}

	@Override
	public void setModelEachColumn(String column, String value) {
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
	public void connectedViewButtonEvent(ActionEvent e) {
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
