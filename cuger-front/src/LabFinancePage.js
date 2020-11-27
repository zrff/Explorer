import React from 'react';
import ReactDOM from 'react-dom';

import { List, InputItem, WhiteSpace } from 'antd-mobile';
import { ListView, PullToRefresh } from 'antd-mobile';
import { Button, WingBlank, Radio } from 'antd-mobile';
import { ImagePicker, SegmentedControl } from 'antd-mobile';
import { Modal, Toast } from 'antd-mobile';
import { NavBar, Icon } from 'antd-mobile';
import { Grid } from 'antd-mobile';
import config from './config';

import './LabFinancePage.less';

const prompt = Modal.prompt;
const RadioItem = Radio.RadioItem;

export default class LabFinancePage extends React.Component {
    constructor(props) {
        super(props);
        const ds = new ListView.DataSource({
            rowHasChanged: (r1, r2) => r1 !== r2
        });
        this.state = {
            dataSource: ds,
            list: {
                // limit: 10,
                // offset: 0,
                // pageNum: 1,
                // pageSize: 10,
                rows: [
                    { item: '记录1', amount: 3689, date: '2017-08-19' },
                    { item: '记录2', amount: -5132, date: '2017-08-23' }
                ],
                totalCount: 2,
                // totalPage: 2
            },
            upLoading: false,
            pullLoading: false,
            /* 基本信息 */
            income: 0,
            outcome: 0,
            balance: 0,
            /* record modal */
            record_modal: false,
            /* 2个输入框 */
            item: '',
            amount: '',
            /* 但选组 */
            record_type: -1
        };
        this.onEndReached = this.onEndReached.bind(this);
        this.onRefresh = this.onRefresh.bind(this);
        this.renderRow = this.renderRow.bind(this);
        this.submitRecord = this.submitRecord.bind(this);
        this.fetchData = this.fetchData.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    //上拉加载
    onEndReached(page, lastPage) {
        //当前页小于总页数继续请求下一页数据，否则停止请求数据
        if (Number(page) < Number(lastPage)) {
            this.setState({ upLoading: true })
            //接口请求下一页数据,完成后将upLoading设为false
            //   ...
        }
    }
    //下拉刷新
    onRefresh() {
        this.setState({ pullLoading: true })
        //接口请求第一页数据,完成后将pullLoading设为false
        //  ...
    }
    //获取item进行展示
    renderRow(item, i) {
        return (
            <div className='list-item-simple'>
                <div className='first_row'>
                    <div className='title'>{item.item}</div>
                    <div className={item.amount > 0 ? 'in' : 'out'}>{item.amount > 0 ? '+' : ''}{item.amount.toLocaleString()}</div>
                </div>
                <div className='second_row'>{item.date}</div>
            </div>
        )
    }

    submitRecord() {
        let item = this.refs.item_input.state.value;
        let amount = parseFloat(this.refs.amount_input.state.value)*this.state.record_type;
        if (item == '' || amount == '') {
            Toast.show('请补齐信息,再提交!');
            return;
        }

        $.ajax({
            url: config.backend + '/lab/addRecord',
            contentType: 'application/json',
            data: { item: item, amount: amount },
            success: (e) => {
                if (e.code == 0) {
                    Toast.show('提交成功!');
                    this.setState({ 'record_modal': false });
                    this.fetchData();
                } else {
                    Toast.show(e.message);
                }
            }
        });
    }
    onChange(value){
        console.log(value);
        this.setState({'record_type':value});
    }
    render() {
        const items = [
            { title: '总收入', value: this.state.income.toLocaleString() },
            { title: '总支出', value: this.state.outcome.toLocaleString() },
            { title: '结余', value: this.state.balance.toLocaleString() },
        ];
        const { list, dataSource, upLoading, pullLoading } = this.state;
        const options = [
            { value: -1, label: '支出' },
            { value: 1, label: '收入' }
        ];
        return (
            <div className='labfinance common-page'>
                {/* 页头区域 */}
                <NavBar
                    mode="dark"
                    // leftContent="Back"
                    rightContent={[
                        // <Icon key="0" type="search" style={{ marginRight: '16px' }} />,
                        // <Icon key="1" type="ellipsis" />,
                        <span key="0" onClick={() => this.setState({ 'record_modal': true })}>
                            记录</span>
                    ]}
                >实验室财务管理</NavBar>

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
                {
                    list && list.rows && list.rows.length ?
                        <ListView
                            className='listview-simple'
                            dataSource={dataSource.cloneWithRows(list.rows)}
                            renderRow={(rowData, id1, i) => this.renderRow(rowData, i)}
                            // initialListSize={10}
                            // pageSize={10}
                            // renderFooter={() => (<div style={{ padding: 30, textAlign: 'center' }}>
                            //     {(list.pageNum < list.totalPage) && upLoading ? <Icon type="loading" /> : null}
                            // </div>)}
                            // onEndReached={() => this.onEndReached(list.pageNum, list.totalPage)}
                            // onEndReachedThreshold={20}
                            useBodyScroll={true}
                            style={{ width: '100vw' }}
                        // pullToRefresh={<PullToRefresh // import { PullToRefresh } from 'antd-mobile'
                        // refreshing={pullLoading}
                        // onRefresh={this.onRefresh}
                        // />}
                        />
                        :
                        list && list.rows && !list.rows.length ?
                            <div className={styles.goodEntry}>
                                <p>暂无数据</p>
                            </div> : null
                }

                <Modal
                    visible={this.state.record_modal}
                    transparent
                    maskClosable={false}
                    onClose={() => { this.setState({ 'record_modal': false }) }}
                    title="财务记账"
                    footer={[
                        { text: '取 消', onPress: () => { console.log('ok'); this.setState({ 'record_modal': false }); } },
                        { text: '提 交', onPress: () => { this.submitRecord() } }
                    ]}
                    wrapProps={{ onTouchStart: this.onWrapTouchStart }}
                // afterClose={() => { alert('afterClose'); }}
                >
                    <List>
                        <InputItem
                            clear
                            placeholder="项目报销名称"
                            ref="item_input"
                        >项目</InputItem>
                        <InputItem
                            placeholder="0.00"
                            extra="¥"
                            ref="amount_input"
                        >金额</InputItem>
                        {/* 但选框 */}
                        {options.map(i => (
                            <RadioItem key={i.value} checked={this.state.record_type === i.value} onChange={() => this.onChange(i.value)}>
                                {i.label}
                            </RadioItem>
                        ))}
                    </List>
                </Modal>
            </div>
        )
    }

    componentDidUpdate() {
    }

    fetchData() {
        // 获取基本信息
        $.ajax({
            url: config.backend + '/lab/getBasic',
            contentType: 'application/json',
            success: (e) => {
                if (e.code == 0) {
                    this.setState(e.data);
                } else Toast.show(e.message);
            }
        });
        // 获取详细信息
        $.ajax({
            url: config.backend + '/lab/getFinanceList',
            contentType: 'application/json',
            success: (e) => {
                if (e.code == 0) {
                    this.setState({ 'list': e.data });
                } else Toast.show(e.message);
            }
        });
    }

    componentDidMount() {
        this.fetchData();
    }
}


ReactDOM.render(
    <LabFinancePage />,
    document.getElementById('root')
);