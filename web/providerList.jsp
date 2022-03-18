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
                <li  ><a href="/userList">用户管理</a></li>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="/loginOut">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面</span>
        </div>
        <div class="search">
            <form action="/providerList" method="get">
            <span>供应商名称：</span>
            <input type="text" name="proName" placeholder="请输入供应商的名称" value="${proName}"/>
            <input type="submit" id="queryByuserName" value="查询"/>
            <a href="providerAdd.jsp">添加供应商</a>
            </form>
        </div>
        <!--供应商操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">供应商编码</th>
                <th width="20%">供应商名称</th>
                <th width="10%">联系人</th>
                <th width="10%">联系电话</th>
                <th width="10%">传真</th>
                <th width="10%">创建时间</th>
                <th width="30%">操作</th>
            </tr>
            <c:forEach items="${proList}" var="p">
            <tr>
                <td>${p.proCode}</td>
                <td>${p.proName}</td>
                <td>${p.proContact}</td>
                <td>${p.proPhone}</td>
                <td>${p.proFax}</td>
                <td>${p.creationDate}</td>
                <td>
                    <a href="javaScript:" proId="${p.id}" class="proView"><img src="images/read.png" alt="查看" title="查看"/></a>
                    <a href="/providerUpdateView?proId=${p.id}"><img src="images/xiugai.png" alt="修改" title="修改"/></a>
                    <a href="javaScript:" proId="${p.id}" class="removeProvider"><img src="images/schu.png" alt="删除" title="删除"/></a>
                </td>
            </tr>
            </c:forEach>
        </table>
        <div>
            共${proPages.totalCount}条记录,${proPages.currentPageNo}/${proPages.totalPageCount}页
            <c:if test="${proPages.currentPageNo>1}">
                <a href="/providerList?proPageIndex=1&proName=${proName}">首页</a>
                <a href="/providerList?proPageIndex=${proPages.currentPageNo-1}&proName=${proName}">上一页</a>
            </c:if>
            <c:if test="${proPages.currentPageNo<proPages.totalPageCount}">
                <a href="/providerList?proPageIndex=${proPages.currentPageNo+1}&proName=${proName}">下一页</a>
                <a href="/providerList?proPageIndex=${proPages.totalPageCount}&proName=${proName}">尾页</a>
            </c:if>
        </div>

    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>你确定要删除该供应商吗？</p>
           <a href="#" id="yes">确定</a>
           <a href="#" id="no">取消</a>
       </div>
   </div>
</div>


<footer class="footer">
    版权归千锋教育
</footer>

<script src="js/jquery.js"></script>
<script>
    var proId
    $(function () {
        $(".proView").click(function () {
            window.location.href="/providerView?proId="+$(this).attr("proId")
        })
        $(".removeProvider").click(function () {
            proId=$(this).attr("proId")
            $(".remove").show()
        })
        $("#yes").click(function () {
            window.location.href="/deleteProvider?id="+proId
        })
        $("#no").click(function () {
            window.location.href="/providerList"
        })
    })
</script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>