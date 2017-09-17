var roleControllers = angular.module('roleControllers', []);

roleControllers.controller('RoleController', function($scope, $routeParams, setAdminService, $location) {

    $scope.setAdmin = function (user) {
    	setAdminService.setAdmin(user).$promise.then(function() {
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

