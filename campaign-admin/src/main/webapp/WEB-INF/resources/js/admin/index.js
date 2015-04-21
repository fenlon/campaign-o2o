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
			method : 'POST'
		},
		'update' : {
			method : "PUT"
		},
		'query' : {
			method : 'GET',
			isArray : true,
			params : {
				format : ''
			}
		},
		'remove' : {
			method : 'DELETE'
		},
		'delete' : {
			method : 'DELETE'
		}
	});
} ]);
app.controller('AccountCtrl', [ '$scope', 'Account', function($scope, Account) {

	Account.get({
		id : 1
	}, function(account) {
		account.name = "lilei6";
		account.$update();
	});
	Account.query();
	//
	// Account.save({
	// name : 'lilei4',
	// number : '564'
	// }, function(account) {
	//
	// });

} ]);