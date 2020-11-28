package controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.AppManager;
import common.Constants;
import model.RepairListModel;
import view.MainView;
import view.RepairListView;

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
//		this._mainView.addRepairListListener(new RepairListButtonListener());
		this._mainView.addAdminButtonListener(Constants.REPAIRLIST, new RepairListButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _repairListView.btnDelete) {
					if (_mainView.getCurRow() != -1) {
						repairListModel.delete(_mainView.getConn(),
								_repairListView.repairListDBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_repairListView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				} else if (e.getSource() == _repairListView.btnUpdate) {
					if (_mainView.getCurRow() != -1) { // 변경할 데이터를 선택한 것이 있다면
						if (_repairListView.repairListInputField[0].getText().length() > 0) {
							repairListModel.setRepairno(Integer.parseInt(_repairListView.repairListInputField[0].getText()));
						} else
							throw new NullPointerException();
						if (_repairListView.repairListInputField[1].getText().length() > 0) {
							repairListModel.setCarid(Integer.parseInt(_repairListView.repairListInputField[1].getText()));
						}
						if (_repairListView.repairListInputField[2].getText().length() > 0) {
							repairListModel.setShopid(Integer.parseInt(_repairListView.repairListInputField[2].getText()));
						}
						if (_repairListView.repairListInputField[3].getText().length() > 0) {
							repairListModel.setCompid(Integer.parseInt(_repairListView.repairListInputField[3].getText()));
						}
						if (_repairListView.repairListInputField[4].getText().length() > 0) {
							repairListModel.setLicense_no(Integer.parseInt(_repairListView.repairListInputField[4].getText()));
						}
						if (_repairListView.repairListInputField[5].getText().length() > 0) {
							repairListModel.setRepairdetails(_repairListView.repairListInputField[5].getText());
						}
						if (_repairListView.repairListInputField[6].getText().length() > 0) {
							repairListModel.setRepairdate(_repairListView.repairListInputField[6].getText());
						}
						if (_repairListView.repairListInputField[7].getText().length() > 0) {
							repairListModel.setRepaircost(Integer.parseInt(_repairListView.repairListInputField[7].getText()));
						}
						if (_repairListView.repairListInputField[8].getText().length() > 0) {
							repairListModel.setPaymentdeadline(_repairListView.repairListInputField[8].getText());
						}
						if (_repairListView.repairListInputField[9].getText().length() > 0) {
							repairListModel.setRepairhistory(_repairListView.repairListInputField[9].getText());
						}
						repairListModel.update(_mainView.getConn(),
								_repairListView.repairListDBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
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
			Object column[] = { "REPAIR NO", "CAR ID", "SHOP ID", "COMP ID", "LICENSE NO", "REPAIR DETAILS",
					"REPAIR DATE", "REPAIR COST", "PAYMENT DEADLINE", "REPAIRHISTORY" };			arr.add(0, column);
			_repairListView.repairListDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_repairListView.repairListDefaultTable.addRow(arr.get(i));
			}
			System.out.println("repairList");
			_mainView.add(AppManager.getInstance().getRepairListView());
			_mainView.revalidate();
			_mainView.repaint();
		}

	}

	private class RepairListMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("hfidfhid");
			_mainView.setCurRow(_repairListView.repairListDBResult.getSelectedRow());
			_mainView.setCurCol(_repairListView.repairListDBResult.getSelectedColumn());

			_repairListView.repairListInputField[0].setText(_repairListView.repairListDBResult.getModel().getValueAt(_mainView.getCurRow(), 0).toString());
			_repairListView.repairListInputField[0].setDisabledTextColor(Color.black);

			_repairListView.repairListInputField[1].setText(_repairListView.repairListDBResult.getModel().getValueAt(_mainView.getCurRow(), 1).toString());
			_repairListView.repairListInputField[1].setDisabledTextColor(Color.black);

			_repairListView.repairListInputField[2].setText(_repairListView.repairListDBResult.getModel().getValueAt(_mainView.getCurRow(), 2).toString());
			_repairListView.repairListInputField[2].setDisabledTextColor(Color.black);

			_repairListView.repairListInputField[3].setText(_repairListView.repairListDBResult.getModel().getValueAt(_mainView.getCurRow(), 3).toString());
			_repairListView.repairListInputField[3].setDisabledTextColor(Color.black);

			_repairListView.repairListInputField[4].setText(_repairListView.repairListDBResult.getModel().getValueAt(_mainView.getCurRow(), 4).toString());
			_repairListView.repairListInputField[4].setDisabledTextColor(Color.black);
		}



	}

}

