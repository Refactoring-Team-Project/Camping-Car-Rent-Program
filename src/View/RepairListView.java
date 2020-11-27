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
	public String[] fieldString = {"repairno", "carid", "shopid", "compid", "license_no", "repairdetails", "repairdate",
									"repaircost", "paymentdeadline", "repairhistory"};
	public int[] fieldSize = {3, 3, 3, 3, 3, 10, 5, 3, 5, 10};

	public RepairListView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780, 420));

		AppManager.getInstance().setRepairListView(this);
		_mainView = AppManager.getInstance().getView();


		initScrollPane();
		initUpdatePanel();
		initButtonPanel();

	}
	public void initUpdatePanel() {
		repairListUpdatePanel = new JPanel();

		repairListlabels = new JLabel[10];
		for (int i = 0; i < repairListlabels.length; i++) {
			repairListlabels[i] = new JLabel(fieldString[i]);

		}

		repairListTextField = new JTextField[10];

		for (int i = 0; i < repairListTextField.length; i++) {
			repairListTextField[i] = new JTextField("", fieldSize[i]);

		}

		for (int i = 0; i < 10; i++) {
			repairListUpdatePanel.add(repairListlabels[i]);
			repairListUpdatePanel.add(repairListTextField[i]);
		}
		for(int i = 0; i < 5; i++) {
			repairListTextField[i].setEnabled(false);

		}

		add(repairListUpdatePanel);
		repairListUpdatePanel.setPreferredSize(new Dimension(780, 60));
	}

	public void initScrollPane() {
		repairListModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		repairListdbResult = new JTable(repairListModel);
		repairListdbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		repairListScrollPane = new JScrollPane(repairListdbResult);



		add(repairListScrollPane);
		repairListScrollPane.setPreferredSize(new Dimension(780, 300));
	}

	public void initButtonPanel() {
		repairListButtonPanel = new JPanel();

		repairListbtnDelete = new JButton("삭제");
		repairListbtnUpdate = new JButton("변경");

		repairListButtonPanel.add(repairListbtnDelete);
		repairListButtonPanel.add(repairListbtnUpdate);

		add(repairListButtonPanel);

		repairListButtonPanel.setPreferredSize(new Dimension(780, 50));
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
