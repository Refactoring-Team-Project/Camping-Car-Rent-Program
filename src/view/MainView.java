package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.AppManager;
import common.Constants;
// 버튼패널, 검색버튼 변수명 변경

public class MainView extends JFrame {
	public JButton btnReset;
	JButton btnUserChange;
	public JButton[] btnOnAdminPanel, btnOnUserPanel;
	JPanel adminPanel, userPanel, userChangePanel;
	JPanel[] tableBtnPanel;

	public enum UserType {
		관리자, 사용자
	}

	UserType currentUserType;
	int currentRow = -1, currentCol = -1;

	public Connection connection;

	public MainView() {
		super("refactoring");
		AppManager.getInstance().setMainView(this);

		init();

		setVisible(true);
		setBounds(500, 200, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init() {
		super.setLayout(new FlowLayout());

		currentUserType = UserType.관리자;
		initUserChangePanel();
		initAdminPanel1();
		initUserPanell();
		tableBtnPanel = new JPanel[] {
				adminPanel,
				userPanel
		};

		add(tableBtnPanel[currentUserType.ordinal()]);

		initPanelSize();
	}

	public void initUserChangePanel() {
		/* 사용자 관리자 전환 panel */
		btnUserChange = new JButton(currentUserType.name());
		userChangePanel = new JPanel();
		userChangePanel.add(btnUserChange);
		add(userChangePanel);
	}

	public void initAdminPanel1() {
		/* panel 1 */
		adminPanel = new JPanel();
		btnOnAdminPanel = new JButton[Constants.ADMIN_BUTTON_NAME.length];

		for(int i=0;i<Constants.ADMIN_BUTTON_NAME.length;i++) {
			btnOnAdminPanel[i] = new JButton(Constants.ADMIN_BUTTON_NAME[i]);
			adminPanel.add(btnOnAdminPanel[i]);
		}

		btnReset = new JButton("초기화");
		adminPanel.add(btnReset);

	}

	public void initUserPanell() {
		/* panel 2 */
		userPanel = new JPanel();
		btnOnUserPanel = new JButton[Constants.USER_BUTTON_NAME.length];

		for(int i=0;i<Constants.USER_BUTTON_NAME.length;i++) {
			btnOnUserPanel[i] = new JButton(Constants.USER_BUTTON_NAME[i]);
			userPanel.add(btnOnUserPanel[i]);
		}

	}

	public void initPanelSize() {
		/*** panel size ****/
		userChangePanel.setPreferredSize(new Dimension(780, 50));
		adminPanel.setPreferredSize(new Dimension(780, 80));
		userPanel.setPreferredSize(new Dimension(780, 80));
	}

	public Connection getConnection() {
		return connection;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int selectedRow) {
		currentRow = selectedRow;
	}

	public int getCurrentCol() {
		return currentCol;
	}

	public void setCurrentCol(int selectedCol) {
		currentCol = selectedCol;
	}

	public void changeUser() {

		if (currentUserType.name().equals("관리자")) { // 관리자 모드
			currentUserType = UserType.사용자;
		} else if (currentUserType.name().equals("사용자")) { // 사용자 모드
			currentUserType = UserType.관리자;
		}
		btnUserChange.setText(currentUserType.name());
		changePanel(null);
	}

	public void changePanel(JPanel view) {
		this.getContentPane().removeAll();
		this.getContentPane().add(userChangePanel, 0);
		this.getContentPane().add(tableBtnPanel[currentUserType.ordinal()], 1);
		if (view != null) this.getContentPane().add(view, 2);

		setCurrentCol(-1);
		setCurrentRow(-1);

		revalidate();
		repaint();
	}

	public void addUserChangeButtonListener(ActionListener listener) {
		btnUserChange.addActionListener(listener);
	}

	public void addAdminButtonListener(String btnName, ActionListener listener){
		btnOnAdminPanel[Arrays.asList(Constants.ADMIN_BUTTON_NAME).indexOf(btnName)].addActionListener(listener);
	}

	public void addUserButtonListener(String btnName, ActionListener listener){
		btnOnUserPanel[Arrays.asList(Constants.USER_BUTTON_NAME).indexOf(btnName)].addActionListener(listener);
	}

	public void addResetButtonListener(ActionListener listener) {
		btnReset.addActionListener(listener);
	}



}