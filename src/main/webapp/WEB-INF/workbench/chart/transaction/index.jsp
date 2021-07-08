<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="/crm/jquery/echarts.min.js"></script>
    <script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
</head>
<body>
<div id="main" style="width: 1500px;height:600px;margin:150px auto"></div>
<script type="text/javascript">

    $.post("/crm/workbench/chart/getTranBar",function(data){

        console.log(data)
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '交易统计图表'
            },
            tooltip: {},
            legend: {
                data:['阶段']
            },
            xAxis: {
                data: data.names
            },
            yAxis: {},
            series: [{
                name: '阶段',
                type: 'bar',
                data: data.values
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);


    },'json');

</script>
</body>
</html>