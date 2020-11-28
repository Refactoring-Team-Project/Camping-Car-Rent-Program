package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import common.AppManager;
import common.Constants;

public class CarRentListView extends JPanel {

	public DefaultTableModel carRentListDefaultTable;
	public JTable carRentListDBResult;
	JScrollPane carRentListScrollPane;
	JPanel updatePanel, buttonPanel;
	public JButton btnRent;
	JLabel[] carRentListFieldName;
	public JTextField[] carRentListInputField;

	public CarRentListView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setCarRentListView(this);

		initScrollPane();
		initUpdatePanel();
		initButtonPanel();
	}

	public void initScrollPane() {
		carRentListDefaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		carRentListDBResult = new JTable(carRentListDefaultTable);
		carRentListDBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		carRentListScrollPane = new JScrollPane(carRentListDBResult);
		add(carRentListScrollPane);
		carRentListScrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();
		updatePanel.setPreferredSize(new Dimension(780, 60));

		carRentListFieldName = new JLabel[Constants.CARRENTLIST_FIELDNUM];
		carRentListInputField = new JTextField[Constants.CARRENTLIST_FIELDNUM];

		for(int i=0; i<Constants.CARRENTLIST_FIELDNUM; i++) {
			carRentListFieldName[i] = new JLabel(Constants.CARRENTLIST_FIELDSTRING[i]);
			updatePanel.add(carRentListFieldName[i]);

			carRentListInputField[i] = new JTextField("", Constants.CARRENTLIST_FIELDLENGTH[i]);
			updatePanel.add(carRentListInputField[i]);
		}

		carRentListInputField[1].setEnabled(false);
		carRentListInputField[3].setEnabled(false);

		add(updatePanel);
	}

	public void initButtonPanel() {
		buttonPanel = new JPanel();

		btnRent = new JButton("대여");

		buttonPanel.add(btnRent);

		add(buttonPanel);
		buttonPanel.setPreferredSize(new Dimension(780, 50));
	}

	public void addButtonListener(ActionListener listener) {
		btnRent.addActionListener(listener);
	}

	public void fieldReset() {
		for (JTextField inputField : carRentListInputField) {
			inputField.setText("");
		}
		repaint();
	}

	public void addMouseListener(MouseListener listener) {
		carRentListDBResult.addMouseListener(listener);
	}

	public String SearchInput(String str) {
		return JOptionPane.showInputDialog(str);
	}

}
