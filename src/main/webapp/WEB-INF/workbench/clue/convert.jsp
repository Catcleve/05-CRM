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
    <script type="text/javascript" src="/crm/jquery/ajaxfileupload.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#isCreateTransaction").click(function () {
                if (this.checked) {
                    $("#create-transaction2").show(200);
                } else {
                    $("#create-transaction2").hide(200);
                }
            });
        });
    </script>

</head>
<body style="overflow-x:hidden;overflow-y:auto">

<!-- 搜索市场活动的模态窗口 -->
<div class="modal fade" id="searchActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">搜索市场活动</h4>
            </div>
            <div class="modal-body">
                <div class="btn-group" style="position: relative; top: 18%; left: 8px;">

                        <div class="form-group has-feedback">
                            <input type="text" class="form-control" id="costActivityName" style="width: 300px;"
                                   placeholder="请输入市场活动名称，支持模糊查询">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>

                </div>
                <table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
                    <thead>
                    <tr style="color: #B3B3B3;">
                        <td></td>
                        <td>名称</td>
                        <td>开始日期</td>
                        <td>结束日期</td>
                        <td>所有者</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody id="bindActivityBody">

                    </tbody>
                </table>

                <div id="activityPage">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="selectActivity()" >确定</button>
                </div>
            </div>



        </div>
    </div>
</div>

<div id="title" class="page-header" style="position: relative; left: 20px;">
    <h4>转换线索 <small>李四先生-动力节点</small></h4>
</div>
<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
    新建客户：动力节点
</div>
<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
    新建联系人：李四先生
</div>
<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
    <input type="checkbox" value="0" id="isCreateTransaction"/>
    为客户创建交易
</div>
<div id="create-transaction2"
     style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;">

    <form id="tranForm">
        <div class="form-group" style="width: 400px; position: relative; left: 20px;">
            <label for="amountOfMoney">金额</label>
            <input type="text" class="form-control" id="amountOfMoney" name="money">
        </div>
        <div class="form-group" style="width: 400px;position: relative; left: 20px;">
            <label for="tradeName">交易名称</label>
            <input type="text" class="form-control" id="tradeName" name="name">
        </div>
        <div class="form-group" style="width: 400px;position: relative; left: 20px;">
            <label for="expectedClosingDate">预计成交日期</label>
            <input type="text" class="form-control" id="expectedClosingDate" name="expectedDate">
        </div>
        <div class="form-group" style="width: 400px;position: relative; left: 20px;">
            <label for="stage">阶段</label>
            <select id="stage" class="form-control" name="stage">

            </select>
        </div>

        <input type="hidden" name="activityId" id="activityId">
        <input type="hidden" name="id" id="id">
        <input type="hidden" name="createTime" id="isTran">


        <div class="form-group" style="width: 400px;position: relative; left: 20px;">
            <label for="activity">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" data-toggle="modal"
                                                      data-target="#searchActivityModal" style="text-decoration: none;"><span
                    class="glyphicon glyphicon-search"></span></a></label>
            <input type="text"  class="form-control" id="activity" placeholder="点击上面搜索" readonly>
        </div>
    </form>

</div>

<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
    记录的所有者：<br>
    <b>zhangsan</b>
</div>
<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
    <input class="btn btn-primary" type="button" onclick="clueConversion()" value="转换">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input class="btn btn-default" type="button" value="取消">
</div>
<script>

<%--    线索对象--%>
    let clue = parent.detail

    //获取字典
    let dirMap = parent.dirMap;

    //根据查字典显示下拉框的方法
    function xx(select, code) {
        select.append(`<option value="">请选择</option>`)
        $(dirMap.get(code)).each(function (i, item) {
            select.append(`<option value="` + item.value + `">` + item.text + `</option>`)
        })
    }

    //阶段下拉框
    xx($("#stage"),"stage")

    //点击转换按钮
    function clueConversion() {
        // 判断选择框的状态，然后赋值给id

        //用交易对象的id来存放线索的id
        $("#id").val(clue.id)
        //用交易对象的createTime来存放是否有交易
        $("#isTran").val($("#isCreateTransaction").val())
        $.post("/crm/workbench/clue/conversion",
            $("#tranForm").serialize()
        , function (data) {
        }, 'json');
    }

    //查询市场活动
    function getActivities(pageNum,pageSize,name) {
        $("#bindActivityBody").empty()
        $.post("/crm/workbench/clue/getActivity", {
            pageNum:pageNum,
            pageSize:pageSize,
            id: clue.id,
            job:name
        }, function (data) {
            $(data.list).each(function (index, item) {
                $("#bindActivityBody").append(`<tr>
                                <td><input name="activity" type="radio" value="` + item.id + `" /></td>
                                <td id="`+ item.id + `">` + item.name + `</td>
                                <td>` + item.startDate + `</td>
                                <td>` + item.endDate + `</td>
                                <td>` + item.owner + `</td>
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
                    getActivities(obj.currentPage, obj.rowsPerPage,name);
                }
            });
        }, 'json');
    }

    //按下回车键显示
    $("#costActivityName").keypress(function () {
        $("#bindActivityBody").empty()
        if (event.keyCode === 13) {
            getActivities(1,5,$(this).val())
        }
    })

    //点击模态框的确定按钮
    function selectActivity() {
        //获取选中的活动的id
        let aId = $("input[name = 'activity']:checked").val()

        //获取选中活动的名字
        let aName = $("#"+ aId).text()

        //活动名显示到输入框
        $("#activity").val(aName)

        //活动id放入表单，提交
        $("#activityId").val(aId)

    }

    //判断创建交易选择框的状态
    $("#isCreateTransaction").click(function () {
        if ($(this).prop("checked")) {
            $(this).val("1");
        } else {
            $(this).val("0");
        }

    });

</script>
</body>
</html>