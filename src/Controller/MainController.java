package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Common.AppManager;
import Model.DatabaseConnectionModel;
import View.MainView;

public class MainController {
	MainView _mainView;
	DatabaseConnectionModel connModel;
	
	
	public MainController() {
		this._mainView = AppManager.getInstance().getView();
		
		this._mainView.addUserButtonListener(new UserButtonListener());
		
		connModel = new DatabaseConnectionModel();
		this._mainView.conn = connModel.getConn();
		//this._campCompView.addUserButtonListener(new UserButtonListener());
	
	}
	
	private class UserButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			_mainView.changeUser();

		
		}
		
	}
}
