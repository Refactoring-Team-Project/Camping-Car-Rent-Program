package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Common.AppManager;
import Model.CampingCarModel;
import View.CampingCarView;
import View.MainView;

public class CampingCarController {
	MainView _view;
	CampingCarView _campCarView;
	private CampingCarModel campCarModel;
	
	public CampingCarController() {
		this._view = AppManager.getInstance().getView();
		this._campCarView = AppManager.getInstance().getCampingCarView();
		campCarModel = new CampingCarModel();
		this._campCarView.addButtonListener(new ButtonListener());
		this._campCarView.addMouseListener(new CampCarMouseListener());
		this._view.addCampCarListener(new CampingCarButtonListener());
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == _campCarView.btnInput)
				{
					if(_campCarView.tf[0].getText().length() > 0) {
						campCarModel.setCarid(Integer.parseInt(_campCarView.tf[0].getText()));
					}
					else throw new NullPointerException();
					
					if (_campCarView.tf[1].getText().length() > 0) {
			            campCarModel.setCarname(_campCarView.tf[1].getText());
			        }
					
					if(_campCarView.tf[2].getText().length() > 0) {
			            campCarModel.setCarno(Integer.parseInt(_campCarView.tf[2].getText()));
			        }
					
					if(_campCarView.tf[3].getText().length() > 0) {
						campCarModel.setSeat(Integer.parseInt(_campCarView.tf[3].getText()));
					}
					
					if(_campCarView.tf[4].getText().length() > 0) {
						campCarModel.setManufacturer(_campCarView.tf[4].getText());
					}
					
					if(_campCarView.tf[5].getText().length() > 0) {
						campCarModel.setManu_year(Integer.parseInt(_campCarView.tf[5].getText()));
					}
			        
					if(_campCarView.tf[6].getText().length() > 0) {
						campCarModel.setDrivingdistance(Integer.parseInt(_campCarView.tf[6].getText()));
					}
					
					if(_campCarView.tf[7].getText().length() > 0) {
						campCarModel.setRentcost(Integer.parseInt(_campCarView.tf[7].getText()));
					}
					
					if (_campCarView.tf[8].getText().length() > 0) {
						campCarModel.setCompid(Integer.parseInt(_campCarView.tf[8].getText()));
					}
					else throw new NullPointerException();

					if(_campCarView.tf[9].getText().length() > 0) {
						campCarModel.setRegistdate(_campCarView.tf[9].getText());
					}

					campCarModel.insert(_campCarView.getConn());
					_campCarView.fieldReset();
				}
				else if (e.getSource() == _campCarView.btnDelete)
				{
					if (_view.getCurRow() != -1) {
						campCarModel.delete(_campCarView.getConn(), _campCarView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
						_campCarView.fieldReset();
					} else {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
					}
				}
				else if (e.getSource() == _campCarView.btnUpdate)
				{
					if(_view.getCurRow() != -1) {
						if(_campCarView.tf[0].getText().length() > 0) {
							campCarModel.setCarid(Integer.parseInt(_campCarView.tf[0].getText()));
						}
						else throw new NullPointerException();
						
						if (_campCarView.tf[1].getText().length() > 0) {
				            campCarModel.setCarname(_campCarView.tf[1].getText());
				        }
						
						if(_campCarView.tf[2].getText().length() > 0) {
				            campCarModel.setCarno(Integer.parseInt(_campCarView.tf[2].getText()));
				        }
						
						if(_campCarView.tf[3].getText().length() > 0) {
							campCarModel.setSeat(Integer.parseInt(_campCarView.tf[3].getText()));
						}
						
						if(_campCarView.tf[4].getText().length() > 0) {
							campCarModel.setManufacturer(_campCarView.tf[4].getText());
						}
						
						if(_campCarView.tf[5].getText().length() > 0) {
							campCarModel.setManu_year(Integer.parseInt(_campCarView.tf[5].getText()));
						}
				        
						if(_campCarView.tf[6].getText().length() > 0) {
							campCarModel.setDrivingdistance(Integer.parseInt(_campCarView.tf[6].getText()));
						}
						
						if(_campCarView.tf[7].getText().length() > 0) {
							campCarModel.setRentcost(Integer.parseInt(_campCarView.tf[7].getText()));
						}
						
						if (_campCarView.tf[8].getText().length() > 0) {
							campCarModel.setCompid(Integer.parseInt(_campCarView.tf[8].getText()));
						}
						else throw new NullPointerException();

						if(_campCarView.tf[9].getText().length() > 0) {
							campCarModel.setRegistdate(_campCarView.tf[9].getText());
						}
						campCarModel.update(_campCarView.getConn(), _campCarView.dbResult.getModel().getValueAt(_view.getCurRow(), 0));
						_campCarView.fieldReset();
					}
					
					else JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
				}
			
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "null");
			}
		}
	}
	
	private class CampCarMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			_view.setCurRow(_campCarView.dbResult.getSelectedRow());
			_view.setCurCol(_campCarView.dbResult.getSelectedColumn());
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
	
	
	private class CampingCarButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			_view.changePanel(_campCarView);
			_view.setCurRow(-1);
			_view.setCurCol(-1);
			
			ArrayList<Object[]> arr = campCarModel.select(_campCarView.getConn());
			_campCarView.model.setDataVector(null, arr.get(0));
			for (int i = 1; i < arr.size(); i++) {
				_campCarView.model.addRow(arr.get(i));
			}
			System.out.println("camping carrrrrrrrrrr");
			_view.add(AppManager.getInstance().getCampingCarView());
			_view.revalidate();
			_view.repaint();
			
		}
		
	}
}
