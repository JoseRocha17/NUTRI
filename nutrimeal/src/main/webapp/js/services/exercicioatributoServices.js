var exercicioatributoServices = angular.module('exercicioatributoServices', [ 'ngResource' ]);

exercicioatributoServices.factory('ExercicioAtributoService', function ($resource) {
    return $resource('/ws/v1/exercicioatributos', {}, {
        query: { method: 'GET', isArray: true },
    	create: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

exercicioatributoServices.factory('ExercicioAtributoViewService', function ($resource) {
    return $resource('/ws/v1/exercicioatributos/:id', {}, {
        show: { method: 'GET', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

exercicioatributoServices.factory('ExercicioAtributoUpdateService', function ($resource) {
    return $resource('/ws/v1/exercicioatributos/update', {}, {
        update: { method: 'PUT' }
    })
});

exercicioatributoServices.factory('ExercicioAtributoPerfilService', function ($resource) {
    return $resource('/ws/v1/exercicio/quantidades/:id', {}, {
    	 query: { method: 'GET', isArray: true, params: {id: '@id'} } 
    })
});