var perfilalimentarControllers = angular.module('perfilalimentarControllers', []);

//perfilalimentarControllers.controller('PerfilalimentarListController', ['$scope', 'PerfilalimentarService','$location', 
//                                                          function($scope, PerfilalimentarService, $location) {
//	
//	$scope.perfilalimentarList = PerfilalimentarService.query(); 
//
//	
//    $scope.createNewPerfilalimentar = function (perfilalimentar) {
//    	PerfilalimentarService.create(perfilalimentar).$promise.then(function(result) {
//        	$scope.close();
//    		$scope.perfilalimentarList = PerfilalimentarService.query(); 
//        }, function(error) {
//        	alert(error);
//        });
//    }
//    
//    $scope.deletePerfilalimentar = function(id) {
//    	PerfilalimentarService.delete({ id : id }).$promise.then(function(result) {
//        	$scope.perfilalimentarList = PerfilalimentarService.query(); 
//        }, function(error) {
//        	alert(error);
//        });
//    };
//
//	    $scope.editPerfilalimentar = function(id) {
//	    	$location.path('/perfilalimentares/' + id);
//	    };
//	    
//		$scope.showModal = false;
//	    
//		$scope.toggleModal = function() {
//			$scope.showModal = !$scope.showModal;
//		};
//		
//		$scope.close = function(){
//			$scope.showModal = false;
//		}
//	    
//    }]);

perfilalimentarControllers.controller('PerfilalimentarViewController', ['$scope', '$routeParams', 'PerfilalimentarViewService',  'PessoaService',
    '$location', function($scope, $routeParams, PerfilalimentarViewService,  PessoaService, $location) {


	$scope.pessoaList = PessoaService.query(); 
	$scope.perfilalimentar = PerfilalimentarViewService.show({ id: $routeParams.id }); 


	$scope.deletePerfilalimentar = function(id) {
		PerfilalimentarViewService.delete({ id : id }).$promise.then(function(result) {
			$location.path('/perfilalimentares');
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



//perfilalimentarControllers.controller('PerfilalimentarUpdateController', function($scope, $routeParams, PerfilalimentarUpdateService, $location) {
//
//	$scope.updatePerfilalimentar = function (perfilalimentar) {
//		PerfilalimentarUpdateService.update(perfilalimentar).$promise.then(function() {
//			$scope.close();
//		}, function(error) {
//    	alert(error);
//		});
//	}
//
////	Modal Create
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
////	Modal Update
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