<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">
<title>领取红包</title>
<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
	var storeId = '${storeId	}';
	var campaignId = '${campaignId	}';
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
	src="${pageContext.request.contextPath }/resources/js/customer/store_campaign/mobile_detail.js"></script>
</head>
<body ng-app="mobileDetailModule">
	<div class="container-fluid text-center" ng-controller="detailCtrl">
		<div ng-show="!showQRCode">
			<div class="list-group">
				<a href="#" class="list-group-item active">
					<h4 class="list-group-item-heading">活动名称</h4>
					<p class="list-group-item-text">{{campaign.name}}</p>
				</a>
			</div>
			<div class="list-group">
				<a href="#" class="list-group-item active">
					<h4 class="list-group-item-heading">活动编号</h4>
					<p class="list-group-item-text">{{campaign.number}}</p>
				</a>
			</div>
			<div class="list-group">
				<a href="#" class="list-group-item active">
					<h4 class="list-group-item-heading">活动名称</h4>
					<p class="list-group-item-text">{{campaign.link}}</p>
				</a>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="headingTwo">
					<h4 class="panel-title" id="-collapsible-group-item-#2-">
						<a class="collapsed" data-toggle="collapse"
							data-parent="#accordion" href="#collapseTwo"
							aria-expanded="false" aria-controls="collapseTwo"><div>活动说明</div></a>
						<a class="anchorjs-link" href="#-collapsible-group-item-#2-"><span
							class="anchorjs-icon"></span></a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body">{{campaign.describtion}}</div>
				</div>
			</div>


			<div class="row clearfix">
				<div class="col-md-12 column">
					<form role="form" name="mobileForm">
						<div class="form-group">
							<input type="hidden" ng-model="data.storeId" value="${storeId }">
							<input type="hidden" ng-model="data.campaignId"
								value="${campaignId }"> <input type="text"
								ng-model="data.mobile" style="background-color: yellow;"
								class="form-control" placeholder="手机号" />
						</div>
						<button type="button" ng-click="getRed()"
							class="btn btn-primary btn-lg btn-block">领取红包</button>
					</form>
				</div>
			</div>
		</div>
		<div ng-show="showQRCode">
			<img ng-src="{{codeUrl}}" ng-show="showQRCode" alt="二维码">
		</div>
	</div>
</body>
</html>