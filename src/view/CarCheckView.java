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

public class CarCheckView extends View {

	public JButton btnRequest;

	public CarCheckView() {
		AppManager.getInstance().setCarCheckView(this);
	}

	@Override
	public void setInputFiledName() {
		fieldName = new JLabel[Constants.CARCHECK_FIELDNUM];
		inputField = new JTextField[Constants.CARCHECK_FIELDNUM];
		for (int i = 0; i < Constants.CARCHECK_FIELDNUM; i++) {
			fieldName[i] = new JLabel(Constants.CARCHECK_FIELDSTRING[i]);
			inputField[i] = new JTextField(Constants.CARCHECK_FIELDLENGTH[i]);
		}

		inputField[1].setEnabled(false);
		inputField[3].setEnabled(false);
		inputField[4].setEnabled(false);
	}

	@Override
	public void setButtons() {
		buttons = new JButton[] {
				btnRequest = new JButton("수리요청")
		};
	}

}
