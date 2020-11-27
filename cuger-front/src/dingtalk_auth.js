import * as dd from 'dingtalk-jsapi';

dd.ready(function() {
    // dd.ready参数为回调函数，在环境准备就绪时触发，jsapi的调用需要保证在该回调函数触发后调用，否则无效。
    dd.runtime.permission.requestAuthCode({
        corpId: "ding57dc00b2b94df573f2c783f7214b6d69",
        onSuccess: function(result) {
            console.log(result);
        /*{
            code: 'hYLK98jkf0m' //string authCode
        }*/
        },
        onFail : function(err) {}
  
    });
});