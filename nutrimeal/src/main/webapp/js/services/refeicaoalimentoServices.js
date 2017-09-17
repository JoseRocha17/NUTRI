var refeicaoalimentoServices = angular.module('refeicaoalimentoServices', [ 'ngResource' ]);

refeicaoalimentoServices.factory('RefeicaoalimentoService', function ($resource) {
    return $resource('/ws/v1/refeicaoalimentos', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

refeicaoalimentoServices.factory('RefeicaoalimentoViewService', function ($resource) {
    return $resource('/ws/v1/refeicaoalimentos/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

refeicaoalimentoServices.factory('RefeicaoalimentoUpdateService', function ($resource) {
    return $resource('/ws/v1/refeicaoalimentos/update', {}, {
        update: { method: 'POST' }
    })
});