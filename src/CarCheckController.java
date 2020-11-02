

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CarCheckController {
	MainView _view;
	CarCheckView _carChkView;
	private CarCheckModel carChkModel;
	private RepairListModel repListModel;

	public CarCheckController() {
		this._view = AppManager.getInstance().getView();
		this._carChkView = AppManager.getInstance().getCarCheckView();
		carChkModel = new CarCheckModel();
		repListModel = new RepairListModel();
		this._carChkView.addButtonListener(new ButtonListener());
		this._view.addCarChkListener(new CarCheckButtonListener());
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _carChkView.btnRequest) {
					if (_view.getCurRow() != -1) {
						if (_carChkView.dbResult.getModel().getValueAt(_view.getCurRow(), 6).equals("Y")) {
							if (_carChkView.tf[0].getText().length() > 0) {
								repListModel.setRepairno(Integer.parseInt(_carChkView.tf[0].getText()));
							} else
								throw new NullPointerException();

							if (_carChkView.tf[1].getText().length() > 0) {
								repListModel.setCarid(Integer.parseInt(_carChkView.tf[1].getText()));
							}
							if (_carChkView.tf[2].getText().length() > 0) {
								repListModel.setShopid(Integer.parseInt(_carChkView.tf[2].getText()));
							}
							if (_carChkView.tf[3].getText().length() > 0) {
								repListModel.setCompid(Integer.parseInt(_carChkView.tf[3].getText()));
							}
							if (_carChkView.tf[4].getText().length() > 0) {
								repListModel.setLicense_no(Integer.parseInt(_carChkView.tf[4].getText()));
							}
							if (_carChkView.tf[5].getText().length() > 0) {
								repListModel.setRepairdetails(_carChkView.tf[5].getText());
							}
							if (_carChkView.tf[6].getText().length() > 0) {
								repListModel.setRepairdate(_carChkView.tf[6].getText());
							}
							if (_carChkView.tf[7].getText().length() > 0) {
								repListModel.setRepaircost(Integer.parseInt(_carChkView.tf[7].getText()));
							}
							if (_carChkView.tf[8].getText().length() > 0) {
								repListModel.setPaymentdeadline(_carChkView.tf[8].getText());
							}
							if (_carChkView.tf[9].getText().length() > 0) {
								repListModel.setRepairhistory(_carChkView.tf[9].getText());
							}

							repListModel.insert(_carChkView.getConn());

						} else if (_carChkView.dbResult.getModel().getValueAt(_view.getCurRow(), 6).equals("N"))
							JOptionPane.showMessageDialog(null, "데이터 선택이 잘못되었습니다.");
					} else
						JOptionPane.showMessageDialog(null, "요청할 데이터를 선택해 주세요.");
					_carChkView.fieldReset();

				}
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null, "null");

			}
		}
	}

	private class CarCheckButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_carChkView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);

			ArrayList<Object[]> arr = carChkModel.select(_carChkView.getConn());
			_carChkView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_carChkView.model.addRow(arr.get(i));
			}
			System.out.println("car check");
			_view.add(AppManager.getInstance().getCarCheckView());
			_view.revalidate();
			_view.repaint();
		}
	}

}