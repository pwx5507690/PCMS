package com.pcms.authority;

import com.pcms.cache.Redis;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAuthority {
    @Autowired
    private Redis _redis;
    
    private static final String USERKEY="USERKEY";
    
}
