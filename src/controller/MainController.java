package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import common.AppManager;
import common.DbReset;
import model.DatabaseConnectionModel;
import view.MainView;

import javax.swing.*;

public class MainController {
	MainView mainView;
	DatabaseConnectionModel connectionModel;

	public MainController() {
		this.mainView = AppManager.getInstance().getMainView();
		addEventListener();
		connectionDB();
	}

	public void addEventListener() {
		this.mainView.addUserChangeButtonListener(new UserChangeButtonListener());
		this.mainView.addResetButtonListener(new ResetButtonListener());
	}

	public void connectionDB() {
		connectionModel = new DatabaseConnectionModel();
		this.mainView.connection = connectionModel.getConnection();
	}

	private class UserChangeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainView.changeUser();
		}
	}


	/**** Refactoring X - 교수님 위한 초기화 코드 ***/
	public class ResetButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == mainView.btnReset)
				{
					mainView.changePanel(null);
					JOptionPane.showMessageDialog(null,"초기화 완료");
					DbReset.initDB(mainView.getConnection());
				}

			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "null");
			}
		}
	}
}
