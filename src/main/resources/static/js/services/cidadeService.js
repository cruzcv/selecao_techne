app.factory("CidadeService", ["$http", "$rootScope", "AlertService", function($http, $rootScope, alertService) {
	var cidadeService = {};
	
    cidadeService.listarCidadesComPaginacao = function(callback, page) {
    	$http.get("/cidade/listarComPaginacao?page="+page)
    		.success(function (data) {
    			if (callback) callback(data);
    		}).error(function(d, s) {
    			if(d != null && $.isArray(d)) {
    				for ( var obj in d) {
    					alertService.add('danger', d[obj].message);
    				}
    			} else {
    				alertService.add('danger', d != null ? d.message : "Falha ao listar as cidades");    				
    			}
    		});
    };
        
    cidadeService.listarTodasCidades = function(callback) {
    	$http.get("/cidade/listar")
    		.success(function (data) {
    			if (callback) callback(data);
    		}).error(function(d, s) {
    			if(d != null && $.isArray(d)) {
    				for ( var obj in d) {
    					alertService.add('danger', d[obj].message);
    				}
    			} else {
    				alertService.add('danger', d != null ? d.message : "Falha ao listar as cidades");    				
    			}
    		});
    };
    
    cidadeService.listarCidadesPorEstado = function(callback, estado){
    	$http.get("/cidade/listarPorEstado?ideEstado="+estado.ideEstado)
		.success(function (data) {
			if (callback) callback(data);
		}).error(function(d, s) {
			if(d != null && $.isArray(d)) {
				for ( var obj in d) {
					alertService.add('danger', d[obj].message);
				}
			} else {
				alertService.add('danger', d != null ? d.message : "Falha ao listar as cidades");    				
			}
		});
    };

    cidadeService.salvarCidade = function(callback, cidade) {
        $http({
            method:"POST",
            url:"/cidade/salvar",
            data:JSON.stringify(cidade)               
        }).success(function (data) {
        	alertService.add('success', "Registro salvo com sucesso!");  
        	if (callback) callback(data);        	
        }).error(function(d, s) {
        	if(d != null && $.isArray(d)) {
				for ( var obj in d) {
					alertService.add('danger', d[obj].message);
				}
			} else {
				alertService.add('danger', d != null ? d.message : "Falha ao salvar o registro");    				
			}
        });
    };
        
	cidadeService.getCidade = function(ideCidade) {
		return $http.get("/cidade/selecionar?ideCidade="+ideCidade);				
	};  
		
	cidadeService.excluirCidade = function(callback, page, ideCidade) {		
		$http({
            method:"DELETE",
            url:"/cidade/excluir?ideCidade=" + ideCidade
        }).success(function (data) {
        	alertService.add('success', "Registro excluido com sucesso!");                
        	$http.get("/cidade/listarComPaginacao?page="+page)
    		.success(function (data) {
    			if (callback) callback(data);
    		}).error(function(d, s) {
    			if(d != null && $.isArray(d)) {
    				for ( var obj in d) {
    					alertService.add('danger', d[obj].message);
    				}
    			} else {
    				alertService.add('danger', d != null ? d.message : "Falha ao listar as cidades");    				
    			}
    		});
            
        }).error(function(d, s) {
        	if(d != null && $.isArray(d)) {
				for ( var obj in d) {
					alertService.add('danger', d[obj].message);
				}
			} else {
				alertService.add('danger', d != null ? d.message : "Falha ao excluir o registro");    				
			}
        });
	};

        return cidadeService;
    }])