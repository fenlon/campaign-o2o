angular.module('userModule', [ 'ngRoute', 'ngResource', 'ui.bootstrap' ])

.factory('UserInfo', function($http, $templateCache) {
	var service = { // our factory definition
		get : function(id, callback) {
			$http({
				method : 'GET',
				url : 'info/' + id + '.json',
				cache : $templateCache
			}).success(callback).error(callback);
		}
	};
	return service;
})

.factory('Store', [ '$resource', function($resource) {
	var storeUrl = ctx + '/store/';
	var url = storeUrl + '/:id.:format';
	return $resource(url, {
		id : '@id',
		format : 'json'
	}, {
		'get' : {
			method : 'GET'
		},
		'save' : {
			url : storeUrl + '/data.json',
			method : 'POST'
		},
		'update' : {
			method : "PUT"
		},
		'query' : {
			url : storeUrl + "queryByPage.json",
			method : 'GET'
		},
		'remove' : {
			method : 'DELETE'
		},
		'delete' : {
			method : 'DELETE'
		}
	});
} ])

.controller(
		'BaseInfoController',
		[
				'$scope',
				'$route',
				'$routeParams',
				'$location',
				'$http',
				'$templateCache',
				'UserInfo',
				function($scope, $route, $routeParams, $location, $http,
						$templateCache, UserInfo) {
					$scope.user = null;
					UserInfo.get($routeParams.id, function(data, status) {
						if (status == 200) {
							if (data.mobile == null) {
								data.mobile = '暂未绑定手机号';
							}
							$scope.user = data;
							return;
						} else {
							console.log(status);
							return;
						}
					});
				} ])

.controller('MessageController', function($scope, $routeParams) {
	$scope.name = "BookController";
	$scope.params = $routeParams;
})

.controller('StoreController',
		function($scope, $routeParams, $location, Store) {
			$scope.store = null;
			$scope.isWarnning = false;

			$scope.pageSize = 2;
			$scope.pageNumber = 1;
			$scope.page = null;
			$scope.maxSize = 5;
			$scope.totalItems = 0;
			$scope.currentPage = 1;

			Store.query({
				pageNumber : $scope.pageNumber,
				pageSize : $scope.pageSize
			}, function(result) {
				if (result.code == "SUCCESS") {
					$scope.page = result.result;
					$scope.totalItems = $scope.page.totalElements;
				}
			});
			/**
			 * 页码
			 */
			$scope.pageChanged = function() {
				$scope.gotoPage($scope.currentPage);
			};

			$scope.gotoPage = function(n) {
				Store.query({
					pageNumber : n,
					pageSize : $scope.pageSize
				}, function(result) {
					if (result.code == "SUCCESS") {
						$scope.page = result.result;
					}
				});
			};

			$scope.createStore = function() {
				if ($scope.store == null || $scope.store == 'undefined') {
					// 对于其他方式做处理
					$scope.isWarnning = true;
					return;
				}

				// 保存Store
				Store.save($scope.store, function(result) {
					if (result.success) {
						$('#addStoreModel').modal('hide');
						return;
					}
					alert(result.code + "--" + result.message);
				});
			};
		})

.config(function($routeProvider, $locationProvider) {
	$routeProvider.when('/', {
		controller : 'BaseInfoController',
		templateUrl : 'base_info.htm'
	}).when('/base_info/:id', {
		controller : 'BaseInfoController',
		templateUrl : 'base_info.htm'
	}).when('/message', {
		controller : 'MessageController',
		templateUrl : 'message.htm'
	}).when('/store', {
		controller : 'StoreController',
		templateUrl : 'store.htm'
	}).when('/view/:id', { // 在id前面加一个冒号，从而制订了一个参数化URL
		controller : 'DetailController',
		templateUrl : 'detail.html'
	}).otherwise({
		redirectTo : '/'
	});
	// configure html5 to get links working on jsfiddle
	$locationProvider.html5Mode(false);
});