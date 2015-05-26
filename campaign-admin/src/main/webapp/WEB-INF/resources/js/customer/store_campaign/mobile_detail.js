angular
		.module('mobileDetailModule', [])

		.controller(
				'detailCtrl',
				[
						'$scope',
						'$http',
						'$templateCache',
						function($scope, $http, $templateCache) {

							// $scope.showQRCode = false;
							$scope.srcUrl = "http://192.168.1.207:8080"
									+ ctx
									+ "/store_operator/mobile/redeem_ui.htm?storeId="
									+ storeId + "&code=";
							$scope.codeUrl = 'http://120.25.230.36:8080/campaign_qrcode/'
									+ "/qrcode/get.html?content=";

							$scope.getImg = function() {
								if ($scope.showQRCode == false) {
									return "null";
								}
							}

							$scope.data = new Object();
							$scope.data.storeId = storeId;
							$scope.data.campaignId = campaignId;
							if (campaignId != null) {
								$http(
										{
											method : 'GET',
											url : ctx
													+ '/store_campaign/mobile/'
													+ campaignId,
											cache : $templateCache
										}).success(function(data, status) {
									if (data.success) {
										$scope.campaign = data.result;
										return;
									}
								}).error(function(data, status) {
									alert(status);
								});
							}

							$scope.getRed = function() {
								if ($scope.data.mobile == ""
										|| $scope.data.mobile == null) {
									alert("请输入手机号!");
									return;
								}

								$http(
										{
											method : 'POST',
											url : ctx
													+ '/store_campaign/mobile/join.json',
											data : $scope.data,
											cache : $templateCache
										}).success(
										function(data, status) {
											if (data.success) {
												$scope.codeUrl = $scope.codeUrl
														+ $scope.srcUrl
														+ data.result[1];
												alert($scope.codeUrl);
												$scope.showQRCode = true;
												return;
											}
											alert(data.message);
										}).error(function(data, status) {
									alert(status);
								});
							};

						} ]);