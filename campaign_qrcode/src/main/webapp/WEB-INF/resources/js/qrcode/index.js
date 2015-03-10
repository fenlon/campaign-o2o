var indexModule = angular.module("indexModule", []);

indexModule.controller("indexController", [ '$scope', '$http',
		function($scope, $http) {
			$scope.content = "";
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
				$scope.codeUrl = baseUrl + content;
			};
		} ]);