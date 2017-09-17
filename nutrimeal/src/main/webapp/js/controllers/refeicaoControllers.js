var refeicaoControllers = angular.module('refeicaoControllers', []);

refeicaoControllers.controller('RefeicaoListController', ['$scope', 'RefeicaoService','PerfilalimentarService','$location', 
                                                          function($scope, RefeicaoService, PerfilalimentarService, $location) {
	$scope.perfilalimentarList = PerfilalimentarService.query(); 

	
    $scope.createNewPerfilalimentar = function (perfilalimentar) {
    	PerfilalimentarService.create(perfilalimentar).$promise.then(function(result) {
        	$scope.close();
    		$scope.perfilalimentarList = PerfilalimentarService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
	$scope.refeicaoList = RefeicaoService.query(); 

	
    $scope.createNewRefeicao = function (refeicao) {
    	RefeicaoService.create(refeicao).$promise.then(function(result) {
        	$scope.close();
    		$scope.refeicaoList = RefeicaoService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteRefeicao = function(id) {
    	RefeicaoService.delete({ id : id }).$promise.then(function(result) {
        	$scope.refeicaoList = RefeicaoService.query(); 
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editRefeicao = function(id) {
	    	$location.path('/refeicao/' + id);
	    };
	    
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
	    
    }]);

refeicaoControllers.controller('RefeicaoViewController', ['$scope', '$routeParams', 'RefeicaoViewService', 'PerfilalimentarService', 
	'$location', function($scope, $routeParams, RefeicaoViewService, PerfilalimentarService, $location) {

	$scope.perfilalimentarList = PerfilalimentarService.query();
	$scope.refeicao = RefeicaoViewService.show({ id: $routeParams.id });



	$scope.deleteRefeicao = function(id) {
		RefeicaoViewService.delete({ id : id }).$promise.then(function(result) {
			$location.path('/refeicao');
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



refeicaoControllers.controller('RefeicaoUpdateController', function($scope, $routeParams, RefeicaoUpdateService, $location) {

	$scope.updateRefeicao = function (refeicao) {
		RefeicaoUpdateService.update(refeicao).$promise.then(function() {
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