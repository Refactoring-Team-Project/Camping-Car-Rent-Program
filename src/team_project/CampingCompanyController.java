package team_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CampingCompanyController {
	MainView _view;
	CampingCompanyView _campCompView;
	private CampingCompanyModel campCompModel;

	public CampingCompanyController() {
		this._view = AppManager.getInstance().getView();
		this._campCompView = AppManager.getInstance().getCampingCompanyView();
		campCompModel = new CampingCompanyModel();
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
	
					campCompModel.insert(_campCompView.getConn());
	
					
				} else if (e.getSource() == _campCompView.btnDelete) {
					if (_view.getCurRow() != -1) {
						campCompModel.delete(_campCompView.getConn(), _campCompView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
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
						campCompModel.update(_campCompView.getConn(),
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

	private class CampingCompanyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_campCompView);

			ArrayList<Object[]> arr = campCompModel.select(_campCompView.getConn());
			_campCompView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_campCompView.model.addRow(arr.get(i));
			}
			System.out.println("abcd");
			//_view.add(AppManager.getInstance().getCampingCompanyView());
			_view.revalidate();
			_view.repaint();
		}

	}
}
