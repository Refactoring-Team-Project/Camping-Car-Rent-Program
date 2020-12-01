package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.AppManager;
import common.Constants;
// 버튼패널, 검색버튼 변수명 변경

public class MainView extends JFrame {
	public JButton btnReset;
	JButton btnUser;
	JButton[] btnAdmins, btnUsers;
	JPanel adminBtnPanel, userBtnPanel, userChangePanel;
	JPanel[] tableBtnPanel = new JPanel[2];

	public enum Admin {
		관리자, 사용자
	}

	Admin admin;
	int curRow = -1, curCol = -1;

	public Connection conn;

	public MainView() {
		super("refactoring");
		AppManager.getInstance().setView(this);

		init();

		setVisible(true);
		setBounds(500, 200, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init() {
		super.setLayout(new FlowLayout());

		admin = Admin.관리자;
		initChangeUserPanel();
		initAdminButtonPanel1();
		initUserButtonPanell();
		tableBtnPanel[Admin.관리자.ordinal()] = adminBtnPanel;
		tableBtnPanel[Admin.사용자.ordinal()] = userBtnPanel;
		add(userChangePanel);
		add(adminBtnPanel);

		initPanelSize();
	}

	public void initChangeUserPanel() {
		/* 사용자 관리자 전환 panel */
		btnUser = new JButton(admin.name());
		userChangePanel = new JPanel();
		userChangePanel.add(btnUser);
	}

	public void initAdminButtonPanel1() {

		/* panel 1 */
		adminBtnPanel = new JPanel();
		btnAdmins = new JButton[Constants.ADMIN_BUTTON_NAME.length];

		for(int i=0;i<Constants.ADMIN_BUTTON_NAME.length;i++) {
			btnAdmins[i] = new JButton(Constants.ADMIN_BUTTON_NAME[i]);
			adminBtnPanel.add(btnAdmins[i]);
		}

		btnReset = new JButton("초기화");
		adminBtnPanel.add(btnReset);

	}

	public void initUserButtonPanell() {
		/* panel 2 */
		userBtnPanel = new JPanel();
		btnUsers = new JButton[Constants.USER_BUTTON_NAME.length];

		for(int i=0;i<Constants.USER_BUTTON_NAME.length;i++) {
			btnUsers[i] = new JButton(Constants.USER_BUTTON_NAME[i]);
			userBtnPanel.add(btnUsers[i]);
		}

	}

	public void initPanelSize() {
		/*** panel size ****/
		userChangePanel.setPreferredSize(new Dimension(780, 50));
		adminBtnPanel.setPreferredSize(new Dimension(780, 80));
		userBtnPanel.setPreferredSize(new Dimension(780, 80));
	}

	public Connection getConn() {
		return conn;
	}

	public int getCurRow() {
		return curRow;
	}

	public void setCurRow(int selectedRow) {
		curRow = selectedRow;
	}

	public int getCurCol() {
		return curCol;
	}

	public void setCurCol(int selectedCol) {
		curCol = selectedCol;
	}

	public void changeUser() {

		if (admin.name().equals("관리자")) { // 관리자 모드
			admin = Admin.사용자;
		} else if (admin.name().equals("사용자")) { // 사용자 모드
			admin = Admin.관리자;
		}
		btnUser.setText(admin.name());
	}

	public void changePanel(JPanel view) {
		this.getContentPane().removeAll();
		this.getContentPane().add(userChangePanel, 0);
		this.getContentPane().add(tableBtnPanel[admin.ordinal()], 1);
		if (view != null)
			this.getContentPane().add(view, 2);

		setCurCol(-1);
		setCurRow(-1);

		this.getContentPane().repaint();
	}

	public void addUserButtonListener(ActionListener listener) {
		btnUser.addActionListener(listener);
	}

	public void addResetButtonListener(ActionListener listener) {
		btnReset.addActionListener(listener);
	}

	public void addAdminButtonListener(String btnName, ActionListener listener){
		for(int i=0;i<Constants.ADMIN_BUTTON_NAME.length;i++) {
			if(btnName.contains(Constants.ADMIN_BUTTON_NAME[i])){
				btnAdmins[i].addActionListener(listener);
			}
		}
	}

	public void addUserButtonListener(String btnName, ActionListener listener){
		for(int i=0;i<Constants.USER_BUTTON_NAME.length;i++) {
			if(btnName.contains(Constants.USER_BUTTON_NAME[i])){
				btnUsers[i].addActionListener(listener);
			}
		}
	}


}