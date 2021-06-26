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


    <link href="/crm/jquery/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/crm/jquery/bs_pagination/en.js" charset=”gb2312″></script>
    <script type="text/javascript" src="/crm/jquery/bs_pagination/jquery.bs_pagination.min.js"></script>


    <script type="text/javascript" src="/crm/jquery/layer/layer/layer.js"></script>

    <script type="text/javascript">

        $(function () {


        });

    </script>
</head>
<body>


<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>交易列表</h3>
        </div>
    </div>
</div>

<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">

    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 105px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">所有者</div>
                        <input class="form-control" type="text" id="owner">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">名称</div>
                        <input class="form-control" type="text" id="name">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">客户名称</div>
                        <input class="form-control" type="text" id="customerId">
                    </div>
                </div>
                <div style="height: 0; width: 101%;  left: -13px; position: relative; top : 50px;"></div>
                <br>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">阶段</div>
                        <select class="form-control" id="stage" >

                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">类型</div>
                        <select class="form-control" id="type">

                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">来源</div>
                        <select class="form-control" id="source">

                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">联系人名称</div>
                        <input class="form-control" type="text" id="contactsId">
                    </div>
                </div>

                <button type="button" class="btn btn-default" onclick="goPage(1,5)">查询</button>

            </form>
            <div style="height: 0; width: 101%;  left: -13px; position: relative; top : 50px;"></div>
        </div>
        <div class="btn-toolbar" role="toolbar"
             style="background-color: #F7F7F7; height: 50px; position: relative;top: 10px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <button type="button" class="btn btn-primary"
                        onclick="window.location.href='/crm/toView/workbench/transaction/save';"><span
                        class="glyphicon glyphicon-plus"></span> 创建
                </button>
                <button type="button" class="btn btn-default" onclick="window.location.href='edit.html';"><span
                        class="glyphicon glyphicon-pencil"></span> 修改
                </button>
                <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
            </div>


        </div>
        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">
                    <td><input type="checkbox"/></td>
                    <td>名称</td>
                    <td>客户名称</td>
                    <td>阶段</td>
                    <td>类型</td>
                    <td>所有者</td>
                    <td>来源</td>
                    <td>联系人名称</td>
                </tr>
                </thead>
                <tbody id="tranBody">
              <%--  <tr>
                    <td><input type="checkbox"/></td>
                    <td><a style="text-decoration: none; cursor: pointer;"
                           onclick="window.location.href='';">动力节点-交易01</a></td>
                    <td>动力节点</td>
                    <td>谈判/复审</td>
                    <td>新业务</td>
                    <td>zhangsan</td>
                    <td>广告</td>
                    <td>李四</td>
                </tr>--%>
               <%-- <tr class="active">
                    <td><input type="checkbox"/></td>
                    <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">动力节点-交易01</a>
                    </td>
                    <td>动力节点</td>
                    <td>谈判/复审</td>
                    <td>新业务</td>
                    <td>zhangsan</td>
                    <td>广告</td>
                    <td>李四</td>
                </tr>--%>
                </tbody>
            </table>
        </div>

        <div id="tran_bs">
        </div>

    </div>

</div>
</body>
<script>

    $(".input-group-addon").css("width","90px")

    //拿到字典
    let dirMap = parent.dirMap

    goPage(1, 5);

    //    阶段下拉
    xx($("#stage"),"stage")
    xx($("#source"),"source")
    xx($("#type"),"transactionType")

    //根据查字典显示下拉框的方法
    function xx(select, code) {
        select.append(`<option value="">请选择</option>`)
        $(dirMap.get(code)).each(function (i, item) {
            select.append(`<option value="` + item.value + `">` + item.text + `</option>`)
        })
    }

    //查询遍历
    function goPage(pageNum, pageSize) {

        $.post("/crm/workbench/tran/list", {
                pageNum: pageNum,
                pageSize: pageSize,
                owner: $("#owner").val(),
                name: $("#name").val(),
                customerId: $("#customerId").val(),
                stage: $("#stage").val(),
                type: $("#type").val(),
                source: $("#source").val(),
                contactsId: $("#contactsId").val()
            },
            function (data) {
                $('#tranBody').html("");
                $.each(data.list, function (index, item) {
                    var str = JSON.stringify(item)
                    $("#tranBody").append(`<tr class="active" >
                                    <td><input type="checkbox" class="zzz" value = '` + str + `'/></td>
                                    <td><a style="text-decoration: none; cursor: pointer;"
                                    onclick='tran_detail(` + str + `)'>` + item.name + `</a></td>
                                    <td>` + item.customerId + `</td>
                                    <td>` + item.stage + `</td>
                                    <td>` + item.type + `</td>
                                    <td>` + item.owner + `</td>
                                    <td>` + item.source + `</td>
                                    <td>` + item.contactsId + `</td>
                                </tr>`)
                })

                //分页导航
                $("#tran_bs").bs_pagination({
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

    //点击创建按钮，跳转创建页面
    function tran_detail(data) {

        //获取本条信息
        parent.detail = data

        //跳转页面
        window.location.href = '/crm/toView/workbench/transaction/detail';

    }


</script>
</html>