<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>创建活动</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/ui/ui-bootstrap-datepicker-0.13.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/ui/ui-bootstrap-datepicker-tpls-0.13.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/store_campaign/create.js"></script>
</head>

<div ng-app="customer.storeCampaignCreate">
	<form class="form-horizontal container"
		ng-controller="createStoreCampaignCtrl" name="campaignForm">
		<div class="form-group"
			ng-class="{'has-error':campaignForm.name.$dirty && campaignForm.name.$invalid}">
			<label for="inputname3" class="col-sm-2 control-label">活动名称</label>

			<div class="col-sm-8">
				<input type="text" autocomplete="off" name="name" ng-required="true"
					class="form-control" id="inputname3" ng-model="campaign.name"
					ng-minlength="4" ng-maxlength="20" placeholder="活动名称" />

				<div
					ng-show="campaignForm.name.$invalid && campaignForm.name.$dirty"
					class="alert alert-danger help-block">
					<p ng-show="campaignForm.name.$error.maxlength">活动名长度不能超过20位字符</p>
					<p ng-show="campaignForm.name.$error.required">活动名不能为空</p>
					<p ng-show="campaignForm.name.$error.minlength">活动名长度不能小于4位字符</p>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="startDateInput3" class="col-sm-2 control-label">活动日期</label>

			<div class="col-md-4">
				<p class="input-group">
					<input type="date" id="startDateInput3" class="form-control"
						datepicker-popup ng-model="campaign.startTime"
						is-open="startOpened" min-date="minDate" show-weeks="true"
						datepicker-options="dateOptions" ng-required="true"
						close-text="Close" /> <span class="input-group-btn">
						<button type="button" class="btn btn-default"
							ng-click="open($event,0)">
							<i class="glyphicon glyphicon-calendar"></i>
						</button>
					</span>
				</p>
			</div>
			<div class="col-md-4">
				<p class="input-group">
					<input type="date" class="form-control" datepicker-popup
						ng-model="campaign.endTime" is-open="endOpened" min-date="minDate"
						show-weeks="true" datepicker-options="dateOptions"
						ng-required="true" close-text="Close" /> <span
						class="input-group-btn">
						<button type="button" class="btn btn-default"
							ng-click="open($event,1)">
							<i class="glyphicon glyphicon-calendar"></i>
						</button>
					</span>
				</p>
			</div>
		</div>

		<div class="form-group"
			ng-class="{'has-error':campaignForm.quantity.$dirty && campaignForm.quantity.$invalid}">
			<label for="inputQuantity3" class="col-sm-2 control-label">预计数量</label>

			<div class="col-sm-8">
				<input type="number" min="10" required="required" autocomplete="off"
					name="quantity" ng-required="true" class="form-control"
					id="inputQuantity3" ng-model="campaign.quantity"
					ng-pattern="/^[1-9]\d*$/" placeholder="预计数量" />
				<div
					ng-show="campaignForm.quantity.$invalid && campaignForm.quantity.$dirty"
					class="alert alert-danger help-block">
					<p ng-show="campaignForm.quantity.$error.required">预计数量不能为空</p>
					<p
						ng-show="campaignForm.quantity.$error.number || campaignForm.quantity.$error.pattern || campaignForm.quantity.$error.min">输入值必须为大于10的正整数</p>
				</div>
			</div>
		</div>

		<div class="form-group"
			ng-class="{'has-error':campaignForm.price.$dirty && campaignForm.price.$invalid}">
			<label for="inputPrice3" class="col-sm-2 control-label">红包价值</label>

			<div class="col-sm-8">
				<input type="number" min="5" required="required" autocomplete="off"
					name="price" ng-required="true" class="form-control"
					ng-pattern="/^[1-9]\d*$/" id="inputPrice3"
					ng-model="campaign.price" placeholder="红包价值" />
				<div class="alert alert-danger help-block"
					ng-show="campaignForm.price.$invalid && campaignForm.price.$dirty">
					<p ng-show="campaignForm.price.$error.required">红包价值不能为空</p>
					<p
						ng-show="campaignForm.price.$error.number || campaignForm.price.$error.pattern || campaignForm.price.$error.min">输入值必须为大于5的正整数</p>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">是否重复领取</label>

			<div class="col-sm-8">
				<div>
					<div class="col-sm-4">
						<input type="radio" id="norepeateInput3" checked="checked"
							ng-model="campaign.repeatGet" value="false" name="repeatGet" /><label
							for="norepeateInput3">不可重复领取</label>
					</div>
					<div class="col-sm-4">
						<input type="radio" id="repeateInput3"
							ng-model="campaign.repeatGet" value="true" name="repeatGet" /><label
							for="repeateInput3">可以重复领取</label>
					</div>
				</div>
			</div>
		</div>

		<div class="form-group"
			ng-class="{'has-error':campaignForm.link.$dirty && campaignForm.link.$invalid}">
			<label for="inputLink3" class="col-sm-2 control-label">活动链接</label>

			<div class="col-sm-8">
				<input type="url" min="5" required="required" autocomplete="off"
					name="link" ng-required="false" class="form-control"
					id="inputLink3" ng-model="campaign.link" placeholder="活动链接:http://" />
				<div
					ng-show="campaignForm.link.$invalid && campaignForm.link.$dirty"
					class="alert alert-danger help-block">
					<p ng-show="campaignForm.link.$error.url">链接格式不正确</p>
				</div>
			</div>
		</div>

		<div class="form-group"
			ng-class="{'has-error':campaignForm.describtion.$dirty && campaignForm.describtion.$invalid}">
			<label for="inputDescribtion3" class="col-sm-2 control-label">活动说明</label>

			<div class="col-sm-8">
				<div>
					<textarea class="col-sm-12" id="inputDescribtion3" rows="3"
						ng-model="campaign.describtion" name="describtion"
						ng-maxlength="250" placeholder="活动说明">活动说明</textarea>
				</div>
				<div
					ng-show="campaignForm.describtion.$invalid && campaignForm.describtion.$dirty"
					class="alert alert-danger help-block">
					<p ng-show="campaignForm.describtion.$error.maxlength">描述不能超过250位字符</p>
				</div>
			</div>
		</div>


		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8 text-center">
				<button type="button" ng-click="doCreate()"
					class="btn btn-primary btn-lg btn-block"
					ng-disabled="campaignForm.$invalid">注册</button>
				<button type="reset" class="btn btn-primary btn-lg btn-block">重置</button>
			</div>
		</div>
	</form>
</div>