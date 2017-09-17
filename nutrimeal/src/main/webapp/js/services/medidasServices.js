var medidasServices = angular.module('medidasServices', [ 'ngResource' ]);

medidasServices.factory('MedidasService', function ($resource) {
    return $resource('/ws/v1/medidas', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

medidasServices.factory('MedidasViewService', function ($resource) {
    return $resource('/ws/v1/medidas/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

medidasServices.factory('MedidasUpdateService', function ($resource) {
    return $resource('/ws/v1/medidas/update', {}, {
        update: { method: 'POST' }
    })
});