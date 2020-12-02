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
		this.mainView.addAdminButtonListener(Constants.RENTCAR, new mainButtonListener());
	}

	@Override
	public void initModel() {
		getDataModel = new CarRentModel();
		carRentModel = (CarRentModel) getDataModel;

		setDataModel = new CarCheckModel();
		carCheckModel = (CarCheckModel) setDataModel;
	}

	@Override
	public void initConnectedView() {
		this.connectedView = AppManager.getInstance().getRentCarView();
		rentCarView = (RentCarView) this.connectedView;
		connectedViewAddListener();
	}

	@Override
	public void setColumnName() {
		columnName = new Object[]{ "RENT NO", "CAR ID", "LICENSE NO", "COMP ID", "RENT DATE", "RENTAL PERIOD", "CHARGE",
				"PAYMENT DEADLINE", "BILL HISTORY", "BILL HISTORY COST" };
	}
	@Override
	public void setModelEachColumn(String column, String value) {
		switch(column){
			case "rentno":
				carCheckModel.setRentNo(Integer.parseInt(value));
				break;
			case "carid":
				carCheckModel.setCarId(Integer.parseInt(value));
				break;
			case "explain_front":
				carCheckModel.setFrontDetail(value);
				break;
			case "explain_left":
				carCheckModel.setLeftDetail(value);
				break;
			case "explain_right":
				carCheckModel.setRigntDetail(value);
				break;
			case "explain_back":
				carCheckModel.setBackDetail(value);
				break;
			case "repair_required":
				carCheckModel.setRepairRequired(value);
				break;
		}
	}



	@Override
	public void connectedViewButtonEvent(ActionEvent e) {
		try {
			if (e.getSource() == rentCarView.btnReturn) {
				if (mainView.getCurrentRow() != -1) {
					inputButtonEvent();
				}
			}
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());

		}
	}

	@Override
	public void connectedViewMouseEvent(MouseEvent e) {
		super.connectedViewMouseEvent(e);

		for(int i=0; i<2; i++) {
			rentCarView.inputFields[i].setText(connectedView.DBResult.getModel().getValueAt(mainView.getCurrentRow(), i).toString());
			rentCarView.inputFields[0].setDisabledTextColor(Color.black);
		}
	}



}
