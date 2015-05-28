angular
		.module('initModule', [])

		.controller(
				'initCtrl',
				[
						'$scope',
						'$http',
						'$templateCache',
						function($scope, $http, $templateCache) {
							$scope.ope = null;
							$scope.doInit = function() {
								if ($scope.ope == null
										|| $scope.ope == 'undefined') {
									return;
								}

								$http(
										{
											method : 'POST',
											url : ctx
													+ '/store_operator/mobile/init.json',
											data : $scope.ope,
											cache : $templateCache
										})
										.success(
												function(data, status) {
													if (!data.success) {
														alert(data.result);
														return;
													}

													window.location.href = ctx
															+ "/store_operator/mobile/redeem_ui.htm";
												}).error(
												function(data, status) {
													alert(status);
												});
							};
						} ]);