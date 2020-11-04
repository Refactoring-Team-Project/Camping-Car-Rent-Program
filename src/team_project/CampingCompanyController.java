package team_project;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

				_campCompView.fieldReset();
			} else if (e.getSource() == _campCompView.btnDelete) {
//		         if(curRow!=-1) {
//		            updatePanel.delete(table, dbResult.getModel().getValueAt(curRow, 0));
//		         }
//		         else JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
//		     
			} else if (e.getSource() == _campCompView.btnUpdate) {
//		        if(curRow!=-1) {
//		          updatePanel.update(table, dbResult.getModel().getValueAt(curRow, 0));
//		        }
//		         else JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
//		     
			}
		}

	}

	private class CampingCompanyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_campCompView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = campCompModel.select(_campCompView.getConn());
			_campCompView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_campCompView.model.addRow(arr.get(i));
			}
			System.out.println("campcomp");
			_view.add(AppManager.getInstance().getCampingCompanyView());
			_view.revalidate();
			_view.repaint();
		}

	}
}
