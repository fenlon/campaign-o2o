angular.module('storeDetailModule', [])

.factory('Store', function($http, $templateCache) {
	var service = { // our factory definition
		get : function(id, callback) {
			$http({
				method : 'GET',
				url : ctx + '/store/mobile/list_campaign/' + id,
				cache : $templateCache
			}).success(callback).error(callback);
		}
	};
	return service;
})

.controller('StoreDetailCtrl', [ '$scope', 'Store', function($scope, Store) {

	if (storeId != "" && storeId != null) {
		Store.get(storeId, function(data, status) {
			if (status == 200) {
				$scope.data = data;
				return;
			} else {
				console.log(status);
				return;
			}
		});
	}
} ]);