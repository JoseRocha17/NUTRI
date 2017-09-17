package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


import pt.ipb.nutrimeal.entity.Objetivos;
import pt.ipb.nutrimeal.entity.User;



public class ObjetivosManagerBean implements ObjetivosManager {
	
private EntityManagerFactory factory;
	
	public ObjetivosManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@Override
	public Objetivos createObjetivo(float peso, float pescoco, float cintura, float quadris, Date data, String nome) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Objetivos o = new Objetivos();
		try {
			User pessoa = em.find(User.class, nome);
				o.setPeso(peso);
				o.setPescoco(pescoco);
				o.setCintura(cintura);
				o.setQuadris(quadris);
				o.setData(data);
				
				o.setUser(pessoa);
				o.user.setEmail(pessoa.getEmail());
				o.user.setName(pessoa.getName());
				pessoa.getObjetivos().add(o);

				em.persist(o);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return o;
	}

	@Override
	public void deleteObjetivo(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Objetivos o = em.find(Objetivos.class, id);
		em.remove(o);
		em.getTransaction().commit();
		em.close();		
	}

//	@Override
//	public Objetivos update(float peso, float pescoco, float cintura, float quadris, Date data, String nome) {
//		EntityManager em = factory.createEntityManager();
//		em.getTransaction().begin();
//		Objetivos objetivos = new Objetivos(peso, pescoco, cintura, quadris, data, nome);
//		em.merge(objetivos);
//		em.getTransaction().commit();
//		em.close();
//		return objetivos;
//	}
//	
	@Override
	public Objetivos update(Objetivos objetivos) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Objetivos o = em.merge(objetivos);
		em.getTransaction().commit();
		em.close();
		return o;
	}

	@Override
	public List<Objetivos> getObjetivos() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Objetivos> query = em.createNamedQuery("Objetivos.getAll", Objetivos.class);
		List<Objetivos> o = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return o;
	}

	@Override
	public Objetivos getObjetivo(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Objetivos o = em.find(Objetivos.class, id);
		em.getTransaction().commit();
		em.close();
		return o;
	}


	@Override
	public List<Objetivos> getObjetivosUser(String email) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Objetivos> query = em.createNamedQuery("Objetivos.getPerfil", Objetivos.class).setParameter("email",email);
		List<Objetivos> o = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return o;
	}

}
