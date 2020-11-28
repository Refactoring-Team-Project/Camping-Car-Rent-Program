package controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import model.CampingCompanyModel;
import view.CampingCompanyView;
import view.MainView;

public class CampingCompanyController {
	MainView _mainView;
	CampingCompanyView _campCompView;
	private CampingCompanyModel campCompModel;

	public CampingCompanyController() {
		this._mainView = AppManager.getInstance().getView();
		this._campCompView = AppManager.getInstance().getCampingCompanyView();
		campCompModel = new CampingCompanyModel();
		this._campCompView.addMouseListener(new CampingCompanyMouseListener());
		this._campCompView.addButtonListener(new ButtonListener());
		this._mainView.addCampCompListener(new CampingCompanyButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				if (e.getSource() == _campCompView.btnInput) {
					if (_campCompView.campingCompanyInputField[0].getText().length() > 0) {
						campCompModel.setCompid(Integer.parseInt(_campCompView.campingCompanyInputField[0].getText()));
					}
					if (_campCompView.campingCompanyInputField[1].getText().length() > 0) {
						campCompModel.setCompname(_campCompView.campingCompanyInputField[1].getText());
					}
					if (_campCompView.campingCompanyInputField[2].getText().length() > 0) {
						campCompModel.setAddress(_campCompView.campingCompanyInputField[2].getText());
					}
					if (_campCompView.campingCompanyInputField[3].getText().length() > 0) {
						campCompModel.setPhone(_campCompView.campingCompanyInputField[3].getText());
					}
					if (_campCompView.campingCompanyInputField[4].getText().length() > 0) {
						campCompModel.setManager_name(_campCompView.campingCompanyInputField[4].getText());
					}
					if (_campCompView.campingCompanyInputField[5].getText().length() > 0) {
						campCompModel.setManager_email(_campCompView.campingCompanyInputField[5].getText());
					}
	
					campCompModel.insert(_mainView.getConn());
	
					
				} else if (e.getSource() == _campCompView.btnDelete) {
					if (_mainView.getCurRow() != -1) {
						campCompModel.delete(_mainView.getConn(), _campCompView.campingCompanyDBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_campCompView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
	
				} else if (e.getSource() == _campCompView.btnUpdate) {
					if (_mainView.getCurRow() != -1) {
						if (_campCompView.campingCompanyInputField[0].getText().length() > 0) {
							campCompModel.setCompid(Integer.parseInt(_campCompView.campingCompanyInputField[0].getText()));
						} else
							throw new NullPointerException();
					
						if (_campCompView.campingCompanyInputField[1].getText().length() > 0) {
							campCompModel.setCompname(_campCompView.campingCompanyInputField[1].getText());
						}
						if (_campCompView.campingCompanyInputField[2].getText().length() > 0) {
							campCompModel.setAddress(_campCompView.campingCompanyInputField[2].getText());
						}
						if (_campCompView.campingCompanyInputField[3].getText().length() > 0) {
							campCompModel.setPhone(_campCompView.campingCompanyInputField[3].getText());
						}
						if (_campCompView.campingCompanyInputField[4].getText().length() > 0) {
							campCompModel.setManager_name(_campCompView.campingCompanyInputField[4].getText());
						}
						if (_campCompView.campingCompanyInputField[5].getText().length() > 0) {
							campCompModel.setManager_email(_campCompView.campingCompanyInputField[5].getText());
						}
						campCompModel.update(_mainView.getConn(),
								_campCompView.campingCompanyDBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_campCompView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
				}
		
				_campCompView.fieldReset();
		} catch (NullPointerException e2) {
			JOptionPane.showMessageDialog(null, "null");
	
			}
		}

	}
	
	private class CampingCompanyMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			_mainView.setCurRow(_campCompView.campingCompanyDBResult.getSelectedRow());
			_mainView.setCurCol(_campCompView.campingCompanyDBResult.getSelectedColumn());
		}
	}

	private class CampingCompanyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_campCompView);
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = campCompModel.select(_mainView.getConn());
			Object column[] = {"COMPID", "COMPNAME", "ADDRESS", "PHONE", "MANAGER NAME", "MANAGER EMAIL"};
			arr.add(0, column);
			_campCompView.campingCompanyDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_campCompView.campingCompanyDefaultTable.addRow(arr.get(i));
			}
			System.out.println("campcomp");
			_mainView.revalidate();
			_mainView.repaint();
		}

	}
}
