<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding= "UTF-8" %>
<! DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>服务器推送技术</title>
</head>
<body>
<h1>服务器推送技术演示</h1>

<ul>
    <li><h2><a href="time" target="_blank">查看服务器时间</a></h2></li>
    <li><h2><a href="pushnews" target="_blank">Servlet异步-推送实时新闻</a></h2></li>
    <li><h2><a href="nobleMetalr" target="_blank">SSE-贵金属期货价格实时查询</a></h2></li>
    <li><h2><a href="first" target="_blank">高频服务（100/S）</a></h2></li>
    <li><h2><a href="second" target="_blank">低频服务（10/天）</a></h2></li>
    <li><h2><a href="#" onclick="trig()">模拟高并发的情况下触发死锁</a></h2></li>
</ul>
<script type="text/javascript" src="assets/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

    function trig(){
        $.get("first",function (data) {
            alert(data);
        });
        $.get("second",function (data) {
            alert(data);
        });
        $.get("third",function (data) {
            alert(data);
        });
    }

</script>
</body>
</html>