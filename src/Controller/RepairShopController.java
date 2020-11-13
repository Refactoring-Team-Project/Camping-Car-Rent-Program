package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.RepairShopModel;
import View.MainView;
import View.RepairShopView;

public class RepairShopController {
	MainView _view;
	RepairShopView _repairShopView;
	private RepairShopModel repairShopModel;

	public RepairShopController() {
		this._view = AppManager.getInstance().getView();
		this._repairShopView = AppManager.getInstance().getRepairShopView();
		repairShopModel = new RepairShopModel();
		this._repairShopView.addButtonListener(new ButtonListener());
		this._repairShopView.addMouseListener(new RepairShopMouseListener());
		this._view.addRepairShopListener(new RepairShopButtonListener());
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

					repairShopModel.insert(_repairShopView.getConn());

					_repairShopView.fieldReset();
				} else if (e.getSource() == _repairShopView.btnDelete) {
					if (_view.getCurRow() != -1) {
						repairShopModel.delete(_repairShopView.getConn(),
								_repairShopView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
						_repairShopView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				} else if (e.getSource() == _repairShopView.btnUpdate) {
					if (_view.getCurRow() != -1) { // 변경할 데이터를 선택한 것이 있다면
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
						repairShopModel.update(_repairShopView.getConn(),
								_repairShopView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
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

	private class RepairShopMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			_view.setCurRow(_repairShopView.dbResult.getSelectedRow());
			_view.setCurCol(_repairShopView.dbResult.getSelectedColumn());
			System.out.println(_view.getCurRow() + " " + _view.getCurCol());
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

	private class RepairShopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_repairShopView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = repairShopModel.select(_repairShopView.getConn());
			_repairShopView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_repairShopView.model.addRow(arr.get(i));
			}
			System.out.println("repairShop");
			_view.add(AppManager.getInstance().getRepairShopView());
			_view.revalidate();
			_view.repaint();
		}

	}
}
