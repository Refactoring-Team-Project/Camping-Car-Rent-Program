package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Common.AppManager;
import Model.DatabaseConnectionModel;
import View.MainView;

public class MainController {
	MainView _view;
	DatabaseConnectionModel connModel;
	
	
	public MainController() {
		this._view = AppManager.getInstance().getView();
		
		this._view.addUserButtonListener(new UserButtonListener());
		
		connModel = new DatabaseConnectionModel();
		this._view.conn = connModel.getConn();
		//this._campCompView.addUserButtonListener(new UserButtonListener());
	
	}
	
	private class UserButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			_view.changeUser();
		
		}
		
	}
}
