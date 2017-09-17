nutriApp = angular.module('nutriApp', [ 'ngRoute', 'mymodal', 'angular-md5',
		'pessoaControllers', 'pessoaServices', 'userControllers',
		'userServices', 'roleControllers', 'roleServices',
		'alimentoControllers', 'alimentoServices',
		'alimentoInformacaoControllers', 'exercicioControllers',
		'exercicioServices', 'medidasControllers', 'medidasServices',
		'objetivosControllers', 'objetivosServices',
		'refeicaoalimentoControllers', 'refeicaoalimentoServices',
		'refeicaoControllers', 'refeicaoServices',
		'perfilalimentarControllers', 'perfilalimentarServices',
		'quantidadealimentarControllers', 'quantidadealimentarServices',
		'perfilfisicoControllers', 'perfilfisicoServices',
		'atributoControllers', 'atributoServices', 'alimentacaoControllers',
		'exercicioatributoControllers', 'exercicioatributoServices',
		'alimentacaoControllers', 'quantidadefisicaControllers',
		'metaexercicioServices', 'metaexercicioControllers',
		'planosalimentaresControllers', 'novidadeControllers',
		'novidadeServices', 'promocaoControllers', 'promocaoServices',
		'pedidosServices', 'pedidosControllers', 'grupoControllers',
		'grupoServices' ]);

nutriApp
		.config([
				'$compileProvider',
				function($compileProvider) {
					$compileProvider
							.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|chrome-extension|data):/);
				} ]);

