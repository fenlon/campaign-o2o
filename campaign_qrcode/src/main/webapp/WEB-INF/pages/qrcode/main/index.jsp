<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QRCode 首页</title>

<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
	var extjsPath = "http://222.22.91.35:8080/ext-4.2.1.883/";
</script>

<link
	href="${pageContext.request.contextPath }/resources/style/common/bootstrap3/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/angularjs/angular.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/qrcode/index.js"></script>
<style type="text/css">
.row textarea {
	outline: 0;
	resize: none;
	border: 1px solid gray;
	font-size: 16px;
	border-radius: 3px;
	padding: 10px;
	font-family: "Microsoft YaHei";
	overflow: hidden;
	font-size: 16px;
	height: 200px;
}

.code {
	width: 100%;
	height: 200px;
	text-align: center;
	border: 1px solid gray;
}

.row {
	margin-top: 20px;
}

.hide {
	display: none;
}
</style>
</head>
<body ng-app="indexModule">
	<div class="container" ng-controller="indexController">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">首页</a>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
						<li><a href="#">Link</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">Link</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="jumbotron">
			<h1>欢迎来到Fenlon二维码平台</h1>
			<p>test</p>
			<div class="row">
				<div class="col-xs-8">
					<textarea class="form-control" placeholder="请输入文字内容，支持普通文本和网址！"
						ng-model="content">
					</textarea>
				</div>
				<div class="col-xs-4">
					<div class="code">
						<img ng-show="show" alt="qrcode" width="200px" height="200px"
							ng-srcset="{{codeUrl}}"> <span ng-hide="show">左侧输入内容，点击生成二维码</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-8">
					<button type="button" class="btn btn-primary btn-lg btn-block"
						ng-click="generateCode(content)">生成二维码</button>
					<button type="button" class="btn btn-primary btn-lg btn-block">下载二维码</button>
					<button type="button" class="btn btn-primary btn-lg btn-block">其他</button>
				</div>
				<div class="col-xs-4"></div>
			</div>
		</div>
	</div>
</body>
</html>