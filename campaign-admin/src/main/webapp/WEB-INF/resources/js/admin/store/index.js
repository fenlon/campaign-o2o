var app = angular.module('app', [ 'ngResource', 'ui.bootstrap' ]);

app.factory('Store', [ '$resource', function($resource) {
	var url = ctx + '/store/:id.:format';
	return $resource(url, {
		id : '@id',
		format : 'json'
	}, {
		'get' : {
			method : 'GET'
		},
		'save' : {
			url : 'data.json',
			method : 'POST'
		},
		'update' : {
			method : "PUT"
		},
		'query' : {
			url : "queryByPage.json",
			method : 'GET'
		},
		// 'query' : {
		// url : "account/query.json",
		// method : 'GET',
		// isArray : true
		// },
		'remove' : {
			method : 'DELETE'
		},
		'delete' : {
			method : 'DELETE'
		}
	});
} ]);
app.controller('StoreCtrl', [ '$scope', 'Store', function($scope, Store) {
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

} ]);