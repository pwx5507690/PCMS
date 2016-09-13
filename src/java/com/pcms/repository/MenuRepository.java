package com.pcms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcms.data.IDataSource;
import com.pcms.data.config.SqlRead;
import com.pcms.modal.SqlField;

@Repository("_menuRepository")
public class MenuRepository extends BaseRepository {

    @Override
    public String getTableName() {
        return "menu";
    }

    @Override
    public Map<String, SqlField> getParams() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlField getKey() {
        return new SqlField("id",SqlField.Field.STRING);
    }

    @Override
    public List<String> getOrderBy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
