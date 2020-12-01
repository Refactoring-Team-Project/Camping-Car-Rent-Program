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

public class RepairListView extends View {

	public JButton btnDelete, btnUpdate;

	public RepairListView() {
		AppManager.getInstance().setRepairListView(this);
	}

	@Override
	public void setInputFiledName() {
		fieldName = new JLabel[Constants.REPAIRLIST_FIELDNUM];
		inputField = new JTextField[Constants.REPAIRLIST_FIELDNUM];
		for (int i = 0; i < Constants.REPAIRLIST_FIELDNUM; i++) {
			fieldName[i] = new JLabel(Constants.REPAIRLIST_FIELDSTIRNG[i]);
			inputField[i] = new JTextField(Constants.REPAIRLIST_FIELDLENGTH[i]);
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
