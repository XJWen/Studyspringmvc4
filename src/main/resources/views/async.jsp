<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta content="text/html" http-equiv="Content-Type">
	<title>Servlet async support</title>
</head>
<body>
<script src="https://cdn.bootcss.com/jquery/3.3.0/jquery.js"></script>
<script type="text/javascript">
	deferred();

	function deferred() {
		$.get('defer',function (data) {
			console.log(data);
			deferred();
        });
    }
</script>
</body>
</html>