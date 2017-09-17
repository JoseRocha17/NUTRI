var novidadeControllers = angular.module('novidadeControllers', []);

novidadeControllers.controller('NovidadeListController', ['$scope', 'NovidadeService','$location', 
                                                          function($scope, NovidadeService, $location) {
	
	$scope.novidadesList = NovidadeService.query(); 

	
    $scope.createNewNovidade = function (novidade) {
    	
    	NovidadeService.create(novidade).$promise.then(function(result) {
    		console.log("Titulo" + novidade.titulo);
    		console.log("Titulo" + novidade.brevedesc);
    		console.log("Titulo" + novidade.descricao);
    		console.log("Titulo" + novidade.data);
        	$scope.close();
    		$scope.novidadesList = NovidadeService.query();
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteNovidade = function(id) {
    	NovidadeService.delete({ id : id }).$promise.then(function(result) {
        	$scope.novidadesList = NovidadeService.query();
        	$scope.apagarNovidade();
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editNovidade = function(id) {
	    	$location.path('/novidadesAdmin/' + id);
	    };
	    
	    $scope.editNovidadeUser = function(id) {
	    	$location.path('/novidades/' + id);
	    };
	    
//	    Create Modal
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
		
		// Modal Apagar
		$scope.showModalApagar = false;

		$scope.apagarNovidade = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeApagar = function(){
			$scope.showModalApagar = false;
		}
		
		//Foto
		$scope.showModalPhoto = false;
	    
		$scope.toggleModalPhoto = function() {
			$scope.showModalPhoto = !$scope.showModalPhoto;
		};
		
		$scope.closePhoto = function(){
			$scope.showModalPhoto = false;
		}
	    
    }]);

novidadeControllers.controller('NovidadeAdminViewController', ['$scope', '$routeParams', 'NovidadeUpdateService', 'NovidadeViewService', 'NovidadeUpdateService',
                                    '$location', function($scope, $routeParams, NovidadeUpdateService, NovidadeViewService, NovidadeUpdateService, $location) {
	
	$scope.novidade = NovidadeViewService.show({ id: $routeParams.id }); 

    $scope.updateNovidade = function (novidade) {
    	NovidadeUpdateService.update(novidade).$promise.then(function() {
    		$scope.close();
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteNovidade = function(id) {
    	NovidadeViewService.delete({ id : id }).$promise.then(function(result) {
    		$location.path('/novidades');
        }, function(error) {
        	alert(error);
        });
    };
    
    $scope.updateNovidade = function (novidade) {
		NovidadeUpdateService.update(novidade).$promise.then(function() {
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
	
	//Photo
	$scope.showModalPhoto = false;
    
	$scope.toggleModalPhoto = function() {
		$scope.showModalPhoto = !$scope.showModalPhoto;
	};
	
	$scope.closePhoto = function(){
		$scope.showModalPhoto = false;
	}
}]);

novidadeControllers.controller('NovidadeUpdateController', function($scope, $routeParams, NovidadeUpdateService, $location) {

	$scope.updateNovidade = function (novidade) {
		NovidadeUpdateService.update(novidade).$promise.then(function() {
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

