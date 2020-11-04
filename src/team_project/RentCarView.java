package team_project;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class RentCarView extends JPanel {
	private RentCarModel rentCarModel;
	private MainView _view;
	DefaultTableModel model;
	JTable dbResult;
	JScrollPane scrollPane;
	JPanel updatePanel, buttonPanel;
	JButton btnReturn;
	JLabel[] labels;
	JTextField[] tf;
	
	Connection _conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public RentCarView() {
		super.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(780,420));
		AppManager.getInstance().setRentCarView(this);
		_view = AppManager.getInstance().getView();
		rentCarModel = new RentCarModel();
		
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
	    labels = new JLabel[7];
	    labels[0] = new JLabel("rentno:");
	    labels[1] = new JLabel("carid:");
	    labels[2] = new JLabel("explain_front:");
	    labels[3] = new JLabel("explain_left:");
	    labels[4] = new JLabel("explain_right:");
	    labels[5] = new JLabel("explain_back:");
	    labels[6] = new JLabel("repair_required:");

	    tf = new JTextField[7];
	    tf[0] = new JTextField("", 3);
	    tf[1] = new JTextField("", 3);
	    tf[2] = new JTextField("", 10);
	    tf[3] = new JTextField("", 10);
	    tf[4] = new JTextField("", 10);
	    tf[5] = new JTextField("", 10);
	    tf[6] = new JTextField("", 2);

	    for (int i = 0; i < 7; i++) {
	    	updatePanel.add(labels[i]);
	    	updatePanel.add(tf[i]);
	    }
	    
	    add(updatePanel);
	    
	    buttonPanel = new JPanel();
	    btnReturn = new JButton("반환");
	    buttonPanel.add(btnReturn);
	    
	    add(buttonPanel);
	    
	    scrollPane.setPreferredSize(new Dimension(780, 300));
	    updatePanel.setPreferredSize(new Dimension(780, 60));
	    buttonPanel.setPreferredSize(new Dimension(780, 50));
	      
	    dbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    dbResult.addMouseListener(new RentCarMouseListener());
	    }
	
	public void addButtonListener(ActionListener listener) {
		btnReturn.addActionListener(listener);
	}
	
	public Connection getConn() {
		return _view.conn;
	}
	
	public void fieldReset() {
		for (JTextField t:tf) {
			t.setText("");
		}
		repaint();
	}
	
	private class RentCarMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
				_view.setCurRow(dbResult.getSelectedRow());
				_view.setCurCol(dbResult.getSelectedColumn());
				
				tf[0].setText(dbResult.getModel().getValueAt(_view.getCurRow(), 0).toString());
		        tf[0].setDisabledTextColor(Color.black);
		        
		        tf[1].setText(dbResult.getModel().getValueAt(_view.getCurRow(), 1).toString());
		        tf[1].setDisabledTextColor(Color.black);
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
