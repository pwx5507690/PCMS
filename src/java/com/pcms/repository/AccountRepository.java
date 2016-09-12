package com.pcms.repository;

import com.pcms.data.IDataSource;
import com.pcms.data.config.SqlRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("_accountRepository ")
public class AccountRepository {

    @Autowired
    private IDataSource iDataSource;
    @Autowired
    private SqlRead _read;
    
    
}
