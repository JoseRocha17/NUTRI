var objetivosControllers = angular.module('objetivosControllers', []);

objetivosControllers.controller('ObjetivosListController', ['$scope', 'ObjetivosService','$location', 
                                                          function($scope, ObjetivosService, $location) {
	
	$scope.objetivosList = ObjetivosService.query(); 

	
    $scope.createNewObjetivo = function (objetivo) {
    	ObjetivosService.create(objetivo).$promise.then(function(result) {
        	$scope.close();
    		$scope.objetivosList = ObjetivosService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.createNewObjetivoUser = function (objetivo, email) {
    	objetivo.user=email;
    	ObjetivosService.create(objetivo).$promise.then(function(result) {
        	$scope.close();
    		$scope.objetivosList = ObjetivosService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteObjetivo = function(id) {
    	ObjetivosService.delete({ id : id }).$promise.then(function(result) {
        	$scope.objetivosList = ObjetivosService.query(); 
        	$scope.apagarObjetivos();
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editObjetivo = function(id) {
	    	$location.path('/objetivo/' + id);
	    };
	    
	    $scope.editObjetivoUser = function(id) {
	    	$location.path('/PlaneamentoObjetivos/' + id);
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

		$scope.apagarObjetivos = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeApagar = function(){
			$scope.showModalApagar = false;
		}
	    
    }]);

objetivosControllers.controller('ObjetivosViewController', ['$scope', '$routeParams', 'ObjetivosViewService',  'UserService',
    '$location', function($scope, $routeParams, ObjetivosViewService,  UserService, $location) {


	$scope.userList = UserService.query(); 
	$scope.objetivo = ObjetivosViewService.show({ id: $routeParams.id }); 


	$scope.deleteObjetivo = function(id) {
		ObjetivosViewService.delete({ id : id }).$promise.then(function(result) {
			$location.path('/objetivos');
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



objetivosControllers.controller('ObjetivosUpdateController', function($scope, $routeParams, ObjetivosUpdateService, $location) {

	$scope.updateObjetivo = function (objetivo) {
		ObjetivosUpdateService.update(objetivo).$promise.then(function() {
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