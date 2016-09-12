package com.pcms.data;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pcms.data.DataBase;

public class MySqlHelper extends DataBase implements IDataSource {

    @Override
    public void getResult(String sql) {
        try {
            this.openConnection();
            this._con.createStatement();
            this._ps = this._con.prepareStatement(sql);
            this._rs = this._ps.executeQuery();
            log.info(sql);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        this.close();
    }

    @Override
    public int doUpdate(String sql) {
        int ret = 0;
        try {
            this.openConnection();
            this._ps = this._con.prepareStatement(sql);
            ret = this._ps.executeUpdate();
            log.info(sql);
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        this.close();
        return ret;
    }

    @Override
    public List<Map<String, String>> getMap(String sql) {
        List<Map<String, String>> maps = null;

        try {

            this.openConnection();
            this._con.createStatement();
            this._ps = this._con.prepareStatement(sql);
            this._rs = this._ps.executeQuery();

            if (!this._rs.next()) {
                return maps;
            }
            this._rs.last();
            int rowCount = this._rs.getRow();

            maps = new ArrayList<Map<String, String>>();
            ResultSetMetaData m = this._rs.getMetaData();

            this._rs.first();
            int columns;

            columns = m.getColumnCount();
            do {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 1; i <= columns; i++) {
                    map.put(m.getColumnName(i), _rs.getString(i));
                }
                maps.add(map);
            } while (this._rs.next());
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        this.close();
        return maps;
    }
}
