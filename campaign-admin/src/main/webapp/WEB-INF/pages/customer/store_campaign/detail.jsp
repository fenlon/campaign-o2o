
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<button type="button" class="btn btn-primary" data-toggle="modal"
	data-target="#addStoreModel" data-whatever="@mdo">添加门店</button>
<button type="button" class="btn btn-primary">删除</button>

<div>
	<div ng-controller="detailCtrl">
		this is detail page{{aa}}
		<!-- <table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" ng-model="selectAll">全选</th>
					<th>门店编号</th>
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
					<th scope="row"></th>
					<td>{{s.number}}</td>
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
			<pre>Page: {{currentPage}} / {{page.totalPages}}</pre>
		</div> -->
	</div>
</div>
