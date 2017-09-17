package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.PerfilFisico;
import pt.ipb.nutrimeal.entity.User;

public class PerfilFisicoManagerBean implements PerfilFisicoManager {
	private EntityManagerFactory factory;
	
	public PerfilFisicoManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}


	@Override
	public PerfilFisico createPerfilFisico(String nome, String dia, Date data, String name) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		PerfilFisico pf = new PerfilFisico ();
		try {
			User pessoa = em.find(User.class, name);
				pf.setNome(nome);
				pf.setData(data);
				pf.setDia(dia);
				
				pf.setUser(pessoa);
				pf.user.setEmail(pessoa.getEmail());
				pf.user.setName(pessoa.getName());
				pessoa.getPerfilFisico ().add(pf);
				
				em.persist(pf);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return pf;
	}

	@Override
	public void deletePerfilFisico(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		PerfilFisico pf = em.find(PerfilFisico.class, id);
		em.remove(pf);
		em.getTransaction().commit();
		em.close();	
	}

	@Override
	public PerfilFisico update(PerfilFisico perfilFisico) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		PerfilFisico pf = em.merge(perfilFisico);
		em.getTransaction().commit();
		em.close();
		return pf;
	}

	@Override
	public List<PerfilFisico> getPerfilFisicos() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PerfilFisico> query = em.createNamedQuery("PerfilFisico.getAll", PerfilFisico.class);
		List<PerfilFisico> pf = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return pf;
	}

	@Override
	public PerfilFisico getPerfilFisico(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		PerfilFisico pf = em.find(PerfilFisico.class, id);
		em.getTransaction().commit();
		em.close();
		return pf;
	}

	@Override
	public List<PerfilFisico> getPerfilFisicoUser(String email) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PerfilFisico> query = em.createNamedQuery("PerfilFisico.getPerfil", PerfilFisico.class).setParameter("email",email);
		List<PerfilFisico> pf = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return pf;
	}
	
}
