/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.modal.sql;

import java.util.List;

public class SqlFieldWhere extends SqlField {

    private String _calculatingSigns;

    public SqlFieldWhere(String name, Field type, String value) {
        super(name, type, value);
        this._calculatingSigns = "=";
    }

    public SqlFieldWhere(String name, Field type, String value, String calculatingSigns) {
        super(name, type, value);
        this._calculatingSigns = calculatingSigns;
    }

    /**
     * @return the _calculatingSigns
     */
    public String getCalculatingSigns() {
        return _calculatingSigns;
    }

    /**
     * @param _calculatingSigns the _calculatingSigns to set
     */
    public void setCalculatingSigns(String _calculatingSigns) {
        this._calculatingSigns = _calculatingSigns;
    }

    public static String Resolve(List<SqlFieldWhere> where) {
        StringBuilder value = new StringBuilder();
        boolean isFirst = true;
        for (SqlFieldWhere item : where) {
            if (isFirst) {
                isFirst = false;
            } else {
                value.append(" and ");
            }
            String calculatingSigns = item.getCalculatingSigns();
            if (calculatingSigns.equals("in")) {
                value.append(item.getName())
                        .append(calculatingSigns)
                        .append("(")
                        .append(item.getValue())
                        .append(")");
            } else {
                value.append(item.getName())
                        .append(calculatingSigns)
                        .append(item.getValue());
            }
        }
        return value.toString();
    }
}
