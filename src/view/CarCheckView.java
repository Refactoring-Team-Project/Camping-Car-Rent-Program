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

public class CarCheckView extends JPanel {

	public DefaultTableModel carCheckDefaultTable;
	public JTable carCheckDBResult;
	JScrollPane carCheckScrollPane;
	public JPanel updatePanel, buttonPanel;
	public JButton btnRequest;
	JLabel[] carCheckFieldName;
	public JTextField[] carCheckInputField;

	public CarCheckView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setCarCheckView(this);

		initScrollPane();
		initUpdatePanel();
		initButtonPanel();
	}

	public void initScrollPane() {
		carCheckDefaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		carCheckDBResult = new JTable(carCheckDefaultTable);
		carCheckDBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		carCheckScrollPane = new JScrollPane(carCheckDBResult);
		add(carCheckScrollPane);
		carCheckScrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();
		updatePanel.setPreferredSize(new Dimension(780, 60));

		carCheckFieldName = new JLabel[Constants.CARCHECK_FIELDNUM];
		carCheckInputField = new JTextField[Constants.CARCHECK_FIELDNUM];

		for (int i = 0; i < Constants.CARCHECK_FIELDNUM; i++) {
			carCheckFieldName[i] = new JLabel(Constants.CARCHECK_FIELDSTRING [i]);
			updatePanel.add(carCheckFieldName[i]);

			carCheckInputField[i] = new JTextField("", Constants.CARCHECK_FIELDLENGTH[i]);
			updatePanel.add(carCheckInputField[i]);
		}

		carCheckInputField[1].setEnabled(false);
		carCheckInputField[3].setEnabled(false);
		carCheckInputField[4].setEnabled(false);

		add(updatePanel);
	}

	public void initButtonPanel() {
		buttonPanel = new JPanel();

		btnRequest = new JButton("수리요청");
		buttonPanel.add(btnRequest);
		add(buttonPanel);

		buttonPanel.setPreferredSize(new Dimension(780, 50));
	}

	public void addButtonListener(ActionListener listener) {
		btnRequest.addActionListener(listener);
	}

	public void addMouseListener(MouseListener listener) {
		carCheckDBResult.addMouseListener(listener);
	}

	public void fieldReset() {
		for (JTextField inputField : carCheckInputField) {
			inputField.setText("");
		}
		repaint();
	}

}
