var app = angular.module('cadastroPessoaApp', ['ngRoute', 'ngResource', 'ngCookies', 'AlertService'])
	
.config(['$routeProvider', '$httpProvider', '$locationProvider', function($routeProvider, $httpProvider, $locationProvider) {
	
	$locationProvider.html5Mode(true);
	
	
    $routeProvider.when('/estado', {
		templateUrl : 'views/estado/listEstado.html',
		controller : 'EstadoController',
		resolve: {
			estado: function(EstadoService, $route){			
				return new Object();
			}
		},
		publicAccess : true
	}).when('/estado/create', {
		templateUrl : 'views/estado/createEstado.html',
		controller : 'EstadoController',
		resolve: {
			estado: function(EstadoService, $route){			
				return new Object();
			}
		},
		publicAccess : true
	}).when('/estado/edit/:ideEstado', {
		templateUrl : 'views/estado/createEstado.html',
		controller : 'EstadoController',
		resolve: {
			estado: function(EstadoService, $route){
				var ideEstado = $route.current.params.ideEstado;
				return EstadoService.getEstado(ideEstado);
			}
		},
		publicAccess : true
	}).when('/cidade', {
		templateUrl : 'views/cidade/listCidade.html',
		controller : 'CidadeController',
		resolve: {
			cidade: function(CidadeService, $route){			
				return new Object();
			}
		},
		publicAccess : true
	}).when('/cidade/create', {
		templateUrl : 'views/cidade/createCidade.html',
		controller : 'CidadeController',
		resolve: {
			cidade: function(CidadeService, $route){			
				return new Object();
			}
		},
		publicAccess : true
	}).when('/cidade/edit/:ideCidade', {
		templateUrl : 'views/cidade/createCidade.html',
		controller : 'CidadeController',
		resolve: {
			cidade: function(CidadeService, $route){
				var ideCidade = $route.current.params.ideCidade;
				return CidadeService.getCidade(ideCidade);
			}
		},
		publicAccess : true
	}).when('/pessoa', {
		templateUrl : 'views/pessoa/listPessoa.html',
		controller : 'PessoaController',
		resolve: {
			pessoa: function(PessoaService, $route){			
				return new Object();
			}
		},
		publicAccess : true
	}).when('/pessoa/create', {
		templateUrl : 'views/pessoa/createPessoa.html',
		controller : 'PessoaController',
		resolve: {
			pessoa: function(PessoaService, $route){			
				return new Object();
			}
		},
		publicAccess : true
	}).when('/pessoa/edit/:idePessoa', {
		templateUrl : 'views/pessoa/createPessoa.html',
		controller : 'PessoaController',
		resolve: {
			pessoa: function(PessoaService, $route){
				var idePessoa = $route.current.params.idePessoa;
				return PessoaService.getPessoa(idePessoa);
			}
		}
	}).otherwise({ redirectTo: '/' });
    
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    
}]).run();