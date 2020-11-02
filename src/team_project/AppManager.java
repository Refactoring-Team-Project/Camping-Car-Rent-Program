package team_project;

public class AppManager {
	private static AppManager s_instance; // AppManager �ڽ��� ��Ÿ���� ����
	private MainView _view; // SimplePainterView�� �״�� �޾ƿͼ� ���� �� ����
	private CampingCompanyView _campingcompview; // DrawingPanelView�� �״�� �޾ƿͼ� ���� �� ����
	private RentCustomerView _rentcustview;
	private CarCheckView _carchkview;

	public MainView getView() { // AppManager�� ���� SimplePainterView�� �����Ϸ� �Ҷ� �� SimplePainterView��
								// ��ȯ�� �� �Լ�
		return _view;
	} // getView()

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

	public static AppManager getInstance() {

		if (s_instance == null) // ó�� ȣ��Ǿ��ٸ� ��ü������ ���� �� AppMananger�� ���� �������ְ� ��ȯ
			s_instance = new AppManager();
		return s_instance;

	} // getInstance()

}
