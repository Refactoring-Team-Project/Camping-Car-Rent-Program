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

public class RepairShopView extends View {

	public JButton btnInput, btnDelete, btnUpdate;

	public RepairShopView() {
		AppManager.getInstance().setRepairShopView(this);
	}

	@Override
	public void setInputFieldName() {
		fieldName = new JLabel[Constants.REPAIRSHOP_FIELDNUM];
		inputFields = new JTextField[Constants.REPAIRSHOP_FIELDNUM];
		for (int i = 0; i < Constants.REPAIRSHOP_FIELDNUM; i++) {
			fieldName[i] = new JLabel(Constants.REPAIRSHOP_FIELDSTIRNG[i]);
			inputFields[i] = new JTextField(Constants.REPAIRSHOP_FIELDLENGTH[i]);
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