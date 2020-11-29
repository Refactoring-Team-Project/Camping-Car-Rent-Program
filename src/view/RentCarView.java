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

public class RentCarView extends JPanel {

	public DefaultTableModel rentCarDefaultTable;
	public JTable rentCarDBResult;
	JScrollPane rentCarScrollPane;
	public JPanel updatePanel, buttonPanel;
	public JButton btnReturn;
	JLabel[] rentCarFieldName;
	public JTextField[] rentCarListInputField;


	public RentCarView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setRentCarView(this);

		initScrollPane();
		initUpdatePanel();
		initButtonPanel();
	}

	public void initScrollPane() {
		rentCarDefaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		rentCarDBResult = new JTable(rentCarDefaultTable);
		rentCarDBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		rentCarScrollPane = new JScrollPane(rentCarDBResult);
		add(rentCarScrollPane);
		rentCarScrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();
		updatePanel.setPreferredSize(new Dimension(780, 60));

		rentCarFieldName = new JLabel[Constants.RENTCAR_FIELDNUM];
		rentCarListInputField = new JTextField[Constants.RENTCAR_FIELDNUM];
		for (int i = 0; i < rentCarFieldName.length; i++) {
			rentCarFieldName[i] = new JLabel(Constants.RENTCAR_FIELDSTRING[i]);
			updatePanel.add(rentCarFieldName[i]);

			rentCarListInputField[i] = new JTextField("", Constants.CARRENTLIST_FIELDLENGTH[i]);
			updatePanel.add(rentCarListInputField[i]);
		}

		rentCarListInputField[0].setEnabled(false);
		rentCarListInputField[1].setEnabled(false);

		add(updatePanel);
	}

	public void initButtonPanel() {
		buttonPanel = new JPanel();
		btnReturn = new JButton("반환");
		buttonPanel.add(btnReturn);

		add(buttonPanel);
		buttonPanel.setPreferredSize(new Dimension(780, 50));
	}

	public void addButtonListener(ActionListener listener) {
		btnReturn.addActionListener(listener);
	}

	public void fieldReset() {
		for (JTextField inputField : rentCarListInputField) {
			inputField.setText("");
		}
		repaint();
	}

	public void addMouseListener(MouseListener listener) {
		rentCarDBResult.addMouseListener(listener);
	}
}
