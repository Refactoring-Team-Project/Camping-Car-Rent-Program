package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import common.AppManager;
import model.DatabaseConnectionModel;
import view.MainView;

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
			_mainView.changePanel(null);
		
		}
		
	}
}
