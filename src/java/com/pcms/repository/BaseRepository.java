package com.pcms.repository;

import com.pcms.data.IDataSource;
import com.pcms.data.config.SqlRead;
import com.pcms.modal.SqlField;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseRepository {

    @Autowired
    private IDataSource iDataSource;
    @Autowired
    private SqlRead _read;

    private final String _dyncTable;

    private final String _dyncDelete;

    private final String _dyncTablePage;
    
    private final String _insertDyncTable;

    private final Logger _log;
    
    public BaseRepository() {
        _insertDyncTable= "insertCustomTable";
        _dyncTable = "queryCustomTable";
        _dyncDelete = "deleteCustomTable";
        _dyncTablePage = "queryCustomTableForPage";  
        _log  = Logger.getLogger(getClass());
    }

    public List<Map<String, String>> query() {
        try {
            String sql = String.format(_read.getConfigByName(_dyncTable), this.getTableName());
            return iDataSource.getMap(_read.getConfigByName(_dyncTable));
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    public List<Map<String, String>> query(int current, int pagesize) {
        try {
            String sql = String.format(_read.getConfigByName(_dyncTablePage), this.getTableName(), current * pagesize, pagesize);
            return iDataSource.getMap(_read.getConfigByName(_dyncTablePage));
        } catch (Exception e) {
           _log.error(e.getMessage());
            return null;
        }
    }

    public List<Map<String, String>> query(String name) {
        try {
            return iDataSource.getMap(_read.getConfigByName(name));
        } catch (Exception e) {
         _log.error(e.getMessage());
            return null;
        }
    }

    public int add() {
        Map<String, SqlField> params = this.getParams();
        Iterator iterator = params.entrySet().iterator();
        StringBuilder col = new StringBuilder();
        StringBuilder value = new StringBuilder();

        while (iterator.hasNext()) {
            Map.Entry<String, SqlField> entry = (Map.Entry<String, SqlField>) iterator.next();
            if (iterator.hasNext()) {
                col.append(entry.getValue().getValue()).append(",");
                col.append(entry.getKey()).append(",");
            } else {
                col.append(entry.getValue().getValue());
                col.append(entry.getKey());
            }
        }
        
        try {
            String sql = String.format(_read.getConfigByName(_insertDyncTable), this.getTableName(), col.toString(), value.toString());
            return iDataSource.doUpdate(sql);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return -1;
        }
    }

    public int remove(String value) {
        try {
            String sql = String.format(_read.getConfigByName(_dyncDelete), this.getTableName(), this.getKey(), value);
            return iDataSource.doUpdate(sql);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return -1;
        }
    }

    public abstract String getTableName();

    public abstract Map<String, SqlField> getParams();

    public abstract String getKey();
}
