package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Common.AppManager;
import Model.RentCarModel;

public class RentCarView extends JPanel {
	private RentCarModel rentCarModel;
	private MainView _view;
	public DefaultTableModel model;
	public JTable dbResult;
	JScrollPane scrollPane;
	public JPanel updatePanel, buttonPanel;
	public JButton btnReturn;
	JLabel[] labels;
	public JTextField[] tf;

	Connection _conn;

	public RentCarView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));
		AppManager.getInstance().setRentCarView(this);
		_view = AppManager.getInstance().getView();
		rentCarModel = new RentCarModel();

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
		labels = new JLabel[7];
		labels[0] = new JLabel("rentno:");
		labels[1] = new JLabel("carid:");
		labels[2] = new JLabel("explain_front:");
		labels[3] = new JLabel("explain_left:");
		labels[4] = new JLabel("explain_right:");
		labels[5] = new JLabel("explain_back:");
		labels[6] = new JLabel("repair_required:");

		tf = new JTextField[7];
		tf[0] = new JTextField("", 3);
		tf[1] = new JTextField("", 3);
		tf[2] = new JTextField("", 10);
		tf[3] = new JTextField("", 10);
		tf[4] = new JTextField("", 10);
		tf[5] = new JTextField("", 10);
		tf[6] = new JTextField("", 2);

		tf[0].setEnabled(false);
		tf[1].setEnabled(false);

		for (int i = 0; i < 7; i++) {
			updatePanel.add(labels[i]);
			updatePanel.add(tf[i]);
		}

		add(updatePanel);

		buttonPanel = new JPanel();
		btnReturn = new JButton("반환");
		buttonPanel.add(btnReturn);

		add(buttonPanel);

		scrollPane.setPreferredSize(new Dimension(780, 300));
		updatePanel.setPreferredSize(new Dimension(780, 60));
		buttonPanel.setPreferredSize(new Dimension(780, 50));

		dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void addButtonListener(ActionListener listener) {
		btnReturn.addActionListener(listener);
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
}
