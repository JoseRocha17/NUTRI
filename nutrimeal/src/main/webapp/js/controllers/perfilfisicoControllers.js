var perfilfisicoControllers = angular.module('perfilfisicoControllers', []);

perfilfisicoControllers.controller('PerfilfisicoListController', ['$scope', 'PerfilfisicoService','$location', 
                                                          function($scope, PerfilfisicoService, $location) {
	
	$scope.perfilfisicoList = PerfilfisicoService.query(); 

	
    $scope.createNewPerfilfisico = function (perfilfisico) {
    	PerfilfisicoService.create(perfilfisico).$promise.then(function(result) {
        	$scope.close();
    		$scope.perfilfisicoList = PerfilfisicoService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deletePerfilfisico = function(id) {
    	PerfilfisicoService.delete({ id : id }).$promise.then(function(result) {
        	$scope.perfilfisicoList = PerfilfisicoService.query(); 
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editPerfilfisico = function(id) {
	    	$location.path('/perfilfisicos/' + id);
	    };
	    
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
	    
    }]);

perfilfisicoControllers.controller('PerfilfisicoViewController', ['$scope', '$routeParams', 'PerfilfisicoViewService',  'PessoaService',
    '$location', function($scope, $routeParams, PerfilfisicoViewService,  PessoaService, $location) {


	$scope.pessoaList = PessoaService.query(); 
	$scope.perfilfisico = PerfilfisicoViewService.show({ id: $routeParams.id }); 


	$scope.deletePerfilfisico = function(id) {
		PerfilfisicoViewService.delete({ id : id }).$promise.then(function(result) {
			$location.path('/perfilfisicos');
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



//perfilfisicoControllers.controller('PerfilfisicoUpdateController', function($scope, $routeParams, PerfilfisicoUpdateService, $location) {
//
//	$scope.updatePerfilfisico = function (perfilfisico) {
//		PerfilfisicoUpdateService.update(perfilfisico).$promise.then(function() {
//			$scope.close();
//		}, function(error) {
//    	alert(error);
//		});
//	}
//
//	$scope.showModal = false;
//
//	$scope.toggleModal = function() {
//		$scope.showModal = !$scope.showModal;
//	};
//
//	$scope.close = function(){
//		$scope.showModal = false;
//	}
//
//});