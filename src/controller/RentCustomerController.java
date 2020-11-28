package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import model.RentCustomerModel;
import view.MainView;
import view.RentCustomerView;

public class RentCustomerController {
	MainView _mainView;
	RentCustomerView _rentCustView;
	private RentCustomerModel rentCustModel;

	public RentCustomerController() {
		this._mainView = AppManager.getInstance().getView();
		this._rentCustView = AppManager.getInstance().getRentCustomerView();
		rentCustModel = new RentCustomerModel();
		this._rentCustView.addButtonListener(new ButtonListener());
		this._rentCustView.addMouseListener(new RentCustomerMouseListener());
		this._mainView.addRentCustListener(new RentCustomerButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _rentCustView.btnInput) {
					if (_rentCustView.tf[0].getText().length() > 0) {
						rentCustModel.setLicense_no(Integer.parseInt(_rentCustView.tf[0].getText()));
					} else
						throw new NullPointerException();

					if (_rentCustView.tf[1].getText().length() > 0) {
						rentCustModel.setName(_rentCustView.tf[1].getText());
					}
					if (_rentCustView.tf[2].getText().length() > 0) {
						rentCustModel.setAddress(_rentCustView.tf[2].getText());
					}
					if (_rentCustView.tf[3].getText().length() > 0) {
						rentCustModel.setPhone(_rentCustView.tf[3].getText());
					}
					if (_rentCustView.tf[4].getText().length() > 0) {
						rentCustModel.setEmail(_rentCustView.tf[4].getText());
					}

					rentCustModel.insert(_mainView.getConn());
					_rentCustView.fieldReset();
				}

				else if (e.getSource() == _rentCustView.btnDelete) {
					if (_mainView.getCurRow() != -1) {
						rentCustModel.delete(_mainView.getConn(),
								_rentCustView.dbResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_rentCustView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");

				} else if (e.getSource() == _rentCustView.btnUpdate) {
					if (_mainView.getCurRow() != -1) {
						if (_rentCustView.tf[0].getText().length() > 0) {
							rentCustModel.setLicense_no(Integer.parseInt(_rentCustView.tf[0].getText()));
						} else
							throw new NullPointerException();

						if (_rentCustView.tf[1].getText().length() > 0) {
							rentCustModel.setName(_rentCustView.tf[1].getText());
						}
						if (_rentCustView.tf[2].getText().length() > 0) {
							rentCustModel.setAddress(_rentCustView.tf[2].getText());
						}
						if (_rentCustView.tf[3].getText().length() > 0) {
							rentCustModel.setPhone(_rentCustView.tf[3].getText());
						}
						if (_rentCustView.tf[4].getText().length() > 0) {
							rentCustModel.setEmail(_rentCustView.tf[4].getText());
						}
						rentCustModel.update(_mainView.getConn(),
								_rentCustView.dbResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_rentCustView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");

				}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null, "null");

			}
		}
	}

	private class RentCustomerMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			_mainView.setCurRow(_rentCustView.dbResult.getSelectedRow());
			_mainView.setCurCol(_rentCustView.dbResult.getSelectedColumn());
		}

	}

	private class RentCustomerButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_rentCustView);
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = rentCustModel.select(_mainView.getConn());
			Object column[] = { "LICENSE_NO", "NAME", "ADDRESS", "PHONE", "EMAIL" };
			arr.add(0, column);
			_rentCustView.rentCustomerDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_rentCustView.rentCustomerDefaultTable.addRow(arr.get(i));
			}
			System.out.println("cust");
			_mainView.add(AppManager.getInstance().getRentCustomerView());
			_mainView.revalidate();
			_mainView.repaint();
		}
	}

}
