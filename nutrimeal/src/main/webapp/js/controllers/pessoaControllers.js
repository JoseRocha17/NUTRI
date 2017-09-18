var pessoaControllers = angular.module('pessoaControllers', []);

pessoaControllers.controller('PessoaListController', ['$scope', 'PessoaService', '$location', function($scope, PessoaService, $location) {
	
	$scope.pessoaList = PessoaService.query(); 
	
    $scope.createNewPessoa = function (pessoa) {
    	PessoaService.create(pessoa).$promise.then(function(result) {
    		location.reload();
        }, function(error) {
        	alert(error);
        });
    }
	
	 $scope.deletePessoa = function(email) {
		 PessoaService.delete({ email : email }).$promise.then(function(result) {
	        	$scope.pessoaList = PessoaService.query(); 
	        }, function(error) {
	        	alert(error);
	        });
	    };

	    $scope.editPessoa = function(email) {
	    	$location.path('/pessoa/' + email);
	    };
	    
	    $scope.orderBy = function (row){
			$scope.rowOrdered = row;
			$scope.orderDirection = !$scope.orderDirection;
		};

}]);

pessoaControllers.controller('PessoaViewController', ['$scope', '$routeParams', 'PessoaViewService', 
                                                      '$location', function($scope, $routeParams, PessoaViewService, $location) {
                  	
                  	$scope.pessoa = PessoaViewService.show({ email: $routeParams.email }); 
                  	
                      $scope.deletePessoa = function(email) {
                      	PessoaViewService.delete({ email : email }).$promise.then(function(result) {
                      		$location.path('/pessoas');
                          }, function(error) {
                          	alert(error);
                          });
                      };
                  }]);

pessoaControllers.controller('PessoaUpdateController', function($scope, $routeParams, PessoaUpdateService, $location) {


    $scope.updatePessoa = function (pessoa) {
    	PessoaUpdateService.update(pessoa).$promise.then(function() {
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
		$scope.showModalTeam = false;
		$scope.showModal = false;
		$scope.showModalPhoto = false;
	}
// PROFILE PICTURE
    $scope.showModalPhoto = false;
    
	$scope.toggleModalPhoto = function() {
		$scope.showModalPhoto = !$scope.showModalPhoto;
	};
	
	


 });
 
 pessoaControllers.controller('ProfileController', ['$scope', '$routeParams', 'ProfileService', '$location', function($scope, $routeParams,
		ProfileService, $location) {
	
	$scope.profile = ProfileService.profile();
	
    $scope.editInscricao = function(email) {
    	$location.path('/inscricoes/' + email);
    };

}]);


pessoaControllers.directive("formatDate", function(){
	  return {
	   require: 'ngModel',
	    link: function(scope, elem, attr, modelCtrl) {
	      modelCtrl.$formatters.push(function(modelValue){
	        return new Date(modelValue);
	      })
	    }
	  }
	})
	
	
pessoaControllers.filter('ageFilter', function () {
    function calculateAge (birthday) { 
        var date = new Date(birthday);
        var ageDifMs = Date.now() - date.getTime();
        var ageDate = new Date(ageDifMs); 
        return Math.abs(ageDate.getUTCFullYear() - 1970);
    }

    return function (birthdate) {
        return calculateAge(birthdate);
    };
});
