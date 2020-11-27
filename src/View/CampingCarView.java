package View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

import Common.AppManager;
import Model.CampingCarModel;

public class CampingCarView extends JPanel {

	public DefaultTableModel campingCarDefaultTable;
	public JTable dbResult;
	JScrollPane scrollPane;
	public JPanel updatePanel, buttonPanel;
	public JButton btnInput, btnDelete, btnUpdate;
	JLabel[] labels;
	public JTextField[] tf;
	public String[] fieldString = {"carid", "carname", "carno", "seat", "manufacturer", "manu_year", "drivingdistance", "rentcost", "compid", "registdate"};
	public int[] fieldSize = {3, 5, 3, 3, 10, 5, 7, 5, 3, 10};
	
	public CampingCarView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780,420));

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

		dbResult = new JTable(campingCarDefaultTable);
		dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane = new JScrollPane(dbResult);
		add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();

		labels = new JLabel[10];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(fieldString[i]);
		}

		tf = new JTextField[10];
		for (int i = 0; i < tf.length; i++) {
			tf[i] = new JTextField(fieldSize[i]);
		}

		for (int i = 0; i < 10; i++) {
			updatePanel.add(labels[i]);
			updatePanel.add(tf[i]);
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
		dbResult.addMouseListener(listener);
	}

	public void fieldReset() {
		for(JTextField t: tf) {
			t.setText("");
        }
		repaint();
	}
	
	
	
}
