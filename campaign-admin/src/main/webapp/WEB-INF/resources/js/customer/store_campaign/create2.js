/**
 * this is register.js
 */
angular.module('createStoreCampaignModule', [ 'ui.bootstrap' ])

.controller(
		'campaignCtrl',
		[
				'$scope',
				'$http',
				'$templateCache',
				function($scope, $http, $templateCache) {

					$scope.dateOptions = {
						formatYear : 'yy',
						startingDay : 1
					};

					$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd',
							'dd.MM.yyyy', 'shortDate' ];
					$scope.format = $scope.formats[0];

					$scope.doRegister = function() {
						$http({
							method : 'POST',
							url : ctx + '/register/register.json',
							data : $scope.user,
							cache : $templateCache
						}).success(
								function(data, status) {
									if (data.success) {
										window.location.href = ctx
												+ "/login/index.htm";
									}
								}).error(function(data, status) {
							// alert(data);
						});

					};
				} ]);