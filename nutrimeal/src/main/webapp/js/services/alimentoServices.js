var alimentoServices = angular.module('alimentoServices', [ 'ngResource' ]);

alimentoServices.factory('AlimentoService', function ($resource) {
    return $resource('/ws/v1/alimentos', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

alimentoServices.factory('AlimentoViewService', function ($resource) {
    return $resource('/ws/v1/alimentos/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

alimentoServices.factory('AlimentoUpdateService', function ($resource) {
    return $resource('/ws/v1/alimentos/update', {}, {
        update: { method: 'PUT' }
    })
});

alimentoServices.factory('AlimentoGrupoService', function ($resource) {
    return $resource('/ws/v1/grupo/alimentos/:id', {}, {
    	query: { method: 'GET', isArray: true, params: {id: '@id'} }
    })
});

alimentoServices.factory('GrupoService', function ($resource) {
    return $resource('/ws/v1/grupos', {}, {
        query: { method: 'GET', isArray: true }
    })
});