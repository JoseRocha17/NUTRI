var userControllers = angular.module('userControllers', []);

userControllers.controller('UserListController', ['$scope', 'UserService', '$location', function($scope, UserService, $location) {
	
	$scope.userList = UserService.query(); 

	
    $scope.createNewUser = function (user) {
    	UserService.create(user).$promise.then(function(result) {
    		$scope.close();
        	$scope.userList = UserService.query(); 
        	delete $scope.user;
        }, function(error) {
        	alert(error);
        });
    }
    
    $scope.deleteUser = function(email) {
    	UserService.delete({ email : email }).$promise.then(function(result) {
        	$scope.userList = UserService.query(); 
        	$scope.apagarUtilizador();
        }, function(error) {
        	alert(error);
        });
    };

	    $scope.editUser = function(email) {
	    	$location.path('/user/' + email);
	    	$scope.userList = UserService.query();
	    };
	    
	    $scope.orderBy = function (row){
			$scope.rowOrdered = row;
			$scope.orderDirection = !$scope.orderDirection;
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

		$scope.apagarUtilizador = function() {
			$scope.showModalApagar = !$scope.showModalApagar;
		};
		
		$scope.closeApagar = function(){
			$scope.showModalApagar = false;
		}
	    
    }]);

userControllers.controller('UserViewController', ['$scope', '$routeParams', 'UserViewService', 
                                    '$location', function($scope, $routeParams, UserViewService, $location) {
	
	$scope.user = UserViewService.show({ email: $routeParams.email }); 
	
    $scope.deleteUser = function(email) {
    	UserViewService.delete({ email : email }).$promise.then(function(result) {
    		$location.path('/users');
        }, function(error) {
        	alert(error);
        });
    };
}]);


userControllers.controller('UserController', ['$scope', '$routeParams', 'ProfileService', '$location', function($scope, $routeParams,
		ProfileService, $location) {
	
	$scope.profile = ProfileService.profile();
	
    $scope.editInscricao = function(email) {
    	$location.path('/inscricoes/' + email);
    };

}]);

userControllers.controller('UserUpdateController', function($scope, $routeParams, UserUpdateService, $location) {

    $scope.updateUser = function (user) {
    	UserUpdateService.update(user).$promise.then(function() {
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

userControllers.controller('PasswordUpdateController', function($scope, $routeParams, PasswordUpdateService, $location) {
	
	$scope.updatePassword = function (email, oldPass, newPass) {
		PasswordUpdateService.update({email : email, oldPass : oldPass, newPass : newPass}).$promise.then(function() {
			$scope.UpdatePass();
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
	


	$scope.UpdatePass = function() {
		$scope.showModalUpdate = !$scope.showModalUpdate;
	};
	$scope.closeUpdate = function(){
		$scope.showModalUpdate = false;
	}
});

userControllers.controller('RecoverController', function($scope, $routeParams, RecoverService, $location) {
	
	$scope.recoverPassword = function (email) {
		RecoverService.recover({email : email}).$promise.then(function() {
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

userControllers.controller('md5Controller', ['$scope', 'md5', function($scope, md5) {
	 
    $scope.$watch(function() {
      $scope.conversion = 'MD5:' + md5.createHash($scope.person.oldPassword);
    });
  }]);

userControllers.directive("passwordVerify", function() {
	   return {
		      require: "ngModel",
		      scope: {
		        passwordVerify: '='
		      },
		      link: function(scope, element, attrs, ctrl) {
		        scope.$watch(function() {
		            var combined;

		            if (scope.passwordVerify || ctrl.$viewValue) {
		               combined = scope.passwordVerify + '_' + ctrl.$viewValue; 
		            }                    
		            return combined;
		        }, function(value) {
		            if (value) {
		                ctrl.$parsers.unshift(function(viewValue) {
		                    var origin = scope.passwordVerify;
		                    if (origin !== viewValue) {
		                        ctrl.$setValidity("passwordVerify", false);
		                        return undefined;
		                    } else {
		                        ctrl.$setValidity("passwordVerify", true);
		                        return viewValue;
		                    }
		                });
		            }
		        });
		     }
		   };
		});