package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.RepairShopModel;
import View.MainView;
import View.RepairShopView;

public class RepairShopController {
	MainView _mainView;
	RepairShopView _repairShopView;
	private RepairShopModel repairShopModel;

	public RepairShopController() {
		this._mainView = AppManager.getInstance().getView();
		this._repairShopView = AppManager.getInstance().getRepairShopView();
		repairShopModel = new RepairShopModel();
		this._repairShopView.addButtonListener(new ButtonListener());
		this._repairShopView.addMouseListener(new RepairShopMouseListener());
		this._mainView.addRepairShopListener(new RepairShopButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _repairShopView.btnInput) {
					if (_repairShopView.tf[0].getText().length() > 0) {
						repairShopModel.setShopid(Integer.parseInt(_repairShopView.tf[0].getText()));
					} else
						throw new NullPointerException();
					if (_repairShopView.tf[1].getText().length() > 0) {
						repairShopModel.setShopname(_repairShopView.tf[1].getText());
					}
					if (_repairShopView.tf[2].getText().length() > 0) {
						repairShopModel.setAddress(_repairShopView.tf[2].getText());
					}
					if (_repairShopView.tf[3].getText().length() > 0) {
						repairShopModel.setPhone(_repairShopView.tf[3].getText());
					}
					if (_repairShopView.tf[4].getText().length() > 0) {
						repairShopModel.setManager_name(_repairShopView.tf[4].getText());
					}
					if (_repairShopView.tf[5].getText().length() > 0) {
						repairShopModel.setManager_email(_repairShopView.tf[5].getText());
					}

					repairShopModel.insert(_mainView.getConn());

					_repairShopView.fieldReset();
				} else if (e.getSource() == _repairShopView.btnDelete) {
					if (_mainView.getCurRow() != -1) {
						repairShopModel.delete(_mainView.getConn(),
								_repairShopView.dbResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_repairShopView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				} else if (e.getSource() == _repairShopView.btnUpdate) {
					if (_mainView.getCurRow() != -1) { // 변경할 데이터를 선택한 것이 있다면
						if (_repairShopView.tf[0].getText().length() > 0) {
							repairShopModel.setShopid(Integer.parseInt(_repairShopView.tf[0].getText()));
						} else
							throw new NullPointerException();
						if (_repairShopView.tf[1].getText().length() > 0) {
							repairShopModel.setShopname(_repairShopView.tf[1].getText());
						}
						if (_repairShopView.tf[2].getText().length() > 0) {
							repairShopModel.setAddress(_repairShopView.tf[2].getText());
						}
						if (_repairShopView.tf[3].getText().length() > 0) {
							repairShopModel.setPhone(_repairShopView.tf[3].getText());
						}
						if (_repairShopView.tf[4].getText().length() > 0) {
							repairShopModel.setManager_name(_repairShopView.tf[4].getText());
						}
						if (_repairShopView.tf[5].getText().length() > 0) {
							repairShopModel.setManager_email(_repairShopView.tf[5].getText());
						}
						repairShopModel.update(_mainView.getConn(),
								_repairShopView.dbResult.getModel().getValueAt(_mainView.getCurRow(), 0));
						_repairShopView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
					}

				}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null, "null");
			}
		}

	}

	private class RepairShopMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			_mainView.setCurRow(_repairShopView.dbResult.getSelectedRow());
			_mainView.setCurCol(_repairShopView.dbResult.getSelectedColumn());
		}

	}

	private class RepairShopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changePanel(_repairShopView);
			_mainView.setCurRow(-1);
			_mainView.setCurCol(-1);

			ArrayList<Object[]> arr = repairShopModel.select(_mainView.getConn());
			Object column[] = {"SHOP ID", "SHOP NAME", "ADDRESS", "PHONE", "MANAGER NAME", "MANAGER EMAIL"};
			arr.add(0, column);
			_repairShopView.repairShopDefaultTable.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_repairShopView.repairShopDefaultTable.addRow(arr.get(i));
			}
			System.out.println("repairShop");
			_mainView.add(AppManager.getInstance().getRepairShopView());
			_mainView.revalidate();
			_mainView.repaint();
		}

	}
}
