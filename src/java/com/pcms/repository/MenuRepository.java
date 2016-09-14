package com.pcms.repository;

import com.pcms.core.util.ArrayUtil;
import com.pcms.modal.sql.Field;
import com.pcms.modal.sql.OrderBy;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pcms.modal.sql.SqlField;
import com.pcms.modal.sql.SqlFieldWhere;
import java.util.ArrayList;
import java.util.HashMap;

@Repository("_menuRepository")
public class MenuRepository extends BaseRepository {

    public List<Map<String, String>> getMenuByUserType(int userType) {

        List<SqlFieldWhere> params = new ArrayList<SqlFieldWhere>();
        params.add(new SqlFieldWhere("userType", Field.INT, String.valueOf(userType), ">"));
        List<Map<String, String>> result = super.query(params);
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public String getTableName() {
        return "menu";
    }

    @Override
    public Map<String, SqlField> getParams() {
        Map<String, SqlField> params = new HashMap<String, SqlField>();
        params.put("id", new SqlField(Field.STRING));
        params.put("name", new SqlField(Field.STRING));
        params.put("action", new SqlField(Field.INT));
        params.put("path", new SqlField(Field.STRING));
        params.put("updateTime", new SqlField(Field.DATETIME));
        params.put("userType", new SqlField(Field.INT));
        params.put("sort", new SqlField(Field.INT));
        return params;
    }

    @Override
    public SqlField getKey() {
        return new SqlField("id", Field.STRING);
    }

    @Override
    public OrderBy getOrderBy() {
        return new OrderBy((List<String>) ArrayUtil.getArray("sort"));
    }

}
