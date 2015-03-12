var indexModule = angular.module("indexModule", []);

indexModule.controller("indexController", [ '$scope', '$http',
		function($scope, $http) {
			$scope.qrcode = {
				content : "",
				errorLevel : 'H',
				size : 300,
				foregroundColor : 0xFF000000,
				foregroundColor : 0xFF000000,
				logoUrl : null
			};

			$scope.show = false;
			$scope.codeUrl = ctx + "/qrcode/get.html?content=''";
			var baseUrl = ctx + "/qrcode/get.html?content=";
			$scope.generateCode = function(content) {
				alert($scope.qrcode.errorLevel);
				return;

				if (content == "") {
					$scope.show = false;
					alert("请先输入要编码的内容!");
					return;
				}
				$scope.show = true;
				$scope.codeUrl = baseUrl + content;
			};
		} ]);