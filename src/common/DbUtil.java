package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DbUtil {

	/******메서드를 메서드 객체로 전환******/
	public static ArrayList<Object[]> getRows(Connection connection, String sqlQuery) {

		ArrayList<Object[]> rowList = new ArrayList<>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
			int columnCount = resultSet.getMetaData().getColumnCount();

			while (resultSet.next()) {
				Object[] data = new Object[columnCount];
				for (int i = 0; i < columnCount; i++) {
					data[i] = resultSet.getString(i + 1);
				}
				;
				rowList.add(data);
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		return rowList;
	}

	public static void execute(Connection connection, String sqlQuery, String[] types, Object[] values) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
			if (types != null)
				for (int i = 0; i < types.length; i++) {
					switch (types[i]) {
					case "int":
						pstmt.setInt(i + 1, (int) values[i]);
						break;
					case "string":
						pstmt.setString(i + 1, (String) values[i]);
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
