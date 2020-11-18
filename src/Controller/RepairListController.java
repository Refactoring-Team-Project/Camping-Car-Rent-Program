package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.RepairListModel;
import View.MainView;
import View.RepairListView;

public class RepairListController {
	MainView _mainView;
	RepairListView _repairListView;
	private RepairListModel repairListModel;

	public RepairListController() {
		this._mainView = AppManager.getInstance().getView();
		this._repairListView = AppManager.getInstance().getRepairListView();
		repairListModel = new RepairListModel();
		this._repairListView.addButtonListener(new ButtonListener());
		this._repairListView.addMouseListener(new RepairListMouseListener());
		this._mainView.addRepairListListener(new RepairListButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _repairListView.repairListbtnDelete) {
					if (_mainView.getCurRow() != -1) {
						repairListModel.delete(_mainView.getConn(),
								_repairListView.repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_repairListView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				} else if (e.getSource() == _repairListView.repairListbtnUpdate) {
					if (_mainView.getCurRow() != -1) { // 변경할 데이터를 선택한 것이 있다면
						if (_repairListView.repairListTextField[0].getText().length() > 0) {
							repairListModel.setRepairno(Integer.parseInt(_repairListView.repairListTextField[0].getText()));
						} else
							throw new NullPointerException();
						if (_repairListView.repairListTextField[1].getText().length() > 0) {
							repairListModel.setCarid(Integer.parseInt(_repairListView.repairListTextField[1].getText()));
						}
						if (_repairListView.repairListTextField[2].getText().length() > 0) {
							repairListModel.setShopid(Integer.parseInt(_repairListView.repairListTextField[2].getText()));
						}
						if (_repairListView.repairListTextField[3].getText().length() > 0) {
							repairListModel.setCompid(Integer.parseInt(_repairListView.repairListTextField[3].getText()));
						}
						if (_repairListView.repairListTextField[4].getText().length() > 0) {
							repairListModel.setLicense_no(Integer.parseInt(_repairListView.repairListTextField[4].getText()));
						}
						if (_repairListView.repairListTextField[5].getText().length() > 0) {
							repairListModel.setRepairdetails(_repairListView.repairListTextField[5].getText());
						}
						if (_repairListView.repairListTextField[6].getText().length() > 0) {
							repairListModel.setRepairdate(_repairListView.repairListTextField[6].getText());
						}
						if (_repairListView.repairListTextField[7].getText().length() > 0) {
							repairListModel.setRepaircost(Integer.parseInt(_repairListView.repairListTextField[7].getText()));
						}
						if (_repairListView.repairListTextField[8].getText().length() > 0) {
							repairListModel.setPaymentdeadline(_repairListView.repairListTextField[8].getText());
						}
						if (_repairListView.repairListTextField[9].getText().length() > 0) {
							repairListModel.setRepairhistory(_repairListView.repairListTextField[9].getText());
						}
						repairListModel.update(_mainView.getConn(),
								_repairListView.repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_repairListView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
					}
				}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null, "null");
			}

		}

	}

	private class RepairListButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_repairListView);
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = repairListModel.select(_mainView.getConn());
			_repairListView.repairListModel.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_repairListView.repairListModel.addRow(arr.get(i));
			}
			System.out.println("repairList");
			_mainView.add(AppManager.getInstance().getRepairListView());
			_mainView.revalidate();
			_mainView.repaint();
		}

	}

	private class RepairListMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("hfidfhid");
			_mainView.setCurRow(_repairListView.repairListdbResult.getSelectedRow());
			_mainView.setCurCol(_repairListView.repairListdbResult.getSelectedColumn());

			_repairListView.repairListTextField[0].setText(_repairListView.repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 0).toString());
			_repairListView.repairListTextField[0].setDisabledTextColor(Color.black);

			_repairListView.repairListTextField[1].setText(_repairListView.repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 1).toString());
			_repairListView.repairListTextField[1].setDisabledTextColor(Color.black);

			_repairListView.repairListTextField[2].setText(_repairListView.repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 2).toString());
			_repairListView.repairListTextField[2].setDisabledTextColor(Color.black);

			_repairListView.repairListTextField[3].setText(_repairListView.repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 3).toString());
			_repairListView.repairListTextField[3].setDisabledTextColor(Color.black);

			_repairListView.repairListTextField[4].setText(_repairListView.repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 4).toString());
			_repairListView.repairListTextField[4].setDisabledTextColor(Color.black);
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

}

