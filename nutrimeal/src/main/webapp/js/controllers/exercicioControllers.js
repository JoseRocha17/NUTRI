var exercicioControllers = angular.module('exercicioControllers', []);

exercicioControllers.controller('ExercicioListController', ['$scope', 'ExercicioService','$location', 
                                                          function($scope, ExercicioService, $location) {
	
	$scope.exercicioList = ExercicioService.query(); 

	
    $scope.createNewExercicio = function (exercicio) {
    	ExercicioService.create(exercicio).$promise.then(function(result) {
    		$scope.exercicioList = ExercicioService.query(); 
        	$scope.close();

        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteExercicio = function(id) {
    	ExercicioService.delete({ id : id }).$promise.then(function(result) {
        	$scope.exercicioList = ExercicioService.query(); 
        	$scope.apagarExercicio();
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editExercicio = function(id) {
	    	$location.path('/exerciciosAdmin/' + id);
	    };
	    
	    $scope.editExercicioUser = function(id) {
			$location.path('/exercicioInformacao/' + id);
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

		$scope.apagarExercicio = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeApagar = function(){
			$scope.showModalApagar = false;
		}
		
    }]);

exercicioControllers.controller('ExercicioViewController', ['$scope', '$routeParams', 'ExercicioViewService',
    '$location', function($scope, $routeParams, ExercicioViewService, $location) {


	$scope.exercicio = ExercicioViewService.show({ id: $routeParams.id }); 


	$scope.deleteExercicio = function(id) {
		ExercicioViewService.delete({ id : id }).$promise.then(function(result) {
			$location.path('/exercicios');
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
	
	//Foto
	$scope.showModalPhoto = false;
    
	$scope.toggleModalPhoto = function() {
		$scope.showModalPhoto = !$scope.showModalPhoto;
	};
	
	$scope.closePhoto = function(){
		$scope.showModalPhoto = false;
	}

}]);



exercicioControllers.controller('ExercicioUpdateController', function($scope, $routeParams, ExercicioUpdateService, $location) {

	$scope.updateExercicio = function (exercicio) {
		ExercicioUpdateService.update(exercicio).$promise.then(function() {
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