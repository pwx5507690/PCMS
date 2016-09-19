(function (window, jQuery, webApp, _) {
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
            //_import(packageName, languageType, function () {
            //    var $elm = jQuery("[data-lan]");
            //    for (var i = 0; i < $elm.length; i++) {

            //        var $this = $elm[i];
            //        var lan = $elm.attr("data-lan");

            //        $this.is("input") ?
            //        $this.attr($this.attr("data-lan-type") || "value", _self.setLanguage[lan]) :
            //        $this.html(_self.setLanguage[lan]);
            //    }
            //    callback();
            //});
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
                contentType: dtd.contentType || "application/json; charset=utf-8",
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

    var messageControl = function () {
        var _self = this;

        var _interval = 3000;

        var _elmMap = null;

        this.confrm = function (title, msgContent, callBackFunc) {
            confirmMsgModal(title, msgContent, callBackFunc);
        };

        this.empty = function () {
            return "<strong>没有文件</strong>";
        };

        this.closeModal = function () {
            jQuery("div.modal").each(function () {
                var modal = $(this).getModalWindow(),
                    data = modal.data('modal');

                if (data) {
                    data.close();
                }
            });
        };

        this.modal = function (title, content) {
            $.modal({
                title: title,
                content: content,
                closeOnBlur: true,
                buttons: false,
                isShowTopBtns: false,
                draggable: true
            });
        };

        this.showMessage = function (content) {
            _elmMap.messageTool.show();
            _elmMap.messageContent.html(content);
            window.setTimeout(function () {
                _self.closeMessage();
            }, _interval);
        };

        this.closeMessage = function (content) {
            _elmMap.messageTool.hide('500');
        };

        this.init = function () {
            _elmMap = this.getMap("messageTool", "messageContent");
            jQuery(document)
                .on("click", "[data-close-modal]", this.closeModal)
                .on("click", "#messageClose", function () {
                    _self.closeMessage();
                });
            return this;
        };
    };
    // search
    var search = function () {

        var _config = {
            serachKey: "SERACH_CACHE",
            total: 20
        };

        var _searchValueSend;

        var _self = this;

        var _searchCall = null;

        var _close = function () {
            _self.elmMap._menuParent.hide();
            _self.elmMap.searchMenu.hide();
        };

        var _showSerachview = function () {

            if (_self.elmMap.searchMenu.find('li').length === 0) {
                return;
            }

            // calcPostion
            var $this = $(this);
            var position = $this.offset();

            var left = position.left;
            var top = position.top + $this.height() + 16;
            var width = $this.width();
            // set searchMenu Postion
            _self.elmMap.searchMenu.css({
                top: top,
                left: left,
                width: width
            }).show();
            _self.elmMap._menuParent.show();
        };

        var _setCahce = function (value) {
            var serachData = webApp.cache.get(_config.serachKey, true);

            if (serachData == null) {
                serachData = [value];
            } else if (jQuery.isArray(serachData) && jQuery.inArray(value, serachData) === -1) {
                if (serachData.length >= _config.total) {
                    serachData.pop();
                }
                serachData.push(value);
            }
            webApp.cache.set(_config.serachKey, serachData);
        };

        var _textChange = function () {
            var val = _self.elmMap.searchText.val();
            var serachData = webApp.cache.get(_config.serachKey, true);

            if (jQuery.isArray(serachData)) {
                var clone = [];
                for (var i = 0; i < serachData.length; i++) {
                    if (serachData[i].indexOf(val) !== -1) {
                        clone.push(serachData[i]);
                    }
                }
                if (clone.length === 0) {
                    _close();
                } else {
                    _self.createSearchList(clone);
                    _showSerachview.call(this);
                }
            }

        };

        var _searchValue = function () {

            var $this = jQuery(this);
            var val;

            if ($this.is("a")) {
                _self.elmMap.searchText.val($this.attr("data-search"));
            }

            val = _self.elmMap.searchText.val();

            if (val.trim() !== "") {
                _setCahce(val);
                _self.createSearchList();
            }

            _searchValueSend(val);
            _close();
        };

        this.setSearchCall = function (callback) {
            _searchCall = callback;
        };

        this.createSearchList = function (serachData) {
            serachData = serachData || webApp.cache.get(_config.serachKey, true);

            if (!serachData) {
                return;
            }

            var viewTemp = new Array();
            // console.log(serachData);
            for (var i = 0; i < serachData.length; i++) {
                var val = serachData[i];
                console.log(val);
                viewTemp.push('<li><a data-search="', val, '">', val, '</a></li>');
            }

            this.elmMap.searchMenu.html(viewTemp.join(''));
        };

        this.initMenuDorpDown = function () {
            var menuDown = [];
            menuDown.push('<ul class="dropdown-menu search-menu" id="searchMenu"' +
                ' style="z-index: 9999; border-radius: 0"></ul>' +
                '<div id="_menuParent" ' +
                'style="position: absolute;left:0;right:0;top:0;bottom: 0;display:none; z-index: 9997; "><div>');
            jQuery(document.body).append(menuDown.join(''));
        };

        this.setSearchValueSend = function (searchValueSend) {
            _searchValueSend = searchValueSend;
            //jQuery.when(_self.send(jQuery.extend(_self.getDeferred(), {
            //    url: _config.url
            //}))).done(_searchCall);
        };

        this.registereEvent = function () {
            jQuery(document)
                .on("click", "#searchText", _showSerachview)
                .on("click", "#_menuParent", _close)
                .on("input propertychange", "#searchText", _textChange)
                .on("click", "[data-search],#searchSub", _searchValue)

            ;
        };

        this.init = function () {
            this.initMenuDorpDown();
            this.elmMap = this.getMap("searchText", "searchMenu", "_menuParent");
            this.registereEvent();
            this.createSearchList();
            return this;
        };
    };
    // file
    var fileUp = function () {

        var _self = this;

        var _isSingle = false;
        // upload fileinput cache 
        var _elmDic = [];

        var _typeError = [];

        var _mediaType;

        var _successCallback;

        var _urlKey = "UPLOAD_URL";

        var _upCount = 0;

        var _progressInterval = 0;

        var _fileParam = {
            dataType: 'json',
            autoUpload: false,
            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
            // file size
            maxFileSize: 999000,
            disableImageResize: /Android(?!.*Chrome)|Opera/
                .test(window.navigator.userAgent),
            // preview image width
            //previewMaxWidth: 500,
            //// preview image height
            //previewMaxHeight: 500,
            previewCrop: true
        };

        var _fileType = {
            Image: [".PNG", ".JPG", ".JPEG", ".BMP", ".GIF"],
            Documentation: ["MP4"],
            Audio: ["WORD", "TXT", "PDF"],
            Video: [".WAV", ".MID", ".MIDI", ".WMA", ".MP3"]
        };

        // var _currentView = "listView";

        var _temp = {
            // temp javascript target  
            imageView:jQuery("#imageView-file-temp").html(),
            listView: jQuery("#listView-file-temp").html()
        };

        //var _getTemp = function () {
        //    return _temp[_currentView];
        //};

        var _getFileData = function (id) {
            for (var i = 0; i < _elmDic.length; i++) {
                var item = _elmDic[i];

                if (!item.validate) {
                    continue;
                }

                if (item.id === id) {
                    return item;
                }
            }
        };

        var _setStartUploadStat = function () {
            _elmDic.length > 0 ? _self.elmMap.startUpload.show() : _self.elmMap.startUpload.hide();
        };

        var _setSendUrl = function (suffix) {
            var current = _self.getParam("currentFolder");
            if (current === "-1") {
                current = "";
            } else {
                current = "?folderId=" + current;
            }

            var value = null;

            if (jQuery.inArray(suffix, _fileType.Image) !== -1) {
                value = "/Upload/UploadImage" + current;
            } else if (jQuery.inArray(suffix, _fileType.Video) !== -1) {
                value = "/Upload/UploadVideo";
            } else if (jQuery.inArray(suffix, _fileType.Documentation) !== -1) {
                value = "/Upload/UploadDocumentation";
            } else if (jQuery.inArray(suffix, _fileType.Audio) !== -1) {
                value = "/Upload/UploadAudio";
            }

            webApp.cache.set(_urlKey, value);
        };
        // validate file type 
        var _validateType = function (suffix) {
            for (var item in _fileType) {

                var arr = _fileType[item];

                for (var i = 0; i < arr.length; i++) {
                    if (arr[i] === suffix) {
                        return item;
                    }
                }
            }
        };

        var _uploadItem = function (isSingle) {
            // all true single false
            _isSingle = !!isSingle;

            var $this = jQuery(this);
            var stats = $this.attr("data-save-stats");
            var item = _getFileData(stats);

            _setSendUrl(item.suffix);

            item.data.submit();

            //_elmDic = jQuery.grep(_elmDic, function (n, i) {
            //    return n.id !== stats;
            //});

            $this.html("上传中");

            webApp.cache.remove(_urlKey);
        };

        var _upload = function () {
            // console.log(_elmDic);

            var timer = setInterval(function () {
                _self.progress(_progressInterval);

                if (++_progressInterval > 99) {
                    _upCount = _progressInterval = 0;
                    window.clearInterval(timer);
                }

            }, 100);

            // var clone = jQuery.extend({}, _elmDic);
            for (var i in _elmDic) {
                var item = _elmDic[i];
                _uploadItem.call(jQuery("[data-save-stats='" + item.id + "']")[0]);
            }
        };

        var _fileuploadadd = function (e, data) {

            jQuery.each(data.files, function (index, file) {
                var name = file.name;
                var suffix = webApp.getFileExt(name).toUpperCase();
                var fileType = _validateType(suffix);

                var fileData = {
                    // type: _mediaType[fileType][1],
                    index: index
                };

                //  console.log(suffix);
                if (!fileType) {
                    _typeError.push(name);
                    // fileData.type = fileType;
                    fileData.validate = false;
                    data.context = fileData;
                    return true;
                }

                data.context = jQuery.extend(fileData, {
                    id: webApp.getGuid(),
                    name: name,
                    isImage: fileType === "Image",
                    type: _mediaType[fileType][1],
                    size: file.size,
                    suffix: suffix,
                    validate: true,
                    data: data,
                    icon: _mediaType[fileType][0]
                });

                _elmDic.push(data.context);
                // UploadImage

                _self.elmMap.rowView.prepend(_.template(_temp.listView)(data.context));
                // create upload image
                if (!_self.elmMap.imageView.find("#__up").is("div")) {
                    _self.elmMap.imageView.prepend('<div id="__up"><h3>等待上传</h3><hr />' +
               '<ul style="display: inline-block; list-style: none">' + '</ul></div>');              
                }
                _self.elmMap.imageView.find("#__up > ul").prepend(_.template(_temp.imageView)(data.context));
                
            });

            _setStartUploadStat();

            if (_typeError.length > 0 &&
                ((_typeError.length + _elmDic.length) === data.filesLength)) {
                _self.showMessage(webApp.format("不正确的文件类型{0}{1}", _typeError.join(','), "!!!"));
                _typeError = [];
            }
        };

        var _fileuploadprocessalways = function (e, data) {
            var context = data.context;
            if (!context.validate) {
                return;
            }

            var index = data.index;
            var file = data.files[index];

            if (file.preview) {
                _getFileData(context.id)["preview"] = file.preview;
                jQuery("[data-img='" + context.id + "']").attr("src", file.preview.toDataURL("image/png"));
                jQuery("[data-up-look='" + context.id + "']").attr("href", file.preview.toDataURL("image/png"));
            }

            _setStartUploadStat();
        };

        var _fileuploadfail = function (e, data) {
            _typeError.push(_elmDic[_upCount].name);
            _self.showMessage(webApp.format("上传错误{0}{1}", _typeError.join(','), "!!!"));

            if (++_upCount === _elmDic.length) {
                _typeError = [];
                _progressInterval = 100;
                _setStartUploadStat();
            }
        };

        var _fileuploaddone = function (e, data) {

            if (_isSingle) {
                _progressInterval = 100;
                _self.hideLoad();
                _setStartUploadStat();
                _successCallback();
            } else {
                if (++_upCount === _elmDic.length) {
                    _elmDic = [];
                    _typeError = [];
                    _progressInterval = 100;
                    _setStartUploadStat();
                    _successCallback();
                } else {
                    _progressInterval = parseInt(_upCount / _elmDic.length * 100, 10);
                }
            }
        };

        var _fileuploadprogressall = function (e, data) {
            // var progress = parseInt(data.loaded / data.total * 100, 10);          
        };

        var _remove = function () {
            var $this = jQuery(this);
            var guid = $this.attr("data-up-remove");

            _elmDic = jQuery.grep(_elmDic, function (n, i) {
                return n.id !== guid;
            });
            jQuery("#" + guid).remove();
            _setStartUploadStat();
        };

        var _registereEvent = function () {
            jQuery(document)
                .on("click", "[data-up-remove]", _remove)
                .on("click", "[data-upload-sub]", _uploadItem)
                .on("click", "#startUpload", _upload)
            ;
        };

        this.removeAll = function () {
            jQuery("input[data-file-up-check]").each(function () {
                var $this = jQuery(this);
                if ($this.prop) {
                    var guid = $this.attr("data-file-up-check");
                    jQuery("#" + guid).remove();

                    _elmDic = jQuery.grep(_elmDic, function (n, i) {
                        return n.id !== guid;
                    });
                }
            });

            _setStartUploadStat();
        };

        this.removeFileData = function () {
            _elmDic = [];
        };

        this.initFileUpControl = function () {

            this.elmMap.fileupload.fileupload(_fileParam)
                .on('fileuploadadd', _fileuploadadd)
                .on('fileuploadprocessalways', _fileuploadprocessalways)
                //.on('fileuploadprogressall', _fileuploadprogressall)
                .on('fileuploaddone', _fileuploaddone).on('fileuploadfail', _fileuploadfail)
            //.prop('disabled', !$.support.fileInput)
            ;
        };

        //this.setCurrentView = function (currentView) {
        //    _currentView = currentView;
        //};

        this.setSuccessCallback = function (callback) {
            _successCallback = callback;
        };

        this.setMediaType = function (mediaType) {
            _mediaType = mediaType;
        };

        this.init = function (param) {
            this.setSuccessCallback(param.successCall);
            this.setMediaType(param.mediaType);

            _fileParam = jQuery.extend(_fileParam, param);
            this.elmMap = this.getMap(
                "fileupload",
                "startUpload",
                "rowView",
                "imageView",
                "dataImageContent");
            this.initFileUpControl();
            _registereEvent();
            return this;
        };
    };
    // view
    var fileView = function () {

        var _self = this;
        // local cache folder
        var _fileData;
        // reload callback data
        var _loadDataCall;

        var _deleteCallback;
        // 
        var _currentView = "listView";

        var _defaultParent = "4FAE3FD9-0734-4A54-907D-47498C509F91";

        var _menuType = "-1";

        var _hash = [];

        var _nextHash = [];

        var _temp = {
            // temp javascript target  
            imageView: jQuery("#imageView-temp").html(),
            listView: jQuery("#listView-temp").html(),
            fileListView: jQuery("#fileView-temp").html(),
            fileImageView: jQuery("#imageFileView-temp").html(),
            navigationView: jQuery("#navigationView-temp").html(),
            addFolder: jQuery("#add-folder-temp").html()
        };

        var _mediaType = {
            "Image": ["fa fa-file-image-o", "图像", "color:#d9534f"],
            "Documentation": ["fa fa-file-text", "文本", "color:#8add6d"],
            "Audio": ["fa fa-file-audio-o", "声音", "color:#0BF"],
            "Video": ["fa fa-file-audio-o", "视频", "color:#0BF"],
            "other": ""
        };

        var _removeHashFiter = function (folderId) {
            var index = -1;
            var isUse = false;
            for (var i = 0; i < _hash.length; i++) {
                if (_hash[i].folderId === folderId) {
                    index = i;
                    isUse = _hash[i].isUse;
                    break;
                }
            }
            // console(_hash.length);
            if (index !== -1) {
                _hash.splice(index + 1, _hash.length - index);
            }
            return isUse;
        };

        var _showCurrentView = function () {
            for (var item in _temp) {
                _self.elmMap[item] && _self.elmMap[item].hide();
            }

            _self.elmMap[_currentView].show();
        };

        var _setNavigation = function () {
            if (_hash.length === 1 || _hash.length === 0) {
                return _self.elmMap.fileNavigation.empty();
            }

            var d = $.grep(_hash, function (n, i) {
                return i > 0;
            });

            var data = {
                folderId: _hash[0].folderId,
                folderName: _hash[0].folderName,
                isGroup: _hash[0].isGroup,
                data: d
            };

            _self.elmMap.fileNavigation.html(_.template(_temp.navigationView)(data));
        };

        var _isUseToolAction = function (isGroup) {

            if (isGroup) {
                jQuery("[data-tool-bar]").first().attr("disabled", "disabled");
            } else {
                _self.getParam("isUse") ? jQuery("[data-tool-bar]").removeAttr("disabled")
               : jQuery("[data-tool-bar]").attr("disabled", "disabled");
            }

        };

        var _setNextBackSatat = function () {
            if (_hash.length === 1 || _hash.length === 0) {
                // next back button hide 
                _self.elmMap.next.attr("disabled", "disabled");
                _self.elmMap.back.attr("disabled", "disabled");

            } else {
                _self.elmMap.back.removeAttr("disabled");
            }

            if (_nextHash.length === 0) {
                _self.elmMap.next.attr("disabled", "disabled");
            } else {
                _self.elmMap.next.removeAttr("disabled");;
            }
        };

        var _getFileData = function (data) {
            var fillData = {
                image: new Object(),
                documentation: new Object(),
                audio: new Object(),
                video: new Object()
            };

            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                if (item.mediaType in _mediaType && item.mediaType.toLowerCase() in fillData) {
                    var name = item.mediaType.toLowerCase();

                    item.icon = _mediaType[item.mediaType][0];
                    item.type = _mediaType[item.mediaType][1];
                    item.color = _mediaType[item.mediaType][2];

                    if (fillData[name].titleName == null) {
                        fillData[name].titleName = item.type;
                    }

                    if (fillData[name].data == null) {
                        fillData[name].data = new Array();
                    }
                    fillData[name].time = "-1";
                    fillData[name].data.push(item);

                }
            }

            return fillData;
        };

        var _createFileView = function (data) {
            var list = _.template(_temp.fileListView);
            var image = _.template(_temp.fileImageView);

            var fillData = _getFileData(data);

            if (_menuType in fillData && fillData[_menuType].data != null) {

                var groupData = _.groupBy(fillData[_menuType].data, function (item) {
                    return webApp.dateFormat(item.dateUpdated, "yyyy-MM-dd");
                });

                var titleName = fillData[_menuType].titleName;
                fillData = [];

                for (var item in groupData) {
                    fillData.push({
                        data: groupData[item],
                        titleName: titleName,
                        time: item
                    });
                }
            } else if (_menuType !== "-1") {
                _self.elmMap.rowView.html(_self.empty());
                _self.elmMap.imageView.html(_self.empty());
                return;
            }

            for (var item in fillData) {
                if (fillData[item].data == null) {
                    continue;
                }
                _self.elmMap.rowView.append(list(fillData[item]));
                _self.elmMap.imageView.append(image(fillData[item]));
            }
        }

        var _createView = function (data, files, isGroup) {
            if (data != null) {
                data = _menuType === "-1" ?
                    { titleName: "文件夹", data: data } :
                    { titleName: "", data: [] };
                console.log(_menuType);
                //console.log(data);
                // list view
                var list = _.template(_temp.listView);
                // image view
                var image = _.template(_temp.imageView);
                // fill view
                _self.elmMap.rowView.html(list(data));
                _self.elmMap.imageView.html(image(data));
            } else {
                _self.elmMap.rowView.html(_self.empty());
                _self.elmMap.imageView.html(_self.empty());
            }

            if (files != null) {
                _createFileView(files);
            }

            //_hash[home];
            _setNextBackSatat();
            // navigation
            _setNavigation();
            // set toolbar 
            _isUseToolAction(isGroup);
            // is show toolbar

            return _self;
        };

        var _refurbish = function (folderId) {

            if (folderId === "-1") {
                // console.log(_fileData);
                return _createView(_fileData.data, _fileData.file);
            }
            // currentFolderId fileUp is use

            var child = _getChildrenFoldersByFolderId(_fileData.data, folderId);
            //console.log(child.files);

            return _createView(child.item, child.files);
        };

        var _getFileByMediaType = function (result, fileData, mediaType) {
            for (var i = 0; i < fileData.length; i++) {
                if (fileData[i].files != null) {
                    for (var j = 0; j < fileData[i].files.length; j++) {
                        if (fileData[i].files[j].mediaType === mediaType) {
                            result.push(fileData[i].files[j]);
                        }
                    }
                }

                if (fileData[i].childrenFolders != null && fileData[i].childrenFolders.length > 0) {
                    _getFileByMediaType(result, fileData[i].childrenFolders, mediaType);
                }
            }
        };

        var _getFileByName = function (result, file, name) {

            for (var i = 0; i < file.length; i++) {
                if (file[i].fileName.indexOf(name) !== -1) {
                    result.file.push(file[i]);
                }
            }
        };

        var _getFoldersByName = function (result, fileData, name) {
            for (var i = 0; i < fileData.length; i++) {
                if (fileData[i].folderName.indexOf(name) !== -1) {
                    result.folder.push(fileData[i]);
                };

                if (fileData[i].files != null) {
                    _getFileByName(result, fileData[i].files, name);
                    // result.log(result.file);
                }

                if (fileData[i].childrenFolders != null && fileData[i].childrenFolders.length > 0) {
                    _getFoldersByName(result, fileData[i].childrenFolders, name);
                }

            }
        };

        var _getChildrenFoldersByFolderId = function (fileData, folderId) {

            var result = {
                isfind: false
            };

            for (var i = 0; i < fileData.length; i++) {
                if (fileData[i].id === folderId) {
                    result.isfind = true;
                    result.item = fileData[i].childrenFolders;
                    result.files = fileData[i].files;
                    break;
                }

                if (fileData[i].childrenFolders != null && fileData[i].childrenFolders.length > 0) {
                    result = _getChildrenFoldersByFolderId(fileData[i].childrenFolders, folderId);
                    if (result.isfind) {
                        break;
                    }
                }
            }
            return result;
        };

        var _createViewByFolderName = function (folderName) {
            var result = {
                folder: [],
                file: []
            };

            _getFoldersByName(result, _fileData.data, folderName);
            _getFileByName(result, _fileData.file, folderName);
            return _createView(result.folder, result.file);
        };

        var _createViewByFolderGroup = function (groupId) {
            var result = [];
            _getFileByMediaType(result, _fileData.data, groupId);
            return _createView(null, result, true);
            //jQuery.when(_self.send(def)).done(_createView);
        };

        var _createViewByFolderId = function (folderId, isUse) {
            _self.setParam({
                currentFolder: folderId,
                isUse: isUse
            });
            _refurbish(folderId);
        };

        var _linkNextView = function () {
            _nextHash.splice(0, _nextHash.length);

            var $this = jQuery(this);
            var folderId = $this.attr("data-link-folder");
            var isUse = webApp.convertToBoolean($this.attr("data-is-user"));

            var folderName = $this.attr("title");

            _hash.push({
                folderName: folderName,
                folderId: folderId,
                isGroup: false,
                isUse: isUse
            });

            _createViewByFolderId(folderId, isUse);
        };

        var _next = function () {
            if (_nextHash.length === 0) {
                return;
            }

            var item = _nextHash[0];
            _nextHash.shift();
            // console.log(_nextHash);
            _hash.push(item);
            _createViewByFolderId(item.folderId, item.isUse);

        };

        var _back = function () {
            //if (_hash.length == 1 || _hash.length == 0) {
            //    return;
            //}

            var item = _hash[_hash.length - 2];
            var array = [];

            array.push(_hash[_hash.length - 1]);

            for (var i = 0; i < _nextHash.length; i++) {
                var t = _nextHash[i];
                if (t.folderId !== _hash[_hash.length - 1].folderId) {
                    array.push(t);
                }
            }
            // console.log(array);
            _nextHash = array;
            _hash.pop();
            _createViewByFolderId(item.folderId, item.isUse);
        };

        var _setCheckBoxSelected = function () {
            var $this = jQuery(this);
            jQuery("input[data-file-check][disabled!=disabled]," +
                "input[data-file-up-check][disabled!=disabled]," +
                "input[data-folder-check][disabled!=disabled]").prop("checked", $this.prop("checked"));
        };

        var _delete = function (fileIds, foterIds) {
            fileIds = fileIds || [];
            foterIds = foterIds || [];

            _self.confrm("请选择要删除的文件和文件夹?", "", function (data) {
                var dtd = jQuery.extend(_self.getDeferred(), {
                    url: "/api/folder/DeleteFileandFoter",
                    data: JSON.stringify({
                        fileIds: fileIds,
                        foterIds: foterIds
                    }),
                    type: "DELETE",
                    isHiddenLoading: false
                });

                jQuery.when(_self.send(dtd)).done(function (result) {
                    _loadDataCall();
                });
            });
        };

        var _deleteFileandFolderByGuids = function () {
            var fileIds = [];
            var foterIds = [];

            jQuery("input[data-file-check]").each(function () {
                var $this = $(this);
                if ($this.prop("checked")) {
                    fileIds.push($this.attr("data-file-check"));
                }
            });

            jQuery("input[data-folder-check]").each(function () {
                var $this = $(this);
                if ($this.prop("checked")) {
                    foterIds.push($this.attr("data-folder-check"));
                }
            });

            if (fileIds.length === 0 && foterIds.length === 0) {
                _self.showMessage("请选择要删除的文件和文件夹！！！");
                return;
            }

            _delete(fileIds, foterIds);
            _deleteCallback();
        };

        var _deleteFile = function () {
            _delete([jQuery(this).attr("data-delete-file")]);
        };

        var _deleteFolder = function () {
            _delete(null, [jQuery(this).attr("data-delete-folder")]);
        };

        var _copyFile = function () {
            _loadDataCall();
        };

        var _copyFolder = function () {
            _loadDataCall();
        };

        var _addFolder = function () {

            _self.modal(null, _temp.addFolder);

            jQuery.extend(_self.elmMap,
                _self.getMap(
                    "folderName",
                    "messageFolder",
                    "messageFolderClose",
                    "messageFolderContent"
                ));
            _self.elmMap.folderName.focus();
        };

        var _saveFolder = function () {
            var val = _self.elmMap.folderName.val();
            if (!val) {
                _self.elmMap.messageFolder.show();
                _self.elmMap.folderName.focus();
                _self.elmMap.messageFolderContent.html("请填写文件名");
                return;
            }

            var parentId = _self.getParam("currentFolder") === "-1" ? _defaultParent :
                _self.getParam("currentFolder");

            var folder = {
                folderName: val,
                parentId: parentId
            };

            _self.closeModal();

            jQuery.when(_self.send(jQuery.extend(_self.getDeferred(), {
                url: "/api/folder",
                data: JSON.stringify(folder),
                type: "POST"
            })))
                .done(function (data) {
                    console.log(data);
                    _loadDataCall();

                });

        };

        var _setFileData = function (data, isUse) {
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                item.isUse = isUse;
            }
        };

        var _setFoldersDataByUserInfo = function (data) {
            console.log(data);
            console.log(_self);
            for (var i = 0; i < data.length; i++) {
                var item = data[i];

                if (item.folderName === "School") {
                    item.isUse = jQuery.inArray(self.roles, "SchoolAdmin") !== -1;
                } else if (item.folderName === "System") {
                    item.isUse = jQuery.inArray(self.roles, "System") !== -1;
                } else {
                    item.isUse = item.ownerId === _self.userId;
                }

                if (item.files != null && item.files.length > 0) {
                    _setFileData(item.files, item.isUse);
                }

                if (item.childrenFolders != null && item.childrenFolders.length > 0) {
                    _setFoldersDataByUserInfo(item.childrenFolders);
                }
            }
        };

        var _convertData = function (data) {
            _setFoldersDataByUserInfo(data);

            var index = -1;
            var files = null;
            var childrenFolders = null;

            for (var i = 0; i < data.length; i++) {
                // console.log(data[i].id);
                if (data[i].id.toUpperCase() === _defaultParent) {
                    index = i;
                    childrenFolders = data[i].childrenFolders;
                    files = data[i].files;
                }
            }

            if (index === -1) {
                throw new Error("not found Parant Folder");
            }
            //  console.log(data);
            data.splice(index, 1);
            return {
                data: data.concat(childrenFolders),
                file: files
            };
        };

        var _changeViewByNavigation = function () {

            var $this = jQuery(this);
            var folderId = $this.attr("data-navigation");
            var isGroup = $this.attr("data-navigation-group");
            var isUse = _removeHashFiter(folderId);

            if (folderId == null) {
                return;
            }
            // menu link 
            if (isGroup === "true") {
                _createViewByFolderGroup(folderId, isUse);
            } else {
                _createViewByFolderId(folderId, isUse);
            }
        };

        var _changeView = function () {
            // remove current class
            jQuery("[data-view]").removeClass("active");
            // add current class and set current name
            _currentView = jQuery(this).addClass("active").attr("data-view");
            // showview
            _showCurrentView();
        };

        var _registereEvent = function () {
            // element attribute "data-link-folder" value is "folderId" 
            jQuery(document)
                .on("click", "#addFolder", _addFolder)
                .on("click", "#next", _next)
                .on("click", "#back", _back)
                .on("click", "#listInfo,#imageInfo", _changeView)
                .on("click", "#refresh", _loadDataCall)
                .on("click", "#addSub", _saveFolder)
                .on("click", "#messageFolderClose", function () {
                    _self.elmMap.messageFolder.hide();
                })
                .on("click", "#allCheckBox", _setCheckBoxSelected)
                .on("click", "#allDelete", _deleteFileandFolderByGuids)
                .on("click", "[data-navigation]", _changeViewByNavigation)
                .on("click", "[data-link-folder]", _linkNextView)
                .on("click", "[data-delete-folder]", _deleteFolder)
                .on("click", "[data-delete-file]", _deleteFile)
                .on("mouseover mouseout", "tr[data-row]", function (event) {
                    jQuery(this).find("td").eq(3).find("span")[event.type === "mouseover" ? "show" : "hide"]();
                })
            ;
        };

        this.createViewByFolderName = function (folderName) {
            return _createViewByFolderName(folderName);
        };

        this.createView = function (data) {
            return _createView(data);
        };

        this.getMediaType = function () {
            return _mediaType;
        };

        this.getloadDataCall = function () {
            return _loadDataCall;
        };

        this.setloadDataCall = function (loadDataCall) {
            _loadDataCall = function () {

                // _nextHash.splice(0, _nextHash.length);
                // _hash.splice(1, _hash.length - 1);

                loadDataCall(function (data) {
                    _fileData = _convertData(data);
                    _refurbish(_self.getParam("currentFolder"));
                });
            };
            return this;
        };

        this.setView = function (value, isRoot) {
            if (isRoot) {
                _hash = [value];
                return this;
            }

            if (value.folderName) {
                return _createViewByFolderName(value.folderName);
            }

            return _createViewByFolderId(value.folderId);
        };

        this.setDeleteCallback = function (callback) {
            _deleteCallback = callback;
        };

        this.changeViewByMediaType = function (mediaType, value) {
            _hash = [value];
            _menuType = mediaType;

            if (mediaType !== "-1") {
                jQuery("[data-tool-bar ]").hide();
            } else {
                jQuery("[data-tool-bar]").removeAttr("disabled")
                jQuery("[data-tool-bar ]").show();
            }
            _createView(_fileData.data, _fileData.file, mediaType !== "-1");
        };

        this.init = function (data, value) {
            this.elmMap = this.getMap("imageView",
                "listView",
                "rowView",
                "imageInfo",
                "listInfo",
                "next",
                "back",
                "addFolderForm",
                "folderName",
                "addSub",
                "refresh",
                "fileNavigation"
            );
            // element event 
            // this event is depute page is only  
            _hash.push(value);

            _registereEvent();

            _fileData = _convertData(data);

            this.setParam({
                currentFolder: "-1",
                isUse: true
            });
            // first page load 
            return _createView(_fileData.data, _fileData.file);
        };
    };

    var page = function () {
        // 键盘key
        var _KEY_CODE = {

        };

        var _firstSendUrl = "/api/folder/currentuser";

        var _base;

        var _search;

        var _fileView;

        var _fileUp;

        //var _changeView = function (url) {
        //    // view change server
        //    jQuery.when(_base.send(jQuery.extend(_base.getDeferred(), {
        //        url: url
        //    }))).done(function (data) {
        //        _fileView.createView(data);
        //    });
        //    // remove file data
        //    _fileUp.removeFileData();
        //    // clear seach value
        //    _search.elmMap.searchText.val('');
        //};

        var _changeViewByMenu = function () {
            jQuery("[data-menu]").removeClass("selected");

            var $this = jQuery(this).addClass("selected");
            var name = $this.attr("data-menu");
            var group = $this.attr("data-menu-group");

            _fileUp.removeFileData();

            _fileView.changeViewByMediaType(name, {
                folderName: name,
                folderId: group,
                isGroup: true,
                isUse: true
            });
        };

        var _registereEvent = function () {
            jQuery(document).on("click", "[data-menu]", _changeViewByMenu);
        };

        var _getDeferred = function () {
            return jQuery.extend(_base.getDeferred(), {
                url: _firstSendUrl
            });
        };

        this.init = function (loading) {

            loading.show();
            // init 
            _base = jQuery.extend(new base().setLoadControl(loading), jQuery.userInfo);
            _base = jQuery.extend(_base, new messageControl()).init();

            delete _base.init;

            _fileUp = jQuery.extend(new fileUp(), _base);
            _fileView = jQuery.extend(new fileView(), _base);
            _search = jQuery.extend(new search(), _base);

            _base.setLanguage("upload", "zh-CN", function () {

                _search.init().setSearchCall(function (name) {
                    _fileView.createViewByFolderName(name);
                });
                // menu event
                _registereEvent();

                jQuery.when(_base.send(_getDeferred())).done(function (folderData) {
                    console.log(folderData);
                    _fileView.init(folderData, {
                        folderName: name,
                        folderId: "-1",
                        isUse: true
                    });
                    loading.hide.call(jQuery)(".left,.top,.right").show();
                });

                _fileView.setloadDataCall(function (callback) {
                    jQuery.when(_base.send(_getDeferred())).done(function (folderData) {
                        console.log(folderData);
                        callback(folderData);
                    });
                });

                _fileUp.init({
                    successCall: _fileView.getloadDataCall(),
                    mediaType: _fileView.getMediaType()
                });

                _fileView.setDeleteCallback(_fileUp.removeAll);

                _search.setSearchValueSend(_fileView.createViewByFolderName);
            });
        };
    };

    // $.userInfo.us
    jQuery(document).ready(function () {
        new page().init(jQuery.loading.init());
    });

})(window, $ || jQuery, webApp, _);