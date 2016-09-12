package com.pcms.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataBase {

    protected Connection _con;

    protected PreparedStatement _ps;

    protected ResultSet _rs;

    protected String _driver;

    protected String _url;

    protected String _username;

    protected String _password;

    public String get_driver() {
        return _driver;
    }

    public void set_driver(String _driver) {
        this._driver = _driver;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    protected final Log log = LogFactory.getLog(getClass());

    protected void openConnection() {
        try {
            Class.forName(_driver);
            this._con = DriverManager.getConnection(_url, _username,
                    _password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void close() {
        try {
            if (_rs != null) {
                _rs.close();
            }
            if (_ps != null) {
                _ps.close();
            }
            if (_con != null) {
                _con.close();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
