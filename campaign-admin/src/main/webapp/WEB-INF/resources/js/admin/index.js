var app = angular.module('app', ['ngResource']);

app.factory('Account', ['$resource', function($resource) {
					return $resource(ctx + '/account/:aid.json', {
								aid : '@id'
							}, {
								'get' : {
									method : 'GET'
								},
								'save' : {
									method : 'POST'
								},
								'query' : {
									method : 'GET',
									isArray : true
								},
								'remove' : {
									method : 'DELETE'
								},
								'delete' : {
									method : 'DELETE'
								}
							});
				}]);

app.controller('AccountCtrl', ['$scope', 'Account', function($scope, Account) {

					var A = Account.get({
								aid : 1
							}, function(account) {
								alert(account.name);
							});
					alert(A.name);
				}]);