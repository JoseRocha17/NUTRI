package pt.ipb.nutrimeal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


import pt.ipb.nutrimeal.entity.PerfilAlimentar;
import pt.ipb.nutrimeal.entity.Refeicao;


public class RefeicaoManagerBean implements RefeicaoManager {
private EntityManagerFactory factory;
	
	public RefeicaoManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@Override
	public Refeicao createRefeicao(String nome, long idPerfil) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Refeicao r = new Refeicao();
		try {
			PerfilAlimentar perfilAlimentar = em.find(PerfilAlimentar.class, idPerfil);
				r.setNome(nome);
				
				r.setPerfilAlimentar(perfilAlimentar);
				r.perfilalimentar.setId(perfilAlimentar.getId());
				r.perfilalimentar.setNome(perfilAlimentar.getNome());
				perfilAlimentar.getRefeicao().add(r);
				
				em.persist(r);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return r;
	}

	@Override
	public void deleteRefeicao(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Refeicao r = em.find(Refeicao.class, id);
		em.remove(r);
		em.getTransaction().commit();
		em.close();		
		
	}

	@Override
	public Refeicao update(Refeicao refeicao) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Refeicao r = em.merge(refeicao);
		em.getTransaction().commit();
		em.close();
		return r;
	}

	@Override
	public List<Refeicao> getRefeicoes() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Refeicao> query = em.createNamedQuery("Refeicao.getAll", Refeicao.class);
		List<Refeicao> r = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return r;
	}
	
	@Override
	public List<Refeicao> getPerfilRefeicao(long id){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Refeicao> query = em.createNamedQuery("Refeicao.getPerfil", Refeicao.class).setParameter("id", id);
		List<Refeicao> r = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return r;
	}

	@Override
	public Refeicao getRefeicao(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Refeicao r = em.find(Refeicao.class, id);
		em.getTransaction().commit();
		em.close();
		return r;
	}


	
	
}
