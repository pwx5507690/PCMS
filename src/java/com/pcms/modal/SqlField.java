package com.pcms.modal;

public class SqlField {

    public SqlField(Field type) {
        this._type = type;
    }
   
    public SqlField(String name,Field type) {
        this._name = name;
        this._type = type;
    }

    /**
     * @return the _value
     */
    public String getValue() {
        if (this._type.equals(Field.STRING) || this._type.equals(Field.DATETIME)) {
            return "'" + _value + "'";
        }
        return _value;
    }

    /**
     * @param _value the _value to set
     */
    public void setValue(String _value) {
        this._value = _value;
    }

    /**
     * @return the _type
     */
    public Field getType() {
        return _type;
    }

    /**
     * @param _type the _type to set
     */
    public void setType(Field _type) {
        this._type = _type;
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    public enum Field {
        STRING,
        INT,
        DATETIME,
        DOUBLE
    }
    private String _value;
    private Field _type;
    private String _name;
}
