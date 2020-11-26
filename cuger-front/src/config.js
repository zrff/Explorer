let config = {
    profile:'prod' // 设置运行模式: prod:test:local（缺省）
};

let prod_url = {

};
let test_url = {

};
let local_url = {
    mainPage:{
        BottomOptions:'/data/mainPage/BottomOptions.json',
        AppList:'/data/mainPage/AppList.json',
        NoticeList:'/data/mainPage/NoticeList.json',
        UserInfo:'/data/mainPage/UserInfo.json',
    }
};



if(config.profile == 'prod'){
    config.resources = prod_url;
    config.backend = '';
}else if(config.profile == 'test'){
    config.resources = test_url;
    config.backend = 'http://127.0.0.1:8088'; // test server
}else{
    config.resources = local_url;
    config.backend = 'http://127.0.0.1:8080';
}


export default config;