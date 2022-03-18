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
        <p><span>下午好！</span><span style="color: #fff21b">${sessionScope.userSession.userName}</span> , 欢迎你！</p>
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
                <li><a href="/providerList">供应商管理</a></li>
                <li  id="active"><a href="/userList">用户管理</a></li>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="/loginOut">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="/userUpdate" method="get">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <input type="hidden" value="${sessionScope.userUpdate.id}" name="id"/>
                <div>c
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" placeholder="${sessionScope.userUpdate.userName}" value="${sessionScope.userUpdate.userName}"/>
                    <span >*</span>
                </div>

                <div>
                    <label >用户性别：</label>
                    <select name="gender">
                            <option value="2" <c:if test="${sessionScope.userUpdate.gender==2}"></c:if>>男 </option>
                        <option value="1" <c:if test="${sessionScope.userUpdate.gender==1}"></c:if>> 女</option>
                    </select>
                </div>
                <div>
                    <label for="data">出生日期：</label>
                    <input type="text" name="data" id="data" placeholder="${sessionScope.userUpdate.birthday}" value="${sessionScope.userUpdate.birthday}"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="userphone" id="userphone" placeholder="${sessionScope.userUpdate.phone}" value="${sessionScope.userUpdate.phone}"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="userAddress" id="userAddress" placeholder="${sessionScope.userUpdate.address}" value="${sessionScope.userUpdate.address}"/>
                </div>
                <div>
                    <label >用户角色：</label>
                    <select name="userRoleup">
                        <c:if test="${roleList!=null}">
                            <option value="0">--请选择--</option>
                            <c:forEach items="${roleList}" var="role">
                                <option <c:if test="${role.id==sessionScope.userUpdate.userRole}">selected="selected"</c:if> value="${role.id}">${role.roleName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
<%--                    <input type="radio" name="userlei" value="1" <c:if test="${userUpdate.userRole==1}">checked="checked"</c:if>/>管理员--%>
<%--                    <input type="radio" name="userlei" value="2" <c:if test="${userUpdate.userRole==2}">checked="checked"</c:if>/>经理--%>
<%--                    <input type="radio" name="userlei" value="3" <c:if test="${userUpdate.userRole==3}">checked="checked"</c:if>/>普通用户--%>

                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
    版权归千锋教育
</footer>
<script src="js/time.js"></script>

</body>
</html>