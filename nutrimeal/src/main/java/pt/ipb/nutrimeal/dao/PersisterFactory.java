package pt.ipb.nutrimeal.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;




public class PersisterFactory {
	
	
	private static PersisterFactory singleton;
	private EntityManagerFactory emf;
	
	
	private UserManager userManager;
//	private PessoaManager pessoaManager;
	private AlimentoManager alimentoManager;
	private ExercicioManager exercicioManager;
	private MedidasManager medidasManager;
	private ObjetivosManager objetivosManager;
	private RefeicaoManager refeicaoManager;
	private QuantidadeAlimentarManager quantidadeAlimentarManager;
	private PerfilAlimentarManager perfilAlimentarManager;
	private PerfilFisicoManager perfilFisicoManager;
	private AtributoManager atributoManager;
	private ExercicioAtributoManager exercicioAtributoManager;
	private MetaExercicioManager metaExercicioManager;
	private NovidadeManager novidadeManager;
	private PromocaoManager promocaoManager;
	private GrupoManager grupoManager;
	private PedidosManager pedidosManager;
	


	protected PersisterFactory() {
		emf = Persistence.createEntityManagerFactory("nutrimeal-pu");
		init();
	}
	
	public void close() {
		emf.close();
	}
	
	private void init() {
		System.out.println("Initializing database - PersisterFactory");
	}
	
	public static PersisterFactory getInstance() {
		if(singleton==null) {
			singleton = new PersisterFactory();
		}
		return singleton;
	}
	
	public UserManager getUserManager() {
		if(this.userManager==null) {
			this.userManager = new UserManagerBean(emf);
		}
		return userManager;
	}
	
	
	
//	public PessoaManager getPessoaManager() {
//		if(this.pessoaManager==null) {
//			this.pessoaManager = new PessoaManagerBean(emf);
//		}
//		return pessoaManager;
//	}
	
	
	public AlimentoManager getAlimentoManager() {
		if(this.alimentoManager==null) {
			this.alimentoManager = new AlimentoManagerBean(emf);
		}
		return alimentoManager;
	}
	
	public ExercicioManager getExercicioManager() {
		if(this.exercicioManager==null) {
			this.exercicioManager = new ExercicioManagerBean(emf);
		}
		return exercicioManager;
	}
	
	public MedidasManager getMedidasManager() {
		if(this.medidasManager==null) {
			this.medidasManager = new MedidasManagerBean(emf);
		}
		return medidasManager;
	}
	
	public ObjetivosManager getObjetivosManager() {
		if(this.objetivosManager==null) {
			this.objetivosManager = new ObjetivosManagerBean(emf);
		}
		return objetivosManager;
	}
	
	
	public RefeicaoManager getRefeicaoManager() {
		if(this.refeicaoManager==null) {
			this.refeicaoManager = new RefeicaoManagerBean(emf);
		}
		return refeicaoManager;
	}
	
	public QuantidadeAlimentarManager getQuantidadeAlimentarManager() {
		if(this.quantidadeAlimentarManager==null) {
			this.quantidadeAlimentarManager = new QuantidadeAlimentarManagerBean(emf);
		}
		return quantidadeAlimentarManager;
	}
	
	public PerfilAlimentarManager getPerfilAlimentarManager() {
		if(this.perfilAlimentarManager==null) {
			this.perfilAlimentarManager = new PerfilAlimentarManagerBean(emf);
		}
		return perfilAlimentarManager;
	}
	
	public PerfilFisicoManager getPerfilFisicoManager() {
		if(this.perfilFisicoManager==null) {
			this.perfilFisicoManager = new PerfilFisicoManagerBean(emf);
		}
		return perfilFisicoManager;
	}
	
	public AtributoManager getAtributoManager() {
		if(this.atributoManager==null) {
			this.atributoManager = new AtributoManagerBean(emf);
		}
		return atributoManager;
	}
	
	public ExercicioAtributoManager getExercicioAtributoManager() {
		if(this.exercicioAtributoManager==null) {
			this.exercicioAtributoManager = new ExercicioAtributoManagerBean(emf);
		}
		return exercicioAtributoManager;
	}
	
	public MetaExercicioManager getMetaExercicioManager() {
		if(this.metaExercicioManager==null) {
			this.metaExercicioManager = new MetaExercicioManagerBean(emf);
		}
		return metaExercicioManager;
	}
	
	public NovidadeManager getNovidadeManager() {
		if(this.novidadeManager==null) {
			this.novidadeManager = new NovidadeManagerBean(emf);
		}
		return novidadeManager;
	}
	
	public PromocaoManager getPromocaoManager() {
		if(this.promocaoManager==null) {
			this.promocaoManager = new PromocaoManagerBean(emf);
		}
		return promocaoManager;
	}
	
	public GrupoManager getGrupoManager() {
		if(this.grupoManager==null) {
			this.grupoManager = new GrupoManagerBean(emf);
		}
		return grupoManager;
	}

	public PedidosManager getPedidosManager() {
		if(this.pedidosManager==null) {
			this.pedidosManager = new PedidosManagerBean(emf);
		}
		return pedidosManager;
	}

	
}
