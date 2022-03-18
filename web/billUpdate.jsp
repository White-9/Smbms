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
                <li id="active"><a href="/billList">账单管理</a></li>
                <li><a href="/providerList">供应商管理</a></li>
                <li  ><a href="/userList">用户管理</a></li>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="/loginOut">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>账单管理页面 >> 订单添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="/billUpdate" method="get">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <input type="hidden" value="${sessionScope.billUpdate.id}" name="id">
                <div class="">
                    <label for="providerId">订单编码：</label>
                    <input type="text" name="providerId" id="providerId" placeholder="${sessionScope.billUpdate.billCode}" value="${sessionScope.billUpdate.billCode}"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="providerName">商品名称：</label>
                    <input type="text" name="providerName" id="providerName" placeholder="${sessionScope.billUpdate.productName}" value="${sessionScope.billUpdate.productName}"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="people">商品单位：</label>
                    <input type="text" name="people" id="people" placeholder="${sessionScope.billUpdate.productUnit}" value="${sessionScope.billUpdate.productUnit}"/>
                    <span>*</span>

                </div>
                <div>
                    <label for="phone">商品数量：</label>
                    <input type="text" name="phone" id="phone" placeholder="${sessionScope.billUpdate.productCount}" value="${sessionScope.billUpdate.productCount}"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="address">总金额：</label>
                    <input type="text" name="address" id="address" placeholder="${bisessionScope.billUpdatell.totalPrice}" value="${sessionScope.billUpdate.totalPrice}"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="fax">供应商：</label>
                    <select name="fax" id="fax">
                        <c:if test="${providerList!=null}">
                            <c:forEach items="${providerList}" var="pro">
                                <option <c:if test="${pro.id==sessionScope.billUpdate.providerId}">selected="selected"</c:if> value="${pro.id}">${pro.proName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <span>*</span>
                </div>
                <div>
                    <label >是否付款：</label>
                    <select name="zhifu">
                        <option value="2" <c:if test="${sessionScope.billUpdate.isPayment==2}">selected="selected"</c:if>>已付款 </option>
                        <option value="1" <c:if test="${sessionScope.billUpdate.isPayment==1}">selected="selected"</c:if>>未付款</option>
                    </select>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="billList.html">返回</a>-->
                    <input type="submit" value="保存" />
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
    版权归千锋教育
</footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>