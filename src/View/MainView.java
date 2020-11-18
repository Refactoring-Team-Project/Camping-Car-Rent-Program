package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Common.AppManager;
// 버튼패널, 검색버튼 변수명 변경

public class MainView extends JFrame {
	JButton btnCampComp, btnCustomer, btnCampCar, btnRepairshop, btnRentCar, btnCarCheck, btnRepairList, btnRentList,
			btnAdminUndectedCampCarSearch, btnAdminTop10CompanySearch, btnAdminBlackCusRankSearch, btnAdminRepairShopRankSearch;
	JButton btnUserPriceSearch, btnUserManufacturingYearSearch, btnUserMileageSearch;
	JButton btnReset, btnUser;
	JPanel adminBtnPanel, userBtnPanel, userChangePanel;


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

	public void init() {
		super.setLayout(new FlowLayout());

		initChangeUserPanel();
		initAdminButtonPanel1();
		initUserButtonPanell();

		add(userChangePanel);
		add(adminBtnPanel);

		initPanelSize();
	}

	public void initChangeUserPanel() {
		/* 사용자 관리자 전환 panel */
		btnUser = new JButton("관리자");
		userChangePanel = new JPanel();
		userChangePanel.add(btnUser);
	}

	public void initAdminButtonPanel1() {

		/* panel 1 */
		btnCampComp = new JButton("Camping_Company");
		btnCampCar = new JButton("Camping_Car");
		btnCustomer = new JButton("Rent_Customer");
		btnRepairshop = new JButton("Repairshop");
		btnRentCar = new JButton("Rent_Car");
		btnCarCheck = new JButton("Car_Check");
		btnRepairList = new JButton("Repair_List");
		btnAdminUndectedCampCarSearch = new JButton("검색1");
		btnAdminTop10CompanySearch = new JButton("검색2");
		btnAdminBlackCusRankSearch = new JButton("검색3");
		btnAdminRepairShopRankSearch = new JButton("검색4");

		btnReset = new JButton("초기화");

		adminBtnPanel = new JPanel();
		adminBtnPanel.add(btnCampComp);
		adminBtnPanel.add(btnCampCar);
		adminBtnPanel.add(btnCustomer);
		adminBtnPanel.add(btnRepairshop);
		adminBtnPanel.add(btnRentCar);
		adminBtnPanel.add(btnCarCheck);
		adminBtnPanel.add(btnRepairList);
		adminBtnPanel.add(btnAdminUndectedCampCarSearch);
		adminBtnPanel.add(btnAdminTop10CompanySearch);
		adminBtnPanel.add(btnAdminBlackCusRankSearch);
		adminBtnPanel.add(btnAdminRepairShopRankSearch);
		adminBtnPanel.add(btnReset);

	}

	public void initUserButtonPanell() {
		/* panel 2 */
		btnRentList = new JButton("Camping_Car(대여가능)");
		btnUserPriceSearch = new JButton("검색1");
		btnUserManufacturingYearSearch = new JButton("검색2");
		btnUserMileageSearch = new JButton("검색3");

		userBtnPanel = new JPanel();
		userBtnPanel.add(btnRentList);
		userBtnPanel.add(btnUserPriceSearch);
		userBtnPanel.add(btnUserManufacturingYearSearch);
		userBtnPanel.add(btnUserMileageSearch);

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
		this.getContentPane().removeAll();

		if (user == 0) { // 관리자 모드
			this.getContentPane().add(userChangePanel, 0);
			btnUser.setText("사용자");
			this.getContentPane().add(userBtnPanel, 1);
			user = 1;
		}

		else if (user == 1) { // 사용자 모드
			this.getContentPane().add(userChangePanel, 0);
			btnUser.setText("관리자");
			this.getContentPane().add(adminBtnPanel, 1);
			user = 0;
		}
		repaint();
	}

	public void changePanel(JPanel view) {
		this.getContentPane().removeAll();
		this.getContentPane().add(userChangePanel, 0);
		if (user == 0) { // 관리자 모드
			this.getContentPane().add(adminBtnPanel, 1);
		} else if (user == 1) { // 사용자 모드
			this.getContentPane().add(userBtnPanel, 1);
		}
		this.getContentPane().add(view, 2);


		this.getContentPane().repaint();
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
		btnAdminUndectedCampCarSearch.addActionListener(listener);
	}

	public void addAdminTop10CompanySearch(ActionListener listener) {
		btnAdminTop10CompanySearch.addActionListener(listener);
	}

	public void addAdminBlackCusRankSearch(ActionListener listener) {
		btnAdminBlackCusRankSearch.addActionListener(listener);
	}

	public void addAdminRepairShopRankSearch(ActionListener listener) {
		btnAdminRepairShopRankSearch.addActionListener(listener);
	}

	public void addUserPriceSearch(ActionListener listener) {
		btnUserPriceSearch.addActionListener(listener);
	}

	public void addUserManufacturingYearSearch(ActionListener listener) {
		btnUserManufacturingYearSearch.addActionListener(listener);
	}

	public void addUserMileageSearch(ActionListener listener) {
		btnUserMileageSearch.addActionListener(listener);
	}

}