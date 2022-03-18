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
                <span>账单管理页面</span>
            </div>
            <div class="search">
                <form action="/billList" method="get">
                <span>商品名称：</span>
                <input type="text" name="productName" placeholder="请输入商品的名称" value="${productName}"/>
                <span>供应商：</span>

                <select name="tigong" >
                    <c:if test="${providerList!=null}">
                        <option value="0">--请选择--</option>
                        <c:forEach items="${providerList}" var="pro">
                            <option <c:if test="${pro.id==tigong}">selected="selected"</c:if> value="${pro.id}">${pro.proName}</option>
                        </c:forEach>
                    </c:if>
                </select>

                <span>是否付款：</span>
                <select name="fukuan">
                    <option value="0">--请选择--</option>
                    <option value="2">已付款</option>
                    <option value="1">未付款</option>
                </select>
                <input type="submit" id="queryByuserName" value="查询"/>
                <a href="billAdd.jsp">添加订单</a>
                </form>
            </div>
            <!--账单表格 样式和供应商公用-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">账单编码</th>
                    <th width="20%">商品名称</th>
                    <th width="10%">供应商</th>
                    <th width="10%">账单金额</th>
                    <th width="10%">是否付款</th>
                    <th width="10%">创建时间</th>
                    <th width="30%">操作</th>
                </tr>
                <c:forEach items="${billList}" var="bill">
                <tr>
                    <td>${bill.billCode}</td>
                    <td>${bill.productName}</td>
                    <td>${bill.providerName}</td>
                    <td>${bill.totalPrice}</td>
                    <td>
                        <c:if test="${bill.isPayment==1}">未付款</c:if>
                        <c:if test="${bill.isPayment==2}">已付款</c:if>
                    </td>
                    <td>${bill.creationDate}</td>
                    <td>
                        <a href="javaScript:" billId="${bill.id}" class="billView"><img src="images/read.png" alt="查看" title="查看"/></a>
                        <a href="/billUpdateView?id=${bill.id}"><img src="images/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="javaScript:" billId="${bill.id}" class="removeBill"><img src="images/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <div>
                共${billPages.totalCount}条记录,${billPages.currentPageNo}/${billPages.totalPageCount}页
                <c:if test="${billPages.currentPageNo>1}">
                    <a href="/billList?billPageIndex=1&productName=${productName}&tigong=${tigong}&fukuan=${fukuan}">首页</a>
                    <a href="/billList?billPageIndex=${billPages.currentPageNo-1}&productName=${productName}&tigong=${tigong}&fukuan=${fukuan}">上一页</a>
                </c:if>
                <c:if test="${billPages.currentPageNo<billPages.totalPageCount}">
                    <a href="/billList?billPageIndex=${billPages.currentPageNo+1}&productName=${productName}&tigong=${tigong}&fukuan=${fukuan}">下一页</a>
                    <a href="/billList?billPageIndex=${billPages.totalPageCount}&productName=${productName}&tigong=${tigong}&fukuan=${fukuan}">尾页</a>
                </c:if>

            </div>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
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
    var billId
    $(function () {
        $(".billView").click(function () {
            window.location.href="/billView?id="+$(this).attr("billId")
        });
        $(".removeBill").click(function () {
            billId=$(this).attr("billId")
            $(".remove").show()
        })
        $("#yes").click(function () {
                window.location.href="/deleteBill?id="+billId
        })
        $("#no").click(function () {
            window.location.href="/billList"
        })
    })
</script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>