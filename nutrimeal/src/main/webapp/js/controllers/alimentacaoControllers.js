var alimentacaoControllers = angular.module('alimentacaoControllers', []);

alimentacaoControllers.controller('AlimentacaoListController', ['$scope', 'PerfilalimentarService','RefeicaoService','$location', 
                                                          function($scope, PerfilalimentarService, RefeicaoService, $location) {
	
	$scope.perfilalimentarList = PerfilalimentarService.query(); 
	$scope.refeicaoList = RefeicaoService.query(); 
	
    $scope.createNewPerfilalimentar = function (perfilalimentar, email) {
    	perfilalimentar.user=email;
    	PerfilalimentarService.create(perfilalimentar).$promise.then(function(result) {
        	
        	$scope.perfilalimentarList = PerfilalimentarService.query();
        	$scope.refeicaoList = RefeicaoService.query(); 
        	$scope.closePerfil();


        }, function(error) {
        	alert(error);
        });
    }
    

    $scope.deletePerfilalimentar = function(id) {
    	PerfilalimentarService.delete({ id : id }).$promise.then(function(result) {
        	$scope.perfilalimentarList = PerfilalimentarService.query();
        }, function(error) {
        	alert(error);
        });
    };

    $scope.addRefeicao = function(id) {
    	$location.path('/planosalimentares/' + id);
    };
    
    $scope.addRefeicaoAdmin = function(id) {
    	$location.path('/planosalimentaresCreateRefeicao/' + id);
    };
    
    $scope.verRefeicoes = function(id) {
    	$location.path('/perfisAlimentaresAdmin/' + id);
    };
    
    

	    
// Modal Perfil
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.closePerfil = function(){
			$scope.showModal = false;
		}
		
// // Modal refeicao
//		
 $scope.showModalRefeicao = false;
	    
 $scope.toggleModalAddRefeicao = function() {
 $scope.showModalRefeicao = !$scope.showModalRefeicao;
 };
		
 $scope.closeRefeicao = function(){
 $scope.showModalRefeicao = false;
 }
	    

    }]);









alimentacaoControllers.controller('AlimentacaoListAdminController', ['$scope', 'PerfilalimentarService', 'PerfilalimentarUpdateService','RefeicaoService','$location', 
    function($scope, PerfilalimentarService, PerfilalimentarUpdateService, RefeicaoService, $location) {

	$scope.perfilalimentarList = PerfilalimentarService.query(); 
	$scope.refeicaoList = RefeicaoService.query(); 

	$scope.createNewPerfilalimentarAdmin = function (perfilalimentar) {
		PerfilalimentarService.create(perfilalimentar).$promise.then(function(result) {

			$scope.perfilalimentarList = PerfilalimentarService.query();
			$scope.refeicaoList = RefeicaoService.query(); 
			$scope.closePerfil();


		}, function(error) {
			alert(error);
		});
	}

	
	$scope.updatePerfilalimentar = function (perfilalimentar) {
		PerfilalimentarUpdateService.update(perfilalimentar).$promise.then(function() {
			$scope.close();
		}, function(error) {
    	alert(error);
		});
	}
	
	

	$scope.deletePerfilalimentar = function(id) {
		PerfilalimentarService.delete({ id : id }).$promise.then(function(result) {
			$scope.apagarPerfil();
			$scope.perfilalimentarList = PerfilalimentarService.query();
		}, function(error) {
			alert(error);
		});
	};

	$scope.addRefeicao = function(id) {
		$location.path('/planosalimentares/' + id);
	};

	$scope.addRefeicaoAdmin = function(id) {
		$location.path('/planosalimentaresCreateRefeicao/' + id);
	};

	$scope.verRefeicoes = function(id) {
		$location.path('/perfisAlimentaresAdmin/' + id);
	};

//Modal Apagar
	$scope.showModalApagar = false;

	$scope.apagarPerfil = function() {
		$scope.showModalApagar = !$scope.showModalApagar;
	};
	
	$scope.closeApagar = function(){
		$scope.showModalApagar = false;
	}

	// Modal Perfil
	$scope.showModal = false;

	$scope.toggleModal = function() {
		$scope.showModal = !$scope.showModal;
	};

	$scope.closePerfil = function(){
		$scope.showModal = false;
	}
	
//	Modal Update Perfil Alimentar

	$scope.showModalUpdatePerfil = false;

	$scope.toggleModalUpdatePerfilAlimentar = function() {
		$scope.showModalUpdatePerfil = !$scope.showModalUpdatePerfil;
	};

	$scope.closeModalUpdatePerfil = function(){
		$scope.showModalUpdatePerfil = false;
	}

	// // Modal refeicao
	//
	 $scope.showModalRefeicao = false;
	
	 $scope.toggleModalAddRefeicao = function() {
	 $scope.showModalRefeicao = !$scope.showModalRefeicao;
	 };
	
	 $scope.closeRefeicao = function(){
	 $scope.showModalRefeicao = false;
	 }


}]);

