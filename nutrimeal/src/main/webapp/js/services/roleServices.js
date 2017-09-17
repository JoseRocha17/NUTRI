var roleServices = angular.module('roleServices', [ 'ngResource' ]);

roleServices.factory('setAdminService', function ($resource) {
    return $resource('/ws/v1/users/setAdmin', {}, {
        setAdmin: { method: 'POST' }
    })
});





