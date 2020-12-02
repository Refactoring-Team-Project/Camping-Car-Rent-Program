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
import model.CampingCarModel;
import model.CarCheckModel;
import model.CarRentModel;
import view.CampingCarView;
import view.MainView;
import view.RentCarView;

public class RentCarController extends Controller {
	private RentCarView rentCarView;
	private CarRentModel carRentModel;
	private CarCheckModel carCheckModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this._mainView.addAdminButtonListener(Constants.RENTCAR, new mainButtonListener());
	}

	@Override
	public void initModel() {
		dataModel = new CarRentModel();
		carRentModel = (CarRentModel) dataModel;

		updateModel = new CarCheckModel();
		carCheckModel = (CarCheckModel) updateModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getRentCarView();
		rentCarView = (RentCarView) this.thisView;
		thisViewAddListener();
	}

	@Override
	public void setColumnName() {
		column = new Object[]{ "RENT NO", "CAR ID", "LICENSE NO", "COMP ID", "RENT DATE", "RENTAL PERIOD", "CHARGE",
				"PAYMENT DEADLINE", "BILL HISTORY", "BILL HISTORY COST" };
	}
	@Override
	public void setModelColumn(String column, String value) {
		switch(column){
			case "rentno":
				carCheckModel.setRentno(Integer.parseInt(value));
				break;
			case "carid":
				carCheckModel.setCarid(Integer.parseInt(value));
				break;
			case "explain_front":
				carCheckModel.setExplain_front(value);
				break;
			case "explain_left":
				carCheckModel.setExplain_left(value);
				break;
			case "explain_right":
				carCheckModel.setExplain_right(value);
				break;
			case "explain_back":
				carCheckModel.setExplain_back(value);
				break;
			case "repair_required":
				carCheckModel.setRepair_required(value);
				break;
		}
	}



	@Override
	public void thisViewButtonEvent(ActionEvent e) {
		try {
			if (e.getSource() == rentCarView.btnReturn) {
				if (_mainView.getCurRow() != -1) {
					inputButtonEvent();
				}
			}
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());

		}
	}

	@Override
	public void thisViewMouseEvent(MouseEvent e) {
		super.thisViewMouseEvent(e);
		rentCarView.inputField[0]
				.setText(thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0).toString());
		rentCarView.inputField[0].setDisabledTextColor(Color.black);

		rentCarView.inputField[1]
				.setText(thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 1).toString());
		rentCarView.inputField[1].setDisabledTextColor(Color.black);
	}



}
