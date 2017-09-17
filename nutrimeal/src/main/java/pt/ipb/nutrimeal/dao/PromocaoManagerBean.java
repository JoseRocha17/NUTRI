package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.Promocao;

public class PromocaoManagerBean implements PromocaoManager {

	private EntityManagerFactory factory;
	public PromocaoManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public Promocao createPromocao(String titulo, String breveDesc, String descricao, float preco, Date dataInicio,
			Date dataFim) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Promocao p = new Promocao();
		try {
			p.setTitulo(titulo);
			p.setBreveDesc(breveDesc);
			p.setDescricao(descricao);
			p.setPreco(preco);
			p.setDataInicio(dataInicio);
			p.setDataFim(dataFim);
			
			em.persist(p);
			em.getTransaction().commit();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return p;
	}

	@Override
	public void deletePromocao(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Promocao p = em.find(Promocao.class, id);
		em.remove(p);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public Promocao update(Promocao promocao) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Promocao p = em.merge(promocao);
		em.getTransaction().commit();
		em.close();
		return p;
	}

	@Override
	public List<Promocao> getPromocoes() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Promocao> query = em.createNamedQuery("Promocao.getAll", Promocao.class);
		List<Promocao> p = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return p;
	}

	@Override
	public Promocao getPromocao(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Promocao p = em.find(Promocao.class, id);
		em.getTransaction().commit();
		em.close();
		return p;
	}

}
