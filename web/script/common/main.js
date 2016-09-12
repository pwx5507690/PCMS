/* global require */

require.config({
    paths: {
        'jQuery':'./script/lib/jquery-1.10.2.min',
        'config': './common/config',
        "util": './common/util',
        'cache': './common/cache',
        "path":'./script/lib/path.min'
    },
    waitSeconds: 0,
    shim: {
        'jQuery': {
            'exports': 'jQuery'
        },
        'util': {
            "deps": ["config"]
        },
        'cache': {
        	"deps": ["util"]
        }
    },
    priority: [
        "jQuery"
    ],
    urlArgs: 'v=1.0.0.1'
});

require(['jQuery',
         'config',
         'util',
         'cache'
], function (angular) {
  
});

