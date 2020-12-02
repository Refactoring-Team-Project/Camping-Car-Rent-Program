package controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.CampingCompanyModel;
import view.CampingCompanyView;
import view.MainView;

public class CampingCompanyController extends Controller {

	private CampingCompanyView campingCompanyView;
	private CampingCompanyModel campingCompanyModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this._mainView.addAdminButtonListener(Constants.CAMPCOMP, new mainButtonListener());
	}

	@Override
	public void initModel() {
		dataModel = new CampingCompanyModel();
		updateModel = dataModel;
		campingCompanyModel = (CampingCompanyModel) dataModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getCampingCompanyView();
		campingCompanyView = (CampingCompanyView) this.thisView;
		thisViewAddListener();
	}

	@Override
	public void setColumnName() {
		column = new Object[]{"COMPID", "COMPNAME", "ADDRESS", "PHONE", "MANAGER NAME", "MANAGER EMAIL"};
	}

	@Override
	public void setModelColumn(String column, String value) {
		switch (column) {
			case "compid":
				campingCompanyModel.setCompid(Integer.parseInt(value));
				break;
			case "compname":
				campingCompanyModel.setCompname(value);
				break;
			case "address":
				campingCompanyModel.setAddress(value);
				break;
			case "phone":
				campingCompanyModel.setPhone(value);
				break;
			case "manager_name":
				campingCompanyModel.setManager_name(value);
				break;
			case "manager_email":
				campingCompanyModel.setManager_email(value);
				break;
		}
	}


	@Override
	public void thisViewButtonEvent(ActionEvent e) {

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

