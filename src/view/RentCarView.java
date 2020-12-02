package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import common.AppManager;
import common.Constants;

public class RentCarView extends View {

	public JButton btnReturn;

	public RentCarView() {
		AppManager.getInstance().setRentCarView(this);
	}

	@Override
	public void setInputFieldName() {
		fieldName = new JLabel[Constants.RENTCAR_FIELDNUM];
		inputFields = new JTextField[Constants.RENTCAR_FIELDNUM];
		for (int i = 0; i < Constants.RENTCAR_FIELDNUM; i++) {
			fieldName[i] = new JLabel(Constants.RENTCAR_FIELDSTRING[i]);
			inputFields[i] = new JTextField(Constants.RENTCAR_FIELDLENGTH[i]);
		}
	}

	@Override
	public void setButtons() {
		buttons = new JButton[] { btnReturn = new JButton("반환") };
	}

}
