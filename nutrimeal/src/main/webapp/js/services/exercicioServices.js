var exercicioServices = angular.module('exercicioServices', [ 'ngResource' ]);

exercicioServices.factory('ExercicioService', function ($resource) {
    return $resource('/ws/v1/exercicios', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

exercicioServices.factory('ExercicioViewService', function ($resource) {
    return $resource('/ws/v1/exercicios/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

exercicioServices.factory('ExercicioUpdateService', function ($resource) {
    return $resource('/ws/v1/exercicios/update', {}, {
        update: { method: 'POST' }
    })
});