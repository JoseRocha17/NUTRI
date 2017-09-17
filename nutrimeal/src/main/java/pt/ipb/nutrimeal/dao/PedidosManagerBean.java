package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.Pedidos;


public class PedidosManagerBean implements PedidosManager {
	
	private EntityManagerFactory factory;
	public PedidosManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@Override
	public Pedidos createPedido(String email, String nome, String contacto, String titulo, String assunto, Date data) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Pedidos p = new Pedidos();
		try {
			p.setEmail(email);
			p.setNome(nome);
			p.setContacto(contacto);
			p.setTitulo(titulo);
			p.setAssunto(assunto);
			p.setData(data);
			
			
			em.persist(p);
			em.getTransaction().commit();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return p;
	}

	@Override
	public void deletePedido(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Pedidos p = em.find(Pedidos.class, id);
		em.remove(p);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Pedidos update(Pedidos pedido) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Pedidos p = em.merge(pedido);
		em.getTransaction().commit();
		em.close();
		return p;
	}

	@Override
	public List<Pedidos> getPedidos() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pedidos> query = em.createNamedQuery("Pedidos.getAll", Pedidos.class);
		List<Pedidos> p = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return p;
	}

	@Override
	public Pedidos getPedido(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Pedidos p = em.find(Pedidos.class, id);
		em.getTransaction().commit();
		em.close();
		return p;
	}

}
