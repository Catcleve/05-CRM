<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

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

            //定制字段
            $("#definedColumns > li").click(function (e) {
                //防止下拉菜单消失
                e.stopPropagation();
            });

        });

    </script>
</head>
<body style="overflow-x:hidden;overflow-y:auto">

<!-- 创建客户的模态窗口 -->
<div class="modal fade" id="createCustomerModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">创建客户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="addForm">

                    <div class="form-group">
                        <label for="create-customerOwner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="create-customerOwner" name="owner">

                            </select>
                        </div>
                        <label for="create-customerName" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-customerName" name="name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-website" class="col-sm-2 control-label">公司网站</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-website" name="website">
                        </div>
                        <label for="create-phone" class="col-sm-2 control-label">公司座机</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-phone" name="phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-describe" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="create-describe" name="description"></textarea>
                        </div>
                    </div>
                    <div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

                    <div style="position: relative;top: 15px;">
                        <div class="form-group">
                            <label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
                            <div class="col-sm-10" style="width: 81%;">
                                <textarea class="form-control" rows="3" id="create-contactSummary"
                                          name="contactSummary"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="create-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-nextContactTime"
                                       name="nextContactTime">
                            </div>
                        </div>
                    </div>

                    <div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

                    <div style="position: relative;top: 20px;">
                        <div class="form-group">
                            <label for="create-address1" class="col-sm-2 control-label">详细地址</label>
                            <div class="col-sm-10" style="width: 81%;">
                                <textarea class="form-control" rows="1" id="create-address1" name="address"></textarea>
                            </div>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveCustomer()">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 修改客户的模态窗口 -->
<div class="modal fade" id="editCustomerModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改客户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="editForm">

                    <div class="form-group">
                        <label for="edit-customerOwner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="edit-customerOwner" name="owner">

                            </select>
                        </div>
                        <label for="edit-customerName" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-customerName" name="name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-website" class="col-sm-2 control-label">公司网站</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-website" name="website">
                        </div>
                        <label for="edit-phone" class="col-sm-2 control-label">公司座机</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-phone" name="phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-describe" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="edit-describe" name="description"></textarea>
                        </div>
                    </div>
                    <div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

                    <div style="position: relative;top: 15px;">
                        <div class="form-group">
                            <label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
                            <div class="col-sm-10" style="width: 81%;">
                                <textarea class="form-control" rows="3" id="edit-contactSummary"
                                          name="contactSummary"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-nextContactTime"
                                       name="nextContactTime">
                            </div>
                        </div>
                    </div>

                    <div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

                    <div style="position: relative;top: 20px;">
                        <div class="form-group">
                            <label for="edit-address" class="col-sm-2 control-label">详细地址</label>
                            <div class="col-sm-10" style="width: 81%;">
                                <textarea class="form-control" rows="1" id="edit-address" name="address"></textarea>
                            </div>
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


<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>客户列表</h3>
        </div>
    </div>
</div>

<%--主列表--%>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">

    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">


                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">名称</div>
                        <input class="form-control" name="name" type="text" id="name">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">所有者</div>
                        <input class="form-control" name="owner" type="text" id="owner">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">公司座机</div>
                        <input class="form-control" type="text" name="phone" id="phone">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">公司网站</div>
                        <input class="form-control" type="text" name="website" id="website">
                    </div>
                </div>

                <button type="button" class="btn btn-default" onclick="goPage(1,5)">查询</button>

            </form>
        </div>
        <div class="btn-toolbar" role="toolbar"
             style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createCustomerModal"
                        onclick="createCustomer()">
                    <span class="glyphicon glyphicon-plus"></span> 创建
                </button>
                <button type="button" class="btn btn-default"
                        onclick="editCustomer()">
                    <span class="glyphicon glyphicon-pencil"></span> 修改
                </button>
                <button type="button" class="btn btn-danger" onclick="deleteCustomer()"><span
                        class="glyphicon glyphicon-minus">
				  </span> 删除
                </button>
            </div>

        </div>
        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">
                    <td><input type="checkbox" id="checkAll"/></td>
                    <td>名称</td>
                    <td>所有者</td>
                    <td>公司座机</td>
                    <td>公司网站</td>
                </tr>
                </thead>
                <tbody id="customerList">

                </tbody>
            </table>
        </div>


        <div id="customerPage">

        </div>


    </div>

</div>

