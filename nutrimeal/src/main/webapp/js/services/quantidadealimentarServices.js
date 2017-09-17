var quantidadealimentarServices = angular.module('quantidadealimentarServices', [ 'ngResource' ]);

quantidadealimentarServices.factory('QuantidadealimentarService', function ($resource) {
    return $resource('/ws/v1/quantidadealimentares', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

quantidadealimentarServices.factory('QuantidadealimentarViewService', function ($resource) {
    return $resource('/ws/v1/quantidadealimentares/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

quantidadealimentarServices.factory('QuantidadealimentarUpdateService', function ($resource) {
    return $resource('/ws/v1/quantidadealimentares/update', {}, {
        update: { method: 'POST' }
    })
     
});

quantidadealimentarServices.factory('QuantidadeRefeicaoService', function ($resource) {
    return $resource('/ws/v1/refeicao/quantidades/:id', {}, {
        query: { method: 'GET', isArray: true, params: {id: '@id'} } 
    })
});