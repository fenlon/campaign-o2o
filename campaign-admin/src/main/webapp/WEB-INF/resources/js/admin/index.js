var app = angular.module('app', ['ngResource', 'ngRoute']);

app.factory('Account', ['$resource', function($resource) {
					return $resource('/account/:id', null, {
								'update' : {
									method : 'PUT'
								}
							});
				}]);

app.controller('NotesCtrl', ['$scope', '$routeParams', 'Notes',
				function($scope, $routeParams, Notes) {
					// First get a note object from the factory
					var note = Notes.get({
								id : $routeParams.id
							});
					$id = note.id;
					Notes.update({
								id : $id
							}, note);
				}]);