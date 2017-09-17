var medidasControllers = angular.module('medidasControllers', []);

medidasControllers.controller('MedidasListController', ['$scope', 'MedidasService','$location', 
                                                          function($scope, MedidasService, $location) {
	
	$scope.medidasList = MedidasService.query(); 

	
    $scope.createNewMedida = function (medida) {
    	MedidasService.create(medida).$promise.then(function(result) {
        	$scope.close();
    		$scope.medidasList = MedidasService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.createNewMedidaUser = function (medida, email) {
    	medida.user=email;
    	MedidasService.create(medida).$promise.then(function(result) {
        	$scope.close();
    		$scope.medidasList = MedidasService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteMedida = function(id) {
    	MedidasService.delete({ id : id }).$promise.then(function(result) {
        	$scope.medidasList = MedidasService.query(); 
        	$scope.apagarMedidas();
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editMedida = function(id) {
	    	$location.path('/medida/' + id);
	    };
	    
	    $scope.editMedidaUser = function(id) {
	    	$location.path('/PlaneamentoMedicoes/' + id);
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

		$scope.apagarMedidas = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeApagar = function(){
			$scope.showModalApagar = false;
		}
	    
    }]);

medidasControllers.controller('MedidasViewController', ['$scope', '$routeParams', 'MedidasViewService',  'UserService',
    '$location', function($scope, $routeParams, MedidasViewService,  UserService, $location) {


	$scope.userList = UserService.query(); 
	$scope.medida = MedidasViewService.show({ id: $routeParams.id }); 


	$scope.deleteMedida = function(id) {
		MedidasViewService.delete({ id : id }).$promise.then(function(result) {
			$location.path('/medidas');
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



medidasControllers.controller('MedidasUpdateController', function($scope, $routeParams, MedidasUpdateService, $location) {

	$scope.updateMedida = function (medida) {
		MedidasUpdateService.update(medida).$promise.then(function() {
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