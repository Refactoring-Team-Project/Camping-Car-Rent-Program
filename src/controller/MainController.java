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
		addEventListener();
		connDB();
	}

	public void addEventListener() {
		this._mainView.addUserChangeButtonListener(new UserChangeButtonListener());
		this._mainView.addResetButtonListener(new ResetButtonListener());
	}

	public void connDB() {
		connModel = new DatabaseConnectionModel();
		this._mainView.connection = connModel.getConn();
	}

	private class UserChangeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			_mainView.changeUser();
		}
	}


	/**** Refactoring X - 교수님 위한 초기화 코드 ***/
	public class ResetButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _mainView.btnReset)
				{
					_mainView.changePanel(null);
					JOptionPane.showMessageDialog(null,"초기화 완료");
					DbReset.initDB(_mainView.getConnection());
				}

			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "null");
			}
		}
	}
}
