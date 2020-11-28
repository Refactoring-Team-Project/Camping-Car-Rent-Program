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
import model.CarCheckModel;
import model.CarRentModel;
import view.MainView;
import view.RentCarView;

public class RentCarController {
	MainView _mainView;
	RentCarView _rentCarView;
	private CarRentModel carRentModel;
	private CarCheckModel carCheckModel;

	public RentCarController() {
		this._mainView = AppManager.getInstance().getView();
		this._rentCarView = AppManager.getInstance().getRentCarView();
		carRentModel = new CarRentModel();
		carCheckModel = new CarCheckModel();
		this._rentCarView.addButtonListener(new ButtonListener());
		this._rentCarView.addMouseListener(new RentCarMouseListener());
//		this._mainView.addRentCarListener(new RentCarButtonListener());
		this._mainView.addAdminButtonListener(Constants.RENTCAR, new RentCarButtonListener());

	}


	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _rentCarView.btnReturn) {
					if (_mainView.getCurRow() != -1) {
						if (_rentCarView.tf[0].getText().length() > 0) {
							carCheckModel.setRentno(Integer.parseInt(_rentCarView.tf[0].getText()));
						}
						if (_rentCarView.tf[1].getText().length() > 0) {
							carCheckModel.setCarid(Integer.parseInt(_rentCarView.tf[1].getText()));
						}
						if (_rentCarView.tf[2].getText().length() > 0) {
							carCheckModel.setExplain_front(_rentCarView.tf[2].getText());
						}
						if (_rentCarView.tf[3].getText().length() > 0) {
							carCheckModel.setExplain_left(_rentCarView.tf[3].getText());
						}
						if (_rentCarView.tf[4].getText().length() > 0) {
							carCheckModel.setExplain_right(_rentCarView.tf[4].getText());
						}
						if (_rentCarView.tf[5].getText().length() > 0) {
							carCheckModel.setExplain_back(_rentCarView.tf[5].getText());
						}
						if (_rentCarView.tf[6].getText().length() > 0) {
							carCheckModel.setRepair_required(_rentCarView.tf[6].getText());
						} else throw new NullPointerException();

						carCheckModel.insert(_mainView.getConn());
						_rentCarView.fieldReset();
						JOptionPane.showMessageDialog(null, "반환정보를 점검내역에 저장하였습니다.");
					}
				}
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());

			}

		}

	}

	private class RentCarMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			_mainView.setCurRow(_rentCarView.dbResult.getSelectedRow());
			_mainView.setCurCol(_rentCarView.dbResult.getSelectedColumn());

			_rentCarView.tf[0]
					.setText(_rentCarView.dbResult.getModel().getValueAt(_mainView.getCurRow(), 0).toString());
			_rentCarView.tf[0].setDisabledTextColor(Color.black);

			_rentCarView.tf[1]
					.setText(_rentCarView.dbResult.getModel().getValueAt(_mainView.getCurRow(), 1).toString());
			_rentCarView.tf[1].setDisabledTextColor(Color.black);
		}
	}

	private class RentCarButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_rentCarView);
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = carRentModel.select(_mainView.getConn());
			Object column[] = { "RENT NO", "CAR ID", "LICENSE NO", "COMP ID", "RENT DATE", "RENTAL PERIOD", "CHARGE",
					"PAYMENT DEADLINE", "BILL HISTORY", "BILL HISTORY COST" };
			arr.add(0, column);
			_rentCarView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_rentCarView.model.addRow(arr.get(i));
			}
			System.out.println("rent carrrrrrrrrr");
			_mainView.revalidate();
			_mainView.repaint();
		}

	}
}
