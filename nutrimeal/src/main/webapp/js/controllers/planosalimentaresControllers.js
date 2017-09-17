var planosalimentaresControllers = angular.module(
		'planosalimentaresControllers', []);

planosalimentaresControllers
		.controller(
				'PlanoAlimentarListController',
				[
						'$scope',
						'RefeicaoService',
						'QuantidadealimentarService',
						'$location',
						'$route',
						function($scope, RefeicaoService,
								QuantidadealimentarService, $location, $route) {

							$scope.refeicaoList = RefeicaoService.query();
							$scope.quantidadealimentaresList = QuantidadealimentarService
									.query();

							$scope.createNewRefeicao = function(refeicao) {
								RefeicaoService.create(refeicao).$promise
										.then(
												function(result) {
													$scope.closeRefeicao();
													$scope.perfilalimentarList = PerfilalimentarService
															.query();
													$scope.refeicaoList = RefeicaoService
															.query();
													$scope.quantidadealimentaresList = QuantidadealimentarService
															.query();
												}, function(error) {
													alert(error);
												});
							}

							$scope.createNewQuantidadealimentar = function(
									quantidadealimentar) {
								QuantidadealimentarService
										.create(quantidadealimentar).$promise
										.then(
												function(result) {
													$scope.closeQuantidade();
													$scope.perfilalimentarList = PerfilalimentarService
															.query();
													$scope.refeicaoList = RefeicaoService
															.query();
													$scope.quantidadealimentaresList = QuantidadealimentarService
															.query();
												}, function(error) {
													alert(error);
												});
							}

							$scope.addRefeicao = function(id) {
								$location.path('/alimentacaoRefeicao/' + id);
							};

							// Modal Perfil
							$scope.showModal = false;

							$scope.toggleModal = function() {
								$scope.showModal = !$scope.showModal;
							};

							$scope.closePerfil = function() {
								$scope.showModal = false;
							}

							// Modal refeicao

							$scope.showModalRefeicao = false;

							$scope.toggleModalAddRefeicao = function() {
								$scope.showModalRefeicao = !$scope.showModalRefeicao;
							};

							$scope.closeRefeicao = function() {
								$scope.showModalRefeicao = false;
							}

							// Modal Quantidade
							$scope.showModalQuantidade = false;

							$scope.toggleModalQuantidade = function() {
								$scope.showModalQuantidade = !$scope.showModalQuantidade;
							};

							$scope.closeQuantidade = function() {
								$scope.showModalQuantidade = false;
							}

						} ]);

planosalimentaresControllers
		.controller(
				'PlanoAlimentarViewController',
				[
						'$scope',
						'$routeParams',
						'RefeicaoViewService',
						'QuantidadealimentarService',
						'QuantidadeRefeicaoService',
						'$location', '$window',
						function($scope, $routeParams, RefeicaoViewService,
								QuantidadealimentarService,
								QuantidadeRefeicaoService, $location, $window) {

							$scope.refeicao = RefeicaoViewService.show({
								id : $routeParams.id
							});
							$scope.quantidadealimentaresList = QuantidadealimentarService
									.query();

							$scope.createNewQuantidadealimentar = function(
									quantidadealimentar, id) {
								quantidadealimentar.refeicao = id;
								QuantidadealimentarService
										.create(quantidadealimentar).$promise
										.then(
												function(result) {
													$scope.closeQuantidade();

													$scope.quantidadealimentaresList = QuantidadealimentarService
															.query();
													$scope.quantidadeRefeicaoUserList = QuantidadeRefeicaoService.query({id});
// $scope.refeicaoAlimeUserList = QuantidadeRefeicaoService.query({id});
												}, function(error) {
													alert(error);
												});
							}
							
							$scope.deleteQuantidadealimentar = function(id) {
								QuantidadealimentarService.delete({ id : id }).$promise.then(function(result) {
									$scope.apagarQuantidade();
								}, function(error) {
									alert(error);
								});
							};
							
							// Modal Quantidade
							$scope.showModalApagar = false;

							$scope.apagarQuantidade = function() {
								$scope.showModalApagar = !$scope.showModalApagar;
							};
							

							
// $scope.verDetalhesRefeicaoAdmin = function(id) {
// $scope.showTableQuantidadesRefeicoesAdmin =
// !$scope.showTableQuantidadesRefeicoesAdmin;
// $scope.refeicaoAlimeUserList = QuantidadeRefeicaoService.query({id});
// };
						    
							
							$scope.verRefeicoesAlimentos = function(id) {
								$location.path('/alimentacaoRefeicaoAlimentos/'
										+ id);
							};

							$scope.voltarAtrasCreateAdmin = function(id) {
								$location.path('/planosalimentaresCreateRefeicao/'
										+ id);
							};
							
							$scope.voltarAtras = function(id) {
								$location.path('/planosalimentares/'
										+ id);
							};
							$scope.verAlimento = function(id) {

								$location.path('/alimentoInformacao/' + id);
							};

							// Modal Quantidade
							$scope.showModalQuantidade = false;

							$scope.toggleModalQuantidade = function() {
								$scope.showModalQuantidade = !$scope.showModalQuantidade;
							};

							$scope.closeQuantidade = function() {
								$scope.showModalQuantidade = false;
							}
							
							  $scope.verQuantidadesRefeicoes = function(id) {
							    	$scope.showTableQuantidadesRefeicoes = !$scope.showTableQuantidadesRefeicoes;
							    	$scope.quantidadeRefeicaoUserList = QuantidadeRefeicaoService.query({id}); 
							    	$scope.showModalApagar = false;
							    };
							    
							    $scope.getTotal = function(){
							    	var total = 0;
							    	var totalGordura = 0;
							    	var totalCarboidrato = 0;
							    	var totalProteina = 0;
							    	for(var i=0; i< $scope.quantidadeRefeicaoUserList.length; i++){
							    		var carboidrato = $scope.quantidadeRefeicaoUserList[i];
							    		var proteina = $scope.quantidadeRefeicaoUserList[i];
							    		var gordura = $scope.quantidadeRefeicaoUserList[i];
							    		
							    		totalGordura += (gordura.alimento.gordura*9);
							    		totalCarboidrato += (carboidrato.alimento.carboidrato*4);
							    		totalProteina += (proteina.alimento.proteina*4);
							    		
							    		
							    		total +=(totalCarboidrato+totalGordura+totalProteina) ;
							    	}
							    	return total;
							    }

						} ]);

