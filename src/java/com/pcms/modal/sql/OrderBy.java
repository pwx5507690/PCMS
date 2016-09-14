package com.pcms.modal.sql;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class OrderBy {

    public OrderBy(List<String> field) {
        this._field = field;
        this._orderByType = OrderByType.ASCE;
    }

    public OrderBy(List<String> field, OrderByType orderByType) {
        this._field = field;
        this._orderByType = orderByType;
    }

    /**
     * @return the _orderByType
     */
    public String getOrderByType() {
        if (_orderByType.equals(OrderByType.ASCE)) {
            return "";
        } else {
            return "desc";
        }
    }

    /**
     * @param _orderByType the _orderByType to set
     */
    public void setOrderByType(OrderByType _orderByType) {
        this._orderByType = _orderByType;
    }

    /**
     * @return the _field
     */
    public List<String> getField() {
        return _field;
    }

    /**
     * @param _field the _field to set
     */
    public void setField(List<String> _field) {
        this._field = _field;
    }

    @Override
    public String toString() {
        List<String> order = this.getField();
        if (order != null) {
            return String.format("order by %s %s", StringUtils.join(order, "  "), this.getOrderByType());
        }
        return StringUtils.EMPTY;
    }

    public enum OrderByType {
        ASCE,
        DESC
    }

    private OrderByType _orderByType;

    private List<String> _field;
}
