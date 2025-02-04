<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<title>登陆页面</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum=1.0,minimum=1.0,user-scalable=0" />
<!-- uc强制竖屏 -->
<meta name="screen-orientation" content="portrait">
<!-- UC强制全屏 -->
<meta name="full-screen" content="yes">
<!-- UC应用模式 -->
<meta name="browsermode" content="application">
<!-- QQ强制竖屏 -->
<meta name="x5-orientation" content="portrait">
<!-- QQ强制全屏 -->
<meta name="x5-fullscreen" content="true">
<!-- QQ应用模式 -->
<meta name="x5-page-mode" content="app">

<head>
    <script src="http://cdn.highcharts.com.cn/highcharts/highcharts.js"></script>
    <script src="<%=basePath%>/assets/jquery.min.js"></script>

    <script src="<%=basePath%>/png/icon/iconfont.js"></script>
    <!-- <link rel="stylesheet" href="./png/icon/iconfont.css"> -->
    <link href="<%=basePath%>/assets/antd/antd-mobile.css" rel="stylesheet">
    <!-- <link href="./assets/mdstyle/github.css" rel="stylesheet"> -->
    <link href="<%=basePath%>/assets/fonts-lib.css" rel="stylesheet">
</head>
<body>
<div id="root"></div>
<script>
    //获取js名称
    document.write('<script src="<%=basePath%>/dist/webpack.assets.js?v=' + Math.random() + '"><\/script>');
</script>
<script>
    document.write('<script src="<%=basePath%>' + window.WEBPACK_ASSETS['LoginPage'].js + '"><\/script>');
</script>
</body>
</html>