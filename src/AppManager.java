public class AppManager {
	private static AppManager s_instance; // AppManager �ڽ��� ��Ÿ���� ����
	private MainView _view; // SimplePainterView�� �״�� �޾ƿͼ� ���� �� ����
	private CampingCarView _campingcarview;
	private RentCarView _rentcarview;
	
	public MainView getView() { // AppManager�� ���� SimplePainterView�� �����Ϸ� �Ҷ� �� SimplePainterView�� ��ȯ�� �� �Լ�
		return _view;
	} // getView()

	public CampingCarView getCampingCarView() {
		return _campingcarview;
	}
	
	public RentCarView getRentCarView() {
		return _rentcarview;
	}

	public void setView(MainView view) { // AppManager�� ���� SimplePainterView�� ������ �� �ֵ��� AppManager��
													// SimplePainterView�� ������� �Լ�
		_view = view;
	} // setView()

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
