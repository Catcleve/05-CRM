<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/crm/jquery/layer/layer/layer.js"></script>
    <script type="text/javascript" src="/crm/jquery/ajaxfileupload.js"></script>

        <script type="text/javascript">

        //页面加载完毕
        $(function () {
            //导航中所有文本颜色为黑色
            $(".liClass > a").css("color", "black");

            //默认选中导航菜单中的第一个菜单项
            $(".liClass:first").addClass("active");

            //第一个菜单项的文字变成白色
            $(".liClass:first > a").css("color", "white");

            //给所有的菜单项注册鼠标单击事件
            $(".liClass").click(function () {
                //移除所有菜单项的激活状态
                $(".liClass").removeClass("active");
                //导航中所有文本颜色为黑色
                $(".liClass > a").css("color", "black");
                //当前项目被选中
                $(this).addClass("active");
                //当前项目颜色变成白色
                $(this).children("a").css("color", "white");
            });

            window.open("/crm/toView/workbench/main/index", "workareaFrame");

        });

        </script>

    <style>
        #systemSet {
            left: -89px;
            min-width: 128px;
        }
    </style>

</head>
<body>

<!-- 我的资料 -->
<div class="modal fade" id="myInformation" role="dialog">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">我的资料</h4>
            </div>
            <div class="modal-body">
                <div style="position: relative; left: 40px;">
                    姓名：<b>张三</b><br><br>
                    登录帐号：<b>zhangsan</b><br><br>
                    组织机构：<b>1005，市场部，二级部门</b><br><br>
                    邮箱：<b>zhangsan@bjpowernode.com</b><br><br>
                    失效时间：<b>2017-02-14 10:10:10</b><br><br>
                    允许访问IP：<b>127.0.0.1,192.168.100.2</b>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 修改密码的模态窗口 -->
<%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
<div class="modal fade" id="editPwdModal" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="">
                    <div class="form-group">
                        <label for="oldPwd" class="col-sm-2 control-label">原密码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="oldPwd" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newPwd" class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="newPwd" name="newPwd">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="confirmPwd" class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="confirmPwd">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary"  id="changePwd_btn"
                        onclick="changePwd()">更新
                </button>
            </div>
        </div>
    </div>
</div>


<!-- 修改头像的模态窗口 -->
<%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
<div class="modal fade" id="editPhoModal" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">更换头像</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" >

                    <div class="form-group">
                        <label  class="col-sm-2 control-label">上传头像</label>
                        <div class="col-sm-10" id="img_class">
                            <input type="file" name="img" id="img">

                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary"  id="changePho_btn"  data-target="#editPhoModal"
                        onclick="changePho()">更新
                </button>
            </div>
        </div>
    </div>
</div>



<!-- 退出系统的模态窗口 -->
<div class="modal fade" id="exitModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">离开</h4>
            </div>
            <div class="modal-body">
                <p>您确定要退出系统吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="logOut()">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- 顶部 -->
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 5px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
    <div style="position: absolute; top: 15px; right: 15px;">
        <ul>
            <li class="dropdown user-dropdown">
                <a href="javascript:void(0)" style="text-decoration: none; color: white;" class="dropdown-toggle"
                   data-toggle="dropdown">
                    <c:choose >
                        <c:when test="${not empty user.img}">

                            <img src="${user.img}" height="30" width="30" id="headPho" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="/crm/upload/default.jpg" height="30" width="30" id="headPho" alt="">
                        </c:otherwise>
                    </c:choose>
                    ${user.name} <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" id="systemSet">
                    <li><a href="../settings/index.html"><span class="glyphicon glyphicon-wrench"></span> 系统设置</a></li>
                    <li><a href="javascript:void(0)" data-toggle="modal" data-target="#myInformation"><span
                            class="glyphicon glyphicon-file"></span> 我的资料</a></li>
                    <li><a href="javascript:void(0)" data-toggle="modal" data-target="#editPwdModal" id="changePwd"><span
                            class="glyphicon glyphicon-edit"></span> 修改密码</a></li>
                    <li><a href="javascript:void(0)" data-toggle="modal" data-target="#editPhoModal" id="changePho"><span
                            class="glyphicon glyphicon-user"></span> 更换头像</a></li>
                    <li><a href="javascript:void(0);" data-toggle="modal" data-target="#exitModal"><span
                            class="glyphicon glyphicon-off"></span> 退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>

