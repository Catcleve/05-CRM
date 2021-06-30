<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="/crm/jquery/echarts.min.js"></script>
    <script type="text/javascript" src="/crm/jquery/jquery-1.11.1-min.js"></script>
</head>
<body>
<div id="main" style="width: 800px;height:600px;margin:150px auto"></div>
<script>
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '40',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    {value: 1048, name: '搜索引擎'},
                    {value: 735, name: '直接访问'},
                    {value: 580, name: '邮件营销'},
                    {value: 484, name: '联盟广告'},
                    {value: 300, name: '视频广告'}
                ]
            }
        ]
    };

    option && myChart.setOption(option);

</script>
</body>
</html>