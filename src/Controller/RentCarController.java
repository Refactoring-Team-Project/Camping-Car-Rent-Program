package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.RentCarModel;
import View.MainView;
import View.RentCarView;

public class RentCarController {
	MainView _view;
	RentCarView _rentCarView;
	private RentCarModel rentCarModel;

	public RentCarController() {
		this._view = AppManager.getInstance().getView();
		this._rentCarView = AppManager.getInstance().getRentCarView();
		rentCarModel = new RentCarModel();
		this._rentCarView.addButtonListener(new ButtonListener());
		this._rentCarView.addMouseListener(new RentCarMouseListener());
		this._view.addRentCarListener(new RentCarButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _rentCarView.btnReturn) {
					if (_view.getCurRow() != -1) {
						if (_rentCarView.tf[0].getText().length() > 0) {
							rentCarModel.setRentno(Integer.parseInt(_rentCarView.tf[0].getText()));
						}
						if (_rentCarView.tf[1].getText().length() > 0) {
							rentCarModel.setCarid(Integer.parseInt(_rentCarView.tf[1].getText()));
						}
						if (_rentCarView.tf[2].getText().length() > 0) {
							rentCarModel.setExplain_front(_rentCarView.tf[2].getText());
						}
						if (_rentCarView.tf[3].getText().length() > 0) {
							rentCarModel.setExplain_left(_rentCarView.tf[3].getText());
						}
						if (_rentCarView.tf[4].getText().length() > 0) {
							rentCarModel.setExplain_right(_rentCarView.tf[4].getText());
						}
						if (_rentCarView.tf[5].getText().length() > 0) {
							rentCarModel.setExplain_back(_rentCarView.tf[5].getText());
						}
						if (_rentCarView.tf[6].getText().length() > 0) {
							rentCarModel.setRepair_required(_rentCarView.tf[6].getText());
						} else
							throw new NullPointerException();

						rentCarModel.insert(_view.getConn());
						_rentCarView.fieldReset();
						JOptionPane.showMessageDialog(null, "반환정보를 점검내역에 저장하였습니다.");
					}
				}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null, "null");

			}

		}

	}

	private class RentCarMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			_view.setCurRow(_rentCarView.dbResult.getSelectedRow());
			_view.setCurCol(_rentCarView.dbResult.getSelectedColumn());

			_rentCarView.tf[0].setText(_rentCarView.dbResult.getModel().getValueAt(_view.getCurRow(), 0).toString());
			_rentCarView.tf[0].setDisabledTextColor(Color.black);

			_rentCarView.tf[1].setText(_rentCarView.dbResult.getModel().getValueAt(_view.getCurRow(), 1).toString());
			_rentCarView.tf[1].setDisabledTextColor(Color.black);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	private class RentCarButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_rentCarView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = rentCarModel.select(_view.getConn());
			_rentCarView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_rentCarView.model.addRow(arr.get(i));
			}
			System.out.println("rent carrrrrrrrrr");
			_view.revalidate();
			_view.repaint();
		}

	}
}
