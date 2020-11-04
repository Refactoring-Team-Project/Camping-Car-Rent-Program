package team_project;

public class AppManager {
	private static AppManager s_instance; // AppManager �ڽ��� ��Ÿ���� ����
	private MainView _view; // SimplePainterView�� �״�� �޾ƿͼ� ���� �� ����
	private CampingCompanyView _campingcompview; // DrawingPanelView�� �״�� �޾ƿͼ� ���� �� ����
	private RentCustomerView _rentcustview;
	private CarCheckView _carchkview;
	private RepairShopView _repairshopview;
	private RepairListView _repairlistview;
	private CampingCarView _campingcarview;
	private RentCarView _rentcarview;
	
	public MainView getView() { // AppManager�� ���� SimplePainterView�� �����Ϸ� �Ҷ� �� SimplePainterView��
								// ��ȯ�� �� �Լ�
		return _view;
	} // getView()
	
	public CampingCarView getCampingCarView() {
		return _campingcarview;
	}
	
	public RentCarView getRentCarView() {
		return _rentcarview;
	}

	public CampingCompanyView getCampingCompanyView() {// AppManager�� ���� DrawingPanelView�� �����Ϸ� �Ҷ� ��
														// DrawingPanelView�� ��ȯ�� �� �Լ�
		return _campingcompview;
	} // getDrawView()

	public RentCustomerView getRentCustomerView() {
		return _rentcustview;
	}

	public CarCheckView getCarCheckView() {
		return _carchkview;
	}

	public RepairShopView getRepairShopView() {
		return _repairshopview;
	}

	public RepairListView getRepairListView() {
		return _repairlistview;
	}
	
	public void setView(MainView view) { // AppManager�� ���� SimplePainterView�� ������ �� �ֵ��� AppManager��
											// SimplePainterView�� ������� �Լ�
		_view = view;
	} // setView()

	public void setCampingCompanyView(CampingCompanyView campingcompview) { // AppManager�� ���� DrawingPanelView��
																			// ������ �� �ֵ��� AppManager��
																			// DrawingPanelView�� ������� �Լ�
		_campingcompview = campingcompview;
	} // setDrawView()

	public void setRentCustomerView(RentCustomerView rentCustomerView) {

		_rentcustview = rentCustomerView;
	}

	public void setCarCheckView(CarCheckView carCheckView) {

		_carchkview = carCheckView;
	}

	public void setRepairShopView(RepairShopView repairshopview) {
		_repairshopview = repairshopview;
	}

	public void setRepairListView(RepairListView repairlistview) {
		_repairlistview = repairlistview;
	}
	
	public void setCampingCarView(CampingCarView campingcarview) { // AppManager�� ���� DrawingPanelView�� ������ �� �ֵ��� AppManager��
		_campingcarview = campingcarview;
	}
	
	public void setRentCarView(RentCarView rentcarview) {
		_rentcarview = rentcarview;
	}

	
	public static AppManager getInstance() {

		if (s_instance == null) // ó�� ȣ��Ǿ��ٸ� ��ü������ ���� �� AppMananger�� ���� �������ְ� ��ȯ
			s_instance = new AppManager();
		return s_instance;

	} // getInstance()

}
