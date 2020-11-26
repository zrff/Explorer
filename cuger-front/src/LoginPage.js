import React from 'react';
import ReactDOM from 'react-dom';

import { List, InputItem, WhiteSpace } from 'antd-mobile';
import { Button, WingBlank,Toast } from 'antd-mobile';
import { ImagePicker, SegmentedControl } from 'antd-mobile';
import config from './config'

import './LoginPage.less';

export default class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            username:'',
            password:''
        }
        this.usernameChangeHandler = this.usernameChangeHandler.bind(this);
        this.passwordChangeHandler = this.passwordChangeHandler.bind(this);
        this.clickHandler = this.clickHandler.bind(this);
    }

    usernameChangeHandler(e){
        this.setState({'username':e});
    }
    passwordChangeHandler(e){
        this.setState({'password':e});
    }

    clickHandler(){
        console.log(config.backend)
        $.ajax({
            url:config.backend+'/login',
            async:true,
            xhrFields: {
                withCredentials: true //允许跨域带Cookie
            },
            // 允许跨域
            crossDomain: true,
            datatype:'json',
            contentType: 'application/json',
            data:{
                username:this.state.username,
                password:this.state.password
            },
            success:(data)=>{
                if(data.code == 0){
                    console.log(document.cookie)
                    window.location.href = '/leetcode/index';
                }else{
                    Toast.info('账号或密码填写错误！');
                }
            },
            error:(data)=>{
                Toast.info('系统错误，请联系管理员！');
            }
        });
    }

    render() {
        return (
            <List className='loginPage' renderHeader={() => 'cug-explorer'}>
                <div className='loginHeadContainer'>
                    <img className='loginHead' src='/png/logo.png' />
                </div>
                <WhiteSpace />
                <InputItem
                    // {...getFieldProps('phone')}
                    type="username"
                    placeholder="186 1234 1234"
                    value={this.state.username}
                    onChange={this.usernameChangeHandler}
                >账号</InputItem>
                <InputItem
                    // {...getFieldProps('password')}
                    type="password"
                    placeholder="****"
                    value={this.state.password}
                    onChange={this.passwordChangeHandler}
                >密码</InputItem>
                <WhiteSpace />
                <Button className='loginBtn' type="primary" activeStyle={{width:'90%'}} onClick={this.clickHandler}>登陆</Button>
            </List>
        )
    }
}


ReactDOM.render(
    <Login />,
    document.getElementById('root')
);