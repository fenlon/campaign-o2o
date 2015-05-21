var userModule = angular.module('userModule', [ 'ngRoute' ]);
userModule.controller('TextController', function($scope) {
	$scope.someText = '测试显示内容000';
});

// 路由
function emailRouteConfig($routeProvider) {
	$routeProvider.when('/', {
		controller : BaseInfoController,
		templateUrl : 'base_info.htm'
	}).when('/base_info/:id', {
		controller : BaseInfoController,
		templateUrl : 'base_info.htm'
	}).when('/message', {
		controller : MessageController,
		templateUrl : 'message.htm'
	}).when('/store', {
		controller : StoreController,
		templateUrl : 'store.htm'
	}).when('/view/:id', { // 在id前面加一个冒号，从而制订了一个参数化URL
		controller : DetailController,
		templateUrl : 'detail.html'
	}).otherwise({
		redirectTo : '/'
	});
}

userModule.config(emailRouteConfig);// 配置我们的路由

function BaseInfoController($scope, $http, $routeParams) {
	$http.get('info/' + $routeParams.id + '.json').success(
			function(data, status, headers, config) {
				alert(data.id);
			}).error(function(data, status, headers, config) {

	});

}
function DetailController($scope) {
	// $scope.messages = messages;
}

function StoreController($scope, $routeParams) {
	// $scope.message = messages[$routeParams.id];
}

function MessageController($scope, $routeParams) {
	// $scope.message = messages[$routeParams.id];
}