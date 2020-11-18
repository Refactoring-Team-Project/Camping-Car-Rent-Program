package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class RepairShopView extends JPanel {

	public DefaultTableModel model;
	public JTable dbResult;
	JScrollPane scrollPane;
	JPanel updatePanel, buttonPanel;
	int curRow = -1, curCol = -1;
	public JButton btnInput, btnDelete, btnUpdate;
	JLabel[] labels;
	public JTextField[] tf;

	public RepairShopView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));
		AppManager.getInstance().setRepairShopView(this);
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dbResult = new JTable(model);
		scrollPane = new JScrollPane(dbResult);
		add(scrollPane);

		updatePanel = new JPanel();
		labels = new JLabel[6];
		labels[0] = new JLabel("shopid:");
		labels[1] = new JLabel("shopname:");
		labels[2] = new JLabel("address:");
		labels[3] = new JLabel("phone:");
		labels[4] = new JLabel("manager_name:");
		labels[5] = new JLabel("manager_email:");

		tf = new JTextField[6];
		tf[0] = new JTextField("", 3);
		tf[1] = new JTextField("", 5);
		tf[2] = new JTextField("", 10);
		tf[3] = new JTextField("", 10);
		tf[4] = new JTextField("", 7);
		tf[5] = new JTextField("", 10);

		for (int i = 0; i < 6; i++) {
			updatePanel.add(labels[i]);
			updatePanel.add(tf[i]);
		}

		add(updatePanel);

		buttonPanel = new JPanel();

		btnInput = new JButton("입력");
		btnDelete = new JButton("삭제");
		btnUpdate = new JButton("변경");

		buttonPanel.add(btnInput);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnUpdate);

		add(buttonPanel);

		buttonPanel = new JPanel();

		scrollPane.setPreferredSize(new Dimension(780, 300));
		updatePanel.setPreferredSize(new Dimension(780, 60));
		buttonPanel.setPreferredSize(new Dimension(780, 50));

		dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// mouseListener 처리하기
	}

	public void addMouseListener(MouseListener listener) {
		dbResult.addMouseListener(listener);
	}

	public void addButtonListener(ActionListener listener) {
		btnInput.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}


	public void fieldReset() {
		for (JTextField t : tf) {
			t.setText("");
		}
		repaint();
	}

}