package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.RepairListModel;
import View.MainView;
import View.RepairListView;

public class RepairListController {
	MainView _view;
	RepairListView _repairListView;
	private RepairListModel repairListModel;

	public RepairListController() {
		this._view = AppManager.getInstance().getView();
		this._repairListView = AppManager.getInstance().getRepairListView();
		repairListModel = new RepairListModel();
		this._repairListView.addButtonListener(new ButtonListener());
		this._view.addRepairListListener(new RepairListButtonListener());

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _repairListView.repairListbtnDelete) {
					if (_view.getCurRow() != -1) {
						repairListModel.delete(_view.getConn(),
								_repairListView.repairListdbResult.getModel().getValueAt(_view.getCurRow(), 0));
						_repairListView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				} else if (e.getSource() == _repairListView.repairListbtnUpdate) {
					if (_view.getCurRow() != -1) { // 변경할 데이터를 선택한 것이 있다면
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
						repairListModel.update(_view.getConn(),
								_repairListView.repairListdbResult.getModel().getValueAt(_view.getCurRow(), 0));
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
			_view.changePanel(_repairListView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = repairListModel.select(_view.getConn());
			_repairListView.repairListModel.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_repairListView.repairListModel.addRow(arr.get(i));
			}
			System.out.println("repairList");
			_view.add(AppManager.getInstance().getRepairListView());
			_view.revalidate();
			_view.repaint();
		}

	}


}

