package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import common.AppManager;
import common.Constants;

public class CampingCarView extends View {
	public JButton btnInput, btnDelete, btnUpdate;
	public CampingCarView() {
		AppManager.getInstance().setCampingCarView(this);
	}
	@Override
	public void setInputFieldName() {
		fieldName = new JLabel[Constants.CAMPINGCAR_FIELDNUM];
		inputFields = new JTextField[Constants.CAMPINGCAR_FIELDNUM];
		for (int i = 0; i < Constants.CAMPINGCAR_FIELDNUM; i++) {
			fieldName[i] = new JLabel(Constants.CAMPINGCAR_FIELDSTRING[i]);
			inputFields[i] = new JTextField(Constants.CAMPINGCAR_FIELDLENGTH[i]);
		}
	}

	@Override
	public void setButtons() {
		buttons = new JButton[] {
				btnInput = new JButton("입력"),
				btnDelete = new JButton("삭제"),
				btnUpdate = new JButton("변경")
		};
	}
}
