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
// 라벨, 텍스트필드 변수명 변경
public class RepairListView extends JPanel {

	private MainView _mainView;
	public DefaultTableModel repairListModel;
	public JTable repairListdbResult;
	JScrollPane repairListScrollPane;
	JPanel repairListUpdatePanel, repairListButtonPanel;
	public JButton repairListbtnDelete, repairListbtnUpdate;
	JLabel[] repairListlabels;
	public JTextField[] repairListTextField;


	public RepairListView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));
		AppManager.getInstance().setRepairListView(this);
		_mainView = AppManager.getInstance().getView();

		repairListModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		repairListdbResult = new JTable(repairListModel);
		repairListScrollPane = new JScrollPane(repairListdbResult);
		add(repairListScrollPane);

		repairListUpdatePanel = new JPanel();
		repairListlabels = new JLabel[10];
		repairListlabels[0] = new JLabel("repairno");
		repairListlabels[1] = new JLabel("carid");
		repairListlabels[2] = new JLabel("shopid");
		repairListlabels[3] = new JLabel("compid");
		repairListlabels[4] = new JLabel("license_no");
		repairListlabels[5] = new JLabel("repairdetails");
		repairListlabels[6] = new JLabel("repairdate");
		repairListlabels[7] = new JLabel("repaircost");
		repairListlabels[8] = new JLabel("paymentdeadline");
		repairListlabels[9] = new JLabel("repairhistory");

		repairListTextField = new JTextField[10];
		repairListTextField[0] = new JTextField("", 3);
		repairListTextField[1] = new JTextField("", 3);
		repairListTextField[2] = new JTextField("", 3);
		repairListTextField[3] = new JTextField("", 3);
		repairListTextField[4] = new JTextField("", 3);
		repairListTextField[5] = new JTextField("", 10);
		repairListTextField[6] = new JTextField("", 5);
		repairListTextField[7] = new JTextField("", 3);
		repairListTextField[8] = new JTextField("", 5);
		repairListTextField[9] = new JTextField("", 10);

		repairListTextField[0].setEnabled(false); // repairno
		repairListTextField[1].setEnabled(false); // carid
		repairListTextField[2].setEnabled(false); // shopid
		repairListTextField[3].setEnabled(false); // compid
		repairListTextField[4].setEnabled(false); // license_no

		for (int i = 0; i < 10; i++) {
			repairListUpdatePanel.add(repairListlabels[i]);
			repairListUpdatePanel.add(repairListTextField[i]);
		}

		add(repairListUpdatePanel);

		repairListButtonPanel = new JPanel();

		repairListbtnDelete = new JButton("삭제");
		repairListbtnUpdate = new JButton("변경");

		repairListButtonPanel.add(repairListbtnDelete);
		repairListButtonPanel.add(repairListbtnUpdate);

		add(repairListButtonPanel);

		repairListScrollPane.setPreferredSize(new Dimension(780, 300));
		repairListUpdatePanel.setPreferredSize(new Dimension(780, 60));
		repairListButtonPanel.setPreferredSize(new Dimension(780, 50));

		repairListdbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		repairListdbResult.addMouseListener(new RepairListMouseListener());
	}

	public void addButtonListener(ActionListener listener) {
		repairListbtnDelete.addActionListener(listener);
		repairListbtnUpdate.addActionListener(listener);
	}

	private class RepairListMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			_mainView.setCurRow(repairListdbResult.getSelectedRow());
			_mainView.setCurCol(repairListdbResult.getSelectedColumn());

			repairListTextField[0].setText(repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 0).toString());
			repairListTextField[0].setDisabledTextColor(Color.black);

			repairListTextField[1].setText(repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 1).toString());
			repairListTextField[1].setDisabledTextColor(Color.black);

			repairListTextField[2].setText(repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 2).toString());
			repairListTextField[2].setDisabledTextColor(Color.black);

			repairListTextField[3].setText(repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 3).toString());
			repairListTextField[3].setDisabledTextColor(Color.black);

			repairListTextField[4].setText(repairListdbResult.getModel().getValueAt(_mainView.getCurRow(), 4).toString());
			repairListTextField[4].setDisabledTextColor(Color.black);
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


	public void fieldReset() {
		for (JTextField t : repairListTextField) {
			t.setText("");
		}
		repaint();
	}
}
