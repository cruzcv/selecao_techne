app.controller("EstadoController", ["$scope", "$rootScope", "$location", "$routeParams", "EstadoService", "estado", function($scope, $rootScope, $location, $routeParams, estadoService, estado) {
		
	var ideEstado = ($routeParams.ideEstado) ? parseInt($routeParams.ideEstado) : null;
	
	if(ideEstado != null) {
		var original = estado.data;
		original.ideEstado = ideEstado;
	
		$scope.estado = angular.copy(original);
		$scope.estado.ideEstado = ideEstado;
	}
	
	$scope.paginaCorrente = 0;
	
	
	$scope.listarEstadosComPaginacao = function() {
		estadoService.listarEstadosComPaginacao(function(estados) {
			$scope.estados = estados;
			$scope.paginas = new Array(estados.totalPages)
		}, $scope.paginaCorrente);			
	};
	
	$scope.listarTodosEstados = function() {
		estadoService.listarTodosEstados(function(estados) {
			$scope.todosEstados = estados;
		});
	}
	

    $scope.salvarEstado = function(estado) {
    	estadoService.salvarEstado(function(estados) {			
			$location.url('/estado');
		}, estado);
    };
    
    $scope.excluirEstado = function(ideEstado) {
    	estadoService.excluirEstado(function(estados) {
			$scope.estados = estados;
			$scope.paginas = new Array(estados.totalPages)
		}, 0, ideEstado);
    }
    
    $scope.gotoPage = function(page) {
    	if(page > -1 && page < $scope.paginas.length) {
	    	$scope.paginaCorrente = page;
	    	$scope.listarEstadosComPaginacao();
    	}
    }
    
    if($location.url() == '/estado') {
    	$scope.listarEstadosComPaginacao();        	
    }
}]);
