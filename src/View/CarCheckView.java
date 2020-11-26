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
import Model.CarCheckModel;

public class CarCheckView extends JPanel {

	public DefaultTableModel model;
	public JTable dbResult;
	JScrollPane scrollPane;
	public JPanel updatePanel, buttonPanel;
	public JButton btnRequest;
	JLabel[] labels;
	public JTextField[] tf;


	public CarCheckView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));
		AppManager.getInstance().setCarCheckView(this);

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

		tf[1].setEnabled(false);
		tf[3].setEnabled(false);
		tf[4].setEnabled(false);

		for (int i = 0; i < 10; i++) {
			updatePanel.add(labels[i]);
			updatePanel.add(tf[i]);
		}

		add(updatePanel);

		buttonPanel = new JPanel();

		btnRequest = new JButton("수리요청");

		buttonPanel.add(btnRequest);

		add(buttonPanel);

		scrollPane.setPreferredSize(new Dimension(780, 300));
		updatePanel.setPreferredSize(new Dimension(780, 60));
		buttonPanel.setPreferredSize(new Dimension(780, 50));

		dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	public void addButtonListener(ActionListener listener) {
		btnRequest.addActionListener(listener);
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
