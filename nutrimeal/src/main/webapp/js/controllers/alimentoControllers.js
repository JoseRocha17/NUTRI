var alimentoControllers = angular.module('alimentoControllers', []);

alimentoControllers.controller('AlimentoListController', ['$scope', 'AlimentoService','$location', 
                                                          function($scope, AlimentoService, $location) {
	
	$scope.alimentoList = AlimentoService.query(); 

	
    $scope.createNewAlimento = function (alimento) {
    	AlimentoService.create(alimento).$promise.then(function(result) {
        	$scope.close();
    		$scope.alimentoList = AlimentoService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteAlimento = function(id) {
    	AlimentoService.delete({ id : id }).$promise.then(function(result) {
    		$scope.apagarAlimento();
        	$scope.alimentoList = AlimentoService.query(); 
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editAlimento = function(id) {
	    	$location.path('/alimento/' + id);
	    };
	    
	    $scope.editAlimentoAdmin = function(id) {
	    	$location.path('/alimentosAdmin/' + id);
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

		$scope.apagarAlimento = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeApagar = function(){
			$scope.showModalApagar = false;
		}
		
	    
    }]);

alimentoControllers.controller('AlimentoViewController', ['$scope', '$routeParams', 'AlimentoViewService', 'AlimentoUpdateService',
                                    '$location', function($scope, $routeParams, AlimentoViewService, AlimentoUpdateService, $location) {
	
	$scope.alimento = AlimentoViewService.show({ id: $routeParams.id }); 
	
    $scope.updateAlimento = function (alimento) {
    	AlimentoUpdateService.update(alimento).$promise.then(function() {
    		$scope.close();
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteAlimento = function(id) {
    	AlimentoViewService.delete({ id : id }).$promise.then(function(result) {
    		$location.path('/alimentos');
        }, function(error) {
        	alert(error);
        });
    };
    
    $scope.showModal = false;
    
	$scope.toggleModal = function() {
		$scope.showModal = !$scope.showModal;
	};
	
	$scope.close = function(){
		$scope.showModal = false;
	}
	
	//Photo
	$scope.showModalPhoto = false;
    
	$scope.toggleModalPhoto = function() {
		$scope.showModalPhoto = !$scope.showModalPhoto;
	};
	
	$scope.closePhoto = function(){
		$scope.showModalPhoto = false;
	}
}]);
