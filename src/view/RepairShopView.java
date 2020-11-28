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

public class RepairShopView extends JPanel {

	public DefaultTableModel repairShopDefaultTable;
	public JTable repairShopDBResult;
	JScrollPane repairShopScrollPane;
	JPanel updatePanel, buttonPanel;
	public JButton btnInput, btnDelete, btnUpdate;
	JLabel[] repairShopFieldName;
	public JTextField[] repairShopInputField;

	public RepairShopView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setRepairShopView(this);

		initScrollPane();
		initUpdatePanel();
		initButtonPanel();
	}

	public void initScrollPane() {
		repairShopDefaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		repairShopDBResult = new JTable(repairShopDefaultTable);
		repairShopDBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		repairShopScrollPane = new JScrollPane(repairShopDBResult);
		add(repairShopScrollPane);
		repairShopScrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();

		repairShopFieldName = new JLabel[Constants.REPAIRSHOP_FIELDNUM];
		repairShopInputField = new JTextField[Constants.REPAIRSHOP_FIELDNUM];

		for (int i = 0; i < Constants.REPAIRSHOP_FIELDNUM; i++) {
			repairShopFieldName[i] = new JLabel(Constants.REPAIRSHOP_FIELDSTIRNG[i]);
			updatePanel.add(repairShopFieldName[i]);

			repairShopInputField[i] = new JTextField(Constants.REPAIRSHOP_FIELDLENGTH[i]);
			updatePanel.add(repairShopInputField[i]);
		}

		add(updatePanel);
		updatePanel.setPreferredSize(new Dimension(780, 60));
	}

	public void initButtonPanel() {
		buttonPanel = new JPanel();

		btnInput = new JButton("입력");
		btnDelete = new JButton("삭제");
		btnUpdate = new JButton("변경");

		buttonPanel.add(btnInput);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnUpdate);

		add(buttonPanel);
		buttonPanel.setPreferredSize(new Dimension(780, 50));
	}

	public void addMouseListener(MouseListener listener) {
		repairShopDBResult.addMouseListener(listener);
	}

	public void addButtonListener(ActionListener listener) {
		btnInput.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}

	public void fieldReset() {
		for (JTextField inputField : repairShopInputField) {
			inputField.setText("");
		}
		repaint();
	}

}