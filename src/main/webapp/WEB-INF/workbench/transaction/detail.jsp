<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <link href="../../../jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../../../jquery/jquery-1.11.1-min.js"></script>
    <link href="/crm/jquery/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/crm/jquery/bs_pagination/en.js" charset=”gb2312″></script>
    <script type="text/javascript" src="/crm/jquery/bs_pagination/jquery.bs_pagination.min.js"></script>

    <style type="text/css">
        .mystage {
            font-size: 20px;
            vertical-align: middle;
            cursor: pointer;
        }

        .closingDate {
            font-size: 15px;
            cursor: pointer;
            vertical-align: middle;
        }
    </style>


    <script type="text/javascript" src="../../../jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/crm/jquery/layer/layer/layer.js"></script>
    <script type="text/javascript">

        //默认情况下取消和保存按钮是隐藏的
        var cancelAndSaveBtnDefault = true;

        $(function () {
            $("#remark").focus(function () {
                if (cancelAndSaveBtnDefault) {
                    //设置remarkDiv的高度为130px
                    $("#remarkDiv").css("height", "130px");
                    //显示
                    $("#cancelAndSaveBtn").show("2000");
                    cancelAndSaveBtnDefault = false;
                }
            });

            $("#cancelBtn").click(function () {
                //显示
                $("#cancelAndSaveBtn").hide();
                //设置remarkDiv的高度为130px
                $("#remarkDiv").css("height", "90px");
                cancelAndSaveBtnDefault = true;
            });

            $(".remarkDiv").mouseover(function () {
                $(this).children("div").children("div").show();
            });

            $(".remarkDiv").mouseout(function () {
                $(this).children("div").children("div").hide();
            });

            $(".myHref").mouseover(function () {
                $(this).children("span").css("color", "red");
            });

            $(".myHref").mouseout(function () {
                $(this).children("span").css("color", "#E6E6E6");
            });


            //阶段提示框
            $(".mystage").popover({
                trigger: 'manual',
                placement: 'bottom',
                html: 'true',
                animation: false
            }).on("mouseenter", function () {
                var _this = this;
                $(this).popover("show");
                $(this).siblings(".popover").on("mouseleave", function () {
                    $(_this).popover('hide');
                });
            }).on("mouseleave", function () {
                var _this = this;
                setTimeout(function () {
                    if (!$(".popover:hover").length) {
                        $(_this).popover("hide")
                    }
                }, 100);
            });
        });


    </script>

</head>
<body style="overflow-x:hidden;overflow-y:auto">

<!-- 修改市场活动备注的模态窗口 -->
<div class="modal fade" id="editRemarkModal" role="dialog">

    <div class="modal-dialog" role="document" style="width: 40%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" >修改备注</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="editForm">
                    <input type="hidden" name="id" id="editRemark-id">
                    <div class="form-group">
                        <label for="noteContent" class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="noteContent" name="noteContent"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="updateRemarkBtn" onclick="updateRemark()">更新</button>
            </div>
        </div>
    </div>
</div>


<!-- 返回按钮 -->
<div style="position: relative; top: 35px; left: 10px;">
    <a href="javascript:void(0);" onclick="window.history.back();">
        <span class="glyphicon glyphicon-arrow-left" style="font-size: 20px; color: #DDDDDD"></span></a>
</div>

<!-- 大标题 -->
<div style="position: relative; left: 40px; top: -30px;">
    <div class="page-header">
        <h3 id="title">动力节点-交易01 <small id="titleMoney">￥5,000</small></h3>
    </div>
    <div style="position: relative; height: 50px; width: 250px;  top: -72px; left: 700px;">
        <button type="button" class="btn btn-default" onclick="window.location.href='edit.html';"><span
                class="glyphicon glyphicon-edit"></span> 编辑
        </button>
        <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
    </div>
</div>

<!-- 阶段状态 -->
<div style="position: relative; left: 40px; top: -50px;" id="stageImg">
    阶段&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</div>

