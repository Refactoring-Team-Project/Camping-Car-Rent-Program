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

public class CampingCarView extends JPanel {

	public DefaultTableModel campingCarDefaultTable;
	public JTable campingCarDBResult;
	JScrollPane campingCarScrollPane;
	public JPanel updatePanel, buttonPanel;
	public JButton btnInput, btnDelete, btnUpdate;
	JLabel[] campingCarFieldName;
	public JTextField[] campingCarInputField;

	public CampingCarView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setCampingCarView(this);

		initScrollPane();
		initUpdatePanel();
		initButtonPanel();
	}

	public void initScrollPane() {
		campingCarDefaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		campingCarDBResult = new JTable(campingCarDefaultTable);
		campingCarDBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		campingCarScrollPane = new JScrollPane(campingCarDBResult);
		add(campingCarScrollPane);
		campingCarScrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();

		campingCarFieldName = new JLabel[Constants.CAMPINGCAR_FIELDNUM];
		campingCarInputField = new JTextField[Constants.CAMPINGCAR_FIELDNUM];

		for (int i = 0; i < Constants.CAMPINGCAR_FIELDNUM; i++) {
			campingCarFieldName[i] = new JLabel(Constants.CAMPINGCAR_FIELDSTRING[i]);
			campingCarInputField[i] = new JTextField(Constants.CAMPINGCAR_FIELDLENGTH[i]);
		}


		for (int i = 0; i < Constants.CAMPINGCAR_FIELDNUM; i++) {
			updatePanel.add(campingCarFieldName[i]);
			updatePanel.add(campingCarInputField[i]);
		}

		add(updatePanel);
		updatePanel.setPreferredSize(new Dimension(780, 60));
	}

	public void initButtonPanel() {
		btnInput = new JButton("입력");
		btnDelete = new JButton("삭제");
		btnUpdate = new JButton("변경");

		buttonPanel = new JPanel();

		buttonPanel.add(btnInput);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnUpdate);

		add(buttonPanel);
		buttonPanel.setPreferredSize(new Dimension(780, 50));
	}

	public void addButtonListener(ActionListener listener) {
		btnInput.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}

	public void addMouseListener(MouseListener listener) {
		campingCarDBResult.addMouseListener(listener);
	}

	public void fieldReset() {
		for (JTextField inputField : campingCarInputField) {
			inputField.setText("");
		}
		repaint();
	}

}
