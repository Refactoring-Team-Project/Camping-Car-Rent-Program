package team_project;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class CampingCarView extends JPanel {
	CampingCarModel campingCarModel;
	private MainView _view;
	DefaultTableModel model;
	JTable dbResult;
	JScrollPane scrollPane;
	JPanel updatePanel, buttonPanel;
	//int curRow = -1, curCol = -1;
	JButton btnInput, btnDelete, btnUpdate;
	JLabel[] labels;
	JTextField[] tf;
	
	Connection _conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public CampingCarView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780,420));
		AppManager.getInstance().setCampingCarView(this);
		campingCarModel = new CampingCarModel();
		
		_view = AppManager.getInstance().getView();
		_conn = _view.conn;
		model = new DefaultTableModel() {
	         public boolean isCellEditable(int row, int column) {
	            return false;
	         }
	      };
	    dbResult = new JTable(model);
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
	    dbResult.addMouseListener(new CampCarMouseListener());
	}
	
	public void addButtonListener(ActionListener listener) {
		btnInput.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnUpdate.addActionListener(listener);
	}
	
	public Connection getConn() {
		return _view.conn;
	}
	
	public void fieldReset() {
		for(JTextField t: tf) {
			t.setText("");
        }
		repaint();
	}
	
	private class CampCarMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			_view.setCurRow(dbResult.getSelectedRow());
			_view.setCurCol(dbResult.getSelectedColumn());
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
}
