package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import common.AppManager;
import common.Constants;

public class CarCheckView extends View {

	public JButton btnRequest;

	public CarCheckView() {
		AppManager.getInstance().setCarCheckView(this);
	}

	@Override
	public void setInputFieldName() {
		fieldName = new JLabel[Constants.CARCHECK_FIELDNUM];
		inputFields = new JTextField[Constants.CARCHECK_FIELDNUM];
		for (int i = 0; i < Constants.CARCHECK_FIELDNUM; i++) {
			fieldName[i] = new JLabel(Constants.CARCHECK_FIELDSTRING[i]);
			inputFields[i] = new JTextField(Constants.CARCHECK_FIELDLENGTH[i]);
		}

		inputFields[1].setEnabled(false);
		inputFields[3].setEnabled(false);
		inputFields[4].setEnabled(false);
	}

	@Override
	public void setButtons() {
		buttons = new JButton[] {
				btnRequest = new JButton("수리요청")
		};
	}

}
