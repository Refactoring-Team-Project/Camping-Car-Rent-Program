package View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

import Common.AppManager;
import Model.CampingCarModel;

public class CampingCarView extends JPanel {

	private MainView _mainView;
	public DefaultTableModel campingCarDefaultTable;
	public JTable dbResult;
	JScrollPane scrollPane;
	public JPanel updatePanel, buttonPanel;
	public JButton btnInput, btnDelete, btnUpdate;
	JLabel[] labels;
	public JTextField[] tf;
	
	Connection _conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public CampingCarView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780,420));
		AppManager.getInstance().setCampingCarView(this);

		_mainView = AppManager.getInstance().getView();
		_conn = _mainView.conn;
		campingCarDefaultTable = new DefaultTableModel() {
	         public boolean isCellEditable(int row, int column) {
	            return false;
	         }
	      };
	    dbResult = new JTable(campingCarDefaultTable);
	    scrollPane = new JScrollPane(dbResult);
	    add(scrollPane);
	    
	    updatePanel = new JPanel();
	    labels = new JLabel[10];
	    labels[0] = new JLabel("carid:");
	    labels[1] = new JLabel("carname:");
	    labels[2] = new JLabel("carno:");
	    labels[3] = new JLabel("seat:");
	    labels[4] = new JLabel("manufacturer:");
	    labels[5] = new JLabel("manu_year:");
	    labels[6] = new JLabel("drivingdistance:");
	    labels[7] = new JLabel("rentcost:");
	    labels[8] = new JLabel("compid:");
	    labels[9] = new JLabel("registdate:");

	    tf = new JTextField[10];
	    tf[0] = new JTextField("", 3);
	    tf[1] = new JTextField("", 5);
	    tf[2] = new JTextField("", 3);
	    tf[3] = new JTextField("", 3);
	    tf[4] = new JTextField("", 10);
	    tf[5] = new JTextField("", 5);
	    tf[6] = new JTextField("", 7);
	    tf[7] = new JTextField("", 5);
	    tf[8] = new JTextField("", 3);
	    tf[9] = new JTextField("", 10);
	    
	    for (int i = 0; i < 10; i++) {
	    	updatePanel.add(labels[i]);
	    	updatePanel.add(tf[i]);
	    }
	    
	    add(updatePanel);
	    
	    btnInput = new JButton("입력");
	    btnDelete = new JButton("삭제");
	    btnUpdate = new JButton("변경");
	    
	    buttonPanel = new JPanel();
	      
	    buttonPanel.add(btnInput);
	    buttonPanel.add(btnDelete);
	    buttonPanel.add(btnUpdate);
	      
	    add(buttonPanel);
	      
	    scrollPane.setPreferredSize(new Dimension(780, 300));
	    updatePanel.setPreferredSize(new Dimension(780, 60));
	    buttonPanel.setPreferredSize(new Dimension(780, 50));
	      
	    dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public void addButtonListener(ActionListener listener) {
		btnInput.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}
	
	public void addMouseListener(MouseListener listener) {
		dbResult.addMouseListener(listener);
	}
	
	public Connection getConn() {
		return _mainView.conn;
	}
	
	public void fieldReset() {
		for(JTextField t: tf) {
			t.setText("");
        }
		repaint();
	}
	
	
	
}
