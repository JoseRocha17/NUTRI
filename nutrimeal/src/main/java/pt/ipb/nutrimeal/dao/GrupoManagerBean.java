package pt.ipb.nutrimeal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


import pt.ipb.nutrimeal.entity.Grupo;

public class GrupoManagerBean implements GrupoManager {

	private EntityManagerFactory factory;

	public GrupoManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public Grupo createGrupo(String nome) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Grupo a = new Grupo();
		try {
			a.setNome(nome);

				em.persist(a);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return a;
	}

	@Override
	public void deleteGrupo(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Grupo a = em.find(Grupo.class, id);
		em.remove(a);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Grupo update(Grupo grupo) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Grupo a = em.merge(grupo);
		em.getTransaction().commit();
		em.close();
		return a;
	}

	@Override
	public List<Grupo> getGrupos() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Grupo> query = em.createNamedQuery("Grupo.getAll", Grupo.class);
		List<Grupo> a = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return a;
	}

	@Override
	public Grupo getGrupo(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Grupo a = em.find(Grupo.class, id);
		em.getTransaction().commit();
		em.close();
		return a;
	}

}
