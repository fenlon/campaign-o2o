/**
 * this is register.js
 */
var registerModule = angular.module('registerModule', [])

.controller(
		'registerCtrl',
		[
				'$scope',
				'$http',
				'$templateCache',
				function($scope, $http, $templateCache) {
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