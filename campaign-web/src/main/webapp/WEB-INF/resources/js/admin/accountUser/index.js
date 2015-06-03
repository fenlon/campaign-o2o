var app = angular.module('app', [ 'ngResource', 'ui.bootstrap' ]);

app.factory('AccountUser', [ '$resource', function($resource) {
	var url = ctx + '/account_user/:id.:format';
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
app.controller('AccountUserCtrl', [ '$scope', 'AccountUser',
		function($scope, AccountUser) {
			$scope.pageSize = 2;
			$scope.pageNumber = 1;
			$scope.page = null;
			$scope.maxSize = 5;
			$scope.totalItems = 0;
			$scope.currentPage = 1;

			AccountUser.query({
				pageNumber : $scope.pageNumber,
				pageSize : $scope.pageSize
			}, function(result) {
				if (result.code == "SUCCESS") {
					$scope.page = result.result;
					$scope.totalItems = $scope.page.totalElements;
				}
			});

			// alert($scope.page.totalElements);

			// $scope.totalItems = 64;
			// $scope.currentPage = 4;

			/*
			 * $scope.setPage = function(pageNo) { $scope.currentPage = pageNo; };
			 */

			$scope.pageChanged = function() {
				$scope.gotoPage($scope.currentPage);
			};
			$scope.give = function(a) {
				alert(a);
			};

			// var id = null;
			// Account.save({
			// name : 'fenlonxiong',
			// number : '564'
			// }, function(account) {
			// id = account.id;
			// });

			// Account.get({
			// id : 1
			// }, function(account) {
			// account.name = "lilei63";
			// account.$update();
			// });
			// AccountUser.query();

			// Account.remove({
			// id : 1
			// }, function(account) {
			//
			// });

			$scope.gotoPage = function(n) {
				AccountUser.query({
					pageNumber : n,
					pageSize : $scope.pageSize
				}, function(result) {
					if (result.code == "SUCCESS") {
						$scope.page = result.result;
					}
				});
			};

		} ]);