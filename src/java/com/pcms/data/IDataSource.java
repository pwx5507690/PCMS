package com.pcms.data;

import java.util.List;
import java.util.Map;

public interface IDataSource {

    public void getResult(String sql);

    public int doUpdate(String sql);

    public List<Map<String, String>> getMap(String sql);
}