<!-- 详细信息 -->
<div style="position: relative; top: 0px;">
    <div style="position: relative; left: 40px; height: 30px;">
        <div style="width: 300px; color: gray;">所有者</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b id="owner"></b></div>
        <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">金额</div>
        <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b id="money"></b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 10px;">
        <div style="width: 300px; color: gray;">名称</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b id="name"></b></div>
        <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">预计成交日期</div>
        <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b id="expectedDate"></b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 20px;">
        <div style="width: 300px; color: gray;">客户名称</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b id="customerId"></b></div>
        <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">阶段</div>
        <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b id="stage"></b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 30px;">
        <div style="width: 300px; color: gray;">类型</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b id="type"></b></div>
        <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">可能性</div>
        <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b id="possibility"></b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 40px;">
        <div style="width: 300px; color: gray;">来源</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b id="source"></b></div>
        <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">市场活动源</div>
        <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b id="activityId"></b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 50px;">
        <div style="width: 300px; color: gray;">联系人名称</div>
        <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b id="contactsId"></b></div>
        <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 60px;">
        <div style="width: 300px; color: gray;">创建者</div>
        <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b id="createBy">&nbsp;&nbsp;</b><small
                style="font-size: 10px; color: gray;" id="createTime"></small></div>
        <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 70px;">
        <div style="width: 300px; color: gray;">修改者</div>
        <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b id="editBy">&nbsp;&nbsp;</b><small
                style="font-size: 10px; color: gray;" id="editTime"></small></div>
        <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 80px;">
        <div style="width: 300px; color: gray;">描述</div>
        <div style="width: 630px;position: relative; left: 200px; top: -20px;">
            <b id="description">

            </b>
        </div>
        <div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 90px;">
        <div style="width: 300px; color: gray;">联系纪要</div>
        <div style="width: 630px;position: relative; left: 200px; top: -20px;">
            <b id="contactSummary">
                &nbsp;
            </b>
        </div>
        <div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 100px;">
        <div style="width: 300px; color: gray;">下次联系时间</div>
        <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b id="nextContactTime">&nbsp;</b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
</div>

<!-- 备注 -->
<div style="position: relative; top: 100px; left: 40px;">
    <div class="page-header" id="remark-header">
        <h4>备注</h4>
    </div>
    <div id="remarkList">

    </div>
    <div id="remarkPage" style="width: 870px;">

    </div>
    <div id="remarkDiv" style="background-color: #E6E6E6; width: 870px; height: 90px;">
        <form role="form" style="position: relative;top: 10px; left: 10px;">
            <textarea id="remark" class="form-control" style="width: 850px; resize : none;" rows="2"
                      placeholder="添加备注..."></textarea>
            <p id="cancelAndSaveBtn" style="position: relative;left: 737px; top: 10px; display: none;">
                <button id="cancelBtn" type="button" class="btn btn-default">取消</button>
                <button type="button" class="btn btn-primary" onclick="insertTranRemark()">保存</button>
            </p>
        </form>
    </div>
</div>

<!-- 阶段历史 -->
<div>
    <div style="position: relative; top: 100px; left: 40px;">
        <div class="page-header">
            <h4>阶段历史</h4>
        </div>
        <div style="position: relative;top: 0px;">
            <table id="activityTable" class="table table-hover" style="width: 900px;">
                <thead>
                <tr style="color: #B3B3B3;">
                    <td>序号</td>
                    <td>阶段</td>
                    <td>金额</td>
                    <td>可能性</td>
                    <td>预计成交日期</td>
                    <td>创建时间</td>
                    <td>创建人</td>
                </tr>
                </thead>
                <tbody id="tranHistoryBody">
                <%--                <tr>
                                    <td>资质审查</td>
                                    <td>5,000</td>
                                    <td>10</td>
                                    <td>2017-02-07</td>
                                    <td>2016-10-10 10:10:10</td>
                                    <td>zhangsan</td>
                                </tr>
                                <tr>
                                    <td>需求分析</td>
                                    <td>5,000</td>
                                    <td>20</td>
                                    <td>2017-02-07</td>
                                    <td>2016-10-20 10:10:10</td>
                                    <td>zhangsan</td>
                                </tr>
                                <tr>
                                    <td>谈判/复审</td>
                                    <td>5,000</td>
                                    <td>90</td>
                                    <td>2017-02-07</td>
                                    <td>2017-02-09 10:10:10</td>
                                    <td>zhangsan</td>
                                </tr>--%>
                </tbody>
            </table>
        </div>

    </div>
</div>

<div style="height: 200px;"></div>


