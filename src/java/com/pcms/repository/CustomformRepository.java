package com.pcms.repository;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcms.data.IDataSource;
import com.pcms.data.config.SqlRead;
import com.pcms.modal.ModalResult;

@Repository("_customformRepository ")
public class CustomformRepository {

    @Autowired
    private IDataSource iDataSource;
    @Autowired
    private SqlRead _read;

    private String getSql(String table, Map<String, String> param) throws Exception {
        String sql = _read.getConfigByName("createCustomTable");
        sql = sql.replace("{tbl}", table);

        java.util.Iterator<Entry<String, String>> it = param.entrySet().iterator();

        StringBuilder sb = new StringBuilder();
        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();

        while (it.hasNext()) {
            Entry<String, String> item = it.next();
            String name = item.getKey();
            String[] values = item.getValue().split("|");

            if (it.hasNext()) {
                key.append(" ").append(name).append(" ,");
                value.append(" '").append(values[1]).append("', ");
            } else {
                key.append(" ").append(name).append("  ");
                value.append(" '").append(values[1]).append("' ");
            }
            sb.append(" ").append(name).append(" ").append(" ").append(values[0]).append(", ");
        }

        return sql.replace("{col}", sb.toString())
                .replace("{keyAssignmentCol}", key.toString())
                .replace("{keyAssignmentVal}", value);
    }

    public ModalResult create(String table, Map<String, String> param) {
        ModalResult result = new ModalResult();
        try {
            String sql = this.getSql(table, param);
            result.setSuccess(iDataSource.doUpdate(sql) > 0);
        } catch (Exception e) {
            result.setSuccess(false).setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
