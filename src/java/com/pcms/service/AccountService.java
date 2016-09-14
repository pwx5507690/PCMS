package com.pcms.service;

import com.pcms.repository.AccountRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("_accountService")
public class AccountService {

    @Autowired
    AccountRepository _accountRepository;

    public Map<String, String> login(String userName, String Password) {
        return _accountRepository.getUserByNamePassword(userName, Password);
    }

    public int add(Map<String, String> values) {
        return _accountRepository.add(values);
    }

    public int remove(String value) {
       return _accountRepository.remove(value);
    }

}
