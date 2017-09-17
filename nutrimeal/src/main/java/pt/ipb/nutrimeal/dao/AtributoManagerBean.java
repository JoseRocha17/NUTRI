package pt.ipb.nutrimeal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.Atributo;

public class AtributoManagerBean implements AtributoManager {

	private EntityManagerFactory factory;

	public AtributoManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}
	@Override
	public Atributo createAtributo(String nome) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Atributo a = new Atributo();
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
	public void deleteAtributo(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Atributo a = em.find(Atributo.class, id);
		em.remove(a);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public Atributo update(Atributo atributo) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Atributo a = em.merge(atributo);
		em.getTransaction().commit();
		em.close();
		return a;
	}

	@Override
	public List<Atributo> getAtributos() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Atributo> query = em.createNamedQuery("Atributo.getAll", Atributo.class);
		List<Atributo> a = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return a;
	}

	@Override
	public Atributo getAtributo(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Atributo a = em.find(Atributo.class, id);
		em.getTransaction().commit();
		em.close();
		return a;
	}

}
