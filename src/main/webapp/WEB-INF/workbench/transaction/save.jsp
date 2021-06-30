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
    <script type="text/javascript" src="/crm/jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>


    <script type="text/javascript" src="/crm/jquery/layer/layer/layer.js"></script>
    <script type="text/javascript" src="/crm/jquery/ajaxfileupload.js"></script>

</head>
<body style="overflow-x:hidden;overflow-y:auto">

<!-- 查找市场活动的模态框 -->
<div class="modal fade" id="findMarketActivity" role="dialog">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">查找市场活动</h4>
            </div>
            <div class="modal-body">
                <div class="btn-group" style="position: relative; top: 18%; left: 8px;">

                        <div class="has-feedback">
                            <input type="text" class="form-control" style="width: 300px;"
                                   placeholder="请输入市场活动名称，支持模糊查询" id="activityName">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>

                        </div>
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-primary" onclick="queryActivity(1,5)">查询</button>
                <table id="activityTable3" class="table table-hover"
                       style="width: 100%; position: relative;top: 10px;">
                    <thead>
                    <tr style="color: #B3B3B3;">
                        <td></td>
                        <td>名称</td>
                        <td>开始日期</td>
                        <td>结束日期</td>
                        <td>所有者</td>
                    </tr>
                    </thead>
                    <tbody id="activeList">
                    <%--<tr>
                        <td><input type="radio" name="activity"/></td>
                        <td>发传单</td>
                        <td>2020-10-10</td>
                        <td>2020-10-20</td>
                        <td>zhangsan</td>
                    </tr>
                    <tr>
                        <td><input type="radio" name="activity"/></td>
                        <td>发传单</td>
                        <td>2020-10-10</td>
                        <td>2020-10-20</td>
                        <td>zhangsan</td>
                    </tr>--%>
                    </tbody>

                </table>

            </div>
            <div id="activityPage" style="width: 98%; margin: auto" >

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="bindActivity()">关联</button>
                <button type="button" class="btn" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 查找联系人模态框
-->
<div class="modal fade" id="findContacts" role="dialog">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">查找联系人</h4>
            </div>
            <div class="modal-body">
                <div class="btn-group" style="position: relative; top: 18%; left: 8px;">

                        <div class="form-group has-feedback">
                            <input type="text" class="form-control" id="contactsName" style="width: 300px;"
                                   placeholder="请输入联系人名称，支持模糊查询">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>

                </div>
                <button type="button" class="btn btn-primary" onclick="queryContacts(1,5)"
                style="position: relative;top: -7px;right: -30px">查询</button>
                <table id="activityTable" class="table table-hover"
                       style="width: 100%; position: relative;top: 10px;">
                    <thead>
                    <tr style="color: #B3B3B3;">
                        <td></td>
                        <td>名称</td>
                        <td>邮箱</td>
                        <td>手机</td>
                    </tr>
                    </thead>
                    <tbody id="contactsList">
<%--                    <tr>
                        <td><input type="radio" name="activity"/></td>
                        <td>李四</td>
                        <td>lisi@bjpowernode.com</td>
                        <td>12345678901</td>
                    </tr>--%>
                    </tbody>
                </table>
                <div id="contactsPage">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="bindContacts()">关联</button>
                    <button type="button" class="btn" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>


<div style="position:  relative; left: 30px;">
    <h3>创建交易</h3>
    <div style="position: relative; top: -40px; left: 70%;">
        <button type="button" class="btn btn-primary" onclick="insertClue()">保存</button>
        <button type="button" class="btn btn-default" onclick=window.history.back()>取消</button>
    </div>
    <hr style="position: relative; top: -40px;">
