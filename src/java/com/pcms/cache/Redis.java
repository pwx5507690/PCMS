package com.pcms.cache;

import com.pcms.core.util.ObjectUtil;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis extends com.pcms.common.Common {

    private JedisPool _jedisPool;

    private String _host;

    public String get_host() {
        return _host;
    }

    public void set_host(String _host) {
        this._host = _host;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public int get_port() {
        return _port;
    }

    public void set_port(int _port) {
        this._port = _port;
    }

    public int get_timeout() {
        return _timeout;
    }

    public void set_timeout(int _timeout) {
        this._timeout = _timeout;
    }

    private String _password;

    private int _port;

    private int _timeout;

    private GenericObjectPoolConfig initPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(10);
        config.setMaxWaitMillis(1000);
        config.setTestOnReturn(true);
        config.setTestOnBorrow(true);
        return config;
    }

    public JedisPool before() {
        GenericObjectPoolConfig config = initPoolConfig();
        _jedisPool = new JedisPool(config, _host, _port, _timeout, _password);
        return _jedisPool;
    }

    public Set getAll() {
        Jedis jedis = null;
        Set result = null;
        try {
            jedis = before().getResource();
            result = jedis.keys("*");
        } catch (Exception e) {
            _log.error(e.getMessage());
            _jedisPool.returnBrokenResource(jedis);

        } finally {
            _jedisPool.returnResource(jedis);
        }
        return result;
    }

    public void set(String key, String value, int timer) {
        Jedis jedis = null;
        try {
            jedis = before().getResource();
            jedis.del(key);
            jedis.setex(key, _port, value);

        } catch (Exception e) {
            _log.error(e.getMessage());
            _jedisPool.returnBrokenResource(jedis);
        } finally {
            _jedisPool.returnResource(jedis);
        }

    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = before().getResource();
            jedis.del(key);
            jedis.set(key, value);

        } catch (Exception e) {
            _log.error(e.getMessage());
            _jedisPool.returnBrokenResource(jedis);
        } finally {
            _jedisPool.returnResource(jedis);
        }
    }

    public void setObject(String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = before().getResource();
            jedis.del(key.getBytes());
            jedis.set(key.getBytes(), ObjectUtil.serialize(value));
        } catch (Exception e) {
            _log.error(e.getMessage());
            _jedisPool.returnBrokenResource(jedis);
        } finally {
            _jedisPool.returnResource(jedis);
        }
    }

    public Object getObject(String key) {
        Jedis jedis = null;
        byte[] value = null;
        try {
            jedis = before().getResource();
            value = jedis.get(key.getBytes());
            _log.info("value"+value.toString());
            if (value == null) {
                return null;
            }
            return ObjectUtil.deserialize(value);
        } catch (Exception e) {
            _log.error(e.getMessage());
            _jedisPool.returnBrokenResource(jedis);
        } finally {
            _jedisPool.returnResource(jedis);
        }
        return null;
    }

    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = before().getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            _log.error(e.getMessage());
            _jedisPool.returnBrokenResource(jedis);
        } finally {
            _jedisPool.returnResource(jedis);
        }
        return value;
    }
}
