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

public class RepairListView extends JPanel {

	public DefaultTableModel repairListDefaultTable;
	public JTable repairListDBResult;
	JScrollPane repairListScrollPane;
	JPanel updatePanel, buttonPanel;
	public JButton btnDelete, btnUpdate;
	JLabel[] repairListFieldName;
	public JTextField[] repairListInputField;


	public RepairListView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setRepairListView(this);

		initScrollPane();
		initUpdatePanel();
		initButtonPanel();

	}
	public void initUpdatePanel() {
		updatePanel = new JPanel();

		repairListFieldName = new JLabel[Constants.REPAIRLIST_FIELDNUM];
		repairListInputField = new JTextField[Constants.REPAIRLIST_FIELDNUM];

		for (int i = 0; i < Constants.REPAIRLIST_FIELDNUM; i++) {
			repairListFieldName[i] = new JLabel(Constants.REPAIRLIST_FIELDSTIRNG[i]);
			updatePanel.add(repairListFieldName[i]);

			repairListInputField[i] = new JTextField("", Constants.REPAIRLIST_FIELDLENGTH[i]);
			updatePanel.add(repairListInputField[i]);
		}

		for(int i = 0; i < 5; i++) {
			repairListInputField[i].setEnabled(false);
		}

		add(updatePanel);
		updatePanel.setPreferredSize(new Dimension(780, 60));
	}

	public void initScrollPane() {
		repairListDefaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		repairListDBResult = new JTable(repairListDefaultTable);
		repairListDBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		repairListScrollPane = new JScrollPane(repairListDBResult);

		add(repairListScrollPane);
		repairListScrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initButtonPanel() {
		buttonPanel = new JPanel();

		btnDelete = new JButton("삭제");
		btnUpdate = new JButton("변경");

		buttonPanel.add(btnDelete);
		buttonPanel.add(btnUpdate);

		add(buttonPanel);

		buttonPanel.setPreferredSize(new Dimension(780, 50));
	}


	public void addButtonListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}

	public void addMouseListener(MouseListener listener) {
		repairListDBResult.addMouseListener(listener);
	}

	public void fieldReset() {
		for (JTextField inputField : repairListInputField) {
			inputField.setText("");
		}
		repaint();
	}
}
