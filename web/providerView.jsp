<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>超市账单管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> ${sessionScope.userSession.userName}</span> , 欢迎你！</p>
        <a href="/loginOut">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="/billList">账单管理</a></li>
                <li id="active"><a href="/providerList">供应商管理</a></li>
                <li ><a href="/userList">用户管理</a></li>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="/loginOut">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 信息查看</span>
        </div>
        <div class="providerView">
            <p><strong>供应商编码：</strong><span>${provider.proCode}</span></p>
            <p><strong>供应商名称：</strong><span>${provider.proName}</span></p>
            <p><strong>联系人：</strong><span>${provider.proContact}</span></p>
            <p><strong>联系电话：</strong><span>${provider.proPhone}</span></p>
            <p><strong>传真：</strong><span>${provider.proFax}</span></p>
            <p><strong>描述：</strong><span>${provider.proDesc}</span></p>

            <a href="/providerList">返回</a>
        </div>
    </div>
</section>
<footer class="footer">
    版权归千锋教育
</footer>
<script src="js/time.js"></script>

</body>
</html>