<!-- 中间 -->
<div id="center" style="position: absolute;top: 50px; bottom: 30px; left: 0px; right: 0px;">

    <!-- 导航 -->
    <div id="navigation" style="left: 0px; width: 18%; position: relative; height: 100%; overflow:auto;">

        <ul id="no1" class="nav nav-pills nav-stacked">
            <li class="liClass"><a href="/crm/toView/workbench/main/index" target="workareaFrame"><span
                    class="glyphicon glyphicon-home"></span> 工作台</a></li>
            <li class="liClass"><a href="javascript:void(0);" target="workareaFrame"><span
                    class="glyphicon glyphicon-tag"></span> 动态</a></li>
            <li class="liClass"><a href="javascript:void(0);" target="workareaFrame"><span
                    class="glyphicon glyphicon-time"></span> 审批</a></li>
            <li class="liClass"><a href="javascript:void(0);" target="workareaFrame"><span
                    class="glyphicon glyphicon-user"></span> 客户公海</a></li>
            <li class="liClass"><a href="/crm/toView/workbench/activity/index" target="workareaFrame"><span
                    class="glyphicon glyphicon-play-circle"></span> 市场活动</a></li>
            <li class="liClass"><a href="/crm/toView/workbench/clue/index" target="workareaFrame"><span
                    class="glyphicon glyphicon-search"></span> 线索（潜在客户）</a></li>
            <li class="liClass"><a href="/crm/toView/workbench/customer/index" target="workareaFrame"><span
                    class="glyphicon glyphicon-user"></span> 客户</a></li>
            <li class="liClass"><a href="/crm/toView/workbench/contacts/index" target="workareaFrame"><span
                    class="glyphicon glyphicon-earphone"></span> 联系人</a></li>
            <li class="liClass"><a href="/crm/toView/workbench/transaction/chart/index" target="workareaFrame"><span
                    class="glyphicon glyphicon-usd"></span> 交易（商机）</a></li>
            <li class="liClass"><a href="/crm/toView/workbench/visit/index" target="workareaFrame"><span
                    class="glyphicon glyphicon-phone-alt"></span> 售后回访</a></li>
            <li class="liClass">
                <a href="#no2" class="collapsed" data-toggle="collapse"><span class="glyphicon glyphicon-stats"></span>
                    统计图表</a>
                <ul id="no2" class="nav nav-pills nav-stacked collapse">
                    <li class="liClass"><a href="chart/activity/index.html"
                                           target="workareaFrame">&nbsp;&nbsp;&nbsp;<span
                            class="glyphicon glyphicon-chevron-right"></span> 市场活动统计图表</a></li>
                    <li class="liClass"><a href="chart/clue/index.html" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span
                            class="glyphicon glyphicon-chevron-right"></span> 线索统计图表</a></li>
                    <li class="liClass"><a href="chart/customerAndContacts/index.html" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span
                            class="glyphicon glyphicon-chevron-right"></span> 客户和联系人统计图表</a></li>
                    <li class="liClass"><a href="chart/transaction/index.html" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span
                            class="glyphicon glyphicon-chevron-right"></span> 交易统计图表</a></li>
                </ul>
            </li>
            <li class="liClass"><a href="javascript:void(0);" target="workareaFrame"><span
                    class="glyphicon glyphicon-file"></span> 报表</a></li>
            <li class="liClass"><a href="javascript:void(0);" target="workareaFrame"><span
                    class="glyphicon glyphicon-shopping-cart"></span> 销售订单</a></li>
            <li class="liClass"><a href="javascript:void(0);" target="workareaFrame"><span
                    class="glyphicon glyphicon-send"></span> 发货单</a></li>
            <li class="liClass"><a href="javascript:void(0);" target="workareaFrame"><span
                    class="glyphicon glyphicon-earphone"></span> 跟进</a></li>
            <li class="liClass"><a href="javascript:void(0);" target="workareaFrame"><span
                    class="glyphicon glyphicon-leaf"></span> 产品</a></li>
            <li class="liClass"><a href="javascript:void(0);" target="workareaFrame"><span
                    class="glyphicon glyphicon-usd"></span> 报价</a></li>
        </ul>

        <!-- 分割线 -->
        <div id="divider1"
             style="position: absolute; top : 0px; right: 0px; width: 1px; height: 100% ; background-color: #B3B3B3;"></div>
    </div>

    <!-- 工作区 -->
    <div id="workarea" style="position: absolute; top : 0px; left: 18%; width: 82%; height: 100%;">
        <iframe style="border-width: 0px; width: 100%; height: 100%;" name="workareaFrame"></iframe>
    </div>

    <form id="userForm">

    </form>

