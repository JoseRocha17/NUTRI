var perfilalimentarServices = angular.module('perfilalimentarServices', [ 'ngResource' ]);

perfilalimentarServices.factory('PerfilalimentarService', function ($resource) {
    return $resource('/ws/v1/perfilalimentares', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

perfilalimentarServices.factory('PerfilalimentarViewService', function ($resource) {
    return $resource('/ws/v1/perfilalimentares/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

perfilalimentarServices.factory('PerfilalimentarUpdateService', function ($resource) {
    return $resource('/ws/v1/perfilalimentares/update', {}, {
        update: { method: 'POST' }
    })
});