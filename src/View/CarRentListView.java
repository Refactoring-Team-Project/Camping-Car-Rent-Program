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

	private MainView _mainView;
	public DefaultTableModel model;
	public JTable dbResult;
	JScrollPane scrollPane;

	JPanel updatePanel, buttonPanel;
	public JButton btnRent;
	JLabel[] labels;
	public JTextField[] tf;
	public String[] fieldString = { "rentno", "carid", "license-no", "compid", "rent_date", "rentalperiod", "charge", "paymentdeadline", "billhistory", "billhistorycost"};
	public int[] fieldSize = { 3, 3, 3, 3, 10, 5, 5, 10, 10, 5};

	public CarRentListView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setCarRentListView(this);
		_mainView = AppManager.getInstance().getView();

		initScrollPane();
		initUpdatePanel();
		initButtonPanel();
	}

	public void initScrollPane() {
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dbResult = new JTable(model);
		dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(dbResult);
		add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initUpdatePanel() {
		updatePanel = new JPanel();

		labels = new JLabel[10];
		for(int i=0; i<labels.length; i++) {
			labels[i] = new JLabel(fieldString[i]);
		}

		tf = new JTextField[10];
		for(int i=0; i<tf.length; i++) {
			tf[i] = new JTextField("", fieldSize[i]);
		}

		tf[1].setEnabled(false);
		tf[3].setEnabled(false);

		for (int i = 0; i < 10; i++) {
			updatePanel.add(labels[i]);
			updatePanel.add(tf[i]);
		}

		add(updatePanel);
		updatePanel.setPreferredSize(new Dimension(780, 60));
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
