(function (window, jQuery) {
    var loading = function () {

        var _self = this;

        var _stats = false;

        var _getMap = function () {
            return {
                loadingModal: jQuery("#loadingModal"),
                loadingModalParent: jQuery("#loadingModalParent"),
                loading: jQuery("#loading")
            };
        };

        this.initMap = function () {
            this.elmMap = _getMap();
        };

        this.hide = function () {
            _stats = false;
            _self.elmMap.loading.find("div>span").hide();

            for (var item in _self.elmMap) {
                _self.elmMap[item].hide();
            }
            //  _self.elmMap.loading.shCircleLoader();
            return this;
        };

        this.create = function () {
            var temp = '<div id="loadingModalParent" style="width: 100%; top:0; left: 0; bottom: 0;z-index:999999; position: absolute; background-color:rgba(255,255,255,0.5)"></div>';
            temp += '<div id="loadingModal" style="position: absolute; top: 40%; left: 45%;z-index:999999;text-align: center">';
            temp += '<label style="font-size: 16px;">Loading</label><div id="loading"></div></div>';

            jQuery(document.body).append(temp);

            this.initMap();
            this.elmMap.loading.shCircleLoader();
        };

        this.show = function () {
            if (_stats) {
                return this;
            }

            for (var item in _self.elmMap) {
                _self.elmMap[item].show();
            }
            _stats = true;

            return this;
        };

        this.progress = function (pro) {
            _self.elmMap.loading.find("div>span").show();
            _self.elmMap.loading.shCircleLoader('progress', pro + '%');
            return _self.show.call(this);
        };

        this.init = function () {

            if (jQuery.fn.shCircleLoader == null) {
                throw new Error("please import shCircleLoader");
            }

            this.initMap();

            if (this.elmMap.loadingModal.is("div")) {
                return this;
            }

            this.create();

            return this;
        };
    };

    jQuery.extend({
        loading: new loading()
    });

})(window, $ || jQuery);