nutriApp
		.config(function($routeProvider) {

			$routeProvider.when('/', {
				templateUrl : 'main.html'
			});
			$routeProvider.when('/main', {
				templateUrl : 'main.html'
			});

			// Parte Pública

			$routeProvider.when('/error', {
				templateUrl : 'error.html'
			});

			// Login / Registo
			$routeProvider.when('/login', {
				templateUrl : 'login.html',
				controller : 'RecoverController'
			});
			$routeProvider.when('/register', {
				templateUrl : 'register.html'
			});
			$routeProvider.when('/loginError', {
				templateUrl : 'auth/loginError.html'
			});
			$routeProvider.when('/registered', {
				templateUrl : 'auth/registered.html'
			});
			$routeProvider.when('/success', {
				templateUrl : 'auth/success.html'
			});
			$routeProvider.when('/verifyError', {
				templateUrl : 'auth/verifyError.html'
			});
			$routeProvider.when('/passwdError', {
				templateUrl : 'auth/passwdError.html'
			});
			$routeProvider.when('/registerError', {
				templateUrl : 'auth/registerError.html'
			});
			$routeProvider.when('/authError', {
				templateUrl : 'auth/authError.html'
			});

			// Ginasio

			$routeProvider.when('/espaco', {
				templateUrl : '/partials/ginasio/espaco.html'
			});

			$routeProvider.when('/instalacoes', {
				templateUrl : '/partials/ginasio/instalacoes.html'
			});

			$routeProvider.when('/equipamentos', {
				templateUrl : '/partials/ginasio/equipamentos.html'
			});

			$routeProvider.when('/funcionarios', {
				templateUrl : '/partials/ginasio/funcionarios.html'
			});

			$routeProvider.when('/novidades', {
				templateUrl : '/partials/ginasio/novidades.html',
				controller : 'NovidadeListController'
			})

			$routeProvider.when('/novidades/:id', {
				templateUrl : '/partials/ginasio/novidade.html',
				controller : 'NovidadeAdminViewController'
			})

			$routeProvider.when('/promocoes', {
				templateUrl : '/partials/ginasio/promocoes.html',
				controller : 'PromocaoListController'
			})

			$routeProvider.when('/promocoes/:id', {
				templateUrl : '/partials/ginasio/promocao.html',
				controller : 'PromocaoAdminViewController'
			})

			$routeProvider.when('/pedidoPage', {
				templateUrl : '/partials/pedidos/pedidos.html',
				controller : 'PedidosListController'
			})

			// Definicao de Objetivos
			// Mediçoes
			$routeProvider.when('/PlaneamentoMedicoes', {
				templateUrl : '/partials/metas/medidas.html',
				controller : 'MedidasListController'
			})

			$routeProvider.when('/PlaneamentoMedicoes/:id', {
				templateUrl : '/partials/metas/medida.html',
				controller : 'MedidasViewController'
			})

			// Objetivos
			$routeProvider.when('/PlaneamentoObjetivos', {
				templateUrl : '/partials/metas/objetivos.html',
				controller : 'ObjetivosListController'
			})

			$routeProvider.when('/PlaneamentoObjetivos/:id', {
				templateUrl : '/partials/metas/objetivo.html',
				controller : 'ObjetivosViewController'
			})

			// Alimentacao
			$routeProvider.when('/alimentacaoPage', {
				templateUrl : '/partials/alimentacao/alimentacaoPage.html'
			});

			$routeProvider.when('/useralimentos', {
				templateUrl : '/partials/alimentacao/alimentoSearch.html',
				controller : 'AlimentoInformacaoListController'
			});

			$routeProvider.when('/useralimentosTodos', {
				templateUrl : '/partials/alimentacao/alimentosList.html',
				controller : 'AlimentoInformacaoListController'
			});

			$routeProvider.when('/useralimentosGrupo', {
				templateUrl : '/partials/alimentacao/alimentosGrupo.html',
				controller : 'GrupoListController'
			});

			$routeProvider.when('/alimentoInformacao/:id', {
				templateUrl : '/partials/alimentacao/informacaoAlimento.html',
				controller : 'AlimentoInformacaoViewController'
			});

			$routeProvider.when('/planosalimentares', {
				templateUrl : '/partials/alimentacao/alimentacaoPerfil.html',
				controller : 'AlimentacaoListController'
			});

			$routeProvider.when('/planosalimentares/:id', {
				templateUrl : '/partials/alimentacao/alimentacaoRefeicao.html',
				controller : 'AlimentacaoViewController'
			});

			$routeProvider.when('/alimentacaoRefeicao', {
				templateUrl : '/partials/alimentacao/alimentacaoPlano.html',
				controller : 'PlanoAlimentarListController'
			});

			$routeProvider
					.when(
							'/alimentacaoRefeicao/:id',
							{
								templateUrl : '/partials/alimentacao/alimentacaoPlanoRefeicao.html',
								controller : 'PlanoAlimentarViewController'
							});

			$routeProvider
					.when(
							'/planosRefeicoes/:id',
							{
								templateUrl : '/partials/alimentacao/alimentacaoPlanoRefeicao.html',
								controller : 'PlanoAlimentarViewController'
							});

			$routeProvider.when('/alimentacaoRefeicaoAlimentos/:id', {
				templateUrl : '/partials/alimentacao/informacaoPlano.html',
				controller : 'PlanoAlimentarCompletoViewController'
			});



			// Exercicios

			$routeProvider.when('/exercicioPage', {
				templateUrl : '/partials/exercicio/exercicioPage.html'
			});

			$routeProvider.when('/userexercicios', {
				templateUrl : '/partials/exercicio/exerciciosList.html',
				controller : 'ExercicioListController'
			});
			$routeProvider.when('/exercicioInformacao/:id', {
				templateUrl : '/partials/exercicio/informacaoExercicio.html',
				controller : 'ExercicioViewController'
			});

			$routeProvider.when('/quantidadesfisicas/', {
				templateUrl : '/partials/exercicio/exercicioPerfil.html',
				controller : 'QuantidadeFisicaListController'
			});

			$routeProvider
					.when(
							'/quantidadesfisicas/:id',
							{
								templateUrl : '/partials/exercicio/exercicioPerfilExercicio.html',
								controller : 'QuantidadeFisicaViewController'
							});

			$routeProvider
					.when(
							'/quantidadesfisicasQuantidade/:id',
							{
								templateUrl : '/partials/exercicio/perfilExercicioQuantidade.html',
								controller : 'QuantidadeExercicioViewController'
							});

			// Profile
			$routeProvider.when('/profile', {
				templateUrl : 'partials/profile/profile.html',
				controller : 'ProfileController'
			});
			$routeProvider.when('/changePass', {
				templateUrl : 'partials/profile/changePass.html',
				controller : 'ProfileController'
			});

			// Requests
			$routeProvider.when('/request', {
				templateUrl : 'partials/requests/request-add.html',
				controller : 'PedidoCreateController'
			});

			// ERROS
			$routeProvider.when('/codeError', {
				templateUrl : 'errors/codeError.html'
			});

			// ///////////////////////////////// MANAGER


			// //////////////////////////////////////////Administrador/////////////////////////////////////////////////////////////////////

			// Ginasio

			$routeProvider
					.when(
							'/novidadesAdmin',
							{
								templateUrl : 'partials/funcionalidades/ginasio/novidades.html',
								controller : 'NovidadeListController'
							});

			$routeProvider.when('/novidadesAdmin/:id', {
				templateUrl : 'partials/funcionalidades/ginasio/novidade.html',
				controller : 'NovidadeAdminViewController'
			});

			$routeProvider
					.when(
							'/promocoesAdmin',
							{
								templateUrl : 'partials/funcionalidades/ginasio/promocoes.html',
								controller : 'PromocaoListController'
							});

			$routeProvider.when('/promocoesAdmin/:id', {
				templateUrl : 'partials/funcionalidades/ginasio/promocao.html',
				controller : 'PromocaoAdminViewController'
			});
			// Alimentação

			$routeProvider
					.when(
							'/alimentacaoPageAdmin',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/alimentacaoPageAdmin.html'
							});

			$routeProvider
					.when(
							'/alimentosAdmin',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/alimentos.html',
								controller : 'AlimentoListController'
							});

			$routeProvider
					.when(
							'/alimentosAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/alimento.html',
								controller : 'AlimentoViewController'
							});

			$routeProvider
					.when(
							'/alimentacoes',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/perfil.html',
								controller : 'AlimentacaoListController'
							});


			$routeProvider
					.when(
							'/alimentos',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/alimentos.html',
								controller : 'AlimentoListController'
							});
			$routeProvider
					.when(
							'/alimento/:id',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/alimento.html',
								controller : 'AlimentoViewController'
							});

			$routeProvider
					.when(
							'/perfisAlimentaresAdmin',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/alimentacaoPerfis.html',
								controller : 'AlimentacaoListController'
							});

			$routeProvider
					.when(
							'/perfisAlimentaresAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/alimentacaoRefeicoes.html',
								controller : 'AlimentacaoViewAdminController'
							});

			$routeProvider
					.when(
							'/planosRefeicoesAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/refeicaoDetalhe.html',
								controller : 'PlanoAlimentarViewAdminController'
							});

			$routeProvider
					.when(
							'/alimentoInformacaoAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/alimento.html',
								controller : 'AlimentoViewController'
							});

			$routeProvider
					.when(
							'/criarPerfisAlimentaresAdmin',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/criarAlimentacaoPerfilAdmin.html',
								controller : 'AlimentacaoListAdminController'
							});

			$routeProvider
					.when(
							'/criarGruposAlimentaresAdmin',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/grupos.html',
								controller : 'GrupoListController'
							});

			$routeProvider
					.when(
							'/criarGruposAlimentaresAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/grupo.html',
								controller : 'GrupoViewController'
							});

			$routeProvider
					.when(
							'/planosalimentaresCreateRefeicao/:id',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/criarAlimentacaoRefeicaoAdmin.html',
								controller : 'AlimentacaoViewController'
							});



			$routeProvider
					.when(
							'/refeicaoCreateQuantidadeAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/alimentacao/alimentacaoDefinirQuantidadeAdmin.html',
								controller : 'PlanoAlimentarViewController'
							});

			// Exercicio
			$routeProvider
					.when(
							'/exercicios',
							{
								templateUrl : 'partials/funcionalidades/exercicio/exercicios.html',
								controller : 'ExercicioListController'
							});
			$routeProvider
					.when(
							'/exercicios/:id',
							{
								templateUrl : 'partials/funcionalidades/exercicio/exercicio.html',
								controller : 'ExercicioViewController'
							});
			$routeProvider
					.when(
							'/criarAtributosAdmin',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/atributos.html',
								controller : 'AtributoListController'
							});
			$routeProvider
					.when(
							'/criarAtributosAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/atributo.html',
								controller : 'AtributoViewController'
							});


			$routeProvider
					.when(
							'/exercicioFisicoPageAdmin',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/exercicioPageAdmin.html'
							});

			$routeProvider
					.when(
							'/exerciciosAdmin',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/exercicios.html',
								controller : 'ExercicioListController'
							});

			$routeProvider
					.when(
							'/exerciciosAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/exercicio.html',
								controller : 'ExercicioViewController'
							});

			$routeProvider
					.when(
							'/perfisFisicosAdmin',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/exercicioPerfisAdmin.html',
								controller : 'QuantidadeFisicaListController'
							});

			$routeProvider
					.when(
							'/verPerfisFisicosAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/exercicioPerfilExercicioAdmin.html',
								controller : 'QuantidadeFisicaViewController'
							});
			$routeProvider
					.when(
							'/verMetaExerciciosAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/perfilFisicoViewDetail.html',
								controller : 'QuantidadeExercicioViewController'
							});

			$routeProvider
					.when(
							'/criarPerfisFisicosAdmin',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/exercicioPerfisCreateAdmin.html',
								controller : 'QuantidadeFisicaListAdminController'
							});

			$routeProvider
					.when(
							'/createPerfisFisicosAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/exercicioPerfilExercicioCreateAdmin.html',
								controller : 'QuantidadeFisicaViewController'
							});

			$routeProvider
					.when(
							'/verCreateMetaExerciciosAdmin/:id',
							{
								templateUrl : 'partials/funcionalidades/exercicioFisico/perfilFisicoViewCreateDetailAdmin.html',
								controller : 'QuantidadeExercicioViewController'
							});

			// Atributos
			$routeProvider
					.when(
							'/atributos',
							{
								templateUrl : 'partials/funcionalidades/atributo/atributos.html',
								controller : 'AtributoListController'
							});

			$routeProvider
					.when(
							'/atributos/:id',
							{
								templateUrl : 'partials/funcionalidades/atributo/atributo.html',
								controller : 'AtributoViewController'
							});

			// Medicoes
			$routeProvider.when('/medicoes', {
				templateUrl : 'partials/funcionalidades/medidas/medidas.html',
				controller : 'MedidasListController'
			});
			$routeProvider.when('/medida/:id', {
				templateUrl : 'partials/funcionalidades/medidas/medida.html',
				controller : 'MedidasViewController'
			});

			// Objetivos
			$routeProvider
					.when(
							'/objetivos',
							{
								templateUrl : 'partials/funcionalidades/objetivos/objetivos.html',
								controller : 'ObjetivosListController'
							});
			$routeProvider
					.when(
							'/objetivo/:id',
							{
								templateUrl : 'partials/funcionalidades/objetivos/objetivo.html',
								controller : 'ObjetivosViewController'
							});



			// Users
			$routeProvider.when('/users', {
				templateUrl : 'partials/users/users.html',
				controller : 'UserListController'
			});
			$routeProvider.when('/user/:email', {
				templateUrl : 'partials/users/user.html',
				controller : 'UserViewController'
			});








			$routeProvider
			.when(
					'/pedidosAdmin',
					{
						templateUrl : 'partials/funcionalidades/pedidos/pedidos.html',
						controller : 'PedidosListController'
					});
			$routeProvider
			.when(
					'/pedidosAdmin/:id',
					{
						templateUrl : 'partials/funcionalidades/pedidos/pedido.html',
						controller : 'PedidosAdminViewController'
					});
			// Planos Fisicos

			$routeProvider.otherwise({
				redirectTo : '/'
			});

		});