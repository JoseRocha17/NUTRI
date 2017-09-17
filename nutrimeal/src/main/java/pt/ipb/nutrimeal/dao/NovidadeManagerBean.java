package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.Novidade;

public class NovidadeManagerBean implements NovidadeManager {
	private EntityManagerFactory factory;

	public NovidadeManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}
	@Override
	public Novidade createNovidade(String titulo, String brevedesc, String descricao, Date data) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Novidade n = new Novidade();
		try {
			n.setTitulo(titulo);
			n.setBrevedesc(brevedesc);
			n.setDescricao(descricao);
			n.setData(data);
			
			em.persist(n);
			em.getTransaction().commit();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return n;
	}

	@Override
	public void deleteNovidade(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Novidade n = em.find(Novidade.class, id);
		em.remove(n);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public Novidade update(Novidade novidade) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Novidade n = em.merge(novidade);
		em.getTransaction().commit();
		em.close();
		return n;
	}

	@Override
	public List<Novidade> getNovidades() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Novidade> query = em.createNamedQuery("Novidade.getAll", Novidade.class);
		List<Novidade> n = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return n;
	}

	@Override
	public Novidade getNovidade(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Novidade n = em.find(Novidade.class, id);
		em.getTransaction().commit();
		em.close();
		return n;
	}

}
