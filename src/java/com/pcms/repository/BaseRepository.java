package com.pcms.repository;

import com.pcms.synchronize.ISynchronizeData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseRepository extends CacheRepository implements ISynchronizeData {

    private static final int TIMER = 86400000;

    public void run() {
        if (isCacheQuery()) {
            super.runSynchronizeData(TIMER, this);
        }
    }

    @Override
    public List<?> getData() {
        return this.query();
    }

    @Override
    public String getCacheName() {
        return this.getTableName();
    }

    @Override
    public List<Map<String, String>> query() {
        String name = this.getTableName();
        _log.info(isCacheQuery());
        if (!isCacheQuery()) {
            return super.query();
        }

        Object object = this.getCacheToObejct(name);
        _log.info("object" + object.toString());
        if (object != null) {
            return (List<Map<String, String>>) object;
        } else {
            List<Map<String, String>> result = super.query();
            _log.info("result" + result.size());
            super.setCacheToObejct(name, result);
            return result;
        }
    }

    @Override
    public int add(Map<String, String> values) {
        if (isCacheQuery()) {
            super.setCacheToObejct(this.getTableName(), this.query().add(values));
        }
        return super.add(values);
    }

    public List<Map<String, String>> queryObject(String name, String value) {
        List<Map<String, String>> query = this.query();
        _log.debug(query.size());
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        for (int i = 0; i < query.size(); i++) {
            Map<String, String> item = query.get(i);
            if (item.get(name).equals(value)) {
                result.add(item);
            }
        }
        return result;
    }

    public abstract boolean isCacheQuery();
}
