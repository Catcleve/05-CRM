<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <link href="/crm/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/crm/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/crm/jquery/layer/layer/layer.js"></script>
    <script type="text/javascript">



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
                        <label for="edit-describe" class="col-sm-2 control-label">内容</label>
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

<!-- 修改市场活动的模态窗口 -->
<div class="modal fade" id="editActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="">修改市场活动</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="edit-marketActivityOwner">
                                <option>zhangsan</option>
                                <option>lisi</option>
                                <option>wangwu</option>
                            </select>
                        </div>
                        <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-startTime" value="2020-10-10">
                        </div>
                        <label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-endTime" value="2020-10-20">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-cost" value="5,000">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-describe" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 返回按钮 -->
<div style="position: relative; top: 35px; left: 10px;">
    <a href="javascript:void(0);" onclick="window.location.href='/crm/toView/workbench/activity/index';">
        <span class="glyphicon glyphicon-arrow-left" style="font-size: 20px; color: #DDDDDD"></span></a>
</div>

<!-- 大标题 -->
<div style="position: relative; left: 40px; top: -30px;">
    <div class="page-header">
        <h3>市场活动-发传单 <small>2020-10-10 ~ 2020-10-20</small></h3>
    </div>
    <div style="position: relative; height: 50px; width: 250px;  top: -72px; left: 700px;">
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editActivityModal"><span
                class="glyphicon glyphicon-edit"></span> 编辑
        </button>
        <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
    </div>
</div>

<!-- 详细信息 -->
<div style="position: relative; top: -70px;">
    <div style="position: relative; left: 40px; height: 30px;">
        <div style="width: 300px; color: gray;">所有者</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b id="aRemake_owner"></b></div>
        <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">名称</div>
        <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b id="aRemake_name"></b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
    </div>

    <div style="position: relative; left: 40px; height: 30px; top: 10px;">
        <div style="width: 300px; color: gray;">开始日期</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b id="aRemake_startDate"></b></div>
        <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">结束日期</div>
        <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b id="aRemake_endDate"></b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 20px;">
        <div style="width: 300px; color: gray;">成本</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b id="aRemark_cost"></b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 30px;">
        <div style="width: 300px; color: gray;">创建者</div>
        <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b id="aRemake_createBy"></b><small
                style="font-size: 10px; color: gray;" id="aRemake_createTime"></small></div>
        <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 40px;">
        <div style="width: 300px; color: gray;">修改者</div>
        <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b id="aRemake_editBy"></b><small
                style="font-size: 10px; color: gray;" id="aRemake_editTime"></small></div>
        <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 50px;">
        <div style="width: 300px; color: gray;">描述</div>
        <div style="width: 630px;position: relative; left: 200px; top: -20px;">
            <b id="aRemake_description">

            </b>
        </div>
        <div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
</div>

<!-- 备注 -->
<div style="position: relative; top: 30px; left: 40px;" id="remarksFather">
    <div class="page-header" id="remarks">
        <h4>备注</h4>
    </div>


    <div id="remarkDiv" style="background-color: #E6E6E6; width: 870px; height: 90px;">
        <form role="form" style="position: relative;top: 10px; left: 10px;" id="insertForm">
            <textarea id="remark" name="noteContent" class="form-control" style="width: 850px; resize : none;" rows="2"
                      placeholder="添加备注..."></textarea>
            <p id="cancelAndSaveBtn" style="position: relative;left: 737px; top: 10px; display: none;">
                <button id="cancelBtn" type="button" class="btn btn-default" >取消</button>
                <button type="button" class="btn btn-primary" onclick="insertRemark()">保存</button>
            </p>
            <input type="hidden" name="activityId" id="activityId">
        </form>
    </div>
</div>
<div style="height: 200px;"></div>
</body>
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
            remarkDivHide()
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
    });

    //隐藏输入按钮
    function remarkDivHide() {

        //清空
        $("#remark").val("")
        //隐藏
        $("#cancelAndSaveBtn").hide();
        //设置remarkDiv的高度为130px
        $("#remarkDiv").css("height", "90px");
        cancelAndSaveBtnDefault = true;
    }

    <%--    获取activity对象--%>
    let activity = parent.activityRemark
    //回显
    showActivity();

    //显示活动信息
    function showActivity() {
        $("#aRemake_owner").text(activity.owner)
        $("#aRemake_name").text(activity.name)
        $("#aRemake_startDate").text(activity.startDate)
        $("#aRemake_endDate").text(activity.endDate)
        $("#aRemake_editBy").text(activity.editBy)
        $("#aRemake_cost").text(activity.cost)
        $("#aRemake_createBy").text(activity.createBy)
        $("#aRemake_description").text(activity.description)
        $("#aRemake_createTime").text(" " + activity.createTime)
        $("#aRemake_editTime").text("  " + activity.editTime)
        let remark = activity.activityRemarks

        remark.forEach((value) => {
            remarkList(value);
        })
    }

    //显示备注列表，遍历备注集合，放入页面的方法
    function remarkList(value) {

        $("#remarks").after(`<div id="remark` + value.id + `" class="remarkDiv" style="height: 60px;">
        <img title="" src="` + value.img + `" style="width: 30px; height:30px;">
        <div style="position: relative; top: -40px; left: 40px;">
            <h5 id="content` + value.id + `" >` + value.noteContent + `</h5>
            <font color="gray">市场活动</font> <font color="gray">-</font> <b>` + activity.name + `</b> <small style="color: gray;">
            ` + value.createTime + ` 由` + value.createBy + `</small>
            <div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
                <a class="myHref" href="javascript:editRemark('` + value.id + `','` + value.noteContent + `' );">
                <span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a class="myHref" href="javascript:deleteRemark('` + value.id + `');">
                <span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #E6E6E6;"></span></a>
                </div>
            </div>
        </div>`)

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

    //新增备注
    function insertRemark() {
        $("#activityId").val(activity.id)
        $.post("/crm/workbench/activity/insertRemark",
            $("#insertForm").serialize(),
            function (data) {
                if (data.ok) {
                    layer.msg("添加成功", {icon: 6});
                    remarkList(data.t);
                    remarkDivHide();
                } else {
                    layer.msg(data.message, {icon: 5});
                }
            }, 'json');
    }

    //删除备注
    function deleteRemark(id) {

        layer.confirm('确定删除备注吗？', {
            btn: ['删除','取消'] //按钮
            }, function(){
                $.post("/crm/workbench/activity/deleteRemark", {id:id},
                    function(data){
                        if (data.ok) {
                            layer.msg("删除成功", {icon: 1});
                            $("#remark"+id).remove()
                        } else {
                            layer.msg(data.message, {icon: 5});
                        }
                    },'json');
            }, function(){
        });
    }

    //弹出编辑备注模态框
    function editRemark(id,note) {
        $("#editRemarkModal").modal("show");
        $("#editRemark-id").val(id);
        $("#noteContent").val(note)
    }

    function updateRemark() {
        $.post("/crm/workbench/activity/updateRemark",
            $("#editForm").serialize(),
            function(data){
                if (data.ok) {
                    layer.msg("修改成功", {icon: 1});
                    $("#remark"+ $("#editRemark-id").val()).remove()
                    remarkList(data.t)
                    $("#editRemarkModal").modal("hide");
                } else {
                    layer.msg(data.message, {icon: 5});
                }
            },'json');
    }

</script>
</html>