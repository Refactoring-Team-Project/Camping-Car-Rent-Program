package controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.CampingCarModel;
import view.CampingCarView;
import view.MainView;

public class CampingCarController extends Controller {

	private CampingCarView campingCarView;
	private CampingCarModel campingCarModel;

	@Override
	public void setMainView() {
		super.setMainView();
		this._mainView.addAdminButtonListener(Constants.CAMPCAR, new mainButtonListener());
	}
	@Override
	public void initModel() {
		dataModel = new CampingCarModel();
		campingCarModel = (CampingCarModel) dataModel;
	}

	@Override
	public void initView() {
		this.thisView = AppManager.getInstance().getCampingCarView();
		campingCarView = (CampingCarView) this.thisView;
		this.thisView.addButtonListener(new ButtonListener());
		this.thisView.addMouseListener(new mainMouseListener());
	}

	@Override
	public void setColumnName() {
		column = new Object[]{"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
	}

	@Override
	public void setModelColumn(String column, String value) {
		switch(column) {
			case "carid":
				campingCarModel.setCarid(Integer.parseInt(value));
				break;
			case "carname":
				campingCarModel.setCarname(value);
				break;
			case "carno":
				campingCarModel.setCarno(Integer.parseInt(value));
				break;
			case "seat":
				campingCarModel.setSeat(Integer.parseInt(value));
				break;
			case "manufacturer":
				campingCarModel.setManufacturer(value);
				break;
			case "manu_year":
				campingCarModel.setManu_year(Integer.parseInt(value));
				break;
			case "drivingdistance":
				campingCarModel.setDrivingdistance(Integer.parseInt(value));
				break;
			case "rentcost":
				campingCarModel.setRentcost(Integer.parseInt(value));
				break;
			case "compid":
				campingCarModel.setCompid(Integer.parseInt(value));
				break;
			case "registdate":
				campingCarModel.setRegistdate(value);
				break;
		}

	}

	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == campingCarView.btnInput)
				{
					setModel();
					campingCarModel.insert(_mainView.getConn());
					thisView.fieldReset();
				}
				else if (e.getSource() == campingCarView.btnDelete)
				{
					if (_mainView.getCurRow() != -1) {
						campingCarModel.delete(_mainView.getConn(), campingCarView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						thisView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				}
				else if (e.getSource() == campingCarView.btnUpdate) {
					if(_mainView.getCurRow() != -1) {
						setModel();
						campingCarModel.update(_mainView.getConn(), thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						thisView.fieldReset();
					}
					else JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");

				}
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "null");
			}

		}
	};

}
