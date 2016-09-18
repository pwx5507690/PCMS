/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.repository;

import com.pcms.synchronize.ISynchronizeData;
import com.pcms.cache.Redis;
import com.pcms.common.Common;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheRepository extends Common {

    private class DataTask implements Runnable {

        public final long _delay;

        public ISynchronizeData _iSynchronizeData;

        public ScheduledExecutorService _service;

        public int _timer;

        public DataTask(int timer, ISynchronizeData iSynchronizeData) {
            _timer = timer;
            _delay = 0;
            _iSynchronizeData = iSynchronizeData;
            _service = Executors
                    .newSingleThreadScheduledExecutor();
        }

        public void start() {
            _service.scheduleAtFixedRate(this, _delay, _timer, TimeUnit.SECONDS);
        }

        @Override
        public void run() {
            //  CacheRepository.this._redis.set(key, value);
            //    _iSynchronizeData.getData();
        }

        public void stop() {
            _service.shutdown();
            _service = null;
        }
    }

    @Autowired
    protected Redis _redis;

    public void runSynchronizeData() {

    }

    public void addCache(String key, String value) {
        _redis.set(key, value);
    }

    public void addCache(String key, String value, int timer) {
        _redis.set(key, value, timer);
    }

    public void getCache(String key) {
        _redis.get(key);
    }
}
