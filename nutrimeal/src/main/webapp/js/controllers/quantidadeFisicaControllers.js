var quantidadefisicaControllers = angular.module('quantidadefisicaControllers', []);

quantidadefisicaControllers.controller('QuantidadeFisicaListController', ['$scope', 'ExercicioAtributoService', 'PerfilfisicoService', 'AtributoService', 'MetaexercicioService','$location', 
                                                          function($scope, ExercicioAtributoService, PerfilfisicoService, AtributoService, MetaexercicioService, $location) {
	
	$scope.perfilfisicoList = PerfilfisicoService.query(); 

	
    $scope.createNewPerfilfisico = function (perfilfisico, email) {
    	perfilfisico.user=email;
    	PerfilfisicoService.create(perfilfisico).$promise.then(function(result) {
        	$scope.close();
    		$scope.perfilfisicoList = PerfilfisicoService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.addExercicio = function(id) {
    	$location.path('/quantidadesfisicas/' + id);
    };

    
    $scope.verPerfilFisicoAdmin = function(id) {
    	$location.path('/verPerfisFisicosAdmin/' + id);
    };
    
    $scope.addExercicioAdminCreate = function(id) {
    	$location.path('/createPerfisFisicosAdmin/' + id);
    };
    

    
// $scope.createNewExercicioAtributo = function (exercicioatributo) {
// ExercicioAtributoService.create(exercicioatributo).$promise.then(function(result)
// {
// $scope.closeExercicio();
// console.log("estou aqui");
// $scope.perfilfisicoList = PerfilfisicoService.query();
// }, function(error) {
// alert(error);
// });
// }
//    
//    
// $scope.createNewMetaexercicio = function (metaexercicio) {
// MetaexercicioService.create(metaexercicio).$promise.then(function(result) {
//    		
// //console.log("Hello");
// console.log("Exercicio - "+ metaexercicio.exercicio);
// console.log("Perfil - "+ metaexercicio.perfil);
//    		
// $scope.closePlano();
// $scope.metaexerciciosList = MetaexercicioService.query();
// }, function(error) {
// alert(error);
// });
// }
    
  
// Modal Perfil
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
		
// Modal Quantidades
		$scope.showModalExercicio = false;
	    
		$scope.toggleModalAddExercicio = function() {
			$scope.showModalExercicio = !$scope.showModalExercicio;
		};
		
		$scope.closeExercicio = function(){
			$scope.showModalExercicio = false;
		}
		
// Modal Plano
		
		$scope.showModalPlano = false;
	    
		$scope.toggleModalAddPlano = function() {
			$scope.showModalPlano = !$scope.showModalPlano;
		};
		
		$scope.closePlano = function(){
			$scope.showModalPlano = false;
		}
		
	    
    }]);

quantidadefisicaControllers.controller('QuantidadeFisicaListAdminController', ['$scope', 'ExercicioAtributoService', 'PerfilfisicoService', 'AtributoService', 'MetaexercicioService','$location', 
    function($scope, ExercicioAtributoService, PerfilfisicoService, AtributoService, MetaexercicioService, $location) {

	$scope.perfilfisicoList = PerfilfisicoService.query(); 


	$scope.createNewPerfilfisicoAdmin = function (perfilfisico) {
		PerfilfisicoService.create(perfilfisico).$promise.then(function(result) {
			$scope.close();
			$scope.perfilfisicoList = PerfilfisicoService.query(); 
		}, function(error) {
			alert(error);
		});
	}

	$scope.addExercicio = function(id) {
		$location.path('/quantidadesfisicas/' + id);
	};

	$scope.verPerfilFisicoAdmin = function(id) {
		$location.path('/verPerfisFisicosAdmin/' + id);
	};

	$scope.addExercicioAdminCreate = function(id) {
		$location.path('/createPerfisFisicosAdmin/' + id);
	};



	// $scope.createNewExercicioAtributo = function (exercicioatributo) {
	// ExercicioAtributoService.create(exercicioatributo).$promise.then(function(result)
	// {
	// $scope.closeExercicio();
	// console.log("estou aqui");
	// $scope.perfilfisicoList = PerfilfisicoService.query();
	// }, function(error) {
	// alert(error);
	// });
	// }
	//
	//
	// $scope.createNewMetaexercicio = function (metaexercicio) {
	// MetaexercicioService.create(metaexercicio).$promise.then(function(result)
	// {
	//
	// //console.log("Hello");
	// console.log("Exercicio - "+ metaexercicio.exercicio);
	// console.log("Perfil - "+ metaexercicio.perfil);
	//
	// $scope.closePlano();
	// $scope.metaexerciciosList = MetaexercicioService.query();
	// }, function(error) {
	// alert(error);
	// });
	// }

	  $scope.deletePerfilfisico = function(id) {
	    	PerfilfisicoService.delete({ id : id }).$promise.then(function(result) {
	        	$scope.perfilfisicoList = PerfilfisicoService.query();
	        	$scope.apagarPerfil();
	        }, function(error) {
	        	alert(error);
	        });
	    };
	//
	// $scope.editExercicioAtributo = function(id) {
	// $location.path('/exercicioatributo/' + id);
	// };
	    
	  // Modal Apagar
		$scope.showModalApagar = false;

		$scope.apagarPerfil = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeDelete = function(){
			$scope.showModalApagar = false;
		}
		    

	// Modal Perfil
	$scope.showModal = false;

	$scope.toggleModal = function() {
		$scope.showModal = !$scope.showModal;
	};

	$scope.close = function(){
		$scope.showModal = false;
	}

	// Modal Quantidades
	$scope.showModalExercicio = false;

	$scope.toggleModalAddExercicio = function() {
		$scope.showModalExercicio = !$scope.showModalExercicio;
	};

	$scope.closeExercicio = function(){
		$scope.showModalExercicio = false;
	}

	// Modal Plano

	$scope.showModalPlano = false;

	$scope.toggleModalAddPlano = function() {
		$scope.showModalPlano = !$scope.showModalPlano;
	};

	$scope.closePlano = function(){
		$scope.showModalPlano = false;
	}


	}]);

quantidadefisicaControllers.controller('QuantidadeFisicaViewController', ['$scope', '$routeParams', 'PerfilfisicoViewService', 'PerfilfisicoUpdateService','MetaexercicioService', 'MetaexercicioPerfilService',
    '$location', function($scope, $routeParams, PerfilfisicoViewService, PerfilfisicoUpdateService, MetaexercicioService,MetaexercicioPerfilService, $location) {

	$scope.metaexerciciosList = MetaexercicioService.query(); 
	$scope.perfilfisico = PerfilfisicoViewService.show({ id: $routeParams.id }); 

	$scope.verExerciciosUserPerfil = function(id) {
    	$scope.showTableExercicios = !$scope.showTableExercicios;
    	$scope.perfilExercicioUserList = MetaexercicioPerfilService.query({id}); 
    	$scope.closeDelete();
	};
	
	

	 $scope.createNewMetaexercicio = function (metaexercicio, id) {
		 metaexercicio.perfil=id;
	    	MetaexercicioService.create(metaexercicio).$promise.then(function(result) {
	    		
	    		// console.log("Hello");
	    		console.log("Exercicio - "+ metaexercicio.exercicio);
	    		console.log("Perfil - "+ metaexercicio.perfil);
	    		
	        	$scope.close();
	    		$scope.metaexerciciosList = MetaexercicioService.query(); 
	    		$scope.perfilExercicioUserList = MetaexercicioPerfilService.query({id}); 
	        }, function(error) {
	        	alert(error);
	        });
	    }
	 
	 $scope.deleteMetaexercicio = function(id) {
	    	MetaexercicioService.delete({ id : id }).$promise.then(function(result) {
	        	$scope.metaexerciciosList = MetaexercicioService.query();
	        	$scope.apagarMetaExercicio();
	        }, function(error) {
	        	alert(error);
	        });
	    };
	 
	 $scope.updatePerfilfisico = function (perfilfisico) {
			PerfilfisicoUpdateService.update(perfilfisico).$promise.then(function() {
				$scope.closeUpdatePerfil();
			}, function(error) {
	    	alert(error);
			});
		}
	 
	 $scope.verMetaExercicio = function(id) {
	    	$location.path('/quantidadesfisicasQuantidade/' + id);
	    };
	    
	    
	$scope.verMetaExercicioAdmin = function(id) {
		    $location.path('/verMetaExerciciosAdmin/' + id);
		 };
		    
	$scope.verCreateMetaExercicioAdmin = function(id) {
			 $location.path('/verCreateMetaExerciciosAdmin/' + id);
		};
		 
	 
		 // Modal Apagar
		$scope.showModalApagar = false;

		$scope.apagarMetaExercicio = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeDelete = function(){
			$scope.showModalApagar = false;
		}
		
		
	 $scope.showModal = false;
	    
		$scope.toggleModalAddExercicio = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
		
// Update Pefil Modal
		$scope.showModalUpdatePerfil = false;

		$scope.toggleModalUpdatePerfil = function() {
			$scope.showModalUpdatePerfil = !$scope.showModalUpdatePerfil;
		};

		$scope.closeUpdatePerfil = function(){
			$scope.showModalUpdatePerfil = false;
		}
}]);

quantidadefisicaControllers.controller('QuantidadeExercicioViewController', ['$scope', '$routeParams', 'ExercicioAtributoService', 'MedidasService', 'MetaexercicioViewService', 'ExercicioAtributoPerfilService',
    '$location', function($scope, $routeParams, ExercicioAtributoService, MedidasService, MetaexercicioViewService, ExercicioAtributoPerfilService, $location) {

	$scope.exercicioatributoList = ExercicioAtributoService.query(); 
	$scope.metaexercicio = MetaexercicioViewService.show({ id: $routeParams.id });
	

	$scope.verQuantidadesExercicioPerfil = function(id) {
    	$scope.showTableAtributosExercicio = !$scope.showTableAtributosExercicio;
    	$scope.exercicioAtributoUserList = ExercicioAtributoPerfilService.query({id}); 
    	$scope.medidasList = MedidasService.query(); 
    	$scope.closeDelete();
    };
    
    $scope.deleteExercicioAtributo = function(id) {
    	ExercicioAtributoService.delete({ id : id }).$promise.then(function(result) {
        	$scope.exercicioatributoList = ExercicioAtributoService.query();
        	$scope.apagarExercicioAtributo();
        }, function(error) {
        	alert(error);
        });
    };
    
    
    
    $scope.getTotalExercicio = function(){
    	var total = 0;
    	var medidas = 0;
    	var y = 0;
    	var peso = 0;
    	var totalAtributo = 0;
// var atributo = 0;
    	for(var i=0; i< $scope.medidasList.length; i++){
    		y++;
    		console.log("Incrementações -- " + y);
    	}
    	
		var medidas = $scope.medidasList[y-1];
		peso = medidas.peso;
    	
    		for(var x=0; x< $scope.exercicioAtributoUserList.length; x++){
    			

    			var atributo = $scope.exercicioAtributoUserList[x];
    			
// console.log("Atributo --"+ atributo.valor);
    			
    			console.log("Peso --"+ medidas.peso);
    			console.log("Atributo --"+ atributo.valor);
    		
    			totalAtributo +=(atributo.valor);
    	}
    		total= peso*totalAtributo;
    	return total;
    }
    
    
    
    
    
    
    $scope.voltarAtras = function(id) {
	    $location.path('/quantidadesfisicas/' + id);
	 };
	 
	    $scope.voltarAtrasAdminExercicio = function(id) {
		    $location.path('/verPerfisFisicosAdmin/' + id);
		 };
		 
		 $scope.voltarAtrasAdminCreateExercicio = function(id) {
			    $location.path('/createPerfisFisicosAdmin/' + id);
			 };

	  $scope.createNewExercicioAtributo = function (exercicioatributo, id) {
		  exercicioatributo.metaexercicio=id;
		  console.log("MetaExercicio == " + id);
		
	    	ExercicioAtributoService.create(exercicioatributo).$promise.then(function(result) {
	        	$scope.closeQuantidade();
	    		$scope.exercicioatributoList = ExercicioAtributoService.query(); 
	    		$scope.exercicioAtributoUserList = ExercicioAtributoPerfilService.query({id}); 
	        }, function(error) {
	        	alert(error);
	        });
	    }
	 
	// Modal Apagar
		$scope.showModalApagar = false;

		$scope.apagarExercicioAtributo = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeDelete = function(){
			$scope.showModalApagar = false;
		}
 
	 
	 $scope.showModalQuantidade = false;
	    
		$scope.toggleModalQuantidadeFisica = function() {
			$scope.showModalQuantidade = !$scope.showModalQuantidade;
		};
		
		$scope.closeQuantidade = function(){
			$scope.showModalQuantidade = false;
		}
}]);

