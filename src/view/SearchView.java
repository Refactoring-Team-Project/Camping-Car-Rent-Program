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

public class SearchView extends JPanel{

	public DefaultTableModel searchDefaultTable;
	public JTable searchDBResult;
	JScrollPane searchScrollPane;

	public SearchView() {
		super.setLayout(new FlowLayout()); 
		setPreferredSize(new Dimension(780, 420));
		AppManager.getInstance().setSearchView(this);

		searchDefaultTable = new DefaultTableModel() {
	         public boolean isCellEditable(int row, int column) {
	            return false;
	         }
	      };
		searchDBResult = new JTable(searchDefaultTable);
		searchScrollPane = new JScrollPane(searchDBResult);
		add(searchScrollPane);


		searchScrollPane.setPreferredSize(new Dimension(780, 300));

		searchDBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	      // mouseListener 처리하기
	}
	
	public String SearchInput(String str) {
		return JOptionPane.showInputDialog(str);
	}
}