alimentacaoControllers.controller('AlimentacaoViewController', ['$scope', '$routeParams', 'PerfilalimentarService', 'PerfilalimentarViewService', 'RefeicaoPerfilService','RefeicaoService','PerfilalimentarUpdateService','$location', function($scope, $routeParams, PerfilalimentarService, PerfilalimentarViewService,RefeicaoPerfilService, RefeicaoService, PerfilalimentarUpdateService, $location) {

	$scope.showTableRefeicoes = false;

	$scope.perfilalimentar = PerfilalimentarViewService.show({ id: $routeParams.id });
	

	 $scope.createNewRefeicao = function (refeicao, id) {
		 refeicao.perfil=id;
	    	RefeicaoService.create(refeicao).$promise.then(function(result) {
	    		$scope.refeicaoList = RefeicaoService.query();
	    		$scope.refeicaoUserList = RefeicaoPerfilService.query({id});
	        	$scope.closeRefeicao();
	        }, function(error) {
	        	alert(error);
	        });
	    }
	 
	 $scope.updatePerfilalimentar = function (perfilalimentar) {
			PerfilalimentarUpdateService.update(perfilalimentar).$promise.then(function() {
				$scope.perfilalimentarList = PerfilalimentarService.query();
				$scope.close();
			}, function(error) {
	    	alert(error);
			});
		}
	 
	 $scope.deleteRefeicao = function(id) {
	    	RefeicaoService.delete({ id : id }).$promise.then(function(result) {
	        	$scope.apagarRefeicao();
	        }, function(error) {
	        	alert(error);
	        });
	    };
	 
    
	    $scope.verRefeicao = function(id) {
	    	$location.path('/planosRefeicoes/' + id);
	    };

	    $scope.verRefeicaoCreateQuantidade = function(id) {
	    	$location.path('/refeicaoCreateQuantidadeAdmin/' + id);
	    };
	    
	    $scope.verRefeicoes = function(id) {
	    	$scope.showTableRefeicoes = !$scope.showTableRefeicoes;
	    	$scope.refeicaoUserList = RefeicaoPerfilService.query({id}); 
	    	$scope.showModalApagar = false;
	    };
	    
	    
	    
//	    Modal Apagar Refeicao
	    
		$scope.showModalApagar = false;

		$scope.apagarRefeicao = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
	    
// Modal Update
		 $scope.showModal = false;

			$scope.toggleModal = function() {
				$scope.showModal = !$scope.showModal;
			};

			$scope.close = function(){
				$scope.showModal = false;
			}
	    
// Modal refeicao
		
		$scope.showModalRefeicao = false;
	    
		$scope.toggleModalAddRefeicao = function() {
			$scope.showModalRefeicao = !$scope.showModalRefeicao;
			
		};
		
		$scope.closeRefeicao = function(){
			$scope.showModalRefeicao = false;
		}


}]);

alimentacaoControllers.controller('AlimentacaoViewAdminController', ['$scope', '$routeParams', 'PerfilalimentarViewService', 'RefeicaoService', 'RefeicaoPerfilService','PerfilalimentarUpdateService','$location', function($scope, $routeParams, PerfilalimentarViewService, RefeicaoService,RefeicaoPerfilService, PerfilalimentarUpdateService,$location) {



	$scope.perfilalimentar = PerfilalimentarViewService.show({ id: $routeParams.id });
	$scope.refeicaoList = RefeicaoService.query(); 

	 $scope.createNewRefeicao = function (refeicao, id) {
		 refeicao.perfil=id;
	    	RefeicaoService.create(refeicao).$promise.then(function(result) {
	    		$scope.refeicaoList = RefeicaoService.query();
	    		$scope.refeicaoUserList = RefeicaoPerfilService.query({id});
	        	$scope.closeRefeicao();
	        }, function(error) {
	        	alert(error);
	        });
	    }
	 

	
	 $scope.verRefeicoesPerfisAdmin = function(id) {
	    	$scope.showTableRefeicoesAdmin = !$scope.showTableRefeicoesAdmin;
	    	$scope.refeicaoUserList = RefeicaoPerfilService.query({id}); 
	    };

	    $scope.verRefeicaoAdmin = function(id) {
	    	$location.path('/planosRefeicoesAdmin/' + id);
	    };
	    
	    
		
// Modal refeicao
		
		$scope.showModalRefeicao = false;
	    
		$scope.toggleModalAddRefeicao = function() {
			$scope.showModalRefeicao = !$scope.showModalRefeicao;
		};
		
		$scope.closeRefeicao = function(){
			$scope.showModalRefeicao = false;
		}


}]);