</div>
<form class="form-horizontal" role="form" style="position: relative; top: -30px;" id="insertTranForm">
    <div class="form-group">
        <label for="create-transactionOwner" class="col-sm-2 control-label">所有者<span
                style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-transactionOwner" name="owner">

            </select>
        </div>
        <label for="create-amountOfMoney" class="col-sm-2 control-label">金额</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-amountOfMoney" name="money">
        </div>
    </div>

    <div class="form-group">
        <label for="create-transactionName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-transactionName" name="name">
        </div>
        <label for="create-expectedClosingDate" class="col-sm-2 control-label">预计成交日期<span
                style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-expectedClosingDate" name="expectedDate">
        </div>
    </div>

    <div class="form-group">
        <label for="create-accountName" class="col-sm-2 control-label">客户名称<span
                style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-accountName"
                   placeholder="支持自动补全，输入客户不存在则新建" name="customerId">
        </div>
        <label for="create-transactionStage" class="col-sm-2 control-label">阶段<span
                style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-transactionStage" name="stage">

            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="create-transactionType" class="col-sm-2 control-label">类型</label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-transactionType" name="type">

            </select>
        </div>
        <label for="create-possibility" class="col-sm-2 control-label">可能性</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-possibility"
                   placeholder="请选择阶段" readonly name="possibility">
        </div>
    </div>

    <div class="form-group">
        <label for="create-clueSource" class="col-sm-2 control-label">来源</label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-clueSource" name="source">

            </select>
        </div>
        <label for="create-activitySrc" class="col-sm-2 control-label">市场活动源&nbsp;&nbsp;
            <a href="#" onclick="queryActivity(1,5)" data-toggle="modal" data-target="#findMarketActivity"><span
                class="glyphicon glyphicon-search"></span></a></label>
        <div class="col-sm-10" onclick="queryActivity(1,5)" data-toggle="modal" data-target="#findMarketActivity" style="width: 300px;">
            <input type="text" class="form-control" id="create-activitySrc"  readonly >
            <input type="hidden" name="activityId" id="activityId" dirname="activityId">
        </div>
    </div>

    <div class="form-group">
        <label for="create-contactsName" class="col-sm-2 control-label">联系人名称&nbsp;&nbsp;
            <a href="#" onclick="queryContacts(1,5)" data-toggle="modal" data-target="#findContacts"><span
                class="glyphicon glyphicon-search"></span></a></label>
        <div class="col-sm-10" onclick="queryContacts(1,5)" data-toggle="modal" data-target="#findContacts" style="width: 300px;">
            <input type="text" class="form-control" id="create-contactsName" readonly>
            <input type="hidden" id="contactsId" name="contactsId">
        </div>
    </div>

    <div class="form-group">
        <label for="create-describe" class="col-sm-2 control-label">描述</label>
        <div class="col-sm-10" style="width: 70%;">
            <textarea class="form-control" rows="3" id="create-describe" name="describe"></textarea>
        </div>
    </div>

    <div class="form-group">
        <label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
        <div class="col-sm-10" style="width: 70%;">
            <textarea class="form-control" rows="3" id="create-contactSummary" name="contactSummary"></textarea>
        </div>
    </div>

    <div class="form-group">
        <label for="create-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-nextContactTime" name="nextContactTime">
        </div>
    </div>
</form>
</body>

