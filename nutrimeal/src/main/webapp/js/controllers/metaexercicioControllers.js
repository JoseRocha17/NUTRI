var metaexercicioControllers = angular.module('metaexercicioControllers', []);

metaexercicioControllers.controller('MetaexercicioListController', ['$scope', 'MetaexercicioService', 'ExercicioService', 'PerfilfisicoService','$location', 
                                                          function($scope, MetaexercicioService, ExercicioService, PerfilfisicoService, $location) {
	
	$scope.metaexerciciosList = MetaexercicioService.query(); 
	

	
    $scope.createNewMetaexercicio = function (metaexercicio) {
    	MetaexercicioService.create(metaexercicio).$promise.then(function(result) {
    		
    		//console.log("Hello");
    		console.log("Exercicio - "+ metaexercicio.exercicio);
    		console.log("Perfil - "+ metaexercicio.perfil);
    		
        	$scope.close();
    		$scope.metaexerciciosList = MetaexercicioService.query(); 
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteMetaexercicio = function(id) {
    	MetaexercicioService.delete({ id : id }).$promise.then(function(result) {
        	$scope.metaexerciciosList = MetaexercicioService.query();
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editMetaexercicio = function(id) {
	    	$location.path('/planosfisicos/' + id);
	    };
	    
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
		

	    
    }]);

metaexercicioControllers.controller('MetaexercicioViewController', ['$scope', '$routeParams', 'MetaexercicioViewService',  'ExercicioService', 'PerfilfisicoService',
    '$location', function($scope, $routeParams, MetaexercicioViewService,  ExercicioService, PerfilfisicoService, $location) {


	$scope.exercicioList = ExercicioService.query(); 
	$scope.perfilfisicoList = PerfilfisicoService.query(); 
	$scope.metaexercicio = MetaexercicioViewService.show({ id: $routeParams.id }); 


	$scope.deleteMetaexercicio = function(id) {
		MetaexercicioViewService.delete({ id : id }).$promise.then(function(result) {
			$location.path('/planosfisicos');
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



metaexercicioControllers.controller('MetaexercicioUpdateController', function($scope, $routeParams, MetaexercicioUpdateService, $location) {

	$scope.updateMetaexercicio = function (metaexercicio) {
		console.log("Calorias - "+ metaexercicio.calorias);
		MetaexercicioUpdateService.update(metaexercicio).$promise.then(function() {
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