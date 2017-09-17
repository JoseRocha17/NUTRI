package pt.ipb.nutrimeal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.ExercicioAtributo;
import pt.ipb.nutrimeal.entity.MetaExercicio;
import pt.ipb.nutrimeal.entity.Atributo;

public class ExercicioAtributoManagerBean implements ExercicioAtributoManager {

	private EntityManagerFactory factory;
	
	public ExercicioAtributoManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public ExercicioAtributo createExercicioAtributo(float valor, long idExercicio, long idAtributo) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		ExercicioAtributo ea = new ExercicioAtributo();
		try {
			MetaExercicio exercicio = em.find(MetaExercicio.class, idExercicio);
			Atributo atributo = em.find(Atributo.class, idAtributo);

			
			ea.setValor(valor);
			
			//ea.setmExercicio(exercicio);
			ea.setMetaexercicio(exercicio);
			ea.metaexercicio.setId(exercicio.getId());
			ea.metaexercicio.exercicio.setNome(exercicio.exercicio.getNome());
			exercicio.getExercicioatributo().add(ea);
			
			ea.setAtributo(atributo);
			ea.atributo.setId(atributo.getId());
			ea.atributo.setNome(atributo.getNome());
			atributo.getExercicioatributo().add(ea);
			

			em.persist(ea);
		em.getTransaction().commit();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	em.close();
	return ea;
	}

	@Override
	public void deleteExercicioAtributo(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		ExercicioAtributo ea = em.find(ExercicioAtributo.class, id);
		em.remove(ea);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public ExercicioAtributo update(ExercicioAtributo exercicioAtributo) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		ExercicioAtributo ea = em.merge(exercicioAtributo);
		em.getTransaction().commit();
		em.close();
		return ea;
	}

	@Override
	public List<ExercicioAtributo> getExercicioAtributos() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ExercicioAtributo> query = em.createNamedQuery("ExercicioAtributo.getAll", ExercicioAtributo.class);
		List<ExercicioAtributo> ea = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return ea;
	}

	@Override
	public ExercicioAtributo getExercicioAtributo(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		ExercicioAtributo ea = em.find(ExercicioAtributo.class, id);
		em.getTransaction().commit();
		em.close();
		return ea;
	}

	@Override
	public List<ExercicioAtributo> getExercicioAtributoUser(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ExercicioAtributo> query = em.createNamedQuery("ExercicioAtributo.getExercicioUser", ExercicioAtributo.class).setParameter("id", id);
		List<ExercicioAtributo> ea = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return ea;
	}

}