<script>



    //根据查字典显示下拉框的方法
    function xx(select, code) {
        select.append(`<option value="">请选择</option>`)
        $(dirMap.get(code)).each(function (i, item) {
            select.append(`<option value="` + item.value + `">` + item.text + `</option>`)
        })
    }


    //拿到字典
    let dirMap = parent.dirMap


    opSelect($("#create-transactionOwner"))


    //回显下拉框，返回含有user id和name的对象
    function opSelect($select) {

        //从父元素拿到缓存的user信息对象，参考src/main/webapp/WEB-INF/workbench/index.jsp下方
        let user = window.parent.userMap;
        //遍历，建立下拉框
        $.each(user, function (index, item) {
            $select.append(`<option value='` + index + `'>` + item + `</option>`)
        });
        //返回  给需要回显下拉框的编辑页面使用
        return user;
    }

    //日历
    $("#create-expectedClosingDate,#create-nextContactTime").datetimepicker({
            language: "zh-CN",
            format: "yyyy-mm-dd",//显示格式
            minView: "month",//设置只显示到月份
            initialDate: new Date(),//初始化当前日期
            autoclose: true,//选中自动关闭
            todayBtn: true, //显示今日按钮
            clearBtn: true,
            pickerPosition: "bottom-left"
        });

    //    阶段下拉
    xx($("#create-transactionStage"), "stage")

    //    来源
    xx($("#create-clueSource"), "source")

    //    类型
    xx($("#create-transactionType"), "transactionType")



    //自动补全功能
    $("#create-accountName").typeahead({
        source: function (customerName, process) {
            $.post(
                "/crm/workbench/tran/queryCustomerName",
                {customerName: customerName},
                function (data) {
                    //alert(data);
                    process(data);
                },
                "json"
            );
        },
        //输入内容后延迟多长时间弹出提示内容
        delay: 500
    });

    //    选择阶段自动显示可能性
    $("#create-transactionStage").change(function () {

        $.post("/crm/workbench/tran/getPossibility",{
            stage: $(this).val()
        },function(data){
            $("#create-possibility").val(data)
        },'json');

    })

    //点击查找市场活动
    function queryActivity(pageNum,pageSize) {
        $("#activeList").empty()
        $.post("/crm/workbench/activity/list", {
            pageNum:pageNum,
            pageSize:pageSize,
            name: $("#activityName").val()
        }, function (data) {
            $(data.list).each(function (index, item) {
                let str = item.id
                $("#activeList").append(`<tr class="active" >
                                    <td><input type="radio" name="activity" class="zzz" value = '`+ str+`'/></td>
                                    <td id="` + str + `">` + item.name + `</a></td>
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
                    queryActivity(obj.currentPage, obj.rowsPerPage,name);
                }
            });
        }, 'json');
    }

    //点击市场活动关联按钮
    function bindActivity() {
        let $bind =  $("input[name=activity]:checked")
        let aNum = $bind.size()
        let activityId = $bind.val()
        let activityName = $("#"+activityId).text()
        if (aNum === 0) {
            layer.msg("请选择要关联的活动")
            return false;
        }
        $("#create-activitySrc").val(activityName)
        $("#activityId").val(activityId)
        $("#findMarketActivity").modal("hide")
    }

    //点击查找联系人
    function queryContacts(pageNum,pageSize) {
        $("#contactsList").empty()
        $.post("/crm/workbench/contacts/list", {
            pageNum:pageNum,
            pageSize:pageSize,
            fullName: $("#contactsName").val()
        }, function (data) {
            $(data.list).each(function (index, item) {
                let str = item.id
                $("#contactsList").append(`<tr class="active" >
                                    <td><input type="radio" name="contacts" class="xxx" value = '`+ str+`'/></td>
                                    <td id="` + str + `">` + item.fullName + `</a></td>
                                    <td>` + item.email + `</td>
                                    <td>` + item.mPhone + `</td>
                                </tr>`)
            })

            //分页导航
            $("#contactsPage").bs_pagination({
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
                    queryActivity(obj.currentPage, obj.rowsPerPage,name);
                }
            });
        }, 'json');
    }

    //点击联系人关联按钮
    function bindContacts() {
        let $bind =  $("input[name=contacts]:checked")
        let aNum = $bind.size()
        let contactsId = $bind.val()
        let contactsName = $("#"+contactsId).text()
        if (aNum === 0) {
            layer.msg("请选择要关联的活动")
            return false;
        }
        $("#create-contactsName").val(contactsName)
        $("#contactsId").val(contactsId)
        $("#findContacts").modal("hide")
    }

    //点击保存按钮
    function insertClue() {
        $.post("/crm/workbench/tran/insertTran",$("#insertTranForm").serialize(),
            function(data){
                if (data.ok) {
                    layer.msg(data.message, {icon: 6});
                } else {
                    layer.msg(data.message, {icon: 5});
                }
                window.history.back()
            },'json');
    }


</script>


</html>