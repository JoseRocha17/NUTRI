var grupoControllers = angular.module('grupoControllers', []);

grupoControllers.controller('GrupoListController', ['$scope', 'GrupoService', 'AlimentoService', 'AlimentoGrupoService','$location', 
                                                          function($scope, GrupoService, AlimentoService,  AlimentoGrupoService, $location) {
	
	$scope.gruposList = GrupoService.query(); 

	
    $scope.createNewGrupo = function (grupo) {
    	GrupoService.create(grupo).$promise.then(function(result) {
        	$scope.close();
    		$scope.gruposList = GrupoService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteGrupo = function(id) {
    	GrupoService.delete({ id : id }).$promise.then(function(result) {
    		$scope.apagarGrupo();
        	$scope.gruposList = GrupoService.query();         	
        }, function(error) {
        	alert(error);
        });
    };
    
    
    

    
    $scope.verAlimentosGrupo = function(id) {
    	$scope.showTableGrupoAlimentos = !$scope.showTableGrupoAlimentos;
    	$scope.alimentoGrupoList = AlimentoGrupoService.query({id}); 
// $scope.showModalApagar = false;
    };

	    $scope.editGrupo = function(id) {
	    	$location.path('/criarGruposAlimentaresAdmin/' + id);
	    };
	    
	    $scope.editAlimento = function(id) {
	    	$location.path('/alimentoInformacao/' + id);
	    };
	    
	    
//	    Modal Create
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
		

		
		// Modal Apagar
		$scope.showModalApagar = false;

		$scope.apagarGrupo = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeApagar = function(){
			$scope.showModalApagar = false;
		}
		
	    
    }]);

grupoControllers.controller('GrupoViewController', ['$scope', '$routeParams', 'GrupoUpdateService','GrupoViewService', 'GrupoUpdateService',
                                    '$location', function($scope, $routeParams, GrupoUpdateService,  GrupoViewService, GrupoUpdateService, $location) {
	
	$scope.grupo = GrupoViewService.show({ id: $routeParams.id }); 
    
    $scope.updateGrupo = function (grupo) {
		GrupoUpdateService.update(grupo).$promise.then(function() {
			$scope.closeUpdate();
		}, function(error) {
    	alert(error);
		});
	}
    
   
//	Modal Update
	$scope.showModalUpdate = false;
    
	$scope.toggleModalUpdateGrupo = function() {
		$scope.showModalUpdate = !$scope.showModalUpdate;
	};
	
	$scope.closeUpdate = function(){
		$scope.showModalUpdate = false;
	}
	

}]);
