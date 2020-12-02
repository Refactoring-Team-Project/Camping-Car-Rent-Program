package controller;

import java.awt.event.*;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.CampingCompanyModel;
import view.CampingCompanyView;

public class CampingCompanyController extends Controller {

	private CampingCompanyView campingCompanyView;
	private CampingCompanyModel campingCompanyModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this.mainView.addAdminButtonListener(Constants.CAMPCOMP, new mainButtonListener());
	}

	@Override
	public void initModel() {
		getDataModel = new CampingCompanyModel();
		setDataModel = getDataModel;
		campingCompanyModel = (CampingCompanyModel) getDataModel;
	}

	@Override
	public void initConnectedView() {
		this.connectedView = AppManager.getInstance().getCampingCompanyView();
		campingCompanyView = (CampingCompanyView) this.connectedView;
		connectedViewAddListener();
	}

	@Override
	public void setColumnName() {
		columnName = new Object[]{"COMPID", "COMPNAME", "ADDRESS", "PHONE", "MANAGER NAME", "MANAGER EMAIL"};
	}

	@Override
	public void setModelEachColumn(String column, String value) {
		switch (column) {
			case "compid":
				campingCompanyModel.setCompanyId(Integer.parseInt(value));
				break;
			case "compname":
				campingCompanyModel.setCompanyName(value);
				break;
			case "address":
				campingCompanyModel.setCompanyAddress(value);
				break;
			case "phone":
				campingCompanyModel.setCompanyContact(value);
				break;
			case "manager_name":
				campingCompanyModel.setCompanyManagerName(value);
				break;
			case "manager_email":
				campingCompanyModel.setCompanyManagerEmail(value);
				break;
		}
	}


	@Override
	public void connectedViewButtonEvent(ActionEvent e) {

		try {
			if (e.getSource() == campingCompanyView.btnInput) {
				inputButtonEvent();
			} else if (e.getSource() == campingCompanyView.btnDelete) {
				deleteButtonEvent();
			} else if (e.getSource() == campingCompanyView.btnUpdate) {
				updateButtonEvent();
			}
	} catch (NullPointerException e2) {
		JOptionPane.showMessageDialog(null, "null");

		}
	}

}

