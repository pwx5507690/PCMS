define(["config"], function (config) {

    'use strict';

    var util = function () {

        var _self = this;

        var _log = function (message) {
            if (config.runningState.toUpperCase() === "DEBUG") {
                console[this.logType](message);
            }
        };

        var _logInfoFormat = function (param) {
            if (param.length === 1) {
                _log.call(this, param[0]);
            } else {
                var log = param[0];
                param.splice(0, 1);
                _log.call(this, _self.format(log[0], param));
            }
        };

        this.format = function () {
            if (arguments.length === 0)
                return null;

            var str = arguments[0];
            var args = null;

            if (arguments[1] instanceof Array) {
                args = arguments[1];
                args.splice(0, 0, 0);
            } else {
                args = arguments;
            }

            for (var i = 1; i < args.length; i++) {
                var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
                str = str.replace(re, args[i]);
            }
            return str;
        };

        this.getGuid = function () {
            return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'
                    .replace(
                            /[xy]/g,
                            function (c) {
                                var r = Math.random() * 16 | 0, v = c === 'x' ? r
                                        : (r & 0x3 | 0x8);
                                return v.toString(16);
                            });
        };

        this.getFileExt = function (filepath) {
            return "." + filepath.replace(/.+\./, "");
        };

        this.convertToBoolean = function (value) {
            if (typeof value === "string") {
                if (value) {
                    if (value.toUpperCase() === "TRUE") {
                        return true;
                    } else if (value.toUpperCase() === "FALSE"
                            || value === "0") {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return !!value;
            }
        };

        this.getStringLenth = function (str) {
            var len = 0;
            for (var i = 0; i < str.length; i++) {
                var c = str.charCodeAt(i);
                if ((c >= 0x0001 && c <= 0x007e)
                        || (0xff60 <= c && c <= 0xff9f)) {
                    len++;
                } else {
                    len += 2;
                }
            }
            return len;
        };

        this.subString = function (str, count) {
            var l = this.getStringLenth(str);

            if (l > +count) {
                return str.substring(0, +count) + "...";
            }
            return str;
        };

        this.getFileName = function (o) {
            var pos = o.lastIndexOf("\\");
            return o.substring(pos + 1);
        };

        this.dateFormat = function (date, fmt) {

            if (typeof date === "string") {
                date = new Date(date);
            }

            var o = {
                "M+": date.getMonth() + 1, // 月份
                "d+": date.getDate(), // 日
                "h+": date.getHours(), // 小时
                "m+": date.getMinutes(), // 分
                "s+": date.getSeconds(), // 秒
                "q+": Math.floor((date.getMonth() + 3) / 3), // 季度
                "S": date.getMilliseconds()
                        // 毫秒
            };

            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "")
                        .substr(4 - RegExp.$1.length));
            }

            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        };

        this.getConfig = function () {
            return config;
        };

        this.getCacheConfig = function () {
            return config.cache;
        };

        this.logInfoFormat = function () {
            _logInfoFormat.call({
                logType: "log"
            }, Array.prototype.slice.call(arguments));
        };

        this.logErrorFormat = function () {
            _logInfoFormat.call({
                logType: "error"
            }, Array.prototype.slice.call(arguments));
        };

        this.onerror = function (errorMessage, scriptURI, lineNumber) {
            console.trace();
            _self.logErrorFormat(
                    "errorMessage:{0},scriptURI:{1},lineNumber:{2}",
                    errorMessage, scriptURI, lineNumber)
        };
    };

    var Util = new util();

    window.onerror = Util.onerror;

    return {
        util: Util
    };
});