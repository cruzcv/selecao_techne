app.directive('validarEmail', ['AlertService', function(alertService){
	return {
		restrict: 'A',
		link: function(scope, element, attrs, ctrl) {
			element.bind('change', function(){				
				if(!isEmailValido(element.val())) {
					alertService.clear();
					alertService.add('danger', 'Email inválido!');					
					element.val('');
					element.focus();
				}
			});
		}
	}
	
	function isEmailValido(email){
		er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/; 
		if( !er.exec(email) )
		{
			return false;
		} else {
			return true;
		}
	}
}]);