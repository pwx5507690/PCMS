package com.pcms.repository;

import com.pcms.core.util.ArrayUtil;
import com.pcms.modal.sql.Field;
import com.pcms.modal.sql.OrderBy;
import com.pcms.modal.sql.SqlField;
import com.pcms.modal.sql.SqlFieldWhere;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("_accountRepository ")
public class AccountRepository extends BaseRepository {

    public Map<String, String> getUserByNamePassword(String name, String password) {
        List<SqlFieldWhere> params = new ArrayList<SqlFieldWhere>();
        params.add(new SqlFieldWhere("name", Field.STRING, name));
        params.add(new SqlFieldWhere("password", Field.STRING, password));
        
        List<Map<String, String>> result = super.query(params);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public Map<String, SqlField> getParams() {
        Map<String, SqlField> params = new HashMap<String, SqlField>();
        params.put("id", new SqlField(Field.STRING));
        params.put("name", new SqlField(Field.STRING));
        params.put("password", new SqlField(Field.STRING));
        params.put("updateTime", new SqlField(Field.DATETIME));
        params.put("headPortrait", new SqlField(Field.STRING));
        params.put("tel", new SqlField(Field.STRING));
        params.put("mail", new SqlField(Field.STRING));
        params.put("type", new SqlField(Field.INT));
        params.put("mobile", new SqlField(Field.STRING));
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
