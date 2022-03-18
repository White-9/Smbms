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
            <form action="/addBill" method="get" id="formAdd">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="billId">订单编码：</label>
                    <input type="text" name="billId" id="billId" required/>
                    <span>*请输入订单编码</span>
                </div>
                <div>
                    <label for="billName">商品名称：</label>
                    <input type="text" name="billName" id="billName" required/>
                    <span >*请输入商品名称</span>
                </div>
                <div>
                    <label for="billCom">商品单位：</label>
                    <input type="text" name="billCom" id="billCom" required/>
                    <span>*请输入商品单位</span>

                </div>
                <div>
                    <label for="billNum">商品数量：</label>
                    <input type="text" name="billNum" id="billNum" required/>
                    <span>*请输入大于0的正自然数，小数点后保留2位</span>
                </div>
                <div>
                    <label for="money">总金额：</label>
                    <input type="text" name="money" id="money" required/>
                    <span>*请输入大于0的正自然数，小数点后保留2位</span>
                </div>
                <div>
                    <label for="desc">商品描述：</label>
                    <input type="text" name="desc" id="desc" required/>
                    <span>*请输入商品描述</span>
                </div>
                <div>
                    <label >供应商：</label>
                    <select name="supplier" id="supplier" >
                        <c:if test="${providerList!=null}">
                            <option value="0">--请选择--</option>
                            <c:forEach items="${providerList}" var="pro">
                                <option value="${pro.id}">${pro.proName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <span>*请选择供应商</span>
                </div>
                <div>
                    <label >是否付款：</label>
                    <select name="zhifu">
                        <option value="0">--请付款--</option>
                        <option value="1">未付款</option>
                        <option value="2">已付款</option>
                    </select>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="billList.html">返回</a>-->
                    <input type="button" id="btn" value="保存"/>
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
<script src="js/jquery.js"></script>
<script>
    var billCode;
    var btn;
    $(function () {
        $.ajax({
            url:"/getProviderListForAddBill",//请求地址
            data:{},//传递的参数
            type:"get",//get post
            dataType:"json",//返回的数据格式
            success:function (data) { //成功后的返回值
                $("#supplier").append("<option value="+0+">--请选择--</option>")
                for (var i = 0;i < data.length;i++){
                    $("#supplier").append("<option value='"+data[i].id+"'>"+data[i].proName+"</option>");
                }
            },
            error:function (a) { //失败后的返回值
            }
        });
        //当文本框失去焦点时
        billCode=$("#billId");
        billCode.blur(function () {
            $.ajax({
                url:"/addBillCode",//请求地址
                data:{billCode:billCode.val()},//传递的参数
                type:"get",//get post
                dataType:"json",//返回的数据格式
                success:function (data) { //成功后的返回值
                    if(data.billCode=="exit"){
                        billCode.next().html("商品编号已经存在");
                        billCode.next().css("color","red");
                        //给文本框动态添加属性
                        billCode.attr("status","false");
                    }else {
                        billCode.next().html("请添加新商品编号");
                        billCode.next().css("color","green");
                        billCode.attr("status","true");
                    }
                },
                error:function (a) { //失败后的返回值
                }
            });
        });
        btn = $("#btn")
        btn.click(function () {
            if(billCode.attr("status")!="true"){
                billCode.blur();
            }else {
                $("#formAdd").submit();
            }
        })
    })
</script>
<script src="js/js.js"></script>
</body>
</html>