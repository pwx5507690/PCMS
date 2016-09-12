package com.pcms.modal;

public class SqlField {

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

    public enum Field {
        STRING,
        INT,
        DATETIME,
        DOUBLE
    }
    private String _value;
    private Field _type;
}
