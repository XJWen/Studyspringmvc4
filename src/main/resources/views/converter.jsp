<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="text/html" http-equiv="Content-Type">
	<title>HttpMessageConverter Demo</title>
</head>
<body>
	<div id="resp"></div>
	<input type="button" onclick="req()" value="请求"/>


	<script src="https://cdn.bootcss.com/jquery/3.3.0/jquery.js"></script>
	<script >
        function req() {
            $.ajax({
                url :  "converter",
                data : "1-wenxingjie",
                type : "POST",
                contentType : "application/x-wisely"
                success:function (data) {
                    $("#resp").html(data);
                }
            });
        }
	</script>
</body>
</html>