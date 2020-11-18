package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.CampingCompanyModel;
import View.CampingCompanyView;
import View.MainView;

public class CampingCompanyController {
	MainView _view;
	CampingCompanyView _campCompView;
	private CampingCompanyModel campCompModel;

	public CampingCompanyController() {
		this._view = AppManager.getInstance().getView();
		this._campCompView = AppManager.getInstance().getCampingCompanyView();
		campCompModel = new CampingCompanyModel();
		this._campCompView.addMouseListener(new CampingCompanyMouseListener());
		this._campCompView.addButtonListener(new ButtonListener());
		this._view.addCampCompListener(new CampingCompanyButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				if (e.getSource() == _campCompView.btnInput) {
					if (_campCompView.tf[0].getText().length() > 0) {
						campCompModel.setCompid(Integer.parseInt(_campCompView.tf[0].getText()));
					}
					if (_campCompView.tf[1].getText().length() > 0) {
						campCompModel.setCompname(_campCompView.tf[1].getText());
					}
					if (_campCompView.tf[2].getText().length() > 0) {
						campCompModel.setAddress(_campCompView.tf[2].getText());
					}
					if (_campCompView.tf[3].getText().length() > 0) {
						campCompModel.setPhone(_campCompView.tf[3].getText());
					}
					if (_campCompView.tf[4].getText().length() > 0) {
						campCompModel.setManager_name(_campCompView.tf[4].getText());
					}
					if (_campCompView.tf[5].getText().length() > 0) {
						campCompModel.setManager_email(_campCompView.tf[5].getText());
					}
	
					campCompModel.insert(_view.getConn());
	
					
				} else if (e.getSource() == _campCompView.btnDelete) {
					if (_view.getCurRow() != -1) {
						campCompModel.delete(_view.getConn(), _campCompView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
						_campCompView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
	
				} else if (e.getSource() == _campCompView.btnUpdate) {
					if (_view.getCurRow() != -1) {
						if (_campCompView.tf[0].getText().length() > 0) {
							campCompModel.setCompid(Integer.parseInt(_campCompView.tf[0].getText()));
						} else
							throw new NullPointerException();
					
						if (_campCompView.tf[1].getText().length() > 0) {
							campCompModel.setCompname(_campCompView.tf[1].getText());
						}
						if (_campCompView.tf[2].getText().length() > 0) {
							campCompModel.setAddress(_campCompView.tf[2].getText());
						}
						if (_campCompView.tf[3].getText().length() > 0) {
							campCompModel.setPhone(_campCompView.tf[3].getText());
						}
						if (_campCompView.tf[4].getText().length() > 0) {
							campCompModel.setManager_name(_campCompView.tf[4].getText());
						}
						if (_campCompView.tf[5].getText().length() > 0) {
							campCompModel.setManager_email(_campCompView.tf[5].getText());
						}
						campCompModel.update(_view.getConn(),
								_campCompView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
						_campCompView.fieldReset();
					} else
						JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
				}
		
				_campCompView.fieldReset();
		} catch (NullPointerException e2) {
			JOptionPane.showMessageDialog(null, "null");
	
			}
		}

	}
	
	private class CampingCompanyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			_view.setCurRow(_campCompView.dbResult.getSelectedRow());
			_view.setCurCol(_campCompView.dbResult.getSelectedColumn());
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

	private class CampingCompanyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_campCompView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = campCompModel.select(_view.getConn());
			_campCompView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_campCompView.model.addRow(arr.get(i));
			}
			System.out.println("campcomp");
			_view.revalidate();
			_view.repaint();
		}

	}
}
