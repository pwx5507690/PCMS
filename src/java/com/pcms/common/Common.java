/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wx.pan
 */
public class Common implements Serializable {
    
    protected final Log _log;
    
    @SuppressWarnings("unchecked")
    public Common() {
        _log = LogFactory.getLog(getClass());
    }
    
    protected Common cpoy() {
        return clone(this);
    }
    
    protected void error(Exception ex) {
        _log.error(ex.getMessage());
    }
    
    public <T extends Serializable> T clone(T obj) {
        T clonedObj = null;
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(stream);
            
            out.writeObject(obj);
            out.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(stream.toByteArray());
            ObjectInputStream os = new ObjectInputStream(bais);
            clonedObj = (T) os.readObject();
            stream.close();
        } catch (IOException e) {
            _log.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            _log.error(e.getMessage());
        }
        return clonedObj;
    }
}
