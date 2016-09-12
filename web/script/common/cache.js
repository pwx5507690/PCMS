define(["util"], function (util) {

    'use strict';

    var cahce = function () {

        var _expired = 172800000;

        this.remove = function (key) {
            localStorage.removeItem(key);
        };

        this.set = function (key, value, interval) {

            util.logInfoFormat("cache:key={0};value={1};interval={2}", key,
                    value, interval);

            var expired = new Date().valueOf() + _expired;

            if (typeof value === "object") {
                value.expired = expired;
                value = JSON.stringify(value);
            } else {
                value = util.format("{0}$${1}", value, expired);
            }

            localStorage.setItem(key, value);

            if (interval) {
                window.setTimeout(function () {
                    localStorage.removeItem(key);
                }, interval);
            }
        };

        this.get = function (key, isParse) {
            var value = localStorage.getItem(key);
            if (value !== "undefined" || value !== undefined) {
                return null;
            }
            if (isParse) {
                value = JSON.parse(value);
            }
            return value;
        };

        this.init = function () {
            var cache = util.getCacheConfig();
            for (var name in cache) {
                var value = this.get(cache[name]);

                if (!value) {
                    continue;
                }
                var expired = value.indexOf('$$') !== -1 ? value.split('$$')[1]
                        : JSON.parse(value).expired;

                if (expired <= new Date().valueOf()) {
                    util.logInfoFormat("remove cache={0}", name);
                    this.remove(name);
                }
            }

            util.logInfoFormat("init cache success");
            return this;
        };
    };

    window.Cache = new cache();
});