import React from 'react';
import ReactDOM from 'react-dom';

import { List, InputItem, WhiteSpace } from 'antd-mobile';
import { Button, WingBlank } from 'antd-mobile';
import { ImagePicker, SegmentedControl } from 'antd-mobile';
import { Modal, Toast } from 'antd-mobile';
import { NavBar, Icon } from 'antd-mobile';
import { Grid } from 'antd-mobile';
import config from './config';

import './LeetcodePage.less';

const prompt = Modal.prompt;

export default class LeetcodePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            thisMon:0,
            thisRate:0.1261111,
            sum:0,
            dates:['2015-01'],
            nums:[0],
            rates:[0]
        };
    }

    submitRecord(value){
        $.ajax({
            url:config.backend+'/leetcode/add',
            contentType: 'application/json',
            data:{num:value},
            success:(e)=>{
                if(e.code==0){
                    Toast.show('提交成功!');
                    window.location.reload();
                }else{
                    Toast.show(e.message);
                }
            }
        });
    }
    render() {
        const items = [
            { title: '本月完成', value: this.state.thisMon },
            { title: '同比增长', value: (this.state.thisRate).toFixed(1)+'%' },
            { title: '总完成量', value: this.state.sum },
        ];
        return (
            <div className='leetcode common-page'>
                {/* 页头区域 */}
                <NavBar
                    mode="dark"
                    // leftContent="Back"
                    rightContent={[
                        // <Icon key="0" type="search" style={{ marginRight: '16px' }} />,
                        // <Icon key="1" type="ellipsis" />,
                        <span key="0" onClick={() => prompt('打  卡', '请输入本次完成题数', [
                                                        { text: '取消' },
                                                        { text: '提交', onPress: value => this.submitRecord(value) },
                                                    ], 'default', '1')}>
                            打卡</span>
                    ]}
                >Leetcode打卡</NavBar>

                <WhiteSpace />

                {/* 数字项目区域 */}
                <Grid data={items} columnNum={3} renderItem={item => (
                    <div className='data-clubs'>
                        <div className='title'>{item.title}</div>
                        <div className='value'>{item.value}</div>
                    </div>
                )} />

                <WhiteSpace />

                {/* 图表区域 */}
                <div id="work-quality"></div>
            </div>
        )
    }

    componentDidUpdate(){
        $('#work-quality').html('');
        $('#work-quality').removeAttr('data-highcharts-chart');
        var chart = Highcharts.chart('work-quality', {
            title: {
                text: '每月Leetcode刷题情况'
            },
            yAxis: [{ // Primary yAxis
                labels: {
                    format: '{value}%',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                title: {
                    text: '增长率',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                opposite: true
            }, { // Secondary yAxis
                gridLineWidth: 0,
                title: {
                    text: '题量',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                },
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                }
            }],
            legend: {
                enabled: false
            },
            plotOptions: {
                series: {
                    label: {
                        connectorAllowed: false
                    },
                    pointStart: 2010
                }
            },
            series: [{
                name: '题量',
                yAxis: 1,
                data: this.state.nums
            }, {
                name: '增长率',
                yAxis: 0,
                data: this.state.rates
            }],
            responsive: {
                rules: [{
                    condition: {
                        maxWidth: 500
                    },
                    chartOptions: {
                        legend: {
                            layout: 'horizontal',
                            align: 'center',
                            verticalAlign: 'bottom'
                        }
                    }
                }]
            },
            credits: {
                enabled: false
            }
        });
    }

    componentDidMount() {
        $.ajax({
            url:config.backend+'/leetcode/fetch',
            contentType: 'application/json',
            success:(e)=>{
                this.setState(e.data);
            }
        });
    }
}


ReactDOM.render(
    <LeetcodePage />,
    document.getElementById('root')
);