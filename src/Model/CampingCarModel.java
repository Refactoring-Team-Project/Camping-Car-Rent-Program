package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CampingCarModel {
	int carid;
	String carname;
	int carno;
	int seat;
	String manufacturer;
	int manu_year;
	int drivingdistance;
	int rentcost;
	int compid;
	String registdate;
	
	int selected_carid;
	int selected_compid;
	
	ResultSet rs;
	
	public ArrayList<Object[]> select(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		
		try {
			String sql = "SELECT * FROM Camping_Car";
            
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
            arr.add(column);
            
            while (rs.next()) {
            	Object[] data = {rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getDate(10)};
            	arr.add(data);
            }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return arr;
	}

	public void insert(Connection conn) {
		try {
			String sql = "INSERT INTO Camping_Car VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
			PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, carid);
            pstmt.setString(2, carname);
            pstmt.setInt(3, carno);
            pstmt.setInt(4, seat);
            pstmt.setString(5,  manufacturer);
            pstmt.setInt(6,  manu_year);
            pstmt.setInt(7,  drivingdistance);
            pstmt.setInt(8,  rentcost);
            pstmt.setInt(9,  compid);
            pstmt.setString(10,  registdate);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "추가되었습니다.");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	public void delete(Connection conn, Object object) {
		try {
			String sql = "DELETE FROM Camping_Car WHERE carid = " + object.toString() + ";";
            
			PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
         
            JOptionPane.showMessageDialog(null, "삭제되었습니다.");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	public void update(Connection conn, Object object) {
		try {
			String sql ="UPDATE Camping_Car SET carid=?,carname=?,carno=?,seat=?,manufacturer=?,manu_year=?,drivingdistance=?,rentcost=?,compid=?,registdate=? WHERE carid = " + object.toString() + ";";
			PreparedStatement pstmt;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, carid);
            pstmt.setString(2, carname);
            pstmt.setInt(3, carno);
            pstmt.setInt(4, seat);
            pstmt.setString(5,  manufacturer);
            pstmt.setInt(6,  manu_year);
            pstmt.setInt(7,  drivingdistance);
            pstmt.setInt(8,  rentcost);
            pstmt.setInt(9,  compid);
            pstmt.setString(10,  registdate);
            pstmt.executeUpdate();
         
            JOptionPane.showMessageDialog(null, "변경되었습니다.");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	public ArrayList<Object[]> selectRentAble(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		try {
			String sql = "select *\r\n" + 
                    "from Camping_Car c\r\n" + 
                    "where not exists (select 1 from Car_Rent r\r\n" + 
                    "left join Car_Check cc on r.rentno = cc.rentno\r\n" + 
                    "where r.carid = c.carid\r\n" + 
                    "and cc.rentno is null\r\n" + 
                    ")\r\n" + 
                    "order by carid;";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
			arr.add(column);

			while (rs.next()) {
				Object[] data = {
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getDate(10)
                  };
				arr.add(data);
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		return arr;
	}

	public ArrayList<Object[]> search1(Connection conn, String maxPrice) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		if(maxPrice!=null) {
			try {
				String sql = "select * from Camping_car c where c.rentcost <= "+ maxPrice +" and "
                    + "c.carid not in (select r.carid from Car_Rent r "
                    + "where r.rentno not in (select rentno from Car_Check)) order by carid;\n";
                 
                 Statement stmt = conn.createStatement();
                 rs = stmt.executeQuery(sql);
                 
                 Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
                 arr.add(column);
                 
                 while(rs.next()) {
                    Object[] data = {
                          rs.getInt(1),
                          rs.getString(2),
                          rs.getInt(3),
                          rs.getInt(4),
                          rs.getString(5),
                          rs.getInt(6),
                          rs.getInt(7),
                          rs.getInt(8),
                          rs.getInt(9),
                          rs.getDate(10)
                    };
                    arr.add(data);
                 }
              } catch (SQLException e1) {
            	  JOptionPane.showMessageDialog(null, e1.getMessage());
              }
		 } else JOptionPane.showMessageDialog(null, "금액을 입력하세요");
		return arr;
	}
	
	public ArrayList<Object[]> search2(Connection conn, String year) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		if(year!=null) {
	         try {
	        	 String sql = "select * from Camping_car c where c.manu_year >= "+ year +" and "
	                     + "c.carid not in (select r.carid from Car_Rent r "
	                     + "where r.rentno not in (select rentno from Car_Check)) order by carid;\n";
	                  
	        	 Statement stmt = conn.createStatement();
                 rs = stmt.executeQuery(sql);
                 
                 Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
                 arr.add(column);
                 
                 while(rs.next()) {
                    Object[] data = {
                          rs.getInt(1),
                          rs.getString(2),
                          rs.getInt(3),
                          rs.getInt(4),
                          rs.getString(5),
                          rs.getInt(6),
                          rs.getInt(7),
                          rs.getInt(8),
                          rs.getInt(9),
                          rs.getDate(10)
                    };
                    arr.add(data);
                 }
	          } catch (SQLException e1) {
	        	  JOptionPane.showMessageDialog(null, e1.getMessage());
	          }
		 } else JOptionPane.showMessageDialog(null, "년도를 입력하세요");
		return arr;
	}
	
	public ArrayList<Object[]> search3(Connection conn, String distance) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		if(distance!=null) {
	         try {
	        	String sql = "select * from Camping_car c where c.drivingdistance <= "+ distance +" and "
	                     + "c.carid not in (select r.carid from Car_Rent r "
	                     + "where r.rentno not in (select rentno from Car_Check)) order by carid;\n";
	                  
	        	Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                
                Object column[] = {"CARID", "CARNAME", "CARNO", "SEAT", "MANUFACTURER", "MANU YEAR", "DRIVING DISTANCE", "RENTCOST", "COMPID", "REGISTDATE"};
                arr.add(column);
                
                while(rs.next()) {
                   Object[] data = {
                         rs.getInt(1),
                         rs.getString(2),
                         rs.getInt(3),
                         rs.getInt(4),
                         rs.getString(5),
                         rs.getInt(6),
                         rs.getInt(7),
                         rs.getInt(8),
                         rs.getInt(9),
                         rs.getDate(10)
                   };
                   arr.add(data);
                }
	          } catch (SQLException e1) {
	        	  JOptionPane.showMessageDialog(null, e1.getMessage());
	          }
		 } else JOptionPane.showMessageDialog(null, "거리를 입력하세요");
		return arr;
	}
	
	public void selectedData(Connection conn, Object object) {
		try {
			String sql = "select carid, compid from Camping_Car where carid = " + object.toString() + ";";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				selected_carid = rs.getInt(1);
				selected_compid = rs.getInt(2);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public int getSelectedCarid() {
		return selected_carid;
	}

	public int getSelectedCompid() {
		return selected_compid;
	}
	
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public int getCarno() {
		return carno;
	}
	public void setCarno(int carno) {
		this.carno = carno;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getManu_year() {
		return manu_year;
	}
	public void setManu_year(int manu_year) {
		this.manu_year = manu_year;
	}
	public int getDrivingdistance() {
		return drivingdistance;
	}
	public void setDrivingdistance(int drivingdistance) {
		this.drivingdistance = drivingdistance;
	}
	public int getRentcost() {
		return rentcost;
	}
	public void setRentcost(int rentcost) {
		this.rentcost = rentcost;
	}
	public int getCompid() {
		return compid;
	}
	public void setCompid(int compid) {
		this.compid = compid;
	}
	public String getRegistdate() {
		return registdate;
	}
	public void setRegistdate(String registdate) {
		this.registdate = registdate;
	}
	
	
	
}
