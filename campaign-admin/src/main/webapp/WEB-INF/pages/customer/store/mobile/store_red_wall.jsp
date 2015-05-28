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
<title>门店红包墙-fenlonxiong@gmail.com</title>
<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
	var storeId = '${storeId	}';
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap-theme.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/store/mobile/store_red_wall.js"></script>
</head>
<body ng-app="storeDetailModule">
	<div class="container-fluid" ng-controller="StoreDetailCtrl">
		<div class="panel panel-danger" ng-show="!data.store.enable">
			<div class="panel-heading">
				<h3 class="panel-title" id="panel-title">
					系统提醒<a class="anchorjs-link" href="#panel-title"><span
						class="anchorjs-icon"></span></a>
				</h3>
			</div>
			<div class="panel-body text-center">本店被禁用</div>
		</div>

		<div>
			<div class="panel panel-primary" ng-repeat="c in data.campaigns">
				<div class="panel-heading">{{c.name}}</div>
				<a
					href='${pageContext.request.contextPath }/store_campaign/mobile/detail/{{data.store.encodeStr}}/{{c.encodeStr}}'>
					<div class="row">
						<div class="col-xs-6">
							<div class="content-money" style="text-align: center">
								<span class="glyphicon glyphicon-yen" aria-hidden="true"
									style="font-size: 50px;"></span> <span
									style="font-size: 100px;">{{c.price }}</span>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="list-group">
								<div class="list-group-item">活动名称: {{c.name }}</div>
								<div class="list-group-item">活动有效期至:{{c.endTime}}</div>
								<div class="list-group-item">
									活动官方链接: <a href="http://{{c.link} }">{{c.link }}</a>
								</div>
								<div class="list-group-item">{{c.describtion}}</div>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
</body>
</html>
