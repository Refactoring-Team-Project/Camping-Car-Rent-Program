package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.CampingCarModel;
import model.RepairShopModel;
import view.CampingCarView;
import view.MainView;
import view.RepairShopView;

public class RepairShopController extends Controller {

	private RepairShopView repairShopView;
	private RepairShopModel repairShopModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this._mainView.addAdminButtonListener(Constants.REPAIRSHOP, new mainButtonListener());
	}

	@Override
	public void initModel() {
		dataModel = new RepairShopModel();
		repairShopModel = (RepairShopModel) dataModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getRepairShopView();
		repairShopView = (RepairShopView) this.thisView;
		this.thisView.addButtonListener(new ButtonListener());
		this.thisView.addMouseListener(new mainMouseListener());
	}

	@Override
	public void setColumnName() {
		column = new Object[]{"SHOP ID", "SHOP NAME", "ADDRESS", "PHONE", "MANAGER NAME", "MANAGER EMAIL"};

	}

	@Override
	public void setModelColumn(String column, String value) {
		switch (column){
			case "shopid":
				repairShopModel.setShopid(Integer.parseInt(value));
				break;
			case "shopname":
				repairShopModel.setShopid(Integer.parseInt(value));
				break;
			case "address":
				repairShopModel.setAddress(value);
				break;
			case "phone":
				repairShopModel.setPhone(value);
				break;
			case "manager_name":
				repairShopModel.setManager_name(value);
				break;
			case "manager_email":
				repairShopModel.setManager_email(value);
				break;
		}
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == repairShopView.btnInput) {
					setModel();
					repairShopModel.insert(_mainView.getConn());
					thisView.fieldReset();
				} else if (e.getSource() == repairShopView.btnDelete) {
					if (_mainView.getCurRow() != -1) {
						repairShopModel.delete(_mainView.getConn(),
								thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						thisView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				} else if (e.getSource() == repairShopView.btnUpdate) {
					if (_mainView.getCurRow() != -1) { // 변경할 데이터를 선택한 것이 있다면
						setModel();
						repairShopModel.update(_mainView.getConn(),
								thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
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
