var quantidadealimentarControllers = angular.module('quantidadealimentarControllers', []);

quantidadealimentarControllers.controller('QuantidadealimentarListController', ['$scope', 'QuantidadealimentarService','PerfilalimentarService','$location', 
                                                          function($scope, QuantidadealimentarService, PerfilalimentarService, $location) {
	
	$scope.quantidadealimentaresList = QuantidadealimentarService.query(); 
	

	
    $scope.createNewQuantidadealimentar = function (quantidadealimentar) {
    	QuantidadealimentarService.create(quantidadealimentar).$promise.then(function(result) {
        	$scope.close();
    		$scope.quantidadealimentaresList = QuantidadealimentarService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteQuantidadealimentar = function(id) {
    	QuantidadealimentarService.delete({ id : id }).$promise.then(function(result) {
        	$scope.quantidadealimentaresList = QuantidadealimentarService.query(); 
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editQuantidadealimentar = function(id) {
	    	$location.path('/quantidadealimentares/' + id);
	    };
	    
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
		

	    
    }]);

quantidadealimentarControllers.controller('QuantidadealimentarViewController', ['$scope', '$routeParams', 'QuantidadealimentarViewService',  'RefeicaoService', 'AlimentoService',
    '$location', function($scope, $routeParams, QuantidadealimentarViewService,  RefeicaoService, AlimentoService, $location) {


	$scope.refeicaoList = RefeicaoService.query(); 
	$scope.alimentoList = AlimentoService.query(); 
	$scope.quantidadealimentar = QuantidadealimentarViewService.show({ id: $routeParams.id }); 


	$scope.deleteQuantidadealimentar = function(id) {
		QuantidadealimentarViewService.delete({ id : id }).$promise.then(function(result) {
			$location.path('/quantidadealimentares');
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



quantidadealimentarControllers.controller('QuantidadealimentarUpdateController', function($scope, $routeParams, QuantidadealimentarUpdateService, $location) {

	$scope.updateQuantidadealimentar = function (quantidadealimentar) {
		QuantidadealimentarUpdateService.update(quantidadealimentar).$promise.then(function() {
			$scope.close();
		}, function(error) {
    	alert(error);
		});
	}

	$scope.showModal = false;

	$scope.toggleModal = function() {
		$scope.showModal = !$scope.showModal;
	};

	$scope.close = function(){
		$scope.showModal = false;
	}

});