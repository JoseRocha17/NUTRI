var userServices = angular.module('userServices', [ 'ngResource' ]);

userServices.factory('UserService', function ($resource) {
    return $resource('/ws/v1/users', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {email: '@email'} }
    })
});

userServices.factory('UserViewService', function ($resource) {
    return $resource('/ws/v1/users/:email', {}, {
        show: { method: 'GET', params: {email: '@email'} }, 
        delete: { method: 'DELETE', params: {email: '@email'} }
    })
});

userServices.factory('UserUpdateService', function ($resource) {
    return $resource('/ws/v1/users/update', {}, {
        update: { method: 'POST' }
    })
});

userServices.factory('PasswordUpdateService', function ($resource) {
    return $resource('/ws/v1/users/password', {}, {
        update: { method: 'POST', params: {email: '@email', oldPass: '@oldPass', newPass: '@newPass'} }
    })
});

userServices.factory('RecoverService', function ($resource) {
    return $resource('/ws/v1/users/recover', {}, {
        recover: { method: 'POST', params: {email: '@email' } }
    })
});