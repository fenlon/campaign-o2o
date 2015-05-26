angular.module('mobileDetailModule', [])

.controller(
		'detailCtrl',
		[ '$scope', '$http', '$templateCache',
				function($scope, $http, $templateCache) {
					$scope.mobile = null;
					$http({
						method : 'GET',
						url : ctx + '/store_campaign/mobile/' + campaignId,
						cache : $templateCache
					}).success(function(data, status) {
						if (data.success) {
							$scope.campaign = data.result;
							return;
						}
					}).error(function(data, status) {
						alert(status);
					});

					$scope.getRed = function() {
						if ($scope.mobile == "" || $scope.mobile == null) {
							alert("请输入手机号!");
							return;
						}
						$http({
							method : 'POST',
							url : ctx + '/store_campaign/mobile/join.htm',
							data : {
								storeId : storeId,
								campaignId : campaignId,
								mobile : $scope.mobile
							},
							cache : $templateCache
						}).success(function(data, status) {
							if (data.success) {
								console.log(data);
								// $scope.campaign = data.result;
								return;
							}
						}).error(function(data, status) {
							alert(status);
						});
					};

				} ]);