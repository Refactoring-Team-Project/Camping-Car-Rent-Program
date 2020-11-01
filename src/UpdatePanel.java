import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdatePanel extends JPanel {
   JPanel textP, btnP;
   JLabel[] labels;
   JTextField[] tf;
   Connection conn;
   Statement stmt; // select
   PreparedStatement pstmt; // insert, delete

   UpdatePanel() {
      setSize(new Dimension(800, 200));
   }

   void campingComp() {
      removeAll();
      labels = new JLabel[6];
      labels[0] = new JLabel("compid:");
      labels[1] = new JLabel("compname:");
      labels[2] = new JLabel("address:");
      labels[3] = new JLabel("phone:");
      labels[4] = new JLabel("manager_name:");
      labels[5] = new JLabel("manager_email:");

      tf = new JTextField[6];
      tf[0] = new JTextField("", 3);
      tf[1] = new JTextField("", 7);
      tf[2] = new JTextField("", 10);
      tf[3] = new JTextField("", 10);
      tf[4] = new JTextField("", 7);
      tf[5] = new JTextField("", 10);

      for (int i = 0; i < 6; i++) {
         add(labels[i]);
         add(tf[i]);
      }

   }

   void campingCar() {
      removeAll();
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
         add(labels[i]);
         add(tf[i]);
      }
   }

   void customer() {
      removeAll();
      labels = new JLabel[5];
      labels[0] = new JLabel("licnese_no::");
      labels[1] = new JLabel("name:");
      labels[2] = new JLabel("address:");
      labels[3] = new JLabel("phone:");
      labels[4] = new JLabel("email:");
      
      tf = new JTextField[5];
      tf[0] = new JTextField("", 3);
      tf[1] = new JTextField("", 7);
      tf[2] = new JTextField("", 10);
      tf[3] = new JTextField("", 10);
      tf[4] = new JTextField("", 10);

      for (int i = 0; i < 5; i++) {
         add(labels[i]);
         add(tf[i]);
      }
   }
   
   void repairshop() {
      removeAll();
      labels = new JLabel[6];
      labels[0] = new JLabel("shopid:");
      labels[1] = new JLabel("shopname:");
      labels[2] = new JLabel("address:");
      labels[3] = new JLabel("phone:");
      labels[4] = new JLabel("manager_name:");
      labels[5] = new JLabel("manager_email:");

      tf = new JTextField[6];
      tf[0] = new JTextField("", 3);
      tf[1] = new JTextField("", 5);
      tf[2] = new JTextField("", 10);
      tf[3] = new JTextField("", 10);
      tf[4] = new JTextField("", 7);
      tf[5] = new JTextField("", 10);

      for (int i = 0; i < 6; i++) {
         add(labels[i]);
         add(tf[i]);
      }
   }
   
   void carCheck() {
      removeAll();
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
         add(labels[i]);
         add(tf[i]);
      }
   }
   void repairList() {
      removeAll();
      labels = new JLabel[10];
      labels[0]= new JLabel("repairno");
      labels[1]= new JLabel("carid");
      labels[2]= new JLabel("shopid");
      labels[3]= new JLabel("compid");
      labels[4]= new JLabel("license_no");
      labels[5]= new JLabel("repairdetails");
      labels[6]= new JLabel("repairdate");
      labels[7]= new JLabel("repaircost");
      labels[8]= new JLabel("paymentdeadline");
      labels[9]= new JLabel("repairhistory");
      
      tf = new JTextField[10];
      tf[0] = new JTextField("",3);
      tf[1] = new JTextField("",3);
      tf[2] = new JTextField("",3);
      tf[3] = new JTextField("",3);
      tf[4] = new JTextField("",3);
      tf[5] = new JTextField("",10);
      tf[6] = new JTextField("",5);
      tf[7] = new JTextField("",3);
      tf[8] = new JTextField("",5);
      tf[9] = new JTextField("",10);
   
      for (int i = 0; i < 10; i++) {
            add(labels[i]);
            add(tf[i]);
         }
   }
   void rentCar() {
      removeAll();
      labels = new JLabel[10];
      labels[0]= new JLabel("rentno");
      labels[1]= new JLabel("carid");
      labels[2]= new JLabel("license_no");
      labels[3]= new JLabel("compid");
      labels[4]= new JLabel("rent_date");
      labels[5]= new JLabel("rentalperiod");
      labels[6]= new JLabel("charge");
      labels[7]= new JLabel("paymentdeadline");
      labels[8]= new JLabel("billhistory");
      labels[9]= new JLabel("billhistorycost");
      
      tf = new JTextField[10];
      tf[0] = new JTextField("",3);
      tf[1] = new JTextField("",3);
      tf[2] = new JTextField("",3);
      tf[3] = new JTextField("",3);
      tf[4] = new JTextField("",10);
      tf[5] = new JTextField("",5);
      tf[6] = new JTextField("",5);
      tf[7] = new JTextField("",10);
      tf[8] = new JTextField("",10);
      tf[9] = new JTextField("",5);
   
      for (int i = 0; i < 10; i++) {
            add(labels[i]);
            add(tf[i]);
         }
   }
   

   void insert(int table) {
      if (table == 1) { //camping_company
         try {
            String sql = "INSERT INTO Camping_Company VALUES(?, ?, ?, ?, ?, ?)";
                  
            pstmt = conn.prepareStatement(sql);
            
            if(tf[0].getText().length() > 0) {
               Integer compid = Integer.parseInt(tf[0].getText());
               pstmt.setInt(1, compid);
            }
            else pstmt.setNull(1, Types.INTEGER);
            
            if(tf[1].getText().length() > 0) {
               String compname =  tf[1].getText();
               pstmt.setString(2, compname);
            }
            else pstmt.setNull(2, Types.VARCHAR);
            
            if(tf[2].getText().length() > 0) {
               String address = tf[2].getText();
               pstmt.setString(3, address);
            }
            else pstmt.setNull(3, Types.VARCHAR);
            
            if(tf[3].getText().length() > 0) {
               String phone = tf[3].getText();
               pstmt.setString(4, phone);
            }
            else pstmt.setNull(4, Types.VARCHAR);
            
            if(tf[4].getText().length() > 0) {
               String manager_name = tf[4].getText();
               pstmt.setString(5,  manager_name);
            }
            else pstmt.setNull(5, Types.VARCHAR);
              
            if(tf[5].getText().length() > 0) {
               String manager_email = tf[5].getText();
               pstmt.setString(6,  manager_email);
            }
            else pstmt.setNull(6, Types.VARCHAR);
            
            pstmt.executeUpdate();
         
            
            JOptionPane.showMessageDialog(null, "추가되었습니다.");
            
            for(JTextField t:tf) {
               t.setText("");
            }
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
      }
      
      if (table == 2) { //camping_car
         try {
            String sql = "INSERT INTO Camping_Car VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                  
            pstmt = conn.prepareStatement(sql);
            
            if(tf[0].getText().length() > 0) {
               Integer carid = Integer.parseInt(tf[0].getText());
               pstmt.setInt(1, carid);
            }
            else pstmt.setNull(1, Types.INTEGER);
            
            if(tf[1].getText().length() > 0) {
               String carname =  tf[1].getText();
               pstmt.setString(2, carname);
            }
            else pstmt.setNull(2, Types.VARCHAR);
            
            if(tf[2].getText().length() > 0) {
               Integer carno = Integer.parseInt(tf[2].getText());
               pstmt.setInt(3, carno);
            }
            else pstmt.setNull(3, Types.VARCHAR);
            
            if(tf[3].getText().length() > 0) {
               Integer seat = Integer.parseInt(tf[3].getText());
               pstmt.setInt(4, seat);
            }
            else pstmt.setNull(4, Types.VARCHAR);
            
            if(tf[4].getText().length() > 0) {
               String manufacturer = tf[4].getText();
               pstmt.setString(5,  manufacturer);
            }
            else pstmt.setNull(5, Types.VARCHAR);
              
            if(tf[5].getText().length() > 0) {
               Integer manu_year = Integer.parseInt(tf[5].getText());
               pstmt.setInt(6,  manu_year);
            }
            else pstmt.setNull(6, Types.VARCHAR);
            
            if(tf[6].getText().length() > 0) {
               Integer drivingdistance = Integer.parseInt(tf[6].getText());
               pstmt.setInt(7,  drivingdistance);
            }
            else pstmt.setNull(7, Types.VARCHAR);
            
            if(tf[7].getText().length() > 0) {
               Integer rentcost = Integer.parseInt(tf[7].getText());
               pstmt.setInt(8,  rentcost);
            }
            else pstmt.setNull(8, Types.VARCHAR);
            
            if(tf[8].getText().length() > 0) {
               Integer compid = Integer.parseInt(tf[8].getText());
               pstmt.setInt(9,  compid);
            }
            else pstmt.setNull(9, Types.VARCHAR);
            
            if(tf[9].getText().length() > 0) {
               String registday = tf[9].getText();
               pstmt.setString(10,  registday);
            }
            else pstmt.setNull(10, Types.VARCHAR);
            pstmt.executeUpdate();
         
            
            JOptionPane.showMessageDialog(null, "추가되었습니다.");
            
            for(JTextField t:tf) {
               t.setText("");
            }
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
      }
      
      if (table == 3) { //customer
         try {
            String sql = "INSERT INTO Rent_Customer VALUES(?, ?, ?, ?, ?)";
                  
            pstmt = conn.prepareStatement(sql);
            
            if(tf[0].getText().length() > 0) {
               Integer license_no = Integer.parseInt(tf[0].getText());
               pstmt.setInt(1, license_no);
            }
            else pstmt.setNull(1, Types.INTEGER);
            
            if(tf[1].getText().length() > 0) {
               String name =  tf[1].getText();
               pstmt.setString(2, name);
            }
            else pstmt.setNull(2, Types.VARCHAR);
            
            if(tf[2].getText().length() > 0) {
               String address = tf[2].getText();
               pstmt.setString(3, address);
            }
            else pstmt.setNull(3, Types.VARCHAR);
            
            if(tf[3].getText().length() > 0) {
               String phone = tf[3].getText();
               pstmt.setString(4, phone);
            }
            else pstmt.setNull(4, Types.VARCHAR);
            
            if(tf[4].getText().length() > 0) {
               String email = tf[4].getText();
               pstmt.setString(5,  email);
            }
            else pstmt.setNull(5, Types.VARCHAR);
            
            pstmt.executeUpdate();
         
            
            JOptionPane.showMessageDialog(null, "추가되었습니다.");
            
            for(JTextField t:tf) {
               t.setText("");
            }
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
      }
      
      if (table == 4) { //repairshop
         try {
            String sql = "INSERT INTO Repairshop VALUES(?, ?, ?, ?, ?, ?)";
                  
            pstmt = conn.prepareStatement(sql);
            
            if(tf[0].getText().length() > 0) {
               Integer shopid = Integer.parseInt(tf[0].getText());
               pstmt.setInt(1, shopid);
            }
            else pstmt.setNull(1, Types.INTEGER);
            
            if(tf[1].getText().length() > 0) {
               String shopname =  tf[1].getText();
               pstmt.setString(2, shopname);
            }
            else pstmt.setNull(2, Types.VARCHAR);
            
            if(tf[2].getText().length() > 0) {
               String address = tf[2].getText();
               pstmt.setString(3, address);
            }
            else pstmt.setNull(3, Types.VARCHAR);
            
            if(tf[3].getText().length() > 0) {
               String phone = tf[3].getText();
               pstmt.setString(4, phone);
            }
            else pstmt.setNull(4, Types.VARCHAR);
            
            if(tf[4].getText().length() > 0) {
               String manager_name = tf[4].getText();
               pstmt.setString(5,  manager_name);
            }
            else pstmt.setNull(5, Types.VARCHAR);
              
            if(tf[5].getText().length() > 0) {
               String manager_email = tf[5].getText();
               pstmt.setString(6,  manager_email);
            }
            else pstmt.setNull(6, Types.VARCHAR);
            
            pstmt.executeUpdate();
         
            
            JOptionPane.showMessageDialog(null, "추가되었습니다.");
            
            for(JTextField t:tf) {
               t.setText("");
            }
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
      }
   }
   
   void delete(int table, Object object) {
      if (table == 1) {
         try {
            String sql = "DELETE FROM Camping_Company WHERE compid = " + object.toString() + ";";
            
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
         
            
            JOptionPane.showMessageDialog(null, "삭제되었습니다.");
            
            for(JTextField t:tf) {
               t.setText("");
            }
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
      }
      
      if (table == 2) {
         try {
            String sql = "DELETE FROM Camping_Car WHERE carid = " + object.toString() + ";";
            
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
         
            
            JOptionPane.showMessageDialog(null, "삭제되었습니다.");
            
            for(JTextField t:tf) {
               t.setText("");
            }
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
      }
      
      if (table == 3) {
         try {
            String sql = "DELETE FROM Rent_Customer WHERE license_no = " + object.toString() + ";";
            
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
         
            
            JOptionPane.showMessageDialog(null, "삭제되었습니다.");
            
            for(JTextField t:tf) {
               t.setText("");
            }
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
      }
      
      if (table == 4) {
         try {
            String sql = "DELETE FROM Repairshop WHERE shopid = " + object.toString() + ";";
            
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
         
            
            JOptionPane.showMessageDialog(null, "삭제되었습니다.");
            
            for(JTextField t:tf) {
               t.setText("");
            }
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
      }
      
      if (table == 7) {
          try {
             String sql = "DELETE FROM Repair_List WHERE repairno = " + object.toString() + ";";
             
             System.out.println(sql);
             pstmt = conn.prepareStatement(sql);
             pstmt.executeUpdate();
          
             
             JOptionPane.showMessageDialog(null, "삭제되었습니다.");
             
             for(JTextField t:tf) {
                t.setText("");
             }
             
          } catch (SQLException e1) {
             JOptionPane.showMessageDialog(null, e1.getMessage());
          }
       }
   }
   
   void update(int table, Object object) {
            
      if (table == 1){
            try {
                String sql = "UPDATE Camping_Company SET compid = ?,compname = ?,address = ?,phone = ?,manager_name = ?, manager_email = ? WHERE compid = " + object.toString() + ";";
                      
                pstmt = conn.prepareStatement(sql);
                
                if(tf[0].getText().length() > 0) {
                   Integer compid = Integer.parseInt(tf[0].getText());
                   pstmt.setInt(1, compid);
                }
                else pstmt.setNull(1, Types.INTEGER);
                
                if(tf[1].getText().length() > 0) {
                   String compname =  tf[1].getText();
                   pstmt.setString(2, compname);
                }
                else pstmt.setNull(2, Types.VARCHAR);
                
                if(tf[2].getText().length() > 0) {
                   String address = tf[2].getText();
                   pstmt.setString(3, address);
                }
                else pstmt.setNull(3, Types.VARCHAR);
                
                if(tf[3].getText().length() > 0) {
                   String phone = tf[3].getText();
                   pstmt.setString(4, phone);
                }
                else pstmt.setNull(4, Types.VARCHAR);
                
                if(tf[4].getText().length() > 0) {
                   String manager_name = tf[4].getText();
                   pstmt.setString(5,  manager_name);
                }
                else pstmt.setNull(5, Types.VARCHAR);
                  
                if(tf[5].getText().length() > 0) {
                   String manager_email = tf[5].getText();
                   pstmt.setString(6,  manager_email);
                }
                else pstmt.setNull(6, Types.VARCHAR);
                
                pstmt.executeUpdate();
             
                
                JOptionPane.showMessageDialog(null, "수정되었습니다.");
                
                for(JTextField t:tf) {
                   t.setText("");
                }
                
             } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
             }
          }

      if (table == 2){
            try {
                String sql = "UPDATE Camping_Car SET carid=?,carname=?,carno=?,seat=?,manufacturer=?,manu_year=?,drivingdistance=?,rentcost=?,compid=?,registdate=? WHERE carid = " + object.toString() + ";";
                      
                pstmt = conn.prepareStatement(sql);
                
                if(tf[0].getText().length() > 0) {
                   Integer carid = Integer.parseInt(tf[0].getText());
                   pstmt.setInt(1, carid);
                }
                else pstmt.setNull(1, Types.INTEGER);
                          
                if(tf[1].getText().length() > 0) {
                   String carname =  tf[1].getText();
                   pstmt.setString(2, carname);
                }
                else pstmt.setNull(2, Types.VARCHAR);
                
                if(tf[2].getText().length() > 0) {
                      Integer carno = Integer.parseInt(tf[2].getText());
                      pstmt.setInt(3, carno);
                   }
                   else pstmt.setNull(3, Types.INTEGER);
                
                if(tf[3].getText().length() > 0) {
                      Integer seat = Integer.parseInt(tf[3].getText());
                      pstmt.setInt(4, seat);
                   }
                   else pstmt.setNull(4, Types.INTEGER);
                
                if(tf[4].getText().length() > 0) {
                      String manufactor = tf[4].getText();
                      pstmt.setString(5, manufactor);
                   }
                   else pstmt.setNull(5, Types.VARCHAR);
                
                if(tf[5].getText().length() > 0) {
                      Integer manu_year = Integer.parseInt(tf[5].getText());
                      pstmt.setInt(6, manu_year);
                   }
                   else pstmt.setNull(6, Types.INTEGER);
                
                if(tf[6].getText().length() > 0) {
                      Integer drivingdistance = Integer.parseInt(tf[6].getText());
                      pstmt.setInt(7, drivingdistance);
                   }
                   else pstmt.setNull(7, Types.INTEGER);
                
                if(tf[7].getText().length() > 0) {
                      Integer rentcost = Integer.parseInt(tf[7].getText());
                      pstmt.setInt(8, rentcost);
                   }
                   else pstmt.setNull(8, Types.INTEGER);
                
                if(tf[8].getText().length() > 0) {
                      Integer compid = Integer.parseInt(tf[8].getText());
                      pstmt.setInt(9, compid);
                   }
                   else pstmt.setNull(9, Types.INTEGER);
                
                if(tf[9].getText().length() > 0) {
                      String registdate = tf[9].getText();
                      pstmt.setString(10, registdate);
                   }
                   else pstmt.setNull(10, Types.VARCHAR);
                
                
                pstmt.executeUpdate();
             
                
                JOptionPane.showMessageDialog(null, "수정되었습니다.");
                
                for(JTextField t:tf) {
                   t.setText("");
                }
                
             } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
             }
          }
      if (table == 3){
            try {
                String sql = "UPDATE Rent_Customer SET license_no=?,name=?,address=?,phone=?,email=? WHERE license_no = " + object.toString() + ";";
                      
                pstmt = conn.prepareStatement(sql);
                
                if(tf[0].getText().length() > 0) {
                   Integer license_no = Integer.parseInt(tf[0].getText());
                   pstmt.setInt(1, license_no);
                }
                else pstmt.setNull(1, Types.INTEGER);
                
                if(tf[1].getText().length() > 0) {
                   String name =  tf[1].getText();
                   pstmt.setString(2, name);
                }
                else pstmt.setNull(2, Types.VARCHAR);
                
                if(tf[2].getText().length() > 0) {
                      String address =  tf[2].getText();
                      pstmt.setString(3, address);
                   }
                   else pstmt.setNull(3, Types.VARCHAR);
                
                if(tf[3].getText().length() > 0) {
                      String phone =  tf[3].getText();
                      pstmt.setString(4, phone);
                   }
                   else pstmt.setNull(4, Types.VARCHAR);
                
                if(tf[4].getText().length() > 0) {
                      String email =  tf[4].getText();
                      pstmt.setString(5, email);
                   }
                   else pstmt.setNull(5, Types.VARCHAR);
               
               
                pstmt.executeUpdate();
             
                
                JOptionPane.showMessageDialog(null, "수정되었습니다.");
                
                for(JTextField t:tf) {
                   t.setText("");
                }
                
             } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
             }
          }   
      
      if (table == 4){
            try {
                String sql = "UPDATE Repairshop SET shopid=?,shopname=?,address=?,phone=?,manager_name=?,manager_email=? WHERE shopid = " + object.toString() + ";";
                      
                pstmt = conn.prepareStatement(sql);
                
                if(tf[0].getText().length() > 0) {
                   Integer shopid = Integer.parseInt(tf[0].getText());
                   pstmt.setInt(1, shopid);
                }
                else pstmt.setNull(1, Types.INTEGER);
                
                if(tf[1].getText().length() > 0) {
                   String shopname =  tf[1].getText();
                   pstmt.setString(2, shopname);
                }
                else pstmt.setNull(2, Types.VARCHAR);
                
                if(tf[2].getText().length() > 0) {
                      String address =  tf[2].getText();
                      pstmt.setString(3, address);
                   }
                   else pstmt.setNull(3, Types.VARCHAR);
                
                if(tf[3].getText().length() > 0) {
                      String phone =  tf[3].getText();
                      pstmt.setString(4, phone);
                   }
                   else pstmt.setNull(4, Types.VARCHAR);
                
                if(tf[4].getText().length() > 0) {
                      String manager_name =  tf[4].getText();
                      pstmt.setString(5, manager_name);
                   }
                   else pstmt.setNull(5, Types.VARCHAR);
                
                
                if(tf[5].getText().length() > 0) {
                      String manager_email =  tf[5].getText();
                      pstmt.setString(6, manager_email);
                   }
                   else pstmt.setNull(6, Types.VARCHAR);
               
               
                pstmt.executeUpdate();
             
                
                JOptionPane.showMessageDialog(null, "수정되었습니다.");
                
                for(JTextField t:tf) {
                   t.setText("");
                }
                
             } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
             }
          }
      
      if (table == 7){
	         try {
	             String sql = "UPDATE Repair_List SET repairno=?,carid=?,shopid=?,compid=?,license_no=?,repairdetails=?,repairdate=?,repaircost=?,paymentdeadline=?,repairhistory=? WHERE repairno = " + object.toString() + ";";
	                   
	             pstmt = conn.prepareStatement(sql);
	             
	             if(tf[0].getText().length() > 0) {
	                 Integer repairno = Integer.parseInt(tf[0].getText());
	                 pstmt.setInt(1, repairno);
	              }
	              else pstmt.setNull(1, Types.INTEGER);
	              
	              if(tf[1].getText().length() > 0) {
	                  Integer carid = Integer.parseInt(tf[1].getText());
	                  pstmt.setInt(2, carid);
	               }
	               else pstmt.setNull(2, Types.INTEGER);
	              
	              if(tf[2].getText().length() > 0) {
	                  Integer shopid = Integer.parseInt(tf[2].getText());
	                  pstmt.setInt(3, shopid);
	               }
	               else pstmt.setNull(3, Types.INTEGER);
	              
	              if(tf[3].getText().length() > 0) {
	                  Integer compid = Integer.parseInt(tf[3].getText());
	                  pstmt.setInt(4, compid);
	               }
	               else pstmt.setNull(4, Types.INTEGER);
	              
	              if(tf[4].getText().length() > 0) {
	                  Integer license_no = Integer.parseInt(tf[4].getText());
	                  pstmt.setInt(5, license_no);
	               }
	               else pstmt.setNull(5, Types.INTEGER);
	              
	              
	              if(tf[5].getText().length() > 0) {
	                  String repairdetails = tf[5].getText();
	                  pstmt.setString(6,  repairdetails);
	               }
	               else pstmt.setNull(6, Types.VARCHAR);
	              
	              if(tf[6].getText().length() > 0) {
	                  String repairdate = tf[6].getText();
	                  pstmt.setString(7,  repairdate);
	               }
	               else pstmt.setNull(7, Types.VARCHAR);
	              
	              if(tf[7].getText().length() > 0) {
	                  Integer repaircost = Integer.parseInt(tf[7].getText());
	                  pstmt.setInt(8, repaircost);
	               }
	               else pstmt.setNull(8, Types.INTEGER);
	              
	              if(tf[8].getText().length() > 0) {
	                  String paymentdeadline = tf[8].getText();
	                  pstmt.setString(9,  paymentdeadline);
	               }
	               else pstmt.setNull(9, Types.VARCHAR);
	              
	              if(tf[9].getText().length() > 0) {
	                  String paymentdeadline = tf[9].getText();
	                  pstmt.setString(10,  paymentdeadline);
	               }
	               else pstmt.setNull(10, Types.VARCHAR);
	              
	            
	             pstmt.executeUpdate();
	          
	             
	             JOptionPane.showMessageDialog(null, "수정되었습니다.");
	             
	             for(JTextField t:tf) {
	                t.setText("");
	             }
	             
	          } catch (SQLException e1) {
	             JOptionPane.showMessageDialog(null, e1.getMessage());
	          }
	       }	
    
   }
   
   void carReutrn() {
      try {
         String sql = "INSERT INTO Car_Check VALUES(?, ?, ?, ?, ?, ?, ?)";
               
         pstmt = conn.prepareStatement(sql);
         
         if(tf[0].getText().length() > 0) {
            Integer rentno = Integer.parseInt(tf[0].getText());
            pstmt.setInt(1, rentno);
         }
         else pstmt.setNull(1, Types.INTEGER);
         
         if(tf[1].getText().length() > 0) {
            Integer carid =  Integer.parseInt(tf[1].getText());
            pstmt.setInt(2, carid);
         }
         else pstmt.setNull(2, Types.VARCHAR);
         
         if(tf[2].getText().length() > 0) {
            String explan_front = tf[2].getText();
            pstmt.setString(3, explan_front);
         }
         else pstmt.setNull(3, Types.VARCHAR);
         
         if(tf[3].getText().length() > 0) {
            String explan_left = tf[3].getText();
            pstmt.setString(4, explan_left);
         }
         else pstmt.setNull(4, Types.VARCHAR);
         
         if(tf[4].getText().length() > 0) {
            String explan_right = tf[4].getText();
            pstmt.setString(5,  explan_right);
         }
         else pstmt.setNull(5, Types.VARCHAR);
           
         if(tf[5].getText().length() > 0) {
            String explan_back = tf[5].getText();
            pstmt.setString(6,  explan_back);
         }
         else pstmt.setNull(6, Types.VARCHAR);
         
         if(tf[6].getText().length() > 0) {
            String repair_required = tf[6].getText();
            pstmt.setString(7,  repair_required);
         }
         else pstmt.setNull(7, Types.VARCHAR);
         
         pstmt.executeUpdate();
      
         
         JOptionPane.showMessageDialog(null, "반환정보를 점검내역에 저장하였습니다.");
         
         for(JTextField t:tf) {
            t.setText("");
         }
         
      } catch (SQLException e1) {
         JOptionPane.showMessageDialog(null, e1.getMessage());
      }
   }
   
   void repairRequest() {
	      try {
	         String sql = "INSERT INTO Repair_List VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	               
	         pstmt = conn.prepareStatement(sql);
	         
	         if(tf[0].getText().length() > 0) {
	            Integer repairno = Integer.parseInt(tf[0].getText());
	            pstmt.setInt(1, repairno);
	         }
	         else pstmt.setNull(1, Types.INTEGER);
	         
	         if(tf[1].getText().length() > 0) {
	             Integer carid = Integer.parseInt(tf[1].getText());
	             pstmt.setInt(2, carid);
	          }
	          else pstmt.setNull(2, Types.INTEGER);
	         
	         if(tf[2].getText().length() > 0) {
	             Integer shopid = Integer.parseInt(tf[2].getText());
	             pstmt.setInt(3, shopid);
	          }
	          else pstmt.setNull(3, Types.INTEGER);
	         
	         if(tf[3].getText().length() > 0) {
	             Integer compid = Integer.parseInt(tf[3].getText());
	             pstmt.setInt(4, compid);
	          }
	          else pstmt.setNull(4, Types.INTEGER);
	         
	         if(tf[4].getText().length() > 0) {
	             Integer license_no = Integer.parseInt(tf[4].getText());
	             pstmt.setInt(5, license_no);
	          }
	          else pstmt.setNull(5, Types.INTEGER);
	         
	         
	         if(tf[5].getText().length() > 0) {
	             String repairdetails = tf[5].getText();
	             pstmt.setString(6,  repairdetails);
	          }
	          else pstmt.setNull(6, Types.VARCHAR);
	         
	         if(tf[6].getText().length() > 0) {
	             String repairdate = tf[6].getText();
	             pstmt.setString(7,  repairdate);
	          }
	          else pstmt.setNull(7, Types.VARCHAR);
	         
	         if(tf[7].getText().length() > 0) {
	             Integer repaircost = Integer.parseInt(tf[7].getText());
	             pstmt.setInt(8, repaircost);
	          }
	          else pstmt.setNull(8, Types.INTEGER);
	         
	         if(tf[8].getText().length() > 0) {
	             String paymentdeadline = tf[8].getText();
	             pstmt.setString(9,  paymentdeadline);
	          }
	          else pstmt.setNull(9, Types.VARCHAR);
	         
	         if(tf[9].getText().length() > 0) {
	             String paymentdeadline = tf[9].getText();
	             pstmt.setString(10,  paymentdeadline);
	          }
	          else pstmt.setNull(10, Types.VARCHAR);
	         
	         
	         pstmt.executeUpdate();
	      
	         
	         JOptionPane.showMessageDialog(null, "요청되었습니다.");
	         
	         for(JTextField t:tf) {
	            t.setText("");
	         }
	         
	      } catch (SQLException e1) {
	         JOptionPane.showMessageDialog(null, e1.getMessage());
	      }
	   }
   
   void carRent() {
         try {
            String sql = "INSERT INTO Car_Rent VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                  
            pstmt = conn.prepareStatement(sql);
            
            if(tf[0].getText().length() > 0) {
               Integer rentno = Integer.parseInt(tf[0].getText());
               pstmt.setInt(1, rentno);
            }
            else pstmt.setNull(1, Types.INTEGER);
            
            if(tf[1].getText().length() > 0) {
               Integer carid =  Integer.parseInt(tf[1].getText());
               pstmt.setInt(2, carid);
            }
            else pstmt.setNull(2, Types.VARCHAR);
            
            if(tf[2].getText().length() > 0) {
               Integer license_no = Integer.parseInt(tf[2].getText());
               pstmt.setInt(3, license_no);
            }
            else pstmt.setNull(3, Types.VARCHAR);
            
            if(tf[3].getText().length() > 0) {
               Integer compid = Integer.parseInt(tf[3].getText());
               pstmt.setInt(4, compid);
            }
            else pstmt.setNull(4, Types.VARCHAR);
            
            if(tf[4].getText().length() > 0) {
               String rent_date = tf[4].getText();
               pstmt.setString(5,  rent_date);
            }
            else pstmt.setNull(5, Types.VARCHAR);
              
            if(tf[5].getText().length() > 0) {
               Integer rentalperiod = Integer.parseInt(tf[5].getText());
               pstmt.setInt(6,  rentalperiod);
            }
            else pstmt.setNull(6, Types.VARCHAR);
            
            if(tf[6].getText().length() > 0) {
               Integer charge = Integer.parseInt(tf[6].getText());
               pstmt.setInt(7,  charge);
            }
            else pstmt.setNull(7, Types.VARCHAR);
            
            if(tf[7].getText().length() > 0) {
               String paymentdeadline = tf[7].getText();
               pstmt.setString(8,  paymentdeadline);
           }
           else pstmt.setNull(8, Types.VARCHAR);
            
            if(tf[8].getText().length() > 0) {
               String billhistory = tf[8].getText();
               pstmt.setString(9,  billhistory);
           }
           else pstmt.setNull(9, Types.VARCHAR);
            
            if(tf[9].getText().length() > 0) {
               Integer billhistorycost = Integer.parseInt(tf[9].getText());
               pstmt.setInt(10, billhistorycost);
           }
           else pstmt.setNull(10, Types.VARCHAR);
            
            pstmt.executeUpdate();
         
            
            JOptionPane.showMessageDialog(null, "대여완료");
            
            for(JTextField t:tf) {
               t.setText("");
            }
            
         } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
         }
      }
}