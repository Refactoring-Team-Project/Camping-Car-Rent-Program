package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import common.AppManager;

public class SearchView extends View{

	public SearchView() {
		AppManager.getInstance().setSearchView(this);
	}

	@Override
	public void initUpdatePanel() {}

	@Override
	public void initButtonPanel() {}

	@Override
	public void setInputFiledName() { }

	@Override
	public void setButtons() { }

	public String searchInput(String str) {
		return JOptionPane.showInputDialog(str);
	}
}
