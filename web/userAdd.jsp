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
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="/addUser" method="get" id="formAdd">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="userId">用户编码：</label>
                    <input type="text" name="userId" id="userId"/>
                    <span>*请输入用户编码，且不能重复</span>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName"/>
                    <span >*请输入用户名称</span>
                </div>
                <div>
                    <label for="userpassword">用户密码：</label>
                    <input type="text" name="userpassword" id="userpassword"/>
                    <span>*密码长度必须大于6位小于20位</span>

                </div>
                <div>
                    <label for="userRemi">确认密码：</label>
                    <input type="text" name="userRemi" id="userRemi"/>
                    <span>*请输入确认密码</span>
                </div>
                <div>
                    <label >用户性别：</label>

                    <select name="gender">
                        <option value="2">男</option>
                        <option value="1">女</option>
                    </select>
                    <span></span>
                </div>
                <div>
                    <label for="data">出生日期：</label>
                    <input type="date" name="data" id="data"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="userphone" id="userphone"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="userAddress" id="userAddress"/>
                </div>
                <div>
                    <label >用户角色：</label>
                    <select name="userlei" id="role">

                    </select>
<%--                    <input type="radio" name="userlei" value="1" />管理员--%>
<%--                    <input type="radio" name="userlei" value="2" />经理--%>
<%--                    <input type="radio" name="userlei" value="3" />普通用户--%>

                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="button" id="btn" value="保存" />
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
    var userCode;
    var btn;
    $(function () {
        $.ajax({
            url:"/roleList",//请求地址
            data:{},//传递的参数
            type:"get",//get post
            dataType:"json",//返回的数据格式
            success:function (data) { //成功后的返回值
                $("#role").append("<option value="+0+">--请选择--</option>")
                for (var i = 0;i < data.length;i++){
                    $("#role").append("<option value='"+data[i].id+"'>"+data[i].roleName+"</option>");
                }
            },
            error:function (a) { //失败后的返回值
            }
        });
        //当文本框失去焦点时
        userCode=$("#userId");
        userCode.blur(function () {
            $.ajax({
                url:"/addUserCode",//请求地址
                data:{userCode:userCode.val()},//传递的参数
                type:"get",//get post
                dataType:"json",//返回的数据格式
                success:function (data) { //成功后的返回值
                    if(data.userCode=="exit"){
                        userCode.next().html("用户编号已经存在");
                        userCode.next().css("color","red");
                        //给文本框动态添加属性
                        userCode.attr("status","false");
                    }else {
                        userCode.next().html("请添加新用户编号");
                        userCode.next().css("color","green");
                        userCode.attr("status","true");
                    }
                },
                error:function (a) { //失败后的返回值

                }
            });
        });
        btn = $("#btn")
        btn.click(function () {
            if(userCode.attr("status")!="true"){
                userCode.blur();
            }else {
                $("#formAdd").submit();
            }
        })
    })
</script>
<script src="js/js.js"></script>

</body>
</html>