</div>

<div id="divider2" style="height: 1px; width: 100%; position: absolute;bottom: 30px; background-color: #B3B3B3;"></div>

<!-- 底部 -->
<div id="down" style="height: 30px; width: 100%; position: absolute;bottom: 0px;"></div>
<script>
    <%--		点击按钮触发的登出方法--%>
    function logOut() {
        location.href = "/crm/settings/user/logout"
    }



    //当原密码窗口失去焦点时，使用异步验证原密码是否正确
    $("#oldPwd").blur(function () {
        if ($(this).val() === null || $(this).val() === "") {
            layer.msg("请输入原密码", {icon: 5})
            $("#oldPwd").focus();
        } else {
            $.post("/crm/settings/user/verifyOldPwd",
                {loginPwd: $(this).val()},
                    function (result) {
                        if (!result.ok) {

                            layer.msg(result.message, {icon: 5})
                            $(this).val("")
                            $(this).focus();

                        }
                    }, "json");
                }
    });

    //当再次输入密码窗口失去焦点时，使用异步判断是否两次输入密码一致
    function changePwd() {
        var $_confirmPwd = $("#confirmPwd")
            const newPwd = $("#newPwd").val();
            const confirmPwd = $_confirmPwd.val();
            if (confirmPwd !== newPwd) {
                 layer.msg("两次输入密码不一致", {icon: 5});
                 return false
            } else if (newPwd === "" || newPwd == null) {
                 layer.msg("密码不能为空", {icon: 5});
                return false
            } else if ($("#oldPwd").val() === "" || $("#oldPwd").val() == null) {
                layer.msg("请输入原密码", {icon: 5});
                return false
            }
            else {
                changePwd1()
            }
    }

    //上传图片，上传完成之后显示预览
    $("#img_class").on("change", "#img", function () {
        $.ajaxFileUpload({
            url:"/crm/settings/user/upload",
            fileElementId: ["img"],
            dataType:"json",
            success: function (data,status) {
                if (!data.ok) {
                    layer.msg(data.message, {icon: 5});
                } else {
                    layer.msg(data.message, {icon: 1});
                    $("#headPho").prop("src",data.t)
                }
            }
        })
    });

    //修改密码
    function changePwd1() {
    $.post("/crm/settings/user/changePwd",
        {newPwd:$("#newPwd").val()},
        function (result) {
            if (!result.ok) {
                layer.msg("修改失败，请重试", {icon: 5});
            } else {
                layer.msg("修改成功，即将跳往登录页面", {icon: 1})

                setTimeout(function () {
                    layer.msg('3');
                },1000)
                setTimeout(function () {
                    layer.msg('2');
                },2000)
                setTimeout(function () {
                    layer.msg('1');
                    logOut()
                },3000)
            }
        },"json");
    }

    //点击更新按钮将图片放入到数据库中
    function changePho() {
        const $img = $("#img");
        if ($img.val() !== null && $img.val() !== '') {
            $.post("/crm/settings/user/changePho",
                {img: $("#headPho").attr("src")},
                function (result) {
                    if (!result.ok) {
                        layer.msg(result.message, {icon: 5});
                    } else {
                        layer.msg(result.message, {icon: 6});
                    }
                }, "json");
            $("#editPhoModal").modal("hide");
        } else {
            layer.msg("请选择头像", {icon: 5})
            return false;
        }
    }

    //获取含有name和id的userMap
    function getUser() {
        let userMap
        $.ajaxSetup({
            async : false
        });
        $.post("/crm/workbench/activity/getUser1", function (data) {
            userMap = data
            // $.each($(data), function (index, item) {
            //     userMap.set(item.id,item.name)
            // });
        }, 'json')
        console.log(userMap)
        return userMap
    }


    //声明全局变量
    var userMap = getUser();
    console.log(userMap)
    // console.log(userMap.attr("b7658115358d4f3ea22f31604d721a18"))

    var activityRemark


</script>
</body>
</html>