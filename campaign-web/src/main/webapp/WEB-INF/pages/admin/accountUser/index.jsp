<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理首页</title>

<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
</script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular-resource.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/ui/ui-bootstrap-page-0.13.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/ui/ui-bootstrap-page-tpls-0.13.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/admin/accountUser/index.js"></script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/ui/ui-bootstrap-tpls-0.13.0.js"></script> --%>
</head>
<body ng-app="app">
	<div class="container" ng-controller="AccountUserCtrl">
		<div>
			<nav>
				<table class="table  table-hover ">
					<thead>
						<tr>
							<th>序号</th>
							<th>ID</th>
							<th>name</th>
							<th>nickName</th>
							<th>password</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in page.content">
							<th scope="row">{{$index+1}}</th>
							<th scope="row">{{u.id}}</th>
							<td>{{u.name}}</td>
							<td>{{u.nickName}}</td>
							<td>{{u.password}}</td>
						</tr>
					</tbody>
				</table>
				<table>
					<pagination total-items="totalItems" ng-model="currentPage"
						max-size="maxSize" class="pagination-sm" items-per-page="pageSize"
						ng-change="pageChanged()" boundary-links="true"
						num-pages="page.totalPages" previous-text="前一页" next-text="后一页"
						first-text="首页" last-text="尾页"></pagination>
					<pre>Page: {{currentPage}} / {{page.totalPages}}</pre>
				</table>

			</nav>
		</div>

	</div>
</body>
</html>