package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Common.AppManager;
import Model.CampingCompanyModel;

public class CampingCompanyView extends JPanel{

	public DefaultTableModel campingCompanyDefaultTable;
	public JTable dbResult;
	JScrollPane scrollPane;
	JPanel updatePanel, buttonPanel;
	public JButton btnInput, btnDelete, btnUpdate;
	JLabel[] labels;
	public JTextField[] tf;
	public String[] fieldString = {"compid", "compname", "address", "phone", "manager_name", "manager_email"};
	public int[] fieldSize = {3, 7, 10, 10, 7, 10};

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
		dbResult = new JTable(campingCompanyDefaultTable);

		scrollPane = new JScrollPane(dbResult);
		add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(780, 300));

		dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();
		labels = new JLabel[6];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(fieldString[i]);
		}

		tf = new JTextField[6];
		for (int i = 0; i < tf.length; i++) {
			tf[i] = new JTextField(fieldSize[i]);
		}


		for (int i = 0; i < 6; i++) {
			updatePanel.add(labels[i]);
			updatePanel.add(tf[i]);
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
	
	public void addButtonListener(ActionListener listener) {
		btnInput.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}
	
	public void addMouseListener(MouseListener listener) {
		dbResult.addMouseListener(listener);
	}

	public void fieldReset() {
		for(JTextField t: tf) {
			t.setText("");
        }
		repaint();
	}
	
	

}
