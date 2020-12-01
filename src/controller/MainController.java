package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import common.AppManager;
import common.DbReset;
import model.DatabaseConnectionModel;
import view.MainView;

import javax.swing.*;

public class MainController {
	MainView _mainView;
	DatabaseConnectionModel connModel;
	
	
	public MainController() {
		this._mainView = AppManager.getInstance().getView();
		
		this._mainView.addUserButtonListener(new UserButtonListener());
		this._mainView.addResetButtonListener(new ResetButtonListener());

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
	public class ResetButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _mainView.btnReset)
				{
					_mainView.changePanel(null);
					JOptionPane.showMessageDialog(null,"초기화 완료");
					DbReset.initDB(_mainView.getConn());
				}

			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "null");
			}
		}
	}
}
