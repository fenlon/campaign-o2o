<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<style type="text/css">
div {
	/* border: 1px solid gray; */
	
}

.base_info {
	padding: 10px;
}

.safe_level {
	color: green;
}
</style>
</head>
<div class="base_info">
	<div>
		<div class="panel panel-primary">

			<div class="panel-heading">
				<strong>基本信息</strong>
			</div>
			<div class="panel-body">
				<div class="userinfo">
					<table class="table table-hover table-bordered">
						<tbody>
							<tr>
								<td rowspan="5" class="text-center">
									<div class="center-block">
										<img alt="头像"
											src="${pageContext.request.contextPath }/resources/images/user_info/user_m.png">
									</div>
									<div>
										<a href="javascript:void(0)">修改头像</a>
									</div>
								</td>
								<td><strong>登录帐号:</strong></td>
								<td><strong>{{user.name}}</strong></td>
							</tr>
							<tr>
								<td><strong>帐号ID:</strong></td>
								<td><strong>{{user.id}}</strong></td>
							</tr>
							<tr>
								<td><strong>美贝余额:</strong></td>
								<td><strong>{{user.mbay}}</strong></td>
							</tr>
							<tr>
								<td><strong>绑定手机号:</strong></td>
								<td><strong>{{user.mobile}}</strong></td>
							</tr>
							<tr>
								<td><strong>注册时间:</strong></td>
								<td><strong>{{user.dateCreated | date:'yyyy-MM-dd
										HH:mm:ss'}}</strong></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>



		<div class="panel panel-primary">
			<div class="panel-heading">账户安全</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-2" id="myScrollspy">
						<div>
							<div>
								<strong>安全程度</strong>
							</div>
						</div>
					</div>
					<div class="col-xs-8" id="myScrollspy">
						<div class="progress">
							<div
								class="progress-bar progress-bar-warning progress-bar-striped"
								role="progressbar" aria-valuenow="60" aria-valuemin="0"
								aria-valuemax="100" style="width: 60%">
								<span class="sr-only">60% Complete (warning)</span>
							</div>
						</div>
					</div>
					<div class="col-xs-2">
						<div class="text-right">
							<div>
								<Strong>级别：<span class="safe_level">中</span></Strong>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2" id="myScrollspy">
						<div>
							<div>
								<strong>登录密码</strong>
							</div>
						</div>
					</div>
					<div class="col-xs-8" id="myScrollspy">
						<div>安全性高的密码可以使帐号更安全。建议您定期更换密码，设置一个包含字母，符号或数字中至少两项且长度超过6位的密码。</div>
					</div>
					<div class="col-xs-2 text-right">
						<div class="text-right">
							<div>
								<span class="finished">已设置</span> <a href="javascript:void(0)">修改</a>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2" id="myScrollspy">
						<div>
							<div>
								<strong>手机绑定</strong>
							</div>
						</div>
					</div>
					<div class="col-xs-8" id="myScrollspy">
						<div>您已绑定了手机186****2672。[您的手机为安全手机，可以找回密码，但不能用于登录]</div>
					</div>
					<div class="col-xs-2 text-right">
						<div class="text-right">
							<div>
								<span class="finished">已设置</span> <a href="javascript:void(0)">修改</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
