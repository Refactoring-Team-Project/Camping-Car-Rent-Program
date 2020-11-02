import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		// SimplePainterController ����
		MainView primary = new MainView();
	
		CampingCarView campCarView = new CampingCarView();
		CampingCarController campCarCont = new CampingCarController();
		
		RentCarView rentCarView = new RentCarView();
		RentCarController rentCarCont = new RentCarController();
	}

}
