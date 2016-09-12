define(['util'], function (path) {

    'use strict';

    var base = function () {

        var _self = this;

        var _load = null;

        var _param = new Object();

        var _languageUrl = "/Script/118/{0}/{1}.js";

        var _import = function (packageName, languageType, callback) {

            if (_self.setLanguage == null) {
                var dtd = jQuery.extend(_self.getDeferred(), {
                    url: webApp.format(_languageUrl, packageName, languageType)
                });

                return jQuery.when(_self.send(dtd)).done(function (language) {
                    _self.setLanguage = language;
                    callback();
                });
            }
            callback();
        };

        this.setLanguage = function (packageName, languageType, callback) {
            callback();
            // _import(packageName, languageType, function () {
            // var $elm = jQuery("[data-lan]");
            // for (var i = 0; i < $elm.length; i++) {

            // var $this = $elm[i];
            // var lan = $elm.attr("data-lan");

            // $this.is("input") ?
            // $this.attr($this.attr("data-lan-type") || "value",
            // _self.setLanguage[lan]) :
            // $this.html(_self.setLanguage[lan]);
            // }
            // callback();
            // });
        };
        // set loading lib
        this.setLoadControl = function (load) {
            _load = load;
            return this;
        };

        this.progress = function (pro) {
            return _load.progress.call(this, pro);
        };

        this.showLoad = function () {
            return _load.show.call(this);
        };

        this.setParam = function () {
            if (arguments.length === 1 && typeof arguments[0] === "object") {
                _param = jQuery.extend(_param, arguments[0]);
            } else {
                _param[arguments[0]] = arguments[1];
            }
            return this;
        };

        this.getParam = function (key) {
            return _param[key];
        };

        this.hideLoad = function () {
            return _load.hide.call(this);
        };

        this.send = function (dtd) {
            _load.show.call(jQuery).ajax({
                url: dtd.url,
                type: dtd.type || "get",
                contentType: dtd.contentType
                        || "application/json; charset=utf-8",
                data: dtd.data,
                success: function (result) {
                    dtd.resolve(result);
                    dtd.isHiddenLoading && _load.hide();
                },
                error: function (xht) {
                    dtd.fail(xht);
                    _load.hide.call(_self).error(xht);
                }
            });
            return dtd;
        };

        this.getMap = function () {
            var map = new Object();
            for (var i in arguments) {
                map[arguments[i]] = jQuery("#" + arguments[i]);
            }
            return map;
        };

        this.getDeferred = function () {
            return jQuery.extend(jQuery.Deferred(), {
                isHiddenLoading: true
            });
        };

        this.error = function (xht) {
            console.log(xht);
            return;
        };
    };

    return {
        base: new base()
    };
});