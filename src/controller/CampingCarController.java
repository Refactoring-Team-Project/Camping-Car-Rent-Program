package controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.CampingCarModel;
import view.CampingCarView;
import view.MainView;

public class CampingCarController {
	MainView _mainView;
	CampingCarView _campCarView;
	private CampingCarModel campCarModel;
	
	public CampingCarController() {
		this._mainView = AppManager.getInstance().getView();
		this._campCarView = AppManager.getInstance().getCampingCarView();
		campCarModel = new CampingCarModel();
		this._campCarView.addButtonListener(new ButtonListener());
		this._campCarView.addMouseListener(new CampCarMouseListener());
//		this._mainView.addCampCarListener(new CampingCarButtonListener());
		this._mainView.addAdminButtonListener(Constants.CAMPCAR, new CampingCarButtonListener());

	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _campCarView.btnInput)
				{
					for(int i = 0; i< Constants.CAMPINGCAR_FIELDNUM; i++) {
						String value = _campCarView.campingCarInputField[i].getText();
						if(value.length() > 0) {
							setModel(Constants.CAMPINGCAR_FIELDSTRING[i], value);
						}
						else throw new NullPointerException();
					}

					campCarModel.insert(_mainView.getConn());
					_campCarView.fieldReset();
				}
				else if (e.getSource() == _campCarView.btnDelete)
				{
					if (_mainView.getCurRow() != -1) {
						campCarModel.delete(_mainView.getConn(), _campCarView.campingCarDBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_campCarView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				}
				else if (e.getSource() == _campCarView.btnUpdate)
				{
					if(_mainView.getCurRow() != -1) {

						for(int i=0;i<Constants.CAMPINGCAR_FIELDNUM; i++) {
							String value = _campCarView.campingCarInputField[i].getText();
							if(value.length() > 0) {
								setModel(Constants.CAMPINGCAR_FIELDSTRING[i], value);
							}
							else throw new NullPointerException();
						}

						campCarModel.update(_mainView.getConn(), _campCarView.campingCarDBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_campCarView.fieldReset();
					}
					
					else JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
				}
			
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "null");
			}
		}
	}

	private void setModel(String column, String value) {
		switch(column) {
			case "carid":
				campCarModel.setCarid(Integer.parseInt(value));
				break;
			case "carname":
				campCarModel.setCarname(value);
				break;
			case "carno":
				campCarModel.setCarno(Integer.parseInt(value));
				break;
			case "seat":
				campCarModel.setSeat(Integer.parseInt(value));
				break;
			case "manufacturer":
				campCarModel.setManufacturer(value);
				break;
			case "manu_year":
				campCarModel.setManu_year(Integer.parseInt(value));
				break;
			case "drivingdistance":
				campCarModel.setDrivingdistance(Integer.parseInt(value));
				break;
			case "rentcost":
				campCarModel.setRentcost(Integer.parseInt(value));
				break;
			case "compid":
				campCarModel.setCompid(Integer.parseInt(value));
				break;
			case "registdate":
				campCarModel.setRegistdate(value);
				break;
		}
	}
	private class CampCarMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			_mainView.setCurRow(_campCarView.campingCarDBResult.getSelectedRow());
			_mainView.setCurCol(_campCarView.campingCarDBResult.getSelectedColumn());
		}
	}
	
	
	private class CampingCarButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_campCarView);
			
			ArrayList<Object[]> arr = campCarModel.select(_mainView.getConn());
			Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
			arr.add(0, column);
			_campCarView.campingCarDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_campCarView.campingCarDefaultTable.addRow(arr.get(i));
			}

			System.out.println("camping carrrrrrrrrrr");
			_mainView.revalidate();
			_mainView.repaint();
			
		}
		
	}
}
