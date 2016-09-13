package com.pcms.repository;

import com.pcms.data.IDataSource;
import com.pcms.data.config.SqlRead;
import com.pcms.modal.SqlField;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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

    private final String _dyncTableWhere;

    private final Logger _log;

    public BaseRepository() {
        _insertDyncTable = "insertCustomTable";
        _dyncTable = "queryCustomTable";
        _dyncDelete = "deleteCustomTable";
        _dyncTablePage = "queryCustomTableForPage";
        _dyncTableWhere = "queryCustomTableWhere";
        _log = Logger.getLogger(getClass());
    }

    public List<Map<String, String>> query() {
        try {
            String order = StringUtils.join(this.getOrderBy(), " ");
            String sql = String.format(_read.getConfigByName(_dyncTable), this.getTableName());
            sql = sql + this.joinOrderBy();
            return iDataSource.getMap(sql);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    public List<Map<String, String>> query(List<SqlField> where) {
        try {
            String whereStr = this.getWhere(where);
            String sql = String.format(_read.getConfigByName(_dyncTableWhere), this.getTableName(), whereStr);
            sql = sql + this.joinOrderBy();
            return iDataSource.getMap(sql);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    public List<Map<String, String>> query(int current, int pagesize, List<SqlField> where) {
        try {
            String whereStr = this.getWhere(where);
            String sql = String.format(_read.getConfigByName(_dyncTablePage),
                    this.getTableName(), 
                    whereStr,
                    this.joinOrderBy(), 
                    current * pagesize, pagesize);
            return iDataSource.getMap(sql);
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

    public int add(Map<String, String> values) {
        Map<String, SqlField> params = this.getParams();
        Iterator iterator = params.entrySet().iterator();
        StringBuilder col = new StringBuilder();
        StringBuilder value = new StringBuilder();

        while (iterator.hasNext()) {
            Map.Entry<String, SqlField> entry = (Map.Entry<String, SqlField>) iterator.next();

            if (!values.containsKey(entry.getKey())) {
                continue;
            }

            String val = values.get(entry.getKey());
            SqlField sqlField = entry.getValue();
            sqlField.setValue(val);

            if (iterator.hasNext()) {
                col.append(sqlField.getValue()).append(",");
                col.append(entry.getKey()).append(",");
            } else {
                col.append(sqlField.getValue());
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
            SqlField item = this.getKey();
            item.setValue(value);
            String sql = String.format(_read.getConfigByName(_dyncDelete), this.getTableName(), item.getName(), item.getValue());
            return iDataSource.doUpdate(sql);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return -1;
        }
    }

    public String createId() {
        return UUID.randomUUID().toString();
    }

    public abstract String getTableName();

    public abstract Map<String, SqlField> getParams();

    public abstract SqlField getKey();

    public abstract List<String> getOrderBy();

    private String getWhere(List<SqlField> where) {
        StringBuilder value = new StringBuilder();
        boolean isFirst = true;
        for (SqlField item : where) {
            if (isFirst) {
                isFirst = false;
                value.append(item.getName()).append("=").append(item.getValue());
            } else {
                value.append(" and ").append(item.getName()).append("=").append(item.getValue());
            }
        }
        return value.toString();
    }

    private String joinOrderBy() {
        List<String> order = this.getOrderBy();
        if (order != null) {
            StringUtils.join(order, "  ");
        }
        return StringUtils.EMPTY;
    }
}
