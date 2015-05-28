angular
		.module('opeLoginModule', [])

		.controller(
				'loginCtrl',
				[
						'$scope',
						'$http',
						'$templateCache',
						function($scope, $http, $templateCache) {
							$scope.ope = null;
							$scope.doLogin = function() {
								if ($scope.ope == null
										|| $scope.ope == 'undefined') {
									return;
								}

								$http(
										{
											method : 'POST',
											url : ctx
													+ '/store_operator/mobile/login.json',
											data : $scope.ope,
											cache : $templateCache
										})
										.success(
												function(data, status) {
													if (!data.success) {
														alert(data.message);
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