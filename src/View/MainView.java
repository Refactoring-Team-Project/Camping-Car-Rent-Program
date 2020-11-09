package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Common.AppManager;

public class MainView extends JFrame {
	JButton btnCampComp, btnCustomer, btnCampCar, btnRepairshop, btnRentCar, btnCarCheck, btnRepairList, btnRentList,
			btnSearch1, btnSearch2, btnSearch3, btnSearch4;
	JButton btnUser_Search1, btnUser_Search2, btnUser_Search3;
	JButton btnReset, btnUser;
	JPanel pn1, pn2, userPanel, pn3;
	JPanel viewPanel;

	int user = 0; // 0은 관리자 1은 사용자
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

	public Connection getConn() {
		return conn;
	}

	public void init() {
		super.setLayout(new FlowLayout());

		/* 사용자 관리자 전환 panel */
		btnUser = new JButton("관리자");
		userPanel = new JPanel();
		userPanel.add(btnUser);
		add(userPanel);

		/* panel 1 */
		btnCampComp = new JButton("Camping_Company");
		btnCampCar = new JButton("Camping_Car");
		btnCustomer = new JButton("Rent_Customer");
		btnRepairshop = new JButton("Repairshop");
		btnRentCar = new JButton("Rent_Car");
		btnCarCheck = new JButton("Car_Check");
		btnRepairList = new JButton("Repair_List");
		btnSearch1 = new JButton("검색1");
		btnSearch2 = new JButton("검색2");
		btnSearch3 = new JButton("검색3");
		btnSearch4 = new JButton("검색4");

		btnReset = new JButton("초기화");

		pn1 = new JPanel();
		pn1.add(btnCampComp);
		pn1.add(btnCampCar);
		pn1.add(btnCustomer);
		pn1.add(btnRepairshop);
		pn1.add(btnRentCar);
		pn1.add(btnCarCheck);
		pn1.add(btnRepairList);
		pn1.add(btnSearch1);
		pn1.add(btnSearch2);
		pn1.add(btnSearch3);
		pn1.add(btnSearch4);
		pn1.add(btnReset);

		/* panel 2 */
		btnRentList = new JButton("Camping_Car(대여가능)");
		btnUser_Search1 = new JButton("검색1");
		btnUser_Search2 = new JButton("검색2");
		btnUser_Search3 = new JButton("검색3");

		pn2 = new JPanel();
		pn2.add(btnRentList);
		pn2.add(btnUser_Search1);
		pn2.add(btnUser_Search2);
		pn2.add(btnUser_Search3);

		add(pn1);
		/*** panel size ****/
		userPanel.setPreferredSize(new Dimension(780, 50));
		pn1.setPreferredSize(new Dimension(780, 80));
		pn2.setPreferredSize(new Dimension(780, 80));

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
		getContentPane().removeAll();

		if (user == 0) { // 관리자 모드
			add(userPanel, 0);
			btnUser.setText("사용자");
			add(pn2, 1);
			user = 1;
		}

		else if (user == 1) { // 사용자 모드
			add(userPanel, 0);
			btnUser.setText("관리자");
			add(pn1, 1);
			user = 0;
		}
		repaint();
	}

	public void changePanel(JPanel view) {
		this.getContentPane().removeAll();
		this.getContentPane().add(userPanel, 0);
		if (user == 0) { // 관리자 모드
			this.getContentPane().remove(pn2);
			this.getContentPane().add(pn1, 1);
		} else if (user == 1) { // 사용자 모드
			this.getContentPane().remove(pn1);
			this.getContentPane().add(pn2, 1);
		}
		this.getContentPane().add(view, 2);

		this.repaint();
	}

	public void addUserButtonListener(ActionListener listener) {
		btnUser.addActionListener(listener);
	}

	public void addCampCompListener(ActionListener listener) {
		btnCampComp.addActionListener(listener);

	}

	public void addRentCustListener(ActionListener listener) {
		btnCustomer.addActionListener(listener);

	}

	public void addCarChkListener(ActionListener listener) {
		btnCarCheck.addActionListener(listener);

	}

	public void addRepairShopListener(ActionListener listener) {
		btnRepairshop.addActionListener(listener);
	}

	public void addRepairListListener(ActionListener listener) {
		btnRepairList.addActionListener(listener);
	}

	public void addCampCarListener(ActionListener listener) {
		btnCampCar.addActionListener(listener);
	}

	public void addRentCarListener(ActionListener listener) {
		btnRentCar.addActionListener(listener);
	}

	public void addCarRentListListener(ActionListener listener) {
		btnRentList.addActionListener(listener);
	}

	public void addSearch1Listener(ActionListener listener) {
		btnSearch1.addActionListener(listener);
	}

	public void addSearch2Listener(ActionListener listener) {
		btnSearch2.addActionListener(listener);
	}

	public void addSearch3Listener(ActionListener listener) {
		btnSearch3.addActionListener(listener);
	}

	public void addSearch4Listener(ActionListener listener) {
		btnSearch4.addActionListener(listener);
	}

	public void addUserSearch1Listener(ActionListener listener) {
		btnUser_Search1.addActionListener(listener);
	}

	public void addUserSearch2Listener(ActionListener listener) {
		btnUser_Search2.addActionListener(listener);
	}

	public void addUserSearch3Listener(ActionListener listener) {
		btnUser_Search3.addActionListener(listener);
	}

}