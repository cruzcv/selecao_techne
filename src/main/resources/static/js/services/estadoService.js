app.factory("EstadoService", ["$http", "$rootScope", "AlertService", function($http, $rootScope, alertService) {
	var estadoService = {};
	
	estadoService.listarEstadosComPaginacao = function(callback, page) {
        	$http.get("/estado/listarComPaginacao?page="+page)
        		.success(function (data) {
        			if (callback) callback(data);
        		}).error(function(d, s) {
        			if(d != null && $.isArray(d)) {
        				for ( var obj in d) {
        					alertService.add('danger', d[obj].message);
        				}
        			} else {
        				alertService.add('danger', d != null ? d.message : "Falha ao listar os estados");    				
        			}
        		});
        };
        
	estadoService.listarTodosEstados = function(callback) {
        	$http.get("/estado/listar")
        		.success(function (data) {
        			if (callback) callback(data);
        		}).error(function(d, s) {
        			if(d != null && $.isArray(d)) {
        				for ( var obj in d) {
        					alertService.add('danger', d[obj].message);
        				}
        			} else {
        				alertService.add('danger', d != null ? d.message : "Falha ao listar os estados");    				
        			}
        		});
        };
        
	estadoService.salvarEstado = function(callback, estado) {
	        $http({
	            method:"POST",
	            url:"/estado/salvar",
	            data:JSON.stringify(estado),                
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
        
	estadoService.getEstado = function(ideEstado) {
		return $http.get("/estado/selecionar?ideEstado="+ideEstado);				
	};  
		
	estadoService.excluirEstado = function(callback, page, ideEstado) {		
		$http({
            method:"DELETE",
            url:"/estado/excluir?ideEstado=" + ideEstado
        }).success(function (data) {
        	alertService.add('success', "Registro excluido com sucesso!");                
        	$http.get("/estado/listarComPaginacao?page="+page)
    		.success(function (data) {
    			if (callback) callback(data);
    		}).error(function(d, s) {
    			if(d != null && $.isArray(d)) {
    				for ( var obj in d) {
    					alertService.add('danger', d[obj].message);
    				}
    			} else {
    				alertService.add('danger', d != null ? d.message : "Falha ao listar os estados");    				
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

        return estadoService;
    }]);