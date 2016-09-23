package com.pcms.repository;

import com.pcms.modal.sql.Field;
import com.pcms.modal.sql.OrderBy;
import com.pcms.modal.sql.SqlField;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository("_assignmentRepository")
public class AssignmentRepository extends BaseRepository {

    @Override
    public String getTableName() {
        return "keyassignments";
    }

    @Override
    public Map<String, SqlField> getParams() {
        Map<String, SqlField> params = new HashMap<String, SqlField>();
        params.put("value", new SqlField(Field.STRING));
        params.put("name", new SqlField(Field.STRING));
        params.put("type", new SqlField(Field.STRING));
        params.put("describing", new SqlField(Field.STRING));
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

    @Override
    public boolean isCacheQuery() {
        return false;
    }

}
