var refeicaoServices = angular.module('refeicaoServices', [ 'ngResource' ]);

refeicaoServices.factory('RefeicaoService', function ($resource) {
    return $resource('/ws/v1/refeicoes', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

refeicaoServices.factory('RefeicaoViewService', function ($resource) {
    return $resource('/ws/v1/refeicoes/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

refeicaoServices.factory('RefeicaoUpdateService', function ($resource) {
    return $resource('/ws/v1/refeicoes/update', {}, {
        update: { method: 'POST' }
    })
    });
    
refeicaoServices.factory('RefeicaoPerfilService', function ($resource) {
        return $resource('/ws/v1/perfis/refeicoes/:id', {}, {
            query: { method: 'GET', isArray: true, params: {id: '@id'} } 
        })
});