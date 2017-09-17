var novidadeServices = angular.module('novidadeServices', [ 'ngResource' ]);

novidadeServices.factory('NovidadeService', function ($resource) {
    return $resource('/ws/v1/novidades', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

novidadeServices.factory('NovidadeViewService', function ($resource) {
    return $resource('/ws/v1/novidades/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

novidadeServices.factory('NovidadeUpdateService', function ($resource) {
    return $resource('/ws/v1/novidades/update', {}, {
        update: { method: 'PUT' }
    })
});