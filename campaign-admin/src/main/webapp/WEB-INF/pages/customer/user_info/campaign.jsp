<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<style type="text/css">
.table tbody td {
	border-bottom: 1px dashed gray;
}
</style>
</head>
<a class="btn btn-primary"
	href="${pageContext.request.contextPath }/store_campaign/create.htm">发布新活动</a>
<button type="button" class="btn btn-primary">删除</button>
<div>
	<div>
		<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th><input type="checkbox" ng-model="selectAll">全选</th>
						<!-- <th>门店编号</th> -->
						<th>活动名称</th>
						<th>发放数</th>
						<th>兑换数</th>
						<th>状态</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>预计总量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="c in page.content" class="text-center">
						<th><input type="checkbox" ng-checked="selectAll"><label>&nbsp;&nbsp;{{(pageSize*(currentPage-1))+$index+1}}</label></th>
						<!-- <th scope="row"></th> -->
						<!-- <td>{{s.number}}</td> -->
						<td>{{c.name}}</td>
						<td>{{c.deliverNum}}</td>
						<td>{{c.redeemNum}}</td>
						<td>{{translateVal(c.status)}}</td>
						<td>{{c.startTime | date:'yyyy-MM-dd'}}</td>
						<td>{{c.endTime | date:'yyyy-MM-dd'}}</td>
						<td>{{c.quantity}}</td>
						<td class="text-center"><a href="javascript:void(0)"
							class="btn">详细</a> <a class="btn"
							ng-class="{'disabled':disable($index)}" href="javascript:void(0)"
							ng-click="cancle(c.encodeStr,$index)">取消</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="text-right">
			<pagination total-items="totalItems" ng-model="currentPage"
				max-size="maxSize" class="pagination-sm" items-per-page="pageSize"
				ng-change="pageChanged()" boundary-links="true"
				num-pages="page.totalPages" previous-text="前一页" next-text="后一页"
				first-text="首页" last-text="尾页"></pagination>
			<!-- <pre>Page: {{currentPage}} / {{page.totalPages}}</pre> -->
		</div>

	</div>
</div>