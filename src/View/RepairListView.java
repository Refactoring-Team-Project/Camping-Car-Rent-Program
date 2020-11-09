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

public class RepairListView extends JPanel {

	private MainView _view;
	public DefaultTableModel model;
	public JTable dbResult;
	JScrollPane scrollPane;
	JPanel updatePanel, buttonPanel;
	int curRow = -1, curCol = -1;
	public JButton btnDelete, btnUpdate;
	JLabel[] labels;
	public JTextField[] tf;

	Connection _conn;
	Statement stmt; // select
	PreparedStatement pstmt; // insert, delete
	ResultSet rs;

	public RepairListView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));
		AppManager.getInstance().setRepairListView(this);
		_view = AppManager.getInstance().getView();
		_conn = _view.conn;
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dbResult = new JTable(model);
		scrollPane = new JScrollPane(dbResult);
		add(scrollPane);

		updatePanel = new JPanel();
		labels = new JLabel[10];
		labels[0] = new JLabel("repairno");
		labels[1] = new JLabel("carid");
		labels[2] = new JLabel("shopid");
		labels[3] = new JLabel("compid");
		labels[4] = new JLabel("license_no");
		labels[5] = new JLabel("repairdetails");
		labels[6] = new JLabel("repairdate");
		labels[7] = new JLabel("repaircost");
		labels[8] = new JLabel("paymentdeadline");
		labels[9] = new JLabel("repairhistory");

		tf = new JTextField[10];
		tf[0] = new JTextField("", 3);
		tf[1] = new JTextField("", 3);
		tf[2] = new JTextField("", 3);
		tf[3] = new JTextField("", 3);
		tf[4] = new JTextField("", 3);
		tf[5] = new JTextField("", 10);
		tf[6] = new JTextField("", 5);
		tf[7] = new JTextField("", 3);
		tf[8] = new JTextField("", 5);
		tf[9] = new JTextField("", 10);

		tf[0].setEnabled(false); // repairno
		tf[1].setEnabled(false); // carid
		tf[2].setEnabled(false); // shopid
		tf[3].setEnabled(false); // compid
		tf[4].setEnabled(false); // license_no

		for (int i = 0; i < 10; i++) {
			updatePanel.add(labels[i]);
			updatePanel.add(tf[i]);
		}

		add(updatePanel);

		buttonPanel = new JPanel();

		btnDelete = new JButton("삭제");
		btnUpdate = new JButton("변경");

		buttonPanel.add(btnDelete);
		buttonPanel.add(btnUpdate);

		add(buttonPanel);

		scrollPane.setPreferredSize(new Dimension(780, 300));
		updatePanel.setPreferredSize(new Dimension(780, 60));
		buttonPanel.setPreferredSize(new Dimension(780, 50));

		dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dbResult.addMouseListener(new RepairListMouseListener());
	}

	public void addButtonListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}

	private class RepairListMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			_view.setCurRow(dbResult.getSelectedRow());
			_view.setCurCol(dbResult.getSelectedColumn());

			tf[0].setText(dbResult.getModel().getValueAt(_view.getCurRow(), 0).toString());
			tf[0].setDisabledTextColor(Color.black);

			tf[1].setText(dbResult.getModel().getValueAt(_view.getCurRow(), 1).toString());
			tf[1].setDisabledTextColor(Color.black);

			tf[2].setText(dbResult.getModel().getValueAt(_view.getCurRow(), 2).toString());
			tf[2].setDisabledTextColor(Color.black);

			tf[3].setText(dbResult.getModel().getValueAt(_view.getCurRow(), 3).toString());
			tf[3].setDisabledTextColor(Color.black);

			tf[4].setText(dbResult.getModel().getValueAt(_view.getCurRow(), 4).toString());
			tf[4].setDisabledTextColor(Color.black);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public Connection getConn() {
		return _view.conn;
	}

	public void fieldReset() {
		for (JTextField t : tf) {
			t.setText("");
		}
		repaint();
	}
}
