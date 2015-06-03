<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<button type="button" class="btn btn-primary" data-toggle="modal"
	data-target="#addStoreModel" data-whatever="@mdo">添加门店</button>
<button type="button" class="btn btn-primary">删除</button>
<div>
	<div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" ng-model="selectAll">全选</th>
					<!-- <th>门店编号</th> -->
					<th>门店名称</th>
					<th>授权码</th>
					<th>状态</th>
					<th>地址</th>
					<th>二维码</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="s in page.content">
					<th><input type="checkbox" ng-checked="selectAll"><label>&nbsp;&nbsp;{{(pageSize*(currentPage-1))+$index+1}}</label></th>
					<!-- <th scope="row"></th> -->
					<!-- <td>{{s.number}}</td> -->
					<td>{{s.name}}</td>
					<td>{{s.authCode}}</td>
					<td>{{s.active}}</td>
					<td>{{s.location.address}}</td>
					<td><a href="javascript:void(0)" ng-click="showQRCode($index)">查看</a></td>
					<td><a href="javascript:void(0)">详细</a> <a
						href="javascript:void(0)" ng-click="deleteStore(s.id)">删除</a></td>
				</tr>
			</tbody>
		</table>
		<div class="text-right">
			<pagination total-items="totalItems" ng-model="currentPage"
				max-size="maxSize" class="pagination-sm" items-per-page="pageSize"
				ng-change="pageChanged()" boundary-links="true"
				num-pages="page.totalPages" previous-text="前一页" next-text="后一页"
				first-text="首页" last-text="尾页"></pagination>
			<!-- <pre>Page: {{currentPage}} / {{page.totalPages}}</pre> -->
		</div>

	</div>

	<div class="modal fade" id="addStoreModel" tabindex="-1" role="dialog"
		aria-labelledby="addStoreModelLabel" aria-hidden="true"
		ng-controller="StoreController">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="addStoreModelLabel">添加门店</h4>
				</div>
				<div class="modal-body">
					<form name="storeForm">
						<div class="form-group">
							<label for="store-name" class="control-label">门店名称:</label>
							<div class="row clearfix">
								<div class="col-md-8 column">
									<input type="text" class="form-control" id="store-name"
										name="name" autocomplete="off" ng-required="true"
										ng-minlength="3" ng-maxlength="12" ng-model="store.name"
										placeholder="门店名称">
								</div>
								<div class="col-md-4 column">
									<div ng-show="storeForm.name.$invalid && storeForm.name.$dirty"
										class="alert-danger help-block text-center">
										<div ng-show="storeForm.name.$error.required">门店名称不能为空!</div>
										<div ng-show="storeForm.name.$error.minlength">门店名称最少为3位字符!</div>
										<div ng-show="storeForm.name.$error.maxlength">门店名称最长为12位字符!</div>
									</div>

									<div class="alert-warning help-block text-center"
										ng-show="!storeForm.name.$dirty">名称必须为3-12个字符</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="store-address" class="control-label">门店地址:</label>
							<div class="row clearfix">
								<div class="col-md-8 column">
									<input type="text" class="form-control" id="store-address"
										autocomplete="off" ng-required="true" ng-minlength="5"
										name="address" ng-maxlength="20"
										ng-model="store.location.address" placeholder="门店地址">
								</div>
								<div class="col-md-4 column">
									<div
										ng-show="storeForm.address.$invalid && storeForm.address.$dirty"
										class="alert-danger help-block text-center">
										<div ng-show="storeForm.address.$error.required">门店地址不能为空!</div>
										<div ng-show="storeForm.address.$error.minlength">门店地址最少为5位字符!</div>
										<div ng-show="storeForm.address.$error.maxlength">门店地址最长为20位字符!</div>
									</div>
									<div class="alert-warning help-block text-center"
										ng-show="!storeForm.address.$dirty">地址必须为5-20个字符</div>
								</div>
							</div>
						</div>

						<div ng-show="isWarnning" class="panel panel-danger">
							<div class="panel-heading">
								<h3 class="panel-title" id="panel-title">
									内容填写错误， 请按着提示填写内容!<a class="anchorjs-link" href="#panel-title"><span
										class="anchorjs-icon"></span></a>
								</h3>
							</div>
							<div class="panel-body">
								<div class="alert-warning help-block text-left">名称必须为3-12个字符</div>
								<div class="alert-warning help-block text-left">地址必须为5-20个字符</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						ng-disabled="storeForm.$invalid" ng-click="createStore()">确定</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="qrcodeModal" tabindex="-1" role="dialog"
		aria-labelledby="qrcodeModelLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="addStoreModelLabel">
						<strong>{{store.name}}</strong>
					</h4>
				</div>
				<div class="modal-body text-center">
					<img src="{{qrcodeUrl}}" alt="二维码" class="img-rounded">
				</div>
			</div>
		</div>
	</div>
</div>