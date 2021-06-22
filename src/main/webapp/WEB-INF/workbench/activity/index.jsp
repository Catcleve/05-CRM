<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
        <link href="/crm/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
              type="text/css" rel="stylesheet"/>

        <script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
        <script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
        <script type="text/javascript"
                src="/crm/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
        <script type="text/javascript"
                src="/crm/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"
                charset="UTF-8"></script>


        <link href="/crm/jquery/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="/crm/jquery/bs_pagination/en.js" charset=”gb2312″></script>
        <script type="text/javascript" src="/crm/jquery/bs_pagination/jquery.bs_pagination.min.js"></script>


        <script type="text/javascript" src="/crm/jquery/layer/layer/layer.js"></script>
        <script type="text/javascript" src="/crm/jquery/ajaxfileupload.js"></script>

    </head>
    <body>

        <!-- 创建市场活动的模态窗口 -->
        <div class="modal fade" id="createActivityModal" role="dialog">
            <div class="modal-dialog" role="document" style="width: 85%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form" id="addForm">

                            <div class="form-group">
                                <label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者
                                    <span style="font-size: 15px; color: red;">*</span>
                                </label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <select class="form-control" id="create-marketActivityOwner" name="owner">

                                    </select>
                                </div>
                                <label for="create-marketActivityName" class="col-sm-2 control-label">名称
                                    <span style="font-size: 15px; color: red;">*</span>
                                </label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="create-marketActivityName" name="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="create-startTime" name="startDate">
                                </div>
                                <label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="create-endTime" name="endDate">
                                </div>
                            </div>
                            <div class="form-group">

                                <label for="create-cost" class="col-sm-2 control-label">成本</label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="create-cost" name="cost">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="create-describe" class="col-sm-2 control-label">描述</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="3" id="create-describe" name="description"></textarea>
                                </div>
                            </div>
                            <input type="hidden" name="createBy" value="${user.name}">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="saveActivity()">保存</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- 修改市场活动的模态窗口 -->
        <div class="modal fade" id="editActivityModal" role="dialog">
            <div class="modal-dialog" role="document" style="width: 85%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form" id="editForm">

                            <div class="form-group">
                                <label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者
                                    <span style="font-size: 15px; color: red;">*</span>
                                </label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <select class="form-control" id="edit-marketActivityOwner" name="owner">

                                    </select>
                                </div>
                                <label for="edit-marketActivityName" class="col-sm-2 control-label">名称
                                    <span style="font-size: 15px; color: red;">*</span>
                                </label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="edit-marketActivityName" name="name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="edit-startTime" name="startDate">
                                </div>
                                <label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="edit-endTime" name="endDate">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="edit-cost" class="col-sm-2 control-label">成本</label>
                                <div class="col-sm-10" style="width: 300px;">
                                    <input type="text" class="form-control" id="edit-cost" name="cost">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="edit-describe" class="col-sm-2 control-label">描述</label>
                                <div class="col-sm-10" style="width: 81%;">
                                            <textarea class="form-control" rows="3" id="edit-describe" name="description">

                                            </textarea>
                                </div>
                            </div>

                            <input type="hidden" name="id" id="edit-id">

                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="updateBtn()">更新</button>
                    </div>
                </div>
            </div>
        </div>

        <%--标题--%>
        <div>
            <div style="position: relative; left: 10px; top: -10px;">
                <div class="page-header">
                    <h3>市场活动列表</h3>
                </div>
            </div>
        </div>

        <%--主列表--%>
        <div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
            <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

                <div class="btn-toolbar" role="toolbar" style="height: 80px;">
                    <form class="form-inline" role="form" id="queryForm" style="position: relative;top: 8%; left: 5px;">

                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">名称</div>
                                <input class="form-control" name="name" id="name" type="text">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">所有者</div>
                                <input class="form-control" id="owner" type="text">
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">开始日期</div>
                                <input class="form-control" type="text" name="startDate" id="startTime"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">结束日期</div>
                                <input class="form-control" type="text" name="endDate" id="endTime">
                            </div>
                        </div>

                        <button type="button" onclick="goPage(1,5)" class="btn btn-default">查询</button>

                    </form>
                </div>
                <div class="btn-toolbar" role="toolbar"
                     style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
                    <div class="btn-group" style="position: relative; top: 18%;">
                        <button type="button" class="btn btn-primary" onclick="createActivity()" data-toggle="modal"
                                data-target="#createActivityModal">
                            <span class="glyphicon glyphicon-plus"></span>
                            创建
                        </button>
                        <button type="button" class="btn btn-default" onclick="editActivity()">
                            <span class="glyphicon glyphicon-pencil"></span>
                            修改
                        </button>
                        <button type="button" class="btn btn-danger " onclick="deleteActivity()">
                            <span class="glyphicon glyphicon-minus"></span>
                            删除
                        </button>
                    </div>
                </div>
                <div style="position: relative;top: 10px;">
                    <table class="table table-hover">
                        <thead>
                        <tr style="color: #B3B3B3;">
                            <td>
                                <input type="checkbox" id="checkAll"/>
                            </td>
                            <td>名称</td>
                            <td>所有者</td>
                            <td>开始日期</td>
                            <td>结束日期</td>
                        </tr>
                        </thead>
                        <tbody id="activeList">

                        </tbody>
                    </table>
                </div>

                <div id="activityPage">

                </div>
            </div>
        </div>

    <%--隐藏的删除表单--%>
        <form id="deleteForm">

        </form>


        <script type="text/javascript">

            //页面加载之后默认显示第一页,5条
            $(function () {
                goPage(1, 5)
            });

            //查询方法
            function goPage(pageNum, pageSize) {

                $.get("/crm/workbench/activity/list", {
                        pageNum: pageNum,
                        pageSize: pageSize,
                        owner: $("#owner").val(),
                        name: $("#name").val(),
                        startDate: $("#startTime").val(),
                        endDate: $("#endTime").val()
                    },
                    function (data) {
                        $('#activeList').html("");
                        $.each(data.list, function (index, item) {
                            var str = JSON.stringify(item)
                            $("#activeList").append(`<tr class="active" >
                                    <td><input type="checkbox" class="zzz" value = '` + str + `'/></td>
                                    <td><a style="text-decoration: none; cursor: pointer;"
                                    onclick='remark_detail(` + str + `)'>` + item.name + `</a></td>
                                    <td>` + item.owner + `</td>
                                    <td>` + item.startDate + `</td>
                                    <td>` + item.endDate + `</td>
                                </tr>`)
                        })

                        //分页导航
                        $("#activityPage").bs_pagination({
                            currentPage: data.pageNum, // 页码
                            rowsPerPage: data.pageSize, // 每页显示的记录条数
                            maxRowsPerPage: 20, // 每页最多显示的记录条数
                            totalPages: data.pages, // 总页数
                            totalRows: data.total, // 总记录条数
                            visiblePageLinks: 3, // 显示几个卡片
                            showGoToPage: true,
                            showRowsPerPage: true,
                            showRowsInfo: true,
                            showRowsDefaultInfo: true,
                            //回调函数，用户每次点击分页插件进行翻页的时候就会触发该函数
                            onChangePage: function (event, obj) {

                                //刷新页面，obj.currentPage:当前点击的页码
                                goPage(obj.currentPage, obj.rowsPerPage);
                            }
                        });
                    }, 'json');
            }

            //全选按钮
            $(function () {
                $("#checkAll").click(function () {
                    $(".zzz").prop("checked", $(this).prop("checked"))
                });
            });

            //多选框决定全选按钮的状态
            $("#activeList").on("click", $(".zzz"), function () {
                if ($(".zzz:not(:checked)").length === 0) {
                    $("#checkAll").prop("checked", true)
                } else {
                    $("#checkAll").prop("checked", false)
                }
            })

            //日历插件使用
            $("#edit-startTime ,#edit-endTime,#create-startTime,#create-endTime,#startTime,#endTime")
                .datetimepicker({
                    language: "zh-CN",
                    format: "yyyy-mm-dd",//显示格式
                    minView: "month",//设置只显示到月份
                    initialDate: new Date(),//初始化当前日期
                    autoclose: true,//选中自动关闭
                    todayBtn: true, //显示今日按钮
                    clearBtn: true,
                    pickerPosition: "bottom-left"
                });

            //    抽取出回显下拉框的方法
            // function opSelect($select) {
            //     $.post("/crm/workbench/activity/getUser", function (data) {
            //         data.forEach((item,index) =>{
            //             $select.append(`<option value='` + item.id + `'>` + item.name + `</option>`)
            //         })
            //
            //         // $.each($(data), function (index, item) {
            //         //     $select.append(`<option value='` + item.id + `'>` + item.name + `</option>`)
            //         // });
            //     }, 'json');
            // }
            //回显下拉框，返回含有user id和name的对象
            function opSelect($select) {

                //从父元素拿到缓存的user信息对象，参考src/main/webapp/WEB-INF/workbench/index.jsp下方
                let user = window.parent.userMap;
                console.log(user)
                //遍历，建立下拉框
                $.each(user, function (index,item) {
                    $select.append(`<option value='` + index + `'>` + item + `</option>`)
                });
                //返回  给需要回显下拉框的编辑页面使用
                return user;
            }

            //点击添加按钮之后启动方法
            function createActivity() {
                const $select = $("#create-marketActivityOwner");
                //    清空
                $select.empty()
                $("#addForm input,textarea").val("")
                //    回显下拉框
                opSelect($select)

            }

            //   点击添加的提交按钮
            function saveActivity() {
                const name = $("#create-marketActivityName").val();
                //先清空窗口
                if (name !== null && name !== "") {

                    $.post("/crm/workbench/activity/saveActivity", $("#addForm").serialize(),
                        function (data) {
                            console.log(data)
                            if (data.ok) {
                                layer.msg("添加成功", {icon: 6});
                            } else {
                                layer.msg(data.message, {icon: 5});
                            }
                            $("#createActivityModal").modal("hide");
                            goPage(1, 5)
                        }, 'json');

                } else {
                    layer.msg("请输入名称！", {icon: 5});
                    return false;
                }
            }

            //    点击编辑按钮
            function editActivity() {
                //获取选中的复选框对象
                const $activity = $(".zzz:checked");
                //获取下拉框select元素
                const $select = $("#edit-marketActivityOwner")
                $select.empty()
                //    判断选中复选框的个数
                const int = $activity.length;
                if (int !== 1) {
                    layer.msg("请选择要修改的信息（一条）", {icon: 5});
                    return false;
                } else {
                    //显示模态框
                    $("#editActivityModal").modal("show")

                    //拿到放在input复选框上面的json对象
                    const active = JSON.parse($activity.val());

                    //使用opSelect()方法获取到存放user name和id 的对象，转为map集合
                    let userList = new Map(Object.entries(opSelect($select)));
                    //遍历，然后选中
                    userList.forEach(((value, key) => {
                        if (value === active.owner) {
                            $select.val([key])
                        }
                    }))
                    console.log(userList)
                    $("#edit-id").val(active.id)
                    $("#edit-marketActivityName").val(active.name)
                    $("#edit-startTime").val(active.startDate)
                    $("#edit-endTime").val(active.endDate)
                    $("#edit-describe").val(active.description)
                    $("#edit-cost").val(active.cost)
                }
            }

            //    点击编辑的提交按钮
            function updateBtn() {
                $.post("/crm/workbench/activity/updateActivity",
                    $("#editForm").serialize(),
                    function (data) {
                        if (data.ok) {
                            layer.msg("修改成功", {icon: 5});
                            goPage(1, 5);
                        } else {
                            layer.msg(data.message, {icon: 5});
                        }
                    }, 'json');

            }

            //    点击删除的按钮
            function deleteActivity() {

                //活动名称
                let activeName = "";
                //获取要放入的form元素
                const $deleteFrom = $("#deleteForm")
                //清空
                $deleteFrom.html("")
                //获取选中的复选框对象
                const $activity = $(".zzz:checked");
                //    判断选中复选框的个数
                const int = $activity.length;
                if (int === 0) {
                    layer.msg("请选择要删除的信息（一条或者多条）", {icon: 5});
                    return false;
                } else {
                //    遍历选中的按钮,把所有id放入要删除的form表单
                    $activity.each(function (index,item) {
                        //这里item的value是一个json对象的字符串形式，转换为json对象
                        const active = JSON.parse($(item).val());
                        //把id放入表单中
                        $("<input type='hidden' name='id'>").attr("value",active.id).appendTo($deleteFrom)
                        //获取活动名称
                        activeName += active.name + ","
                        console.log(active.id)
                    })
                    activeName = activeName.substring(0,activeName.length-1)
                    console.log(activeName)
                //    弹出确认删除窗口
                    if (confirm("确认删除 "+ activeName + " 吗?")) {
                        $.post("/crm/workbench/activity/deleteActivity",
                            $deleteFrom.serialize(),
                            function(data){
                                if (data.ok) {
                                    layer.msg("删除成功", {icon: 6});
                                    goPage(1,5)
                                } else {
                                    layer.msg(data.message, {icon: 5});
                                }

                            },'json');
                    }
                }
            }

            //    点击名称，跳转到detail页面
            function remark_detail(data) {
                //给父页面公共变量赋值，等于点击的活动对象
                parent.activityRemark = data
                window.location.href='/crm/toView/workbench/activity/detail';

            }

        </script>

    </body>
</html>