var grupoServices = angular.module('grupoServices', [ 'ngResource' ]);

grupoServices.factory('GrupoService', function ($resource) {
    return $resource('/ws/v1/grupos', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

grupoServices.factory('GrupoViewService', function ($resource) {
    return $resource('/ws/v1/grupos/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

grupoServices.factory('GrupoUpdateService', function ($resource) {
    return $resource('/ws/v1/grupos/update', {}, {
        update: { method: 'PUT' }
    })
});