var app = angular.module('app', [ 'ngResource' ]);

app.factory('Account', [ '$resource', function($resource) {
	var url = ctx + '/account/:id.:format';
	return $resource(url, {
		id : '@id',
		format : 'json'
	}, {
		'get' : {
			method : 'GET'
		},
		'save' : {
			url : 'account/data.json',
			method : 'POST'
		},
		'update' : {
			method : "PUT"
		},
		'query' : {
			url : "account/queryByPage.json",
			method : 'GET',
			isArray : true
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
app.controller('AccountCtrl', [ '$scope', 'Account', function($scope, Account) {
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
	Account.query();

	// Account.remove({
	// id : 1
	// }, function(account) {
	//
	// });

} ]);