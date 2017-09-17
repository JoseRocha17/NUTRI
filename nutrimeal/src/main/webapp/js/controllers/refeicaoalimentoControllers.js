var refeicaoalimentoControllers = angular.module('refeicaoalimentoControllers', []);

refeicaoalimentoControllers.controller('RefeicaoalimentoListController', ['$scope', 'RefeicaoalimentoService','$location', 
                                                          function($scope, RefeicaoalimentoService, $location) {
	
	$scope.refeicaoalimentosList = RefeicaoalimentoService.query(); 

	
    $scope.createNewRefeicaoalimento = function (refeicaoalimento) {
    	RefeicaoalimentoService.create(refeicaoalimento).$promise.then(function(result) {
        	$scope.close();
    		$scope.refeicaoalimentosList = RefeicaoalimentoService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteRefeicaoalimento = function(id) {
    	RefeicaoalimentoService.delete({ id : id }).$promise.then(function(result) {
        	$scope.refeicaoalimentosList = RefeicaoalimentoService.query(); 
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editRefeicaoalimento = function(id) {
	    	$location.path('/refeicaoalimentos/' + id);
	    };
	    
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
	    
    }]);

refeicaoalimentoControllers.controller('RefeicaoalimentoViewController', ['$scope', '$routeParams', 'RefeicaoalimentoViewService',  'RefeicaoService', 'AlimentoService',
    '$location', function($scope, $routeParams, RefeicaoalimentoViewService,  RefeicaoService, AlimentoService, $location) {


	$scope.refeicaoList = RefeicaoService.query(); 
	$scope.AlimentoList = AlimentoService.query(); 
	$scope.refeicaoalimento = RefeicaoalimentoViewService.show({ id: $routeParams.id }); 


	$scope.deleteRefeicaoalimento = function(id) {
		RefeicaoalimentoViewService.delete({ id : id }).$promise.then(function(result) {
			$location.path('/refeicaoalimentos');
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



refeicaoalimentoControllers.controller('RefeicaoalimentoUpdateController', function($scope, $routeParams, RefeicaoalimentoUpdateService, $location) {

	$scope.updateRefeicaoalimento = function (refeicaoalimento) {
		RefeicaoalimentoUpdateService.update(refeicaoalimento).$promise.then(function() {
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