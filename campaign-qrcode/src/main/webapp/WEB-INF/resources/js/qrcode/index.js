var indexModule = angular.module("indexModule", []);

indexModule.controller("indexController", [
		'$scope',
		'$http',
		function($scope, $http) {

			$scope.show = false;
			$scope.fore = "FF6600";
			$scope.back = "FFFFCC";
			$scope.codeUrl = ctx + "/qrcode/get.html?content=''";

			$scope.qrcode = {
				content : "finish",
				errorLevel : 'H',
				size : 300,
				foregroundColor : 0,
				backgroundColor : 0,
				logoUrl : null
			};

			var baseUrl = ctx + "/qrcode/get.html?content=";
			$scope.generateCode = function(content) {
				if (content == "") {
					$scope.show = false;
					alert("请先输入要编码的内容!");
					return;
				}

				$scope.qrcode.foregroundColor = parseInt($scope.fore, 16);
				$scope.qrcode.backgroundColor = parseInt($scope.back, 16);

				$scope.show = true;
				$scope.codeUrl = encodeURI(baseUrl + content + "&errorLevel="
						+ $scope.qrcode.errorLevel + "&size="
						+ $scope.qrcode.size + "&foregroundColor="
						+ $scope.qrcode.foregroundColor + "&backgroundColor="
						+ $scope.qrcode.backgroundColor);
				if ($scope.qrcode.logoUrl != null) {
					$scope.codeUrl = $scope.codeUrl + "&logoUrl="
							+ $scope.qrcode.logoUrl;
				}
			};

			$scope.downloadCode = function() {
				$scope.generateCode($scope.qrcode.content);
				return $scope.codeUrl;
				var url = ctx + "/qrcode/d_load.html";
				// $scope.qrcode.errorLevel = 2;
				$http.get(url, {
					params : $scope.qrcode
				}).success(function(data, status, headers, config) {
					// 加载成功之后做一些事
				}).error(function(data, status, headers, config) {
					// 处理错误
				});
			}

		} ]);