<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
    <link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/crm/jquery/layer/layer/layer.js"></script>
    <title>登录</title>
</head>
<body onkeydown="keyLogin()">
<div style="position: absolute; top: 0; left: 0; width: 60%;">
    <img src="/crm/image/IMG_7114.JPG" style="width: 100%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        CRM &nbsp;<span style="font-size: 12px;">&copy;2020&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <form id="loginForm" class="form-horizontal" role="form">
            <div class="form-group form-group-lg">
                <div style="width: 350px;">
                    <input class="form-control" name="loginAct" type="text" placeholder="用户名">
                </div>
                <div style="width: 350px; position: relative;top: 20px;">
                    <input class="form-control" name="loginPwd" type="password" placeholder="密码">
                </div>
                <div class="checkbox" style="position: relative;top: 30px; left: 10px;">

                    <span id="msg"></span>

                </div>
                <button type="button" onclick="login()" class="btn btn-primary btn-lg btn-block"
                        style="width: 350px; position: relative;top: 45px;">登录
                </button>
            </div>
        </form>
    </div>
</div>
<script>

    function keyLogin() {
        //空格键是13
        if (event.keyCode === 13) {
            $("button").click()
        }

    }

    function login() {

        let loginForm = $("#loginForm").serialize();
        $.post("/crm/settings/user/login", loginForm, function (result) {

            if (!result.ok) {
                layer.msg(result.message, {icon: 5});
            } else {
            //    访问去首页的方法
                location.href = "/crm/toView/workbench/index"
            }
        }, "json");
    }
</script>
</body>
</html>
