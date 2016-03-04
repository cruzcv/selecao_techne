app.factory("PessoaService", ["$http", "$rootScope", "AlertService", function($http, $rootScope, alertService) {
	var pessoaService = {};
	
    pessoaService.listarPessoasComPaginacao = function(callback, page) {
    	$http.get("/pessoa/listarComPaginacao?page="+page)
    		.success(function (data) {
    			if (callback) callback(data);
    		}).error(function(d, s) {
    			if(d != null && $.isArray(d)) {
    				for ( var obj in d) {
    					alertService.add('danger', d[obj].message);
					}
    			} else {
    				alertService.add('danger', d != null ? d.message : "Falha ao listar as pessoas");    				
    			}
    		});
    }
        
    pessoaService.listarTodasPessoas = function(callback) {
    	$http.get("/pessoa/listar")
    		.success(function (data) {
    			if (callback) callback(data);
    		}).error(function(d, s) {
    			if(d != null && $.isArray(d)) {
    				for ( var obj in d) {
    					alertService.add('danger', d[obj].message);
					}
    			} else {
    				alertService.add('danger', d != null ? d.message : "Falha ao listar as pessoas");    				
    			}
    		});
    }

    pessoaService.salvarPessoa = function(callback, pessoa) {
        $http({
            method:"POST",
            url:"/pessoa/salvar",
            data:JSON.stringify(pessoa),                
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
        
	pessoaService.getPessoa = function(idePessoa) {
		return $http.get("/pessoa/selecionar?idePessoa="+idePessoa);				
	};  
		
	pessoaService.excluirPessoa = function(callback, page, idePessoa) {		
		$http({
            method:"DELETE",
            url:"/pessoa/excluir?idePessoa=" + idePessoa
        }).success(function (data) {
        	alertService.add('success', "Registro excluido com sucesso!");                
        	$http.get("/pessoa/listarComPaginacao?page="+page)
    		.success(function (data) {
    			if (callback) callback(data);
    		}).error(function(d, s) {
    			if(d != null && $.isArray(d)) {
    				for ( var obj in d) {
    					alertService.add('danger', d[obj].message);
					}
    			} else {
    				alertService.add('danger', d != null ? d.message : "Falha ao listar as pessoas");    				
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

        return pessoaService;
    }])