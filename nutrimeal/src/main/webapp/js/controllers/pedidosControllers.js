var pedidosControllers = angular.module('pedidosControllers', []);

pedidosControllers.controller('PedidosListController', ['$scope', 'PedidosService','$location', 
                                                          function($scope, PedidosService, $location) {
	
	$scope.pedidosList = PedidosService.query(); 

	
    $scope.createNewPedidos = function (pedidos) {
    	
    	PedidosService.create(pedidos).$promise.then(function(result) {

        	$scope.close();
    		$scope.pedidosList = PedidosService.query();
    		$location.path('/main');
    		
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deletePedidos = function(id) {
    	PedidosService.delete({ id : id }).$promise.then(function(result) {
        	$scope.pedidosList = PedidosService.query();
        	$scope.apagarPedidos();
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editPedidos = function(id) {
	    	$location.path('/pedidosAdmin/' + id);
	    };
	    
	    
		$scope.showModal = false;
	    
		$scope.toggleModal = function() {
			$scope.showModal = !$scope.showModal;
		};
		
		$scope.close = function(){
			$scope.showModal = false;
		}
		

		// Modal Apagar
		$scope.showModalDone = false;

		$scope.successPedido = function() {
			$scope.showModalDone = !$scope.showModalDone;
			
		};
		
		$scope.closeDone = function(){
			
			
		}
		
    }]);

pedidosControllers.controller('PedidosAdminViewController', ['$scope', '$routeParams', 'PedidosViewService', 'PedidosUpdateService', 'PedidosUpdateService',
                                    '$location', function($scope, $routeParams, PedidosViewService, PedidosUpdateService, PedidosUpdateService, $location) {
	
	$scope.pedidos = PedidosViewService.show({ id: $routeParams.id }); 

      
    $scope.deletePedidos = function(id) {
    	PedidosViewService.delete({ id : id }).$promise.then(function(result) {
    		$location.path('/pedidos');
    	
        }, function(error) {
        	alert(error);
        });
    };
    
 
}]);



