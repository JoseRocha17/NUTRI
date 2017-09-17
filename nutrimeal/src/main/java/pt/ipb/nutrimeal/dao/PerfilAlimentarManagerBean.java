package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.PerfilAlimentar;
import pt.ipb.nutrimeal.entity.User;

public class PerfilAlimentarManagerBean implements PerfilAlimentarManager{
	private EntityManagerFactory factory;
	
	public PerfilAlimentarManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@Override
	public PerfilAlimentar createPerfilAlimentar(String nome, String dia, Date data, String name) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		PerfilAlimentar pa = new PerfilAlimentar();
		try {
			User pessoa = em.find(User.class, name);
				pa.setNome(nome);
				pa.setData(data);
				pa.setDia(dia);
				
				pa.setUser(pessoa);
				pa.user.setEmail(pessoa.getEmail());
				pa.user.setName(pessoa.getName());
				pessoa.getPerfilAlimentar().add(pa);
				
				em.persist(pa);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return pa;
	}

	@Override
	public void deletePerfilAlimentar(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		PerfilAlimentar pa = em.find(PerfilAlimentar.class, id);
		em.remove(pa);
		em.getTransaction().commit();
		em.close();		
	}


	@Override
	public PerfilAlimentar update(PerfilAlimentar perfilAlimentar) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		PerfilAlimentar pa = em.merge(perfilAlimentar);
		em.getTransaction().commit();
		em.close();
		return pa;
	}

	@Override
	public List<PerfilAlimentar> getPerfilAlimentares() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PerfilAlimentar> query = em.createNamedQuery("PerfilAlimentar.getAll", PerfilAlimentar.class);
		List<PerfilAlimentar> pa = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return pa;
	}

	@Override
	public PerfilAlimentar getPerfilAlimentar(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		PerfilAlimentar pa = em.find(PerfilAlimentar.class, id);
		em.getTransaction().commit();
		em.close();
		return pa;
	}

	@Override
	public List<PerfilAlimentar> getPerfilAlimentarNome(String email) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PerfilAlimentar> query = em.createNamedQuery("PerfilAlimentar.getPerfil", PerfilAlimentar.class).setParameter("email",email);
		List<PerfilAlimentar> pa = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return pa;
	}

}
