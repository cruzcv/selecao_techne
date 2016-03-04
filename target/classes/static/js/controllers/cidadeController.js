app.controller("CidadeController", ["$scope", "$rootScope", "$location", "$routeParams", "CidadeService", "EstadoService", "cidade", function($scope, $rootScope, $location, $routeParams, cidadeService, estadoService, cidade) {
		
	var ideCidade = ($routeParams.ideCidade) ? parseInt($routeParams.ideCidade) : null;

	$scope.paginaCorrente = 0;
			
	if(ideCidade != null) {
		var original = cidade.data;
		original.ideCidade = ideCidade;
	
		$scope.cidade = angular.copy(original);
		$scope.cidade.ideCidade = ideCidade;
	}	
	
	
	$scope.listarCidadesComPaginacao = function() {
		cidadeService.listarCidadesComPaginacao(function(cidades) {
			$scope.cidades = cidades;
			$scope.paginas = new Array(cidades.totalPages)
		}, $scope.paginaCorrente);			
	};
	
	$scope.listarTodasCidades = function() {
		cidadeService.listarTodosCidades(function(cidades) {
			$scope.todasCidade = cidades;
		});
	}
	
	$scope.salvarCidade = function(cidade) {
		cidadeService.salvarCidade(function(cidade) {			
			$location.url('/cidade');
		}, cidade);
    };
    
    $scope.excluirCidade = function(ideCidade) {
    	cidadeService.excluirCidade(function(cidades) {
			$scope.cidades = cidades;
			$scope.paginas = new Array(cidades.totalPages);
			$scope.paginaCorrente = 0;
		}, 0, ideCidade);
    }
    
    $scope.gotoPage = function(page) {
    	if(page > -1 && page < $scope.paginas.length) {
	    	$scope.paginaCorrente = page;
	    	$scope.listarCidadesComPaginacao();
    	}
    }
    
    if($location.url() == '/cidade') {
    	$scope.listarCidadesComPaginacao();        	
    }
    
    if($location.url() == '/cidade/create' || $location.url().indexOf("/cidade/edit") > -1) {
        estadoService.listarTodosEstados(function(estados) {
			$scope.todosEstados = estados;
		});
    }
}]);