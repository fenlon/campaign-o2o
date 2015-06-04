angular
		.module(
				'redeemModule',
				[],
				function($httpProvider) {
					$httpProvider.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded;charset=utf-8";
					$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
					var param = function(obj) {
						var query = "", name, value, fullSubName, subName, subValue, innerObj, i;
						for (name in obj) {
							value = obj[name];
							if (value instanceof Array) {
								for (i = 0; i < value.length; ++i) {
									subValue = value[i];
									fullSubName = name + "[" + i + "]";
									innerObj = {};
									innerObj[fullSubName] = subValue;
									query += param(innerObj) + "&";
								}
							} else if (value instanceof Object) {
								for (subName in value) {
									subValue = value[subName];
									fullSubName = name + "[" + subName + "]";
									innerObj = {};
									innerObj[fullSubName] = subValue;
									query += param(innerObj) + "&";
								}
							} else if (value !== undefined && value !== null) {
								query += encodeURIComponent(name) + "="
										+ encodeURIComponent(value) + "&";
							}
						}
						return query.length ? query.substr(0, query.length - 1)
								: query;
					};
					$httpProvider.defaults.transformRequest = [ function(data) {
						return angular.isObject(data)
								&& String(data) !== "[object File]" ? param(data)
								: data;
					} ];
				})

		.controller(
				'redeemCtrl',
				[
						'$scope',
						'$http',
						'$templateCache',
						function($scope, $http, $templateCache) {

							$scope.redeem = function() {
								$http(
										{
											method : 'POST',
											url : ctx
													+ '/store_operator/mobile/redeem.json',
											headers : {
												'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
											},
											data : {
												code : code
											},
											cache : $templateCache
										}).success(
										function(data, status) {
											if (!data.success) {
												alert(data.message);
												return;
											}
											alert("兑换结果:" + data.message
													+ "/n活动链接:"
													+ data.result.link
													+ "兑换码为："
													+ data.result.code + "核销码："
													+ data.result.checkCode
													+ "红包金额："
													+ data.result.price);
										}).error(function(data, status) {
									alert(status);
								});
							};
						} ]);