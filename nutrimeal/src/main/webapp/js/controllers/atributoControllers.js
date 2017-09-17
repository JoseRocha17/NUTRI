var atributoControllers = angular.module('atributoControllers', []);

atributoControllers.controller('AtributoListController', ['$scope', 'AtributoService','$location', 
                                                          function($scope, AtributoService, $location) {
	
	$scope.atributoList = AtributoService.query(); 

	
    $scope.createNewAtributo = function (atributo) {
    	AtributoService.create(atributo).$promise.then(function(result) {
        	$scope.close();
    		$scope.atributoList = AtributoService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteAtributo = function(id) {
    	AtributoService.delete({ id : id }).$promise.then(function(result) {
    		$scope.apagarAtributo();
        	$scope.atributoList = AtributoService.query(); 
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editAtributo = function(id) {
	    	$location.path('/criarAtributosAdmin/' + id);
	    };
	    
	    
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
		
		// Modal Apagar
		$scope.showModalApagar = false;

		$scope.apagarAtributo = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeApagar = function(){
			$scope.showModalApagar = false;
		}
		
	    
    }]);

atributoControllers.controller('AtributoViewController', ['$scope', '$routeParams', 'AtributoViewService', 'AtributoUpdateService',
                                    '$location', function($scope, $routeParams, AtributoViewService, AtributoUpdateService, $location) {
	
	$scope.atributo = AtributoViewService.show({ id: $routeParams.id }); 
	
    $scope.updateAtributo = function (atributo) {
    	AtributoUpdateService.update(atributo).$promise.then(function() {
    		$scope.close();
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteAtributo = function(id) {
    	AtributoViewService.delete({ id : id }).$promise.then(function(result) {
    		$location.path('/criarAtributosAdmin');
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
	

}]);
