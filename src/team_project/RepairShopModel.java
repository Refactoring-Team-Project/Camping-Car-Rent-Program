package team_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RepairShopModel {
	int shopid;
	String shopname;
	String address;
	String phone;
	String manager_name;
	String manager_email;
	
	ResultSet rs; 
	
	public ArrayList<Object[]> search4(Connection conn) {
		ArrayList<Object[]> arr = new ArrayList<Object[]>();
		try {
			String sql = "select rs.shopid, rs.shopname, coalesce(sum(rl.repaircost), 0) as income\r\n" + 
                    "from Repairshop rs\r\n" + 
                    "left join Repair_List rl\r\n" + 
                    "on rs.shopid = rl.shopid\r\n" + 
                    "group by rs.shopid, rs.shopname\r\n" + 
                    "order by income desc;";

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            Object column[] = {"SHOP ID", "SHOP NAME", "INCOME"};
            arr.add(column);
        
            while(rs.next()) {
               Object[] data = {
            		   rs.getInt(1),
                       rs.getString(2),
                       rs.getInt(3)
               };
               arr.add(data);
            }
          } catch (SQLException e1) {
             e1.printStackTrace();
          }
		return arr;
	}
	
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getManager_email() {
		return manager_email;
	}
	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}
	
}
