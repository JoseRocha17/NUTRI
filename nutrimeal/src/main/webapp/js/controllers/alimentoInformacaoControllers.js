var alimentoInformacaoControllers = angular.module('alimentoInformacaoControllers', []);

alimentoInformacaoControllers.controller('AlimentoInformacaoListController', ['$scope', 'AlimentoService', 'AlimentoGrupoService','$location', 
                                                          function($scope, AlimentoService, AlimentoGrupoService, $location) {
	
	$scope.alimentoList = AlimentoService.query(); 

	
    $scope.createNewAlimento = function (alimento) {
    	AlimentoService.create(alimento).$promise.then(function(result) {
        	$scope.close();
    		$scope.alimentoList = AlimentoService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
      
   
	    $scope.editAlimento = function(id) {
	    	$location.path('/alimentoInformacao/' + id);
	    };
	    
	    
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
	    
    }]);



alimentoInformacaoControllers.controller('AlimentoInformacaoViewController', ['$scope', '$routeParams', 'AlimentoViewService', 'AlimentoUpdateService',
    '$location', function($scope, $routeParams, AlimentoViewService, AlimentoUpdateService, $location) {

$scope.alimento = AlimentoViewService.show({ id: $routeParams.id }); 

$scope.updateAlimento = function (alimento) {
AlimentoUpdateService.update(alimento).$promise.then(function() {
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

// Photo
$scope.showModalPhoto = false;

$scope.toggleModalPhoto = function() {
$scope.showModalPhoto = !$scope.showModalPhoto;
};

$scope.closePhoto = function(){
$scope.showModalPhoto = false;
}
}]);
