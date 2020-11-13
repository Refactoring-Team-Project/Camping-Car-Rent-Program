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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Common.AppManager;
import Model.CampingCarModel;

public class CarRentListView extends JPanel {

	CampingCarModel campCarModel;
	private MainView _view;
	public DefaultTableModel model;
	public JTable dbResult;
	JScrollPane scrollPane;
	JPanel updatePanel, buttonPanel;
	int curRow = -1, curCol = -1;
	public JButton btnRent;
	JLabel[] labels;
	public JTextField[] tf;

	Connection _conn;
	Statement stmt; // select
	PreparedStatement pstmt; // insert, delete
	ResultSet rs;

	public CarRentListView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));
		AppManager.getInstance().setCarRentListView(this);
		_view = AppManager.getInstance().getView();
		_conn = _view.conn;

		campCarModel = new CampingCarModel();
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
		labels[0] = new JLabel("rentno");
		labels[1] = new JLabel("carid");
		labels[2] = new JLabel("license_no");
		labels[3] = new JLabel("compid");
		labels[4] = new JLabel("rent_date");
		labels[5] = new JLabel("rentalperiod");
		labels[6] = new JLabel("charge");
		labels[7] = new JLabel("paymentdeadline");
		labels[8] = new JLabel("billhistory");
		labels[9] = new JLabel("billhistorycost");

		tf = new JTextField[10];
		tf[0] = new JTextField("", 3);
		tf[1] = new JTextField("", 3);
		tf[2] = new JTextField("", 3);
		tf[3] = new JTextField("", 3);
		tf[4] = new JTextField("", 10);
		tf[5] = new JTextField("", 5);
		tf[6] = new JTextField("", 5);
		tf[7] = new JTextField("", 10);
		tf[8] = new JTextField("", 10);
		tf[9] = new JTextField("", 5);

		tf[1].setEnabled(false);
		tf[3].setEnabled(false);

		for (int i = 0; i < 10; i++) {
			updatePanel.add(labels[i]);
			updatePanel.add(tf[i]);
		}

		add(updatePanel);

		buttonPanel = new JPanel();

		btnRent = new JButton("대여");

		buttonPanel.add(btnRent);

		add(buttonPanel);

		scrollPane.setPreferredSize(new Dimension(780, 300));
		updatePanel.setPreferredSize(new Dimension(780, 60));
		buttonPanel.setPreferredSize(new Dimension(780, 50));

		dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// mouseListener 처리하기
	}

	public void addButtonListener(ActionListener listener) {
		btnRent.addActionListener(listener);
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

	public void addMouseListener(MouseListener listener) {
		dbResult.addMouseListener(listener);
	}

	public String SearchInput(String str) {
		return JOptionPane.showInputDialog(str);
	}

}
