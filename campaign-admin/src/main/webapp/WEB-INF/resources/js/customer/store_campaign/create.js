angular.module('customer.storeCampaignCreate', [ 'ui.bootstrap' ])

.controller(
		'createStoreCampaignCtrl',
		[
				'$scope',
				'$http',
				'$templateCache',
				function($scope, $http, $templateCache) {

					$scope.campaign = new Object();
					/**
					 * 注册
					 */
					$scope.doCreate = function() {
						// console.log($scope.campaign);
						// $scope.campaign.startTime = new Date();
						// $scope.campaign.startTime;
						// var b = new Date().toLocaleDateString();
						/*
						 * if ($scope.repeatGet == 'false') { $scope.repeatGet =
						 * false; } else { $scope.repeatGet = true; }
						 */

						$http({
							method : 'POST',
							url : ctx + '/store_campaign/campaign.json',
							data : $scope.campaign,
							cache : $templateCache
						}).success(
								function(data, status) {
									if (data.success) {
										window.location.href = ctx
												+ '/user_info/#/campaign';
									}
								}).error(function(data, status) {
							alert(data.message);
						});
					};

					/**
					 * 今天
					 */
					$scope.today = function() {
						$scope.campaign.startTime = new Date();
						$scope.campaign.endTime = new Date();
					};
					$scope.today();

					$scope.check = function() {
						alert($scope.campaign.startTime + "-"
								+ $scope.campaign.endTime);
					};

					/**
					 * 打开日期面板
					 */
					$scope.startOpened = false;// 开始
					$scope.endOpened = false;// 结束
					$scope.open = function($event, flag) {
						$event.preventDefault();
						$event.stopPropagation();
						if (flag == 0) {
							$scope.startOpened = true;
							return;
						}
						if (flag == 1) {
							$scope.endOpened = true;
							return;
						}
					};

					$scope.toggleMin = function() {
						$scope.minDate = $scope.minDate ? null : new Date();
					};
					$scope.toggleMin();
					/**
					 * 配置信息
					 */
					$scope.dateOptions = {
						formatYear : 'yy',
						startingDay : 1
					};

					$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd',
							'dd.MM.yyyy', 'shortDate' ];
					$scope.format = $scope.formats[0];
				} ]);