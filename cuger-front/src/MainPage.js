import config from './config';
import React from 'react';
import ReactDOM from 'react-dom';

import { Menu, Icon, Button, NavBar, TabBar, Grid } from 'antd-mobile';
import { Route, Switch, Link, BrowserRouter } from 'react-router-dom';

import NotLiveRoute from 'react-live-route'
import { withRouter } from 'react-router-dom'

const LiveRoute = withRouter(NotLiveRoute)

import AppList from './pages/AppList';
import NoticeList from './pages/NoticeList';
import Setting from './pages/Setting';

import './MainPage.less';
import './Common.less';

export default class MobileRoute extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            bottomBarHidden:false,
            bottomOptions:[]
        }
        this.focusHandle = this.focusHandle.bind(this);
    }

    componentDidMount(){
        let url = config.resources.mainPage.BottomOptions;
        $.ajax({
            url:url,
            async:false,
            dataType:'json',
            success:(data)=>{
                if(data.code == 0){
                    this.setState({bottomOptions:data.data})
                }else{
                    console.log('错误')
                }
            },
            error:(data)=>{
                console.log('错误')
            }
        });
    }

    focusHandle(bottomBarHidden){
        if(bottomBarHidden && bottomBarHidden == this.state.bottomBarHidden){
            this.setState({bottomBarHidden});
        }else{
            this.setState({bottomBarHidden});
        }
    }

    renderContent(item) {
        let pageText = item.key;
        return (
            pageText == 'app_list' ? <AppList />
            :pageText == 'notice' ? <NoticeList />
            :pageText == 'setting' ? <Setting focusHandle={this.focusHandle} />
            :<div className='sub-page'>{pageText}</div>
        );
    }

    render() {
        let bottomOptions = this.state.bottomOptions;
        const tab_items = bottomOptions.map((d,idx)=>{
            let icon = 'url('+d.icon+') center center /  21px 21px no-repeat';
            let selected_icon = 'url('+d.selected_icon+') center center /  21px 21px no-repeat';
            let selectedTab = d.key+'Tab';
            let badge = d.badge == undefined || d.badge==''?'':d.badge;
            return  <TabBar.Item
                        title={d.title}
                        key={d.key}
                        icon={<div style={{
                            width: '22px',
                            height: '22px',
                            background: icon
                        }}
                        />
                        }
                        selectedIcon={<div style={{
                            width: '22px',
                            height: '22px',
                            background: selected_icon
                        }}
                        />
                        }
                        selected={this.state.selectedTab==undefined && idx==0? true:this.state.selectedTab === selectedTab}
                        badge={badge}
                        onPress={() => {
                            this.setState({
                                selectedTab: selectedTab,
                            });
                        }}
                    >
                        {this.renderContent(d)}
                    </TabBar.Item>;
        });
        return (
            <BrowserRouter>
                <div className="MobilePage common-page">
                    <NavBar
                        className="main-top-bar"
                        mode="dark"
                        // leftContent="Back"
                        rightContent={[
                            // <Icon key="0" type="search" style={{ marginRight: '16px' }} />,
                            <Icon key="1" type="ellipsis" />,
                        ]}
                    >首 页</NavBar>
                    <TabBar
                        unselectedTintColor="#949494"
                        tintColor="#33A3F4"
                        barTintColor="white"
                        hidden={this.state.bottomBarHidden}
                        tabBarPosition="bottom"
                    >
                        {tab_items}
                    </TabBar>
                </div>
            </BrowserRouter>
        )
    }
}


ReactDOM.render(
    <MobileRoute />,
    document.getElementById('root')
);