var atributoServices = angular.module('atributoServices', [ 'ngResource' ]);

atributoServices.factory('AtributoService', function ($resource) {
    return $resource('/ws/v1/atributos', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

atributoServices.factory('AtributoViewService', function ($resource) {
    return $resource('/ws/v1/atributos/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

atributoServices.factory('AtributoUpdateService', function ($resource) {
    return $resource('/ws/v1/atributos/update', {}, {
        update: { method: 'PUT' }
    })
});