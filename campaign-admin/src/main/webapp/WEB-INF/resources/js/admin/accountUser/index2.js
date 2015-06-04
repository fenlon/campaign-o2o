var app = angular.module('accountUser', [ 'ngResource', 'ngTouch', 'ui.grid',
		'ui.grid.resizeColumns', 'ui.grid.moveColumns', 'ui.grid.pagination' ]);

app.factory('AccountUser', [ '$resource', function($resource) {
	var url = ctx + '/account_user/:id/.:format';
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
app
		.controller(
				'AccountUserCtrl',
				[
						'$scope',
						'AccountUser',
						function($scope, AccountUser) {
							$scope.model = {
								"name" : "fenlon",
								"nickName" : "fenlonguy2",
								"password" : "xfl2",
								"age" : 18,
								"number" : "123456",
								"email" : "fenlon2@gmail.com",
								"mobile" : "123456",
								"address" : "ssss",
								"gender" : "MALE",
								"mbay" : 10.0,
								"lockmbay" : 0.0,
								"account_id" : 0
							};
							// AccountUser.save(angular.toJson($scope.model),
							// function(accountUser) {
							// });

							// Account.get({
							// id : 1
							// }, function(account) {
							// account.name = "lilei63";
							// account.$update();
							// });
							// $scope.users = AccountUser.query();

							// Account.remove({
							// id : 1
							// }, function(account) {
							//
							// });

							$scope.gridOptions = {
								enableColumnResizing : true,
								paginationPageSizes : [ 25, 50, 75 ],
								paginationPageSize : 10,
								columnDefs : [
										{
											displayName : '用户名',
											field : 'name'
										},
										{
											displayName : '性别',
											field : 'gender',
											cellTemplate : '<span>{{row.entity.gender==="MALE"?"男":"女"}}</span>',
											enableColumnResizing : false
										},
										{
											displayName : '年龄',
											field : 'age'
										},
										{
											displayName : '注册时间',
											field : 'dateCreated',
											cellTemplate : '<div>{{row.entity.dateCreated | date:"yyyy-MM-dd HH:mm:ss"}}</div>'
										},
										{
											name : 'edit',
											// field : 'gender',
											displayName : 'Edit',
											cellTemplate : '<button id="editBtn" type="button" class="btn-small" ng-click="edit(row.entity)" >{{}}</button> '
										} ]
							};

							AccountUser.query({}, function(page) {
								$scope.gridOptions.data = page.content;
							});
						} ]);
