app.directive('validarCpf', ['AlertService', function(alertService){
	return {
		restrict: 'A',
		link: function(scope, element, attrs, ctrl) {
			element.bind('change', function(){
				if(!isCpfValido(element.val())) {
					alertService.clear();
					alertService.add('danger', 'CPF inválido!');					
					element.val('');
					element.focus();
				}
			});
		}
	}
	
	function isCpfValido(cpf) {
		cpf = cpf.replace(/[^a-zA-Z0-9]/g, "");
	    var Soma;
	    var Resto;
	    Soma = 0;
		if (cpf == "00000000000") return false;
	    
		for (i=1; i<=9; i++) Soma = Soma + parseInt(cpf.substring(i-1, i)) * (11 - i);
		Resto = (Soma * 10) % 11;
		
	    if ((Resto == 10) || (Resto == 11))  Resto = 0;
	    if (Resto != parseInt(cpf.substring(9, 10)) ) return false;
		
		Soma = 0;
	    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(cpf.substring(i-1, i)) * (12 - i);
	    Resto = (Soma * 10) % 11;
		
	    if ((Resto == 10) || (Resto == 11))  Resto = 0;
	    if (Resto != parseInt(cpf.substring(10, 11) ) ) return false;
	    return true;
	}
}]);