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

public class RentCarView extends JPanel {

	public DefaultTableModel model;
	public JTable dbResult;
	JScrollPane scrollPane;
	public JPanel updatePanel, buttonPanel;
	public JButton btnReturn;
	JLabel[] labels;
	public JTextField[] tf;
	public String[] fieldString = { "rentno", "carid", "explain_front", "explain_left", "explain_right", "explain_back",
			"repair_required" };
	public int[] fieldSize = { 3, 3, 10, 10, 10, 10, 2 };

	public RentCarView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setRentCarView(this);

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

		labels = new JLabel[7];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(fieldString[i]);
		}

		tf = new JTextField[7];
		for (int i = 0; i < tf.length; i++) {
			tf[i] = new JTextField("", fieldSize[i]);
		}

		tf[0].setEnabled(false);
		tf[1].setEnabled(false);

		for (int i = 0; i < 7; i++) {
			updatePanel.add(labels[i]);
			updatePanel.add(tf[i]);
		}

		add(updatePanel);
		updatePanel.setPreferredSize(new Dimension(780, 60));
	}

	public void initButtonPanel() {
		buttonPanel = new JPanel();
		btnReturn = new JButton("반환");
		buttonPanel.add(btnReturn);

		add(buttonPanel);
		buttonPanel.setPreferredSize(new Dimension(780, 50));
	}

	public void addButtonListener(ActionListener listener) {
		btnReturn.addActionListener(listener);
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
