package com.pcms.repository;

import com.pcms.cache.Redis;
import com.pcms.core.util.DateUtil;
import com.pcms.data.IDataSource;
import com.pcms.data.config.SqlRead;
import com.pcms.modal.sql.OrderBy;
import com.pcms.modal.sql.SqlField;
import com.pcms.modal.sql.SqlFieldWhere;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseRepository extends CacheRepository {

    @Autowired
    protected IDataSource iDataSource;
    @Autowired
    protected SqlRead _read;

    private final String _dyncTable;

    private final String _dyncDelete;

    private final String _dyncTablePage;

    private final String _insertDyncTable;

    private final String _dyncTableWhere;

    private final String _automaticUuid;

    private final String _automaticDateTime;

    public BaseRepository() {
        _insertDyncTable = "insertCustomTable";
        _dyncTable = "queryCustomTable";
        _dyncDelete = "deleteCustomTable";
        _dyncTablePage = "queryCustomTableForPage";
        _dyncTableWhere = "queryCustomTableWhere";
        _automaticUuid = "id";
        _automaticDateTime = "updateTime";
    }

    public List<Map<String, String>> query() {
        try {
            String order = StringUtils.join(this.getOrderBy(), " ");
            String sql = String.format(_read.getConfigByName(_dyncTable), this.getTableName());
            sql = sql + this.getOrderBy().toString();
            return iDataSource.getMap(sql);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    public List<Map<String, String>> query(List<SqlFieldWhere> where) {
        try {
            String whereStr = SqlFieldWhere.Resolve(where);
            String sql = String.format(_read.getConfigByName(_dyncTableWhere), this.getTableName(), whereStr);
            sql = sql + this.getOrderBy().toString();
            return iDataSource.getMap(sql);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
    }

    public List<Map<String, String>> query(int current, int pagesize, List<SqlFieldWhere> where) {
        try {
            String whereStr = SqlFieldWhere.Resolve(where);
            String sql = String.format(_read.getConfigByName(_dyncTablePage),
                    this.getTableName(),
                    whereStr,
                    this.getOrderBy().toString(),
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
            SqlField sqlField = entry.getValue();
            String name = entry.getKey();

            if (name.equals(_automaticUuid)) {
                sqlField.setValue(createId());
            } else if (name.equals(_automaticDateTime)) {
                sqlField.setValue(DateUtil.dateFormate(null, DateUtil.FORMAT_SCAPE_HMS));
            } else {
                if (!values.containsKey(name)) {
                    continue;
                }
                String val = values.get(name);
                sqlField.setValue(val);
            }

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

    public abstract OrderBy getOrderBy();

    public abstract boolean isCacheQuery();

}
