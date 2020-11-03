package team_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RentCustomerController {
	MainView _view;
	RentCustomerView _rentCustView;
	private RentCustomerModel rentCustModel;

	public RentCustomerController() {
		this._view = AppManager.getInstance().getView();
		this._rentCustView = AppManager.getInstance().getRentCustomerView();
		rentCustModel = new RentCustomerModel();
		this._rentCustView.addButtonListener(new ButtonListener());
		this._view.addRentCustListener(new RentCustomerButtonListener());
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

					rentCustModel.insert(_rentCustView.getConn());
					_rentCustView.fieldReset();
				}

				else if (e.getSource() == _rentCustView.btnDelete) {
					if (_view.getCurRow() != -1) {
						rentCustModel.delete(_rentCustView.getConn(),
								_rentCustView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
						_rentCustView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");

				} else if (e.getSource() == _rentCustView.btnUpdate) {
					if (_view.getCurRow() != -1) {
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
						rentCustModel.update(_rentCustView.getConn(),
								_rentCustView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
						_rentCustView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");

				}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null, "null");

			}
		}
	}

	private class RentCustomerButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_rentCustView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = rentCustModel.select(_rentCustView.getConn());
			_rentCustView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_rentCustView.model.addRow(arr.get(i));
			}
			System.out.println("cust");
			_view.add(AppManager.getInstance().getRentCustomerView());
			_view.revalidate();
			_view.repaint();
		}
	}

}
