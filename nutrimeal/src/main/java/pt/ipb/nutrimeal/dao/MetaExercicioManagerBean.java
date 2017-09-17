package pt.ipb.nutrimeal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.Exercicio;
import pt.ipb.nutrimeal.entity.MetaExercicio;
import pt.ipb.nutrimeal.entity.PerfilFisico;


public class MetaExercicioManagerBean implements MetaExercicioManager {
	
	private EntityManagerFactory factory;

	public MetaExercicioManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}


	@Override
	public MetaExercicio createMetaExercicio(float calorias, long idExercicio, long idPerfil) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		MetaExercicio me = new MetaExercicio();
		try {
			Exercicio exercicio = em.find(Exercicio.class, idExercicio);
			PerfilFisico perfil = em.find(PerfilFisico.class, idPerfil);
			me.setCalorias(calorias);
			me.setExercicio(exercicio);
			me.exercicio.setId(exercicio.getId());
			me.exercicio.setNome(exercicio.getNome());
			exercicio.getMetaexercicio().add(me);
			
			me.setPerfil(perfil);
			me.perfil.setId(perfil.getId());
			me.perfil.setNome(perfil.getNome());
			perfil.getMetaexercicio().add(me);

			em.persist(me);
		em.getTransaction().commit();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	em.close();
	return me;
	}

	@Override
	public void deleteMetaExercicio(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		MetaExercicio me = em.find(MetaExercicio.class, id);
		em.remove(me);
		em.getTransaction().commit();
		em.close();
	}


	@Override
	public MetaExercicio update(MetaExercicio metaExercicio) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		MetaExercicio me = em.merge(metaExercicio);
		em.getTransaction().commit();
		em.close();
		return me;
	}

	@Override
	public List<MetaExercicio> getMetaExercicios() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<MetaExercicio> query = em.createNamedQuery("MetaExercicio.getAll", MetaExercicio.class);
		List<MetaExercicio> me = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return me;
	}

	@Override
	public MetaExercicio getMetaExercicio(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		MetaExercicio me = em.find(MetaExercicio.class, id);
		em.getTransaction().commit();
		em.close();
		return me;
	}


	@Override
	public List<MetaExercicio> getPerfilUser(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<MetaExercicio> query = em.createNamedQuery("MetaExercicio.getPerfilUser", MetaExercicio.class).setParameter("id", id);
		List<MetaExercicio> me = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return me;
	}

}
