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
	}

	public void addButtonListener(ActionListener listener) {
		repairListbtnDelete.addActionListener(listener);
		repairListbtnUpdate.addActionListener(listener);
	}

	public void addMouseListener(MouseListener listener) {
		repairListdbResult.addMouseListener(listener);
	}

	public void fieldReset() {
		for (JTextField t : repairListTextField) {
			t.setText("");
		}
		repaint();
	}
}
