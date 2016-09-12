/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.modal;

public class ModalTemplateLoader {

    public ModalTemplateLoader() {
    }

    public ModalTemplateLoader(String name, TemplateLoaderModalType type, String value) {
        this._name = name;
        this._value = value;
        this.type = type;
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

    /**
     * @return the type
     */
    public TemplateLoaderModalType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TemplateLoaderModalType type) {
        this.type = type;
    }

    /**
     * @return the _value
     */
    public String getValue() {
        return _value;
    }

    /**
     * @param _value the _value to set
     */
    public void setValue(String _value) {
        this._value = _value;
    }

    public enum TemplateLoaderModalType {
        FILE, STRING
    }

    private String _name;
    private TemplateLoaderModalType type;
    private String _value;
}
