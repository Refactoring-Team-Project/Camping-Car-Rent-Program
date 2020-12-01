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
		campingCompanyModel = (CampingCompanyModel) dataModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getCampingCompanyView();
		campingCompanyView = (CampingCompanyView) this.thisView;
		this.thisView.addButtonListener(new ButtonListener());
		this.thisView.addMouseListener(new mainMouseListener());
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

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				if (e.getSource() == campingCompanyView.btnInput)
				{
					setModel();
					campingCompanyModel.insert(_mainView.getConn());
					thisView.fieldReset();
				} else if (e.getSource() == campingCompanyView.btnDelete) {
					if (_mainView.getCurRow() != -1) {
						campingCompanyModel.delete(_mainView.getConn(), thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						thisView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
				} else if (e.getSource() == campingCompanyView.btnUpdate) {
					if (_mainView.getCurRow() != -1) {
						setModel();
						campingCompanyModel.update(_mainView.getConn(), thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
					} else
						JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
				}
		} catch (NullPointerException e2) {
			JOptionPane.showMessageDialog(null, "null");
	
			}
		}

	}
}

