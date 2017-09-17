package pt.ipb.nutrimeal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.Alimento;
import pt.ipb.nutrimeal.entity.Grupo;

public class AlimentoManagerBean implements AlimentoManager{
	private EntityManagerFactory factory;

	public AlimentoManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public Alimento createAlimento(String nome, long idGrupo, float calorias, float gordura, float colestrol, float sodio, float potassio, float carboidrato,
			float fibra, float acucar, float proteina){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Alimento d = new Alimento();
		try {
			Grupo grupo = em.find(Grupo.class, idGrupo);
				d.setNome(nome);
			
				d.setGrupo(grupo);
				d.grupo.setId(grupo.getId());
				d.grupo.setNome(grupo.getNome());
				grupo.getAlimento().add(d);
				
				d.setCalorias(calorias);
				d.setGordura(gordura);
				d.setColestrol(colestrol);
				d.setSodio(sodio);
				d.setPotassio(potassio);
				d.setCarboidrato(carboidrato);
				d.setFibra(fibra);
				d.setAcucar(acucar);
				d.setProteina(proteina);

				em.persist(d);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return d;
	}
	
	@Override
	public void deleteAlimento(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Alimento d = em.find(Alimento.class, id);
		em.remove(d);
		em.getTransaction().commit();
		em.close();
	}
	

	@Override
	public Alimento update(Alimento alimento) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Alimento d = em.merge(alimento);
		em.getTransaction().commit();
		em.close();
		return d;
	}
	
	@Override
	public List<Alimento> getAlimentos(){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Alimento> query = em.createNamedQuery("Alimento.getAll", Alimento.class);
		List<Alimento> d = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return d;
	}
	
	@Override
	public Alimento getAlimento(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Alimento d = em.find(Alimento.class, id);
		em.getTransaction().commit();
		em.close();
		return d;
	}
	
	@Override
	public Alimento getAlimentoNome(String nome) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Alimento d = em.find(Alimento.class, nome);
		em.getTransaction().commit();
		em.close();
		return d;
	}

	@Override
	public List<Alimento> getAlimentoGrupo(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Alimento> query = em.createNamedQuery("Alimento.getGrupo", Alimento.class).setParameter("id", id);
		List<Alimento> a = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return a;
	}
	
}
