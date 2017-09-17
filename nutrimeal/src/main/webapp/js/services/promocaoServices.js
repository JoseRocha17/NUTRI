var promocaoServices = angular.module('promocaoServices', [ 'ngResource' ]);

promocaoServices.factory('PromocaoService', function ($resource) {
    return $resource('/ws/v1/promocoes', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

promocaoServices.factory('PromocaoViewService', function ($resource) {
    return $resource('/ws/v1/promocoes/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

promocaoServices.factory('PromocaoUpdateService', function ($resource) {
    return $resource('/ws/v1/promocoes/update', {}, {
        update: { method: 'PUT' }
    })
});