planosalimentaresControllers.controller('PlanoAlimentarViewAdminController', [
		'$scope',
		'$routeParams',
		'RefeicaoViewService',
		'QuantidadealimentarService',
		'QuantidadeRefeicaoService',
		'$location',
		function($scope, $routeParams, RefeicaoViewService,
				QuantidadealimentarService, QuantidadeRefeicaoService, $location) {

			$scope.refeicao = RefeicaoViewService.show({
				id : $routeParams.id
			});
			$scope.quantidadealimentaresList = QuantidadealimentarService
					.query();

			// $scope.createNewQuantidadealimentar = function
			// (quantidadealimentar, id) {
			// quantidadealimentar.refeicao=id;
			// QuantidadealimentarService.create(quantidadealimentar).$promise.then(function(result)
			// {
			// $scope.closeQuantidade();
			//
			// $scope.quantidadealimentaresList =
			// QuantidadealimentarService.query();
			// }, function(error) {
			// alert(error);
			// });
			// }
			
			 $scope.verQuantidadesRefeicoes = function(id) {
			    	$scope.showTableQuantidadesRefeicoes = !$scope.showTableQuantidadesRefeicoes;
			    	$scope.quantidadeRefeicaoUserList = QuantidadeRefeicaoService.query({id}); 
			    };

			$scope.verRefeicoesAlimentos = function(id) {
				$location.path('/alimentacaoRefeicaoAlimentos/' + id);
			};
			
			$scope.voltarAtrasVerAdmin = function(id) {
				$location.path('/perfisAlimentaresAdmin/' + id);
			};

			// $scope.verAlimentoAdmin = function(id) {
			//    	
			// $location.path('/alimentoInformacaoAdmin/' + id);
			// };

			// Modal Quantidade
			$scope.showModalQuantidade = false;

			$scope.toggleModalQuantidade = function() {
				$scope.showModalQuantidade = !$scope.showModalQuantidade;
			};

			$scope.closeQuantidade = function() {
				$scope.showModalQuantidade = false;
			}

		} ]);

planosalimentaresControllers.controller('PlanoAlimentarCompletoViewController',
		[
				'$scope',
				'$routeParams',
				'RefeicaoViewService',
				'QuantidadealimentarViewService',
				'$location',
				function($scope, $routeParams, RefeicaoViewService,
						QuantidadealimentarViewService, $location) {

					$scope.quantidadealimentar = QuantidadealimentarViewService
							.show({
								id : $routeParams.id
							});
					// $scope.quantidadealimentaresList =
					// QuantidadealimentarService.query();
					//	 
					// $scope.createNewQuantidadealimentar = function
					// (quantidadealimentar, id) {
					// quantidadealimentar.refeicao=id;
					// QuantidadealimentarService.create(quantidadealimentar).$promise.then(function(result)
					// {
					// $scope.closeQuantidade();
					//
					// $scope.quantidadealimentaresList =
					// QuantidadealimentarService.query();
					// }, function(error) {
					// alert(error);
					// });
					// }

					// $scope.verRefeicoesAlimentos = function(id) {
					// $location.path('/alimentacaoRefeicaoAlimentos/' + id);
					// };
					//
					//	
					//	
					// // Modal Quantidade
					// $scope.showModalQuantidade = false;
					//    
					// $scope.toggleModalQuantidade = function() {
					// $scope.showModalQuantidade = !$scope.showModalQuantidade;
					// };
					//	
					// $scope.closeQuantidade = function(){
					// $scope.showModalQuantidade = false;
					// }

				} ]);

planosalimentaresControllers
		.controller('PerfilalimentarUpdateController', function($scope,
				$routeParams, PerfilalimentarUpdateService, $location) {

			$scope.updatePerfilalimentar = function(perfilalimentar) {
				PerfilalimentarUpdateService.update(perfilalimentar).$promise
						.then(function() {
							$scope.close();
						}, function(error) {
							alert(error);
						});
			}

			$scope.showModal = false;

			$scope.toggleModal = function() {
				$scope.showModal = !$scope.showModal;
			};

			$scope.close = function() {
				$scope.showModal = false;
			}

		});