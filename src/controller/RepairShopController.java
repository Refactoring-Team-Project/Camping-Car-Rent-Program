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
		this.mainView.addAdminButtonListener(Constants.REPAIRSHOP, new mainButtonListener());
	}

	@Override
	public void initModel() {
		getDataModel = new RepairShopModel();
		setDataModel = getDataModel;
		repairShopModel = (RepairShopModel) getDataModel;
	}

	@Override
	public void initConnectedView() {
		this.connectedView = AppManager.getInstance().getRepairShopView();
		repairShopView = (RepairShopView) this.connectedView;
		connectedViewAddListener();
	}

	@Override
	public void setColumnName() {
		columnName = new Object[]{"SHOP ID", "SHOP NAME", "ADDRESS", "PHONE", "MANAGER NAME", "MANAGER EMAIL"};

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


	@Override
	public void connectedViewButtonEvent(ActionEvent e) {
		try {
			if (e.getSource() == repairShopView.btnInput) {
				inputButtonEvent();
			} else if (e.getSource() == repairShopView.btnDelete) {
				deleteButtonEvent();
			} else if (e.getSource() == repairShopView.btnUpdate) {
				updateButtonEvent();
			}
		} catch (NullPointerException e2) {
			JOptionPane.showMessageDialog(null, "null");
		}
	}


}
