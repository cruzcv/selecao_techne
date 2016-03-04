app.controller("PessoaController", ["$scope", "$rootScope", "$location", "$routeParams", "PessoaService", "EstadoService", "CidadeService", "pessoa", function($scope, $rootScope, $location, $routeParams, pessoaService, estadoService, cidadeService, pessoa) {
		
	var idePessoa = ($routeParams.idePessoa) ? parseInt($routeParams.idePessoa) : null;

	$scope.paginaCorrente = 0;
	$scope.estadoSelecionado = {};
			
	if(idePessoa != null) {
		var original = pessoa.data;
		original.idePessoa = idePessoa;
	
		$scope.pessoa = angular.copy(original);
		$scope.pessoa.idePessoa = idePessoa;
	}	
	
	
	$scope.listarPessoasComPaginacao = function() {
		pessoaService.listarPessoasComPaginacao(function(pessoas) {
			$scope.pessoas = pessoas;
			$scope.paginas = new Array(pessoas.totalPages)
		}, $scope.paginaCorrente);			
	};
	
	$scope.listarTodasPessoas = function() {
		pessoaService.listarTodosPessoas(function(pessoas) {
			$scope.todasPessoa = pessoas;
		});
	}
	
	$scope.salvarPessoa = function(pessoa) {
		pessoaService.salvarPessoa(function(pessoa) {			
			$location.url('/pessoa');
		}, pessoa);
    };
    
    $scope.excluirPessoa = function(idePessoa) {
    	pessoaService.excluirPessoa(function(pessoas) {
			$scope.pessoas = pessoas;
			$scope.paginas = new Array(pessoas.totalPages);
			$scope.paginaCorrente = 0;
		}, 0, idePessoa);
    }
    
    $scope.gotoPage = function(page) {
    	if(page > -1 && page < $scope.paginas.length) {
	    	$scope.paginaCorrente = page;
	    	$scope.listarPessoasComPaginacao();
    	}
    }
    
    if($location.url() == '/pessoa') {
    	$scope.listarPessoasComPaginacao();        	
    }
    
    if($location.url() == '/pessoa/create' || $location.url().indexOf("/pessoa/edit") > -1) {
        estadoService.listarTodosEstados(function(estados) {
			$scope.todosEstados = estados;
		});
        if($location.url().indexOf("/pessoa/edit") > -1) {
        	$scope.estadoSelecionado = $scope.pessoa.ideCidade.ideEstado
        	cidadeService.listarCidadesPorEstado(function(cidades){
        		$scope.todasCidades = cidades;
        	}, $scope.pessoa.ideCidade.ideEstado);
        }
    }
    
    $scope.listarCidadesPorEstado = function() {
    	cidadeService.listarCidadesPorEstado(function(cidades){
    		$scope.todasCidades = cidades;
    	}, $scope.estadoSelecionado);
    }
}]);