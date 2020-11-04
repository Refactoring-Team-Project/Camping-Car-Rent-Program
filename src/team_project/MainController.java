package team_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
	MainView _view;
	
	
	
	public MainController() {
		this._view = AppManager.getInstance().getView();
		
		this._view.addUserButtonListener(new UserButtonListener());
		
		//this._campCompView.addUserButtonListener(new UserButtonListener());
	
	}
	
	private class UserButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			_view.changeUser();
		
		}
		
	}
}
