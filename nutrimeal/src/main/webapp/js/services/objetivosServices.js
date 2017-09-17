var objetivosServices = angular.module('objetivosServices', [ 'ngResource' ]);

objetivosServices.factory('ObjetivosService', function ($resource) {
    return $resource('/ws/v1/objetivos', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

objetivosServices.factory('ObjetivosViewService', function ($resource) {
    return $resource('/ws/v1/objetivos/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

objetivosServices.factory('ObjetivosUpdateService', function ($resource) {
    return $resource('/ws/v1/objetivos/update', {}, {
        update: { method: 'POST' }
    })
});