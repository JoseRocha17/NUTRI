package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.Medidas;
import pt.ipb.nutrimeal.entity.User;

public class MedidasManagerBean implements MedidasManager{
	private EntityManagerFactory factory;
	
	public MedidasManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@Override
	public Medidas createMedida(float peso, float altura, float pescoco, float cintura, float quadris, Date data, String nome) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Medidas m = new Medidas();
		try {
			User pessoa = em.find(User.class, nome);
				m.setPeso(peso);
				m.setAltura(altura);
				m.setPescoco(pescoco);
				m.setCintura(cintura);
				m.setQuadris(quadris);
				m.setData(data);
				
				m.setUser(pessoa);
				m.user.setEmail(pessoa.getEmail());
				m.user.setName(pessoa.getName());
				pessoa.getMedidas().add(m);
				
				em.persist(m);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return m;
	}

	@Override
	public void deleteMedida(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Medidas m = em.find(Medidas.class, id);
		em.remove(m);
		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public Medidas update(Medidas medidas) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Medidas m = em.merge(medidas);
		em.getTransaction().commit();
		em.close();
		return m;
	}

	@Override
	public List<Medidas> getMedidas() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Medidas> query = em.createNamedQuery("Medidas.getAll", Medidas.class);
		List<Medidas> m = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return m;
	}

	@Override
	public Medidas getMedida(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Medidas m = em.find(Medidas.class, id);
		em.getTransaction().commit();
		em.close();
		return m;
	}


	@Override
	public List<Medidas> getMedidasUser(String email) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Medidas> query = em.createNamedQuery("Medidas.getPerfil", Medidas.class).setParameter("email",email);
		List<Medidas> m = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return m;
	}

}