<script>

    <%--    获取交易的对象--%>
    let tran = parent.detail

    show(tran)

    //显示信息
    function show(data) {
        $("#title").html(data.customerId + `-` + data.name + `<small id="titleMoney">` + `￥` + data.money + `</small>`)
        $("#owner").text(data.owner)
        $("#money").text(data.money)
        $("#name").text(data.name)
        $("#expectedDate").text(data.expectedDate)
        $("#customerId").text(data.customerId)
        $("#type").text(data.type)
        $("#stage").text(data.stage)
        $("#possibility").text(data.possibility)
        $("#source").text(data.source)
        $("#activityId").text(data.activityId)
        $("#contactsId").text(data.contactsId)
        $("#createBy").text(data.createBy)
        $("#editBy").text(data.editBy)
        $("#createTime").text(data.createTime)
        $("#editTime").text(data.editTime)
        $("#description").text(data.description)
        $("#contactSummary").text(data.contactSummary)
        $("#nextContactTime").text(data.nextContactTime)
    }

    //显示阶段图标
    tranStage(tran)

    //分页显示交易备注
    tranRemarkList(1,3)

    //给交易备注按钮绑定CSS
    function xAnde() {

        $(".remarkDiv").mouseover(function () {
            $(this).children("div").children("div").show();
        });

        $(".remarkDiv").mouseout(function () {
            $(this).children("div").children("div").hide();
        });

        $(".myHref").mouseover(function () {
            $(this).children("span").css("color", "red");
        });

        $(".myHref").mouseout(function () {
            $(this).children("span").css("color", "#E6E6E6");
        });


    }

    //交易阶段图
    function tranStage(tran) {
        $("#stageImg").html("")
        $.ajaxSetup({
            async: false
        })
        let flag
        $.post("/crm/workbench/tran/stage", tran
            , function (data) {
                flag = data
                $.each(data.t, function (index, item) {

                    if (item.icon === "黑圈") {
                        $("#stageImg").append(`<span onclick='changeStage("` + item.stage + `","` + item.possibility + `")' class="glyphicon glyphicon-record mystage" data-toggle="popover" data-placement="bottom" data-content="` + item.stage + `"></span>`)
                    }
                    if (item.icon === "绿圈") {
                        $("#stageImg").append(`<span onclick='changeStage("` + item.stage + `","` + item.possibility + `")' class="glyphicon glyphicon-ok-circle mystage" data-toggle="popover" data-placement="bottom" data-content="` + item.stage + `" style="color: #90F790;"></span>`)
                    }
                    if (item.icon === "红叉") {
                        $("#stageImg").append(`<span class="glyphicon glyphicon-remove mystage" data-toggle="popover" data-placement="bottom" data-content="` + item.stage + `" style="color: #f79090;"></span>`)
                    }
                    if (item.icon === "黑叉") {
                        $("#stageImg").append(`<span onclick='changeStage("` + item.stage + `","` + item.possibility + `")' class="glyphicon glyphicon-remove mystage" data-toggle="popover" data-placement="bottom" data-content="` + item.stage + `" style="color: #000000;"></span>`)
                    }
                    if (item.icon === "锚点") {
                        $("#stageImg").append(`<span class="glyphicon glyphicon-map-marker mystage" data-toggle="popover" data-placement="bottom" data-content="` + item.stage + `" style="color: #90F790;"></span>`)
                    }
                    $("#stageImg").append("-----------")
                });
                $("#stageImg").append(tran.expectedDate)


                //阶段提示框
                $(".mystage").popover({
                    trigger: 'manual',
                    placement: 'bottom',
                    html: 'true',
                    animation: false
                }).on("mouseenter", function () {
                    var _this = this;
                    $(this).popover("show");
                    $(this).siblings(".popover").on("mouseleave", function () {
                        $(_this).popover('hide');
                    });
                }).on("mouseleave", function () {
                    var _this = this;
                    setTimeout(function () {
                        if (!$(".popover:hover").length) {
                            $(_this).popover("hide")
                        }
                    }, 100);
                });
                tranHistoryList(tran)

            }, 'json');
        return flag
    }

    //    交易历史显示
    function tranHistoryList(tran) {
        $("#tranHistoryBody").html("")
        $.post("/crm/workbench/tran/history", tran, function (data) {
            $.each(data, function (index, item) {
                let stage = item.stage;
                let count = index + 1
                stage = stage.substring(2, stage.size)
                $("#tranHistoryBody").append(`<tr>
                    <td><b>` + count + `</b></td>
                    <td>` + stage + `</td>
                    <td>` + item.money + `</td>
                    <td>` + item.possibility + `</td>
                    <td>` + item.expectedDate + `</td>
                    <td>` + item.createTime + `</td>
                    <td>` + item.createBy + `</td>
                </tr>`)
            })
        }, 'json');
    }

    //    点击图标更改交易阶段
    function changeStage(stage, possibility) {
        layer.confirm('确定要更改阶段吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            tran.stage = stage
            tran.possibility = possibility
            let flag = tranStage(tran)
            // tranHistoryList(tran)
            $("#stage").text(stage)
            $("#possibility").text(possibility)
            if (flag.ok) {
                layer.msg(flag.message, {icon: 1});
            } else {
                layer.msg(flag.message, {icon: 5});
            }
        }, function () {

        });
    }

    //    显示交易备注的方法
    function tranRemarkList(pageNum,pageSize) {
        $("#remarkList").empty()
        $.post("/crm/workbench/tran/tranRemarkList", {
            tranId: tran.id,
            pageNum: pageNum,
            pageSize: pageSize
        }, function (data) {
            if (data.list.length == 0) {
                $("#remarkPage").hide()
            } else {
                $("#remarkPage").show()
            }
            $.each(data.list, function (index, item) {
                $("#remarkList").append(`<div class="remarkDiv" style="height: 60px;">
                    <img title="zhangsan" src="../../../image/user-thumbnail.png" style="width: 30px; height:30px;">
                    <div style="position: relative; top: -40px; left: 40px;">
                    <h5>` + item.noteContent + `</h5>
                    <font color="gray">交易</font> <font color="gray">-</font> <b>` + tran.customerId + `-` + tran.name + `</b> <small style="color: gray;">
                    ` + item.createTime + ` 由` + item.createBy + `</small>
                    <div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
                    <a class="myHref"  href="javascript:editRemark('` + item.id + `','` + item.noteContent + `')"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="myHref"  href="javascript:deleteRemark('` + item.id + `')"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #E6E6E6;"></span></a>
                    </div></div></div>`)
            });

            xAnde()

            //分页导航
            $("#remarkPage").bs_pagination({
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
                    tranRemarkList(obj.currentPage, obj.rowsPerPage);
                }
            });

        }, 'json');
    }

    //    创建交易备注
    function insertTranRemark() {
        let noteContent = $("#remark").val()
        if (noteContent === "" || noteContent == null) {
            layer.msg("请输入备注内容",{icon:4})
            return false
        }

        $.post("/crm/workbench/tran/insertTranRemark", {
            tranId: tran.id,
            noteContent:noteContent
        }, function (data) {
            if (data.ok) {
                layer.msg(data.message, {icon: 6});
                tranRemarkList(1,3)
            //    关闭收起添加窗口
                $("#cancelAndSaveBtn").hide();
                //设置remarkDiv的高度为130px
                $("#remarkDiv").css("height", "90px");
                cancelAndSaveBtnDefault = true;
            } else {
                layer.msg(data.message, {icon: 5});
            }
        //    清空窗口
            $("#remark").val("")
        }, 'json');
    }

    //    删除交易备注
    function deleteRemark(id) {
        layer.confirm('确定要删除备注吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            $.post("/crm/workbench/tran/deleteRemark",{
                id:id
            },function(data){
                if (data.ok) {
                    layer.msg(data.message, {icon: 6});
                } else {
                    layer.msg(data.message, {icon: 5});
                }
                //分页显示交易备注
                tranRemarkList(1,3)
            },'json');
        }, function () {

        });

    }

    //    点击编辑交易备注按钮
    function editRemark(id,noteContent) {
        $("#editRemarkModal").modal("show");
        $("#editRemark-id").val(id);
        $("#noteContent").val(noteContent)
    }

    function updateRemark() {
        $.post("/crm/workbench/tran/updateRemark",
            $("#editForm").serialize(),
            function(data){
                if (data.ok) {
                    layer.msg(data.message, {icon: 1});
                } else {
                    layer.msg(data.message, {icon: 5});
                }
                $("#editRemarkModal").modal("hide");
                tranRemarkList(1,3)
            },'json');
    }
</script>
</body>
</html>