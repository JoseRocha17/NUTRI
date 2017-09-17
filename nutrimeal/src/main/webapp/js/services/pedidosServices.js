var pedidosServices = angular.module('pedidosServices', [ 'ngResource' ]);

pedidosServices.factory('PedidosService', function ($resource) {
    return $resource('/ws/v1/pedidos', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

pedidosServices.factory('PedidosViewService', function ($resource) {
    return $resource('/ws/v1/pedidos/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

pedidosServices.factory('PedidosUpdateService', function ($resource) {
    return $resource('/ws/v1/pedidos/update', {}, {
        update: { method: 'PUT' }
    })
});