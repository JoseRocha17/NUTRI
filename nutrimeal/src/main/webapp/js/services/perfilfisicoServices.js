var perfilfisicoServices = angular.module('perfilfisicoServices', [ 'ngResource' ]);

perfilfisicoServices.factory('PerfilfisicoService', function ($resource) {
    return $resource('/ws/v1/perfilfisicos', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

perfilfisicoServices.factory('PerfilfisicoViewService', function ($resource) {
    return $resource('/ws/v1/perfilfisicos/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

perfilfisicoServices.factory('PerfilfisicoUpdateService', function ($resource) {
    return $resource('/ws/v1/perfilfisicos/update', {}, {
        update: { method: 'POST' }
    })
});