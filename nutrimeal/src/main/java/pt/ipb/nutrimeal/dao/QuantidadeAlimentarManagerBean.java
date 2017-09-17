package pt.ipb.nutrimeal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.Alimento;
import pt.ipb.nutrimeal.entity.QuantidadeAlimentar;
import pt.ipb.nutrimeal.entity.Refeicao;



public class QuantidadeAlimentarManagerBean implements QuantidadeAlimentarManager {
	
	private EntityManagerFactory factory;

	public QuantidadeAlimentarManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@Override
	public QuantidadeAlimentar createQuantidadeAlimentar(long quantidade, String atributo, long idRefeicao, long idAlimento) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		QuantidadeAlimentar qa = new QuantidadeAlimentar();
		try {
			Refeicao refeicao = em.find(Refeicao.class, idRefeicao);
			Alimento alimento = em.find(Alimento.class, idAlimento);

			qa.setQuantidade(quantidade);
			qa.setAtributo(atributo);
			qa.setRefeicao(refeicao);
			qa.refeicao.setId(refeicao.getId());
			qa.refeicao.setNome(refeicao.getNome());
			refeicao.getQuantidadealimentar().add(qa);
			
			qa.setAlimento(alimento);
			qa.alimento.setId(alimento.getId());
			qa.alimento.setNome(alimento.getNome());
			alimento.getQuantidadealimentar().add(qa);

			em.persist(qa);
		em.getTransaction().commit();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	em.close();
	return qa;
	}

	@Override
	public void deleteQuantidadeAlimentar(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		QuantidadeAlimentar qa = em.find(QuantidadeAlimentar.class, id);
		em.remove(qa);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public QuantidadeAlimentar update(QuantidadeAlimentar quantidadeAlimentar) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		QuantidadeAlimentar qa = em.merge(quantidadeAlimentar);
		em.getTransaction().commit();
		em.close();
		return qa;
	}

	@Override
	public List<QuantidadeAlimentar> getQuantidadeAlimentares() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<QuantidadeAlimentar> query = em.createNamedQuery("QuantidadeAlimentar.getAll", QuantidadeAlimentar.class);
		List<QuantidadeAlimentar> qa = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return qa;
	}

	@Override
	public QuantidadeAlimentar getQuantidadeAlimentar(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		QuantidadeAlimentar qa = em.find(QuantidadeAlimentar.class, id);
		em.getTransaction().commit();
		em.close();
		return qa;
	}

	@Override
	public List<QuantidadeAlimentar> getQuantidadeRefeicao(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<QuantidadeAlimentar> query = em.createNamedQuery("QuantidadeAlimentar.getQuantidadeRefeicao", QuantidadeAlimentar.class).setParameter("id", id);
		List<QuantidadeAlimentar> qa = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return qa;
	}
	

		
}
