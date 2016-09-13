package com.pcms.repository;

import com.pcms.modal.SqlField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("_accountRepository ")
public class AccountRepository extends BaseRepository {

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public Map<String, SqlField> getParams() {
        Map<String, SqlField> params = new HashMap<String, SqlField>();
        params.put("id", new SqlField(SqlField.Field.STRING));
        params.put("name", new SqlField(SqlField.Field.STRING));
        params.put("password", new SqlField(SqlField.Field.STRING));
        params.put("updateTime", new SqlField(SqlField.Field.DATETIME));
        params.put("headPortrait", new SqlField(SqlField.Field.STRING));
        params.put("tel", new SqlField(SqlField.Field.STRING));
        params.put("mail", new SqlField(SqlField.Field.STRING));
        params.put("type", new SqlField(SqlField.Field.INT));
        params.put("mobile", new SqlField(SqlField.Field.STRING));
        return params;
    }

    @Override
    public SqlField getKey() {
       return new SqlField("id",SqlField.Field.STRING);
    }

    @Override
    public List<String> getOrderBy() {
        List<String> order = new ArrayList<String>();
        order.add("sort");
        return order;
    }
    
}
