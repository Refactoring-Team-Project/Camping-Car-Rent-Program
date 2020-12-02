package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import common.AppManager;
import common.Constants;

public class RepairListView extends View {

	public JButton btnDelete, btnUpdate;

	public RepairListView() {
		AppManager.getInstance().setRepairListView(this);
	}

	@Override
	public void setInputFieldName() {
		fieldName = new JLabel[Constants.REPAIRLIST_FIELDNUM];
		inputFields = new JTextField[Constants.REPAIRLIST_FIELDNUM];
		for (int i = 0; i < Constants.REPAIRLIST_FIELDNUM; i++) {
			fieldName[i] = new JLabel(Constants.REPAIRLIST_FIELDSTIRNG[i]);
			inputFields[i] = new JTextField(Constants.REPAIRLIST_FIELDLENGTH[i]);
		}
	}

	@Override
	public void setButtons() {
		buttons = new JButton[] {
				btnDelete = new JButton("삭제"),
		btnUpdate = new JButton("변경")
		};
	}
}
