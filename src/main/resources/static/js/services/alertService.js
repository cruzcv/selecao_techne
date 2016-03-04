angular.module('AlertService', []).factory("AlertService", function($rootScope, $timeout) {
	var alertService = {};
	
	$rootScope.alerts = [];
	
	alertService.add = function(type, msg) {
		$rootScope.alerts.push({
			type: type,
			msg: msg,
			close: function() {
				return alertService.closeAlert(this);
			}
		});
		
        $timeout(function(){ 	               
            alertService.closeAlert(this); 
        }, 4000); 
	}
	
	alertService.closeAlert = function(alert) {
		alertService.closeAlertIdx($rootScope.alerts.indexOf(alert));
	}
	
	alertService.closeAlertIdx = function(index) {
		$rootScope.alerts.splice(index, 1);
	}
	
	alertService.clear = function(){
		$rootScope.alerts = [];
	}
	
	alertService.get = function() {
		$rootScope.alerts;
	}
	
	$rootScope.closeAlert = alertService.closeAlert;
	
	return alertService;
});
