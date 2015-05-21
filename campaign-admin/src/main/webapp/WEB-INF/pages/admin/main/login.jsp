<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>登录页面</title>
<link rel="icon"
	href="${pageContext.request.contextPath }/resources/images/logo/logo_title.png" />
<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
</script>
<meta name="keywords" content="Fenlon Xiong Personal Page" />
<meta name="description"
	content="Fenlon Xiong (fenlonxiong@gmail.com) Personal Page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/style/main/login/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/style/main/login/supersized.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/style/main/login/style.css">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

</head>

<body>
	<div class="page-container">
		<h1>登录</h1>
		<form action="${pageContext.request.contextPath }/login/login.htm"
			method="post">
			<input type="text" name="userName" class="username" placeholder="用户名">
			<input type="password" name="password" class="password"
				placeholder="密码"> <img
				src="${pageContext.request.contextPath}/authcode/getAuthImg.htm"
				alt="验证码" style="cursor: pointer; vertical-align: text-bottom;"
				onclick="this.src=this.src+'?'+Math.random();" /> <input
				type="text" name="authCode" class="password" placeholder="验证码">
			<button type="submit">提交</button>
			<div class="error">
				<span>+</span>
			</div>
		</form>
		<div class="connect">
			<p>Or connect with:</p>
			<p>
				<a class="facebook" href=""></a> <a class="twitter" href=""></a>
			</p>
		</div>
	</div>

	<!-- Javascript -->
	<script
		src="${pageContext.request.contextPath }/resources/common/jquery/jquery-2.1.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/admin/main/login/supersized.3.2.7.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/admin/main/login/supersized-init.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/admin/main/login/scripts.js"></script>
</body>

</html>


