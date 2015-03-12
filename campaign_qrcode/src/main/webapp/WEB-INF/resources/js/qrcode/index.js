var indexModule = angular.module("indexModule", []);

indexModule.controller("indexController", ['$scope', '$http',
				function($scope, $http) {
					$scope.qrcode = {
						content : "",
						errorLevel : 'H',
						size : 300,
						foregroundColor : "FF6600",
						backgroundColor : "FFFFCC",
						logoUrl : null
					};
					$scope.show = false;
					$scope.codeUrl = ctx + "/qrcode/get.html?content=''";
					var baseUrl = ctx + "/qrcode/get.html?content=";
					$scope.generateCode = function(content) {
						if (content == "") {
							$scope.show = false;
							alert("请先输入要编码的内容!");
							return;
						}

						$scope.show = true;
						$scope.codeUrl = encodeURI(baseUrl + content
								+ "&errorLevel=" + $scope.qrcode.errorLevel
								+ "&size=" + $scope.qrcode.size
								+ "&foregroundColor="
								+ parseInt($scope.qrcode.foregroundColor, 16)
								+ "&backgroundColor="
								+ parseInt($scope.qrcode.backgroundColor, 16)
								+ "&logoUrl=" + $scope.qrcode.logoUrl);
					};
				}]);