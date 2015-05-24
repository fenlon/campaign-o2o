<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>QRCode 首页</title>

<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
	//var extjsPath = "http://222.22.91.35:8080/ext-4.2.1.883/";
</script>

<link
	href="${pageContext.request.contextPath }/resources/style/common/bootstrap3/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/angularjs/angular.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/qrcode/index.js"></script>
<style type="text/css">
/* body {
	font-family: "helvetica neue", helvetica, arial, "Hiragino Sans GB",
		"Hiragino Sans GB W3", "Microsoft Yahei", "SimHei", Sans-serif;
} */

/* table {
	border-color: gray;
	border-collapse: collapse;
	border-spacing: 0;
} */
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

.progress-bar {
	background: gray;
	height: 10px;
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
						ng-model="qrcode.content">
					</textarea>
				</div>
				<div class="col-xs-4">
					<div class="code">
						<img ng-show="show" alt="qrcode" width="200px" height="200px"
							ng-src="{{codeUrl}}"> <span ng-hide="show">左侧输入内容，点击生成二维码</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-8">
					<button type="button" class="btn btn-primary btn-lg btn-block"
						ng-click="generateCode(qrcode.content)">生成二维码</button>
					<button type="button" class="btn btn-primary btn-lg btn-block"
						ng-click="downloadCode()">下载二维码</button>
					<button type="button" class="btn btn-primary btn-lg btn-block">其他</button>
				</div>
				<div class="col-xs-4">
					<div style="border: 1px solid red;">
						<table class="table">
							<tr>
								<td>容错:</td>
								<td id="level"><label style="background-position: 0px 2px;"><input
										type="radio" name="errorLevel" value="L"
										ng-model="qrcode.errorLevel" hidefocus="">7%</label><label
									style="background-position: 0px 2px;"><input
										type="radio" name="errorLevel" value="M"
										ng-model="qrcode.errorLevel" hidefocus="">15%</label><label
									style="background-position: 0px 2px;"><input
										type="radio" name="errorLevel" value="Q"
										ng-model="qrcode.errorLevel" hidefocus="">25%</label><label
									class="first" style="background-position: 0px -26px;"><input
										type="radio" name="errorLevel" value="H"
										ng-model="qrcode.errorLevel" hidefocus="">30%</label></td>
							</tr>
							<tr>
								<td>图案大小</td>
								<td><input type="text" name="size" placeholder="size"
									ng-model="qrcode.size"><span>{{qrcode.size}}px</span></td>
							</tr>
							<tr>
								<td>前景色</td>
								<td><input type="text" name="foregroundColor"
									placeholder="{{fore}}" ng-model="fore"><span>{{fore}}</span></td>
							</tr>
							<tr>
								<td>背景色</td>
								<td><input type="text" name="backgroundColor"
									placeholder="{{back}}" ng-model="back"><span>{{back}}</span></td>
							</tr>
							<tr>
								<td>logo路径</td>
								<td><input type="text" name="logoUrl"
									placeholder="{{qrcode.logoUrl}}" ng-model="qrcode.logoUrl"><span>
										<!-- {{qrcode.logoUrl}} -->
								</span></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>