package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import common.AppManager;
import common.Constants;

public class CampingCompanyView extends View{

	public JButton btnInput, btnDelete, btnUpdate;

	public CampingCompanyView() {
		AppManager.getInstance().setCampingCompanyView(this);
	}

	@Override
	public void setInputFieldName() {
		fieldName = new JLabel[Constants.CAMPINGCOMPANY_FIELDNUM];
		inputFields = new JTextField[Constants.CAMPINGCOMPANY_FIELDNUM];
		for (int i = 0; i < Constants.CAMPINGCOMPANY_FIELDNUM; i++) {
			fieldName[i] = new JLabel(Constants.CAMPINGCOMPANY_FIELDSTRING[i]);
			inputFields[i] = new JTextField(Constants.CAMPINGCOMPANY_FIELDLENGTH[i]);
		}
	}

	@Override
	public void setButtons() {
		buttons = new JButton[]{
				btnInput = new JButton("입력"),
				btnDelete = new JButton("삭제"),
				btnUpdate = new JButton("변경")
		};
	}
}
