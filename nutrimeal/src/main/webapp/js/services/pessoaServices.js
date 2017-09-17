var pessoaServices = angular.module('pessoaServices', [ 'ngResource' ]);

pessoaServices.factory('PessoaService', function ($resource) {
    return $resource('/ws/v1/users', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {email: '@email'} }
    })
});

pessoaServices.factory('PessoaViewService', function ($resource) {
    return $resource('/ws/v1/users/:email', {}, {
        show: { method: 'GET', params: {email: '@email'} }, 
        delete: { method: 'DELETE', params: {email: '@email'} }
    })
});

pessoaServices.factory('PessoaUpdateService', function ($resource) {
    return $resource('/ws/v1/users/update', {}, {
        update: { method: 'POST' }
    })
});

pessoaServices.factory('ProfileService', function ($resource) {
    return $resource('/ws/v1/auth', {}, {
        profile: { method: 'GET' }
    })
});

pessoaServices.factory('ProfileInscritoService', function ($resource) {
    return $resource('/ws/v1/auth/:id', {}, {
        profileInscrito: { method: 'GET', params: {id: '@id'} }
    })
});