<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon"
	href="${pageContext.request.contextPath }/resources/images/logo/logo_title.png" />

<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath}';
</script>

<title>登录页面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap-theme.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/store_operator/login.js"></script>
</head>
<body ng-app="opeLoginModule">
	<div class="container-fluid">
		<div class="alert alert-danger text-center" role="alert">
			<strong>登录</strong>
		</div>
		<div class="alert alert-warning" role="alert">
			<form class="form-horizontal" ng-controller="loginCtrl"
				name="opeForm">
				<div class="form-group"
					ng-class="{'has-error':opeForm.authCode.$dirty && opeForm.authCode.$invalid}">
					<div class="col-sm-12">
						<input type="text" autocomplete="off" name="authCode"
							ng-required="true" class="form-control" ng-model="ope.authCode"
							ng-pattern="/^[0-9A-Z]{8}$/" placeholder="授权码" />
						<div
							ng-show="opeForm.authCode.$invalid && opeForm.authCode.$dirty"
							class="alert alert-danger help-block">
							<p ng-show="opeForm.authCode.$error.pattern">授权码为8位字符</p>
							<p ng-show="opeForm.authCode.$error.required">授权码不能为空</p>
						</div>
					</div>
				</div>

				<div class="form-group"
					ng-class="{'has-error':opeForm.mobile.$dirty && opeForm.mobile.$invalid}">
					<div class="col-sm-12">
						<input type="mobile" autocomplete="off" name="mobile"
							ng-required="true" class="form-control" ng-model="ope.mobile"
							placeholder="手机号" ng-pattern="/^[1]{1}[0-9]{10}$/" />
						<div ng-show="opeForm.mobile.$invalid && opeForm.mobile.$dirty"
							class="alert alert-danger help-block">
							<p ng-show="opeForm.mobile.$error.required">手机号不能为空</p>
							<p ng-show="opeForm.mobile.$error.pattern">手机号格式不正确</p>
						</div>
					</div>
				</div>

				<div class="form-group"
					ng-class="{'has-error':opeForm.password.$dirty && opeForm.password.$invalid}">
					<div class="col-sm-12">
						<input type="password" autocomplete="off" name="password"
							ng-required="true" class="form-control" ng-model="ope.password"
							placeholder="密码" />

						<div
							ng-show="opeForm.password.$dirty && opeForm.password.$invalid"
							class="alert alert-danger help-block">
							<p ng-show="opeForm.password.$error.required">密码不能为空</p>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-12 text-center">
						<button type="submit" class="btn btn-primary btn-lg btn-block"
							ng-click="doLogin()" ng-disabled="opeForm.$invalid">登录</button>
						<a
							href="${pageContext.request.contextPath }/store_operator/mobile/init_ui.htm"
							class="btn btn-primary btn-lg btn-block">初始化</a>
					</div>
				</div>
			</form>
		</div>

	</div>
</body>
</html>