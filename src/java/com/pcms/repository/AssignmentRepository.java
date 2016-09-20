package com.pcms.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcms.cache.Redis;
import com.pcms.data.IDataSource;
import com.pcms.data.config.SqlRead;

@Repository("_assignmentRepository")
public class AssignmentRepository implements Runnable {

    private final Log _log = LogFactory.getLog(getClass());

    private final long _delay = 0;

    private final long _timer = 1800000;
    @Autowired
    private IDataSource iDataSource;
    @Autowired
    private SqlRead _read;
    @Autowired
    private Redis _redis;

    private boolean add(String name, String value) {
        try {
            return iDataSource.doUpdate(String.format(_read.getConfigByName("addAssignment"), name, value)) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getValue(String name) {
        return _redis.get(name).toString();
    }

    public boolean setValue(String name, String value) {
        boolean isSuccess = this.add(name, value);
        if (isSuccess) {
            _redis.set(name, value);
        }
        return isSuccess;
    }

    @PostConstruct
    public void init() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this, _delay, _timer, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        try {
            List<Map<String, String>> assignment = iDataSource.getMap(_read.getConfigByName("queryAssignment"));
            for (int i = 0; i < assignment.size(); i++) {
                Map<String, String> item = assignment.get(i);
                _redis.set(item.get("key").toString(), item.get("value").toString());
            }
        } catch (Exception e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