<script type="text/javascript">

    //全选按钮
    $(function () {
        $("#checkAll").click(function () {
            $(".zzz").prop("checked", $(this).prop("checked"))
        });
    });

    //多选框决定全选按钮的状态
    $("#customerList").on("click", $(".zzz"), function () {
        if ($(".zzz:not(:checked)").length === 0) {
            $("#checkAll").prop("checked", true)
        } else {
            $("#checkAll").prop("checked", false)
        }
    })

    <%--	回显下拉框--%>

    function opSelect($select) {

        //从父元素拿到缓存的user信息对象，参考src/main/webapp/WEB-INF/workbench/index.jsp下方
        let user = window.parent.userMap;
        console.log(user)
        //遍历，建立下拉框
        $.each(user, function (index, item) {
            $select.append(`<option value='` + index + `'>` + item + `</option>`)
        });
        //返回  给需要回显下拉框的编辑页面使用
        return user;
    }


    //页面加载之后默认显示第一页,5条
    $(function () {
        goPage(1, 5)
    });

    //查询方法
    function goPage(pageNum, pageSize) {

        $.get("/crm/workbench/customer/list", {
                pageNum: pageNum,
                pageSize: pageSize,
                owner: $("#owner").val(),
                name: $("#name").val(),
                phone: $("#phone").val(),
                website: $("#website").val()
            },
            function (data) {
                $('#customerList').html("");
                $.each(data.list, function (index, item) {
                    var str = JSON.stringify(item)
                    $("#customerList").append(`<tr class="active" >
                                    <td><input type="checkbox" class="zzz" value = '` + str + `'/></td>
                                    <td><a style="text-decoration: none; cursor: pointer;"
                                    onclick='customer_detail(` + str + `)'>` + item.name + `</a></td>
                                    <td>` + item.owner + `</td>
                                    <td>` + item.phone + `</td>
                                    <td>` + item.website + `</td>
                                </tr>`)
                })

                //分页导航
                $("#customerPage").bs_pagination({
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
                $("#checkAll").prop("checked", false)
            }, 'json');
    }

    //点击创建按钮
    function createCustomer() {
        const $select = $("#create-customerOwner");

        //    回显下拉框
        opSelect($select)

    }

    //   点击添加的提交按钮
    function saveCustomer() {

        //判断名称是否为空
        const name = $("#create-customerName").val();

        if (name !== null && name !== "") {

            $.post("/crm/workbench/customer/saveCustomer", $("#addForm").serialize(),
                function (data) {
                    console.log(data)
                    if (data.ok) {
                        layer.msg("添加成功", {icon: 6});
                        $("#create-customerOwner").empty()
                        $("#addForm input,textarea").val("")
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                    $("#createCustomerModal").modal("hide");
                    goPage(1, 5)
                }, 'json');

        } else {
            layer.msg("请输入名称！", {icon: 5});
            return false;
        }
    }

    //    点击编辑按钮
    function editCustomer() {
        //获取选中的复选框对象
        const $customer_delete = $(".zzz:checked");
        //获取下拉框select元素
        const $select = $("#edit-customerOwner")
        $select.empty()
        //    判断选中复选框的个数
        const int = $customer_delete.length;
        if (int !== 1) {
            layer.msg("请选择要修改的信息（一条）", {icon: 5});
            return false;
        } else {
            //显示模态框
            $("#editCustomerModal").modal("show")

            //拿到放在input复选框上面的json对象
            const customer = JSON.parse($customer_delete.val());

            //使用opSelect()方法获取到存放user name和id 的对象，转为map集合
            let userList = new Map(Object.entries(opSelect($select)));
            //遍历，然后选中
            userList.forEach(((value, key) => {
                if (value === customer.owner) {
                    $select.val(key)
                }
            }))
            $("#edit-id").val(customer.id)
            $("#edit-customerName").val(customer.name)
            $("#edit-phone").val(customer.phone)
            $("#edit-website").val(customer.website)
            $("#edit-describe").val(customer.description)
            $("#edit-contactSummary").val(customer.contactSummary)
            $("#edit-nextContactTime").val(customer.nextContactTime)
            $("#edit-address").val(customer.address)
        }
    }

    //    点击编辑的提交按钮
    function updateBtn() {
        $.post("/crm/workbench/customer/updateCustomer",
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
    function deleteCustomer() {

        //活动名称
        let activeName = "";

        let ids = [];

        //获取选中的复选框对象
        const $customer = $(".zzz:checked");
        //    判断选中复选框的个数
        const int = $customer.length;
        if (int === 0) {
            layer.msg("请选择要删除的信息（一条或者多条）", {icon: 5});
            return false;
        } else {
            //    遍历选中的按钮,把所有id放入要删除的form表单
            $customer.each(function (index, item) {
                //这里item的value是一个json对象的字符串形式，转换为json对象
                const custom = JSON.parse($(item).val());
                if (index < int) {
                    //id拼接的字符串，可以直接传入后台作为数组
                    ids.push(custom.id)
                    //获取活动名称
                    activeName += custom.name + ","
                }
                console.log(ids)
            })
            activeName = activeName.substring(0, activeName.length - 1)


            layer.confirm('<h4>确定删除以下' + int + '条</h4>' + activeName + '吗？', {
                btn: ['删除', '取消'] //按钮
            }, function () {
                $.post("/crm/workbench/customer/deleteCustomer",
                    {id: ids.join()},
                    function (data) {
                        if (data.ok) {
                            layer.msg("删除成功", {icon: 6});
                            goPage(1, 5)
                        } else {
                            layer.msg(data.message, {icon: 5});
                        }

                    }, 'json');
            });
        }
    }

    //跳往详情页面
    function customer_detail(customer) {
        parent.customerDetail = customer
        location.href = "/crm/toView/workbench/customer/detail"
    }

</script>
</body>
</html>