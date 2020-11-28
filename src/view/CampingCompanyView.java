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

public class CampingCompanyView extends JPanel{

	public DefaultTableModel campingCompanyDefaultTable;
	public JTable campingCompanyDBResult;
	JScrollPane campingCompanyScrollPane;
	JPanel updatePanel, buttonPanel;
	public JButton btnInput, btnDelete, btnUpdate;
	JLabel[] campingCompanyFieldName;
	public JTextField[] campingCompanyInputField;

	public CampingCompanyView() {
		super.setLayout(new FlowLayout()); 
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setCampingCompanyView(this);

		initScrollPane();
		initUpdatePanel();
		initButtonPanel();
	}

	public void initScrollPane() {
		campingCompanyDefaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		campingCompanyDBResult = new JTable(campingCompanyDefaultTable);
		campingCompanyDBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		campingCompanyScrollPane = new JScrollPane(campingCompanyDBResult);
		add(campingCompanyScrollPane);
		campingCompanyScrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();
		updatePanel.setPreferredSize(new Dimension(780, 60));

		campingCompanyFieldName = new JLabel[Constants.CAMPINGCOMPANY_FIELDNUM];
		campingCompanyInputField = new JTextField[Constants.CAMPINGCOMPANY_FIELDNUM];

		for (int i = 0; i < Constants.CAMPINGCOMPANY_FIELDNUM; i++) {
			campingCompanyFieldName[i] = new JLabel(Constants.CAMPINGCOMPANY_FIELDSTRING[i]);
			updatePanel.add(campingCompanyFieldName[i]);

			campingCompanyInputField[i] = new JTextField(Constants.CAMPINGCOMPANY_FIELDLENGTH[i]);
			updatePanel.add(campingCompanyInputField[i]);
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
	
	public void addButtonListener(ActionListener listener) {
		btnInput.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}
	
	public void addMouseListener(MouseListener listener) {
		campingCompanyDBResult.addMouseListener(listener);
	}

	public void fieldReset() {
		for(JTextField inputField: campingCompanyInputField) {
			inputField.setText("");
        }
		repaint();
	}
	
	

}
