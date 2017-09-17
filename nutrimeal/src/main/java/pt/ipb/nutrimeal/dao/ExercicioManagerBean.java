package pt.ipb.nutrimeal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


import pt.ipb.nutrimeal.entity.Exercicio;


public class ExercicioManagerBean implements ExercicioManager {
	
	private EntityManagerFactory factory;

	public ExercicioManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@Override
	public Exercicio createExercicio(String nome, String tipo, String descricao) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Exercicio e = new Exercicio();
		try {
				e.setNome(nome);
				e.setTipo(tipo);
				e.setDescricao(descricao);
				
				em.persist(e);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return e;
	}

	@Override
	public void deleteExercicio(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Exercicio e = em.find(Exercicio.class, id);
		em.remove(e);
		em.getTransaction().commit();
		em.close();	
	}

	@Override
	public Exercicio update(Exercicio exercicio) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Exercicio e = em.merge(exercicio);
		em.getTransaction().commit();
		em.close();
		return e;
	}

	@Override
	public List<Exercicio> getExercicio() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Exercicio> query = em.createNamedQuery("Exercicio.getAll", Exercicio.class);
		List<Exercicio> e = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return e;
	}

	@Override
	public Exercicio getExercicio(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Exercicio e = em.find(Exercicio.class, id);
		em.getTransaction().commit();
		em.close();
		return e;
	}

	@Override
	public Exercicio getExercicioNome(String nome) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Exercicio e = em.find(Exercicio.class, nome);
		em.getTransaction().commit();
		em.close();
		return e;
	}
	
	

}
