package com.pcms.repository;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;
import com.pcms.modal.sql.Field;
import com.pcms.modal.sql.OrderBy;
import com.pcms.modal.sql.SqlField;
import java.util.HashMap;

@Repository("_customTableRepository ")
public class CustomTableRepository extends BaseRepository {

    private String getSql(String table, Map<String, SqlField> param) throws Exception {
        String sql = _read.getConfigByName("createCustomTable");
        java.util.Iterator<Entry<String, SqlField>> it = param.entrySet().iterator();

        StringBuilder sb = new StringBuilder();
        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();

        while (it.hasNext()) {
            Entry<String, SqlField> item = it.next();
            String name = item.getKey();
            SqlField values = item.getValue();

            if (it.hasNext()) {
                key.append(" ").append(name).append(" ,");
                value.append(" '").append(values.getType()).append("', ");
            } else {
                key.append(" ").append(name).append("  ");
                value.append(" '").append(values.getType()).append("' ");
            }
        }
        sql = String.format(sql, table, key.toString(), value);
        return sql;
    }

    public int create(String table, Map<String, SqlField> param) {
        try {
            String sql = this.getSql(table, param);
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", table);
            params.put("tagName", table + "pcmsData");

            int result = iDataSource.doUpdate(sql);

            if (result != -1) {
                result = result + super.add(params);
            }

            return result;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String getTableName() {
        return "customtable";
    }

    @Override
    public Map<String, SqlField> getParams() {
        Map<String, SqlField> params = new HashMap<String, SqlField>();
        params.put("name", new SqlField(Field.STRING));
        params.put("tagName", new SqlField(Field.STRING));
        params.put("customProperties", new SqlField(Field.STRING));
        params.put("updateTime", new SqlField(Field.DATETIME));
        params.put("temp", new SqlField(Field.STRING));
        return params;
    }

    @Override
    public SqlField getKey() {
        return new SqlField("name", Field.STRING);
    }

    @Override
    public OrderBy getOrderBy() {
        return null;
    }
}
