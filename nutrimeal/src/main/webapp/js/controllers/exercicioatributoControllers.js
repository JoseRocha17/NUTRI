var exercicioatributoControllers = angular.module('exercicioatributoControllers', []);

exercicioatributoControllers.controller('ExercicioAtributoListController', ['$scope', 'ExercicioAtributoService','$location', 
                                                          function($scope, ExercicioAtributoService, $location) {
	
	$scope.exercicioatributoList = ExercicioAtributoService.query(); 

	
    $scope.createNewExercicioAtributo = function (exercicioatributo) {
    	ExercicioAtributoService.create(exercicioatributo).$promise.then(function(result) {
        	$scope.close();
    		$scope.exercicioatributoList = ExercicioAtributoService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
  
    
    
    $scope.deleteExercicioAtributo = function(id) {
    	ExercicioAtributoService.delete({ id : id }).$promise.then(function(result) {
        	$scope.exercicioatributoList = ExercicioAtributoService.query(); 
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editExercicioAtributo = function(id) {
	    	$location.path('/exercicioatributo/' + id);
	    };
	    
	    
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
		
		
	
		
//		$scope.selectedFruits = function selectedFruits() {
//		    return ExercicioAtributoService($scope.atributoList = AtributoService.query(), { selected: true });
//		  };

		  // Watch fruits for changes
//		  $scope.$watch('atributoList|filter:{selected:true}', function (nv) {
//		    $scope.selection = nv.map(function (atributo) {
//		      return atributo.id;
//		    });
//		  }, true);
		
//		$scope.atributoList = AtributoService.query()
//
//		  // Selected fruits
//		  $scope.selection = [];
//
//		  // Toggle selection for a given fruit by name
//		  $scope.toggleSelection = function toggleSelection(atributo) {
//		    var idx = $scope.selection.indexOf(atributo);
//
//		    // Is currently selected
//		    if (idx > -1) {
//		      $scope.selection.splice(idx, 1);
//		    }
//
//		    // Is newly selected
//		    else {
//		      $scope.selection.push(atributo);
//		    }
//		  };
		
//		$scope.atributoList = AtributoService.query()
//		
//		$scope.selection=[];
//		  // toggle selection for a given employee by name
//		  $scope.toggleSelection = function toggleSelection(atributo) {
//		     var idx = $scope.selection.indexOf(atributo);
//		 
//		     // is currently selected
//		     if (idx > -1) {
//		       $scope.selection.splice(idx, 1);
//		     }
//		 
//		     // is newly selected
//		     else {
//		       $scope.selection.push(atributo);
//		     }
//		   };

	    
    }]);

exercicioatributoControllers.controller('ExercicioAtributoViewController', ['$scope', '$routeParams', 'ExercicioAtributoViewService', 'ExercicioAtributoUpdateService',
                                    '$location', function($scope, $routeParams, ExercicioAtributoViewService, ExercicioAtributoUpdateService, $location) {
	
	$scope.exercicioatributo = ExercicioAtributoViewService.show({ id: $routeParams.id }); 
	
    $scope.updateExercicioAtributo = function (exercicioatributo) {
    	ExercicioAtributoUpdateService.update(exercicioatributo).$promise.then(function() {
    		$scope.close();
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteExercicioAtributo = function(id) {
    	ExercicioAtributoViewService.delete({ id : id }).$promise.then(function(result) {
    		$location.path('/exercicioatributos');
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
