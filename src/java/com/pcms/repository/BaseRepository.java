package com.pcms.repository;

import com.pcms.data.IDataSource;
import com.pcms.data.config.SqlRead;
import com.pcms.modal.SqlField;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseRepository {

    @Autowired
    private IDataSource iDataSource;
    @Autowired
    private SqlRead _read;

    private final String _dyncTable;

    private final String _dyncDelete;

    private final String _dyncTablePage;

    public BaseRepository() {
        _dyncTable = "queryCustomTable";
        _dyncDelete = "deleteCustomTable";
        _dyncTablePage = "queryCustomTableForPage";
    }

    public List<Map<String, String>> query() {
        try {
            String sql = String.format(_read.getConfigByName(_dyncTable), this.getTableName());
            return iDataSource.getMap(_read.getConfigByName(_dyncTable));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, String>> query(int current, int pagesize) {
        try {
            String sql = String.format(_read.getConfigByName(_dyncTable), this.getTableName(), current * pagesize, pagesize);
            return iDataSource.getMap(_read.getConfigByName(_dyncTable));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, String>> query(String name) {
        try {
            return iDataSource.getMap(_read.getConfigByName(name));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int add() {
        return 0;
    }

    public int remove(String value) {
        try {
            String sql = String.format(_read.getConfigByName(_dyncDelete), this.getTableName(), this.getKey(), value);
            return iDataSource.doUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public abstract String getTableName();

    public abstract Map<String, SqlField> getParams();

    public abstract String getKey();
}
