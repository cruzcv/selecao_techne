app.controller('IndexController', function($q, $scope, $rootScope, $http, i18n, $location) {
	        $scope.language = function() {
	            return i18n.language;
	        };
	        $scope.setLanguage = function(lang) {
	            i18n.setLanguage(lang);
	        };
	        $scope.activeWhen = function(value) {
	            return value ? 'active' : '';
	        };
	
	        $scope.path = function() {
	            return $location.url();
	        };
	        
	        $scope.redirectTo = function(url) { 
	        	$location.url(url);
	        }	        
	        
	    });
