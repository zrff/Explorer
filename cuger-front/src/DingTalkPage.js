import React from 'react';
import ReactDOM from 'react-dom';

import { List, InputItem, WhiteSpace } from 'antd-mobile';
import { Button, WingBlank,Toast } from 'antd-mobile';
import { ImagePicker, SegmentedControl } from 'antd-mobile';

import * as dd from 'dingtalk-jsapi';

import config from './config';
import GetRequestParam from './util/GetRequestParam';

export default class DingTalkPage extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div></div>
        )
    }
    componentDidMount(){
        const params = GetRequestParam();
        console.log(params);
        // dingtalk授权验证
        dd.ready(function() {
            // dd.ready参数为回调函数，在环境准备就绪时触发，jsapi的调用需要保证在该回调函数触发后调用，否则无效。
            dd.runtime.permission.requestAuthCode({
                corpId: "ding57dc00b2b94df573f2c783f7214b6d69",
                onSuccess: function(result) {
                    /* 得到应用免登授权码
                    {
                    code: 'hYLK98jkf0m' //string authCode
                    }*/
                    // Toast.show(result.code);
                    // Toast.show(JSON.stringify(params));
                    // 认证用户
                    $.ajax({
                        url:config.backend+'/dingtalk/auth',
                        contentType: 'application/json',
                        data:{auth_code:result.code, agent_id:result.agent_id?result.agent_id:''},
                        success:(e)=>{
                            Toast.show(JSON.stringify(e));
                            window.location.href = params.app?params.app:'/leetcode/index';
                        },
                        error:(e)=>{
                            Toast.show(JSON.stringify(e));
                        }
                    });
                
                },
                onFail : function(err) {}
            });
        });
    }
}


ReactDOM.render(
    <DingTalkPage />,
    document.getElementById('root')
);