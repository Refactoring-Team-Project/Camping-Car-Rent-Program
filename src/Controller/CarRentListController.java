package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.CampingCarModel;
import Model.CarRentModel;
import View.CarRentListView;
import View.MainView;

public class CarRentListController {
	MainView _view;
	CarRentListView _carRentListView;
	private CampingCarModel campCarModel;
	private CarRentModel carRentModel;

	public CarRentListController() {
		this._view = AppManager.getInstance().getView();
		this._carRentListView = AppManager.getInstance().getCarRentListView();
		campCarModel = new CampingCarModel();
		carRentModel = new CarRentModel();
		this._carRentListView.addButtonListener(new ButtonListener());
		this._carRentListView.addMouseListener(new CarRentListMouseListener());
		this._view.addCarRentListListener(new CarRentListButtonListener());
		this._view.addUserPriceSearch(new UserSearch1ButtonListener());
		this._view.addUserManufacturingYearSearch(new UserSearch2ButtonListener());
		this._view.addUserMileageSearch(new UserSearch3ButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == _carRentListView.btnRent) {
				if (_view.getCurRow() != -1) {
					if (_carRentListView.tf[0].getText().length() > 0) {
						carRentModel.setRentno(Integer.parseInt(_carRentListView.tf[0].getText()));
					}
					if (_carRentListView.tf[1].getText().length() > 0) {
						carRentModel.setCarid(Integer.parseInt(_carRentListView.tf[1].getText()));
					}
					if (_carRentListView.tf[2].getText().length() > 0) {
						carRentModel.setLicense_no(Integer.parseInt(_carRentListView.tf[2].getText()));
					}
					if (_carRentListView.tf[3].getText().length() > 0) {
						carRentModel.setCompid(Integer.parseInt(_carRentListView.tf[3].getText()));
					}
					if (_carRentListView.tf[4].getText().length() > 0) {
						carRentModel.setRentdate(_carRentListView.tf[4].getText());
					}
					if (_carRentListView.tf[5].getText().length() > 0) {
						carRentModel.setRentalperiod(Integer.parseInt(_carRentListView.tf[5].getText()));
					}
					if (_carRentListView.tf[6].getText().length() > 0) {
						carRentModel.setCharge(Integer.parseInt(_carRentListView.tf[6].getText()));
					}
					if (_carRentListView.tf[7].getText().length() > 0) {
						carRentModel.setPaymentdeadline(_carRentListView.tf[7].getText());
					}
					if (_carRentListView.tf[8].getText().length() > 0) {
						carRentModel.setBillhistory(_carRentListView.tf[8].getText());
					}
					if (_carRentListView.tf[9].getText().length() > 0) {
						carRentModel.setBillhistorycost(_carRentListView.tf[9].getText());
					}

					carRentModel.insert(_view.getConn());

					_carRentListView.fieldReset();
				} else
					JOptionPane.showMessageDialog(null, "대여할 차를 선택해주세요");
			}
		}

	}

	private class CarRentListMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			_view.setCurRow(_carRentListView.dbResult.getSelectedRow());
			_view.setCurCol(_carRentListView.dbResult.getSelectedColumn());

			System.out.println(_carRentListView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
			campCarModel.selectedData(_view.getConn(),
					_carRentListView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));

			_carRentListView.tf[1].setText(Integer.toString(campCarModel.getSelectedCarid()));
			_carRentListView.tf[1].setDisabledTextColor(Color.black);

			_carRentListView.tf[3].setText(Integer.toString(campCarModel.getSelectedCompid()));
			_carRentListView.tf[3].setDisabledTextColor(Color.black);

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class CarRentListButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			ArrayList<Object[]> arr = campCarModel.selectRentAble(_view.getConn());
			_carRentListView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.model.addRow(arr.get(i));
			}
			System.out.println("rentablelist");
			_view.revalidate();
			_view.repaint();
		}

	}

	private class UserSearch1ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _carRentListView.SearchInput("원하는 최대 금액을 입력해주세요");

			_view.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = campCarModel.search1(_view.getConn(), input);
			if (arr.size() != 0)
				_carRentListView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.model.addRow(arr.get(i));
			}
			System.out.println("search1");
			_view.revalidate();
			_view.repaint();
		}

	}

	private class UserSearch2ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _carRentListView.SearchInput("원하는 최소 제조년도를 입력해주세요");

			_view.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = campCarModel.search2(_view.getConn(), input);
			_carRentListView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.model.addRow(arr.get(i));
			}
			System.out.println("search2");
			_view.revalidate();
			_view.repaint();
		}

	}

	private class UserSearch3ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = _carRentListView.SearchInput("원하는 최대 주행거리를 입력해주세요");

			_view.changePanel(_carRentListView);
			_carRentListView.fieldReset();
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = campCarModel.search3(_view.getConn(), input);
			_carRentListView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carRentListView.model.addRow(arr.get(i));
			}
			System.out.println("search3");
			_view.revalidate();
			_view.repaint();
		}

	}

}
