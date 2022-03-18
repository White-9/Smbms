<%@ page import="org.qf.service.SmbmsUserService" %>
<%@ page import="org.qf.service.Impl.SmbmsUserServiceImpl" %>
<%@ page import="org.qf.pojo.SmbmsUser" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
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
        <a href="login.jsp">退出</a>
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
            <span>用户管理页面</span>
        </div>
        <div class="search">
            <form action="/userList" method="get">
                <span>用户名：</span>
                <input type="text" name="userName" placeholder="请输入用户名" value="${userName}"/>
                <span>用户角色：</span>
                <select name="userRole">
                    <c:if test="${roleList!=null}">
                        <option value="0">--请选择--</option>
                        <c:forEach items="${roleList}" var="role">
                            <option <c:if test="${role.id==userRole}">selected="selected"</c:if>value="${role.id}">${role.roleName}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <input type="submit" id="queryByuserName" value="查询"/>
                <a href="userAdd.jsp">添加用户</a>
            </form>
        </div>
        <!--用户-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">用户编码</th>
                <th width="20%">用户名称</th>
                <th width="10%">性别</th>
                <th width="10%">年龄</th>
                <th width="10%">电话</th>
                <th width="10%">用户类型</th>
                <th width="30%">操作</th>
            </tr>
            <c:forEach items="${list}" var="u">
                <tr>
                    <td>${u.userCode}</td>
                    <td>${u.userName}</td>
                    <td>
                        <c:if test="${u.gender==1}">女</c:if>
                        <c:if test="${u.gender==2}">男</c:if>
                    </td>
                    <td>${u.age}</td>
                    <td>${u.phone}</td>
                    <td>${u.userRoleName}</td>
                    <td>
                        <a href="javaScript:" userId="${u.id}" class="userView"><img src="../images/read.png" alt="查看" title="查看"/></a>
                        <a href="/userUpdateView?id=${u.id}"><img src="../images/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="javaScript:" userId="${u.id}" id="removeUser"><img src="../images/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div>
            共${pages.totalCount}条记录,${pages.currentPageNo}/${pages.totalPageCount}页
            <c:if test="${pages.currentPageNo>1}">
                <a href="/userList?pageIndex=1&userName=${userName}&userRole=${userRole}">首页</a>
                <a href="/userList?pageIndex=${pages.currentPageNo-1}&userName=${userName}&userRole=${userRole}">上一页</a>
            </c:if>
            <c:if test="${pages.currentPageNo<pages.totalPageCount}">
                <a href="/userList?pageIndex=${pages.currentPageNo+1}&userName=${userName}&userRole=${userRole}">下一页</a>
                <a href="/userList?pageIndex=${pages.totalPageCount}&userName=${userName}&userRole=${userRole}">尾页</a>
            </c:if>

        </div>
    </div>
</section>
<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="javaScript:" id="yes">确定</a>
            <a href="javaScript:" id="no">取消</a>
        </div>
    </div>
</div>



<footer class="footer">
    版权归千锋教育
</footer>

<script src="js/jquery.js"></script>
<script>
    var userId
        $(function () {
            $(".userView").click(function () {
                window.location.href="/userView?id="+$(this).attr("userId")
            });
            $("#removeUser").click(function () {
                userId=$(this).attr("userId")
                $(".remove").show()
            })
            $("#yes").click(function () {
                window.location.href="/deleteUser?id="+userId
            })
            $("#no").click(function () {
                window.location.href="/userList"
            })
        })
</script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>