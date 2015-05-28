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
<title>兑换页面</title>
<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
	var code = '${code	}';
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap-theme.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/store_operator/redeem_ui.js"></script>
</head>
<body ng-app="redeemModule">
	<div class="container-fluid text-center" ng-controller="redeemCtrl">
		<div ng-show="!showQRCode">
			<div class="list-group">
				<a href="#" class="list-group-item active">
					<h4 class="list-group-item-heading">操作员手机号</h4>
					<p class="list-group-item-text">${mobile}</p>
				</a>
			</div>
			<div class="list-group">
				<a href="#" class="list-group-item active">
					<h4 class="list-group-item-heading">活动名称</h4>
					<p class="list-group-item-text">${campaign.name }</p>
				</a>
			</div>
			<div class="list-group">
				<a href="#" class="list-group-item active">
					<h4 class="list-group-item-heading">兑换码</h4>
					<p class="list-group-item-text">${code}</p>
				</a>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingTwo">
					<h4 class="panel-title" id="-collapsible-group-item-#2-">
						<a class="collapsed" data-toggle="collapse"
							data-parent="#accordion" href="#collapseTwo" aria-expanded="true"
							aria-controls="collapseTwo"><div>活动说明</div></a> <a
							class="anchorjs-link" href="#-collapsible-group-item-#2-"><span
							class="anchorjs-icon"></span></a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">${campaign.describtion }</div>
				</div>
			</div>

			<div class="list-group">
				<button type="button" class="btn btn-primary btn-lg btn-block">扫一扫</button>
			</div>

			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-6 column">
							<button type="button" class="btn btn-primary btn-lg btn-block"
								ng-click="redeem()">确定兑换</button>
						</div>
						<div class="col-md-6 column">
							<button type="button" class="btn btn-primary btn-lg btn-block"
								ng-click="showRecord()">兑换记录</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>