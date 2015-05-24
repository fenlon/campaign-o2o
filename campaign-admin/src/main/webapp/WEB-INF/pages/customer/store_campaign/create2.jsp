<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>注册页面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/ui/ui-bootstrap-datepicker-0.13.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/ui/ui-bootstrap-datepicker-tpls-0.13.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/store_campaign/create.js"></script>
</head>
<div ng-app="createStoreCampaignModule" class="container">
	<form class="form-horizontal container" ng-controller="campaignCtrl"
		name="userForm">
		<div class="form-group"
			ng-class="{'has-error':userForm.name.$dirty && userForm.name.$invalid}">
			<label for="inputname3" class="col-sm-2 control-label">用户名</label>

			<div class="col-sm-8">
				<input type="text" autocomplete="off" name="name" ng-required="true"
					class="form-control" id="inputname3" ng-model="user.name"
					ng-pattern="/^[a-zA-Z]{1}/" ng-minlength="5" ng-maxlength="12"
					placeholder="用户名" />

				<div ng-show="userForm.name.$invalid && userForm.name.$dirty"
					class="alert alert-danger help-block">
					<p ng-show="userForm.name.$error.maxlength">用户名长度不能超过10位字符</p>
					<p ng-show="userForm.name.$error.required">用户名不能为空</p>
					<p ng-show="userForm.name.$error.minlength">用户名长度不能小于5位字符</p>
					<p ng-show="userForm.name.$error.pattern">用户名必须以英文字符开始</p>
				</div>
			</div>
		</div>

		<div class="form-group"
			ng-class="{'has-error':userForm.password.$dirty && userForm.password.$invalid}">
			<label for="inputPassword3" class="col-sm-2 control-label">密码</label>

			<div class="col-sm-8">
				<input type="password" autocomplete="off" name="password"
					ng-required="true" class="form-control" id="inputPassword3"
					ng-model="user.password" ng-minlength="5" ng-maxlength="12"
					placeholder="密码" />
				<div
					ng-show="userForm.password.$invalid && userForm.password.$dirty"
					class="alert alert-danger help-block">
					<p ng-show="userForm.password.$error.required">密码不能为空</p>
					<p ng-show="userForm.password.$error.maxlength">用户名长度不能超过10位字符</p>
					<p ng-show="userForm.password.$error.minlength">用户名长度不能小于5位字符</p>
				</div>
			</div>
		</div>

		<div class="form-group"
			ng-class="{'has-error':userForm.passwordConfirm.$dirty && userForm.passwordConfirm.$invalid}">
			<label for="inputPasswordConfirm3" class="col-sm-2 control-label">确认密码</label>

			<div class="col-sm-8">
				<input type="password" autocomplete="off" name="passwordConfirm"
					ng-required="true" class="form-control" id="inputPasswordConfirm3"
					ng-model="user.passwordConfirm" placeholder="确认密码" />

				<div
					ng-show="userForm.password.$dirty && userForm.passwordConfirm.$dirty && user.password !== user.passwordConfirm"
					class="alert alert-danger help-block">密码和确认密码不一致</div>
			</div>
		</div>
		<div class="form-group">
			<label for="inputAuthCode3" class="col-sm-2 control-label">验证码</label>
			<div class="col-sm-6">
				<input type="text" name="authCode" autocomplete="off"
					class="form-control" ng-model="user.authCode" ng-required="true"
					id="inputAuthCode3" placeholder="验证码">
				<div
					ng-show="userForm.authCode.$invalid && userForm.authCode.$dirty"
					class="alert alert-danger help-block">验证码不能为空</div>
			</div>
			<div class="col-sm-2">
				<img
					src="${pageContext.request.contextPath}/authcode/getAuthImg.htm"
					alt="验证码" style="cursor: pointer; vertical-align: text-bottom;"
					onclick="this.src=this.src+'?'+Math.random();" />
			</div>

		</div>

		<div class="form-group"
			ng-class="{'has-error':userForm.passwordConfirm.$dirty && userForm.passwordConfirm.$invalid}">
			<label for="inputPasswordConfirm3" class="col-sm-2 control-label">开始日期</label>

			<div class="col-sm-8">
				<p class="input-group">
					<input type="text" class="form-control"
						datepicker-popup="{{format}}" ng-model="dt" is-open="opened"
						min-date="minDate" max-date="'2015-06-22'"
						datepicker-options="dateOptions"
						date-disabled="disabled(date, mode)" ng-required="true"
						close-text="Close" /> <span class="input-group-btn">
						<button type="button" class="btn btn-default"
							ng-click="open($event)">
							<i class="glyphicon glyphicon-calendar"></i>
						</button>
					</span>
				</p>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8 text-center">
				<button type="button" ng-click="doRegister()"
					class="btn btn-default btn-lg btn-block"
					ng-disabled="userForm.$invalid">注册</button>
				<button type="reset" class="btn btn-default btn-lg btn-block">重置</button>
			</div>
		</div>
	</form>
</div>