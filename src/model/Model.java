package model;

import common.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Model {

    public ArrayList<Object[]> select(Connection conn) {
        String sql = "";
        return DbUtil.getRows(conn, sql);
    }

}
