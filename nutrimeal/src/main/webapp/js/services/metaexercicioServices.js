var metaexercicioServices = angular.module('metaexercicioServices', [ 'ngResource' ]);

metaexercicioServices.factory('MetaexercicioService', function ($resource) {
    return $resource('/ws/v1/metaexercicios', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

metaexercicioServices.factory('MetaexercicioViewService', function ($resource) {
    return $resource('/ws/v1/metaexercicios/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

metaexercicioServices.factory('MetaexercicioUpdateService', function ($resource) {
    return $resource('/ws/v1/metaexercicios/update', {}, {
        update: { method: 'POST' }
    })
});

metaexercicioServices.factory('MetaexercicioPerfilService', function ($resource) {
    return $resource('/ws/v1/perfis/exercicios/:id', {}, {
    	query: { method: 'GET', isArray: true, params: {id: '@id'} }
    })
});