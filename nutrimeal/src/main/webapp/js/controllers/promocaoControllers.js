var promocaoControllers = angular.module('promocaoControllers', []);

promocaoControllers.controller('PromocaoListController', ['$scope', 'PromocaoService','$location', 
                                                          function($scope, PromocaoService, $location) {
	
	$scope.promocoesList = PromocaoService.query(); 

	
    $scope.createNewPromocao = function (promocao) {
    	
    	PromocaoService.create(promocao).$promise.then(function(result) {
//    		console.log("Titulo" + promocao.titulo);
//    		console.log("Titulo" + promocao.brevedesc);
//    		console.log("Titulo" + promocao.descricao);
//    		console.log("Titulo" + promocao.data);
        	$scope.close();
    		$scope.promocoesList = PromocaoService.query();
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deletePromocao = function(id) {
    	PromocaoService.delete({ id : id }).$promise.then(function(result) {
        	$scope.promocoesList = PromocaoService.query();
        	$scope.apagarPromocao();
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editPromocao = function(id) {
	    	$location.path('/promocoesAdmin/' + id);
	    };
	    
	    $scope.editPromocaoUser = function(id) {
	    	$location.path('/promocoes/' + id);
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

		$scope.apagarPromocao = function() {
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

promocaoControllers.controller('PromocaoAdminViewController', ['$scope', '$routeParams', 'PromocaoViewService', 'PromocaoUpdateService', 'PromocaoUpdateService',
                                    '$location', function($scope, $routeParams, PromocaoViewService, PromocaoUpdateService, PromocaoUpdateService, $location) {
	
	$scope.promocao = PromocaoViewService.show({ id: $routeParams.id }); 

    $scope.updatePromocao = function (promocao) {
    	PromocaoUpdateService.update(promocao).$promise.then(function() {
    		$scope.close();
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.updatePromocao = function (promocao) {
		PromocaoUpdateService.update(promocao).$promise.then(function() {
			$scope.close();
		}, function(error) {
    	alert(error);
		});
	}
    
    $scope.deletePromocao = function(id) {
    	PromocaoViewService.delete({ id : id }).$promise.then(function(result) {
    		$location.path('/promocoes');
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

promocaoControllers.controller('PromocaoUpdateController', function($scope, $routeParams, PromocaoUpdateService, $location) {

	$scope.updatePromocao = function (promocao) {
		PromocaoUpdateService.update(exercicio).$promise.then(function() {
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

