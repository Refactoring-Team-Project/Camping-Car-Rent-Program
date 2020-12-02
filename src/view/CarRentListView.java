package view;

import common.AppManager;
import common.Constants;

import javax.swing.*;

public class CarRentListView extends View{
	public JButton btnRent;
	public CarRentListView() {
		AppManager.getInstance().setCarRentListView(this);
	}
	@Override
	public void setInputFieldName() {
		fieldName = new JLabel[Constants.CARRENTLIST_FIELDNUM];
		inputFields = new JTextField[Constants.CARRENTLIST_FIELDNUM];
		for (int i = 0; i < Constants.CARRENTLIST_FIELDNUM; i++) {
			fieldName[i] = new JLabel(Constants.CARRENTLIST_FIELDSTRING[i]);
			inputFields[i] = new JTextField(Constants.CARRENTLIST_FIELDLENGTH[i]);
		}

		inputFields[1].setEnabled(false);
		inputFields[3].setEnabled(false);
	}

	@Override
	public void setButtons() {
		buttons = new JButton[] {
				btnRent = new JButton("대여")
		};
	}

	public String searchInput(String str) {
		return JOptionPane.showInputDialog(str);
	}
}
