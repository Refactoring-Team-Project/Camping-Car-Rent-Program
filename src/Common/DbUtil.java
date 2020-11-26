package Common;


import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

public class DbUtil {

    public static ArrayList<Object[]> getRows(Connection conn, String sql) {
        ResultSet rs;
        ArrayList<Object[]> arr = new ArrayList<Object[]>();
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsm = rs.getMetaData();

            int columnCount = rsm.getColumnCount();
            Object[] columns = new String[columnCount];
            for(int i=0; i<columnCount; i++) {
                columns[i] = rsm.getColumnLabel(i+1);
            }
            arr.add(columns);

            while (rs.next()) {
                Object[] data = new Object[columnCount];
                for(int i=0; i<columnCount; i++) {
                    data[i] = rs.getString(i+1);
                };
                arr.add(data);
            }
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
        return arr;
    }

    public static void execute(Connection conn, String sql, String[] types, Object[] values) {
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            if(types != null)
                for(int i=0; i<types.length; i++) {
                    switch(types[i]) {
                        case "int":
                            pstmt.setInt(i+1, (int)values[i]);
                            break;
                        case "String":
                            pstmt.setString(i+1, (String)values[i]);
                            break;
                    }
                }
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "완료되었습니다.");
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }
}
