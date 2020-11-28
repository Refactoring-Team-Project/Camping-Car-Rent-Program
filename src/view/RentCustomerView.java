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

public class RentCustomerView extends JPanel {

	public DefaultTableModel rentCustomerDefaultTable;
	public JTable rentCustomerDBResult;
	JScrollPane rentCustomerScrollPane;
	public JPanel updatePanel, buttonPanel;
	public JButton btnInput, btnDelete, btnUpdate;
	JLabel[] rentCustomerFieldName;
	public JTextField[] rentCustomerInputField;


	public RentCustomerView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setRentCustomerView(this);

		initScrollPane();
		initUpdatePanel();
		initButtonPanel();
	}

	public void initScrollPane() {
		rentCustomerDefaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		rentCustomerDBResult = new JTable(rentCustomerDefaultTable);
		rentCustomerDBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		rentCustomerScrollPane = new JScrollPane(rentCustomerDBResult);
		add(rentCustomerScrollPane);
		rentCustomerScrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();
		updatePanel.setPreferredSize(new Dimension(780, 60));

		rentCustomerFieldName = new JLabel[Constants.RENTCUSTOMER_FIELDNUM];
		rentCustomerInputField = new JTextField[Constants.RENTCUSTOMER_FIELDNUM];

		for (int i = 0; i < Constants.RENTCUSTOMER_FIELDNUM; i++) {
			rentCustomerFieldName[i] = new JLabel(Constants.RENTCUSTOMER_FIELDSTIRNG[i]);
			updatePanel.add(rentCustomerFieldName[i]);

			rentCustomerInputField[i] = new JTextField(Constants.RENTCUSTOMER_FIELDLENGTH[i]);
			updatePanel.add(rentCustomerInputField[i]);
		}

		add(updatePanel);
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
		rentCustomerDBResult.addMouseListener(listener);
	}

	public void addButtonListener(ActionListener listener) {
		btnInput.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}

	public void fieldReset() {
		for (JTextField inputField : rentCustomerInputField) {
			inputField.setText("");
		}
		repaint();
	}

}
