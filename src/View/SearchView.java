package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Common.AppManager;

public class SearchView extends JPanel{

	public DefaultTableModel model;
	public JTable dbResult;
	JScrollPane scrollPane;
	int curRow=-1, curCol=-1;
	   
	Connection _conn;
	Statement stmt; //select
	PreparedStatement pstmt; //insert, delete
	ResultSet rs; 
	   
	public SearchView() {
		super.setLayout(new FlowLayout()); 
		setPreferredSize(new Dimension(780, 420));
		AppManager.getInstance().setSearchView(this);
		
		model = new DefaultTableModel() {
	         public boolean isCellEditable(int row, int column) {
	            return false;
	         }
	      };
		dbResult = new JTable(model);
		scrollPane = new JScrollPane(dbResult);
		add(scrollPane);
		
	      
	      scrollPane.setPreferredSize(new Dimension(780, 300));
	      
	      dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	      // mouseListener 처리하기
	}
	
	public String SearchInput(String str) {
		return JOptionPane.showInputDialog(str);
	}
}
