package model;

import common.DbUtil;

import java.sql.Connection;
import java.util.ArrayList;

public class Model {

    public ArrayList<Object[]> select(Connection connection) {
        String sqlQuery = "";
        return DbUtil.getRows(connection, sqlQuery);
    }

    public void insert(Connection connection) {}

    public void delete(Connection connection, Object valueAt) {}

    public void update(Connection connection, Object valueAt) {
    }
}
