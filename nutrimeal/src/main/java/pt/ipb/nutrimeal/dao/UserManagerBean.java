package pt.ipb.nutrimeal.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import pt.ipb.nutrimeal.entity.*;
import pt.ipb.nutrimeal.entity.Group.ROLE;

public class UserManagerBean implements UserManager {

	private EntityManagerFactory factory;

	public UserManagerBean(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@Override
	public User create(String email, String name, String lastName, String password) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User owner = em.find(User.class, email);
		try {
			if (owner == null) {
				owner = new User(email, name, lastName, password);
				em.persist(owner);
				addToGroupInner(em, owner, ROLE.USER);
			}

			if (password != null) {
				String pass = crypt(password);

				owner.setPasswd("PassInativa-" + pass);
				String verificationKey = getVerificationKey(email, pass);
				owner.setVerificationKey(verificationKey);
				owner = em.merge(owner);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return owner;
	}

	
	@Override
	public User createAdmin(String email, String name, String lastName, String password) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User owner = em.find(User.class, email);
		try {
			if (owner == null) {
				owner = new User(email, name, lastName, password);
				em.persist(owner);
				addToGroupInner(em, owner, ROLE.ADMIN);
			}

			if (password != null) {
				String pass = crypt(password);

				owner.setPasswd("PassInativa-" + pass);
				String verificationKey = getVerificationKey(email, pass);
				owner.setVerificationKey(verificationKey);
				owner = em.merge(owner);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return owner;
	}

	@Override
	public void delete(User owner) {
		if (owner == null)
			return;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		owner = em.merge(owner);
		em.remove(owner);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(String email) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User u = em.find(User.class, email);
		em.remove(u);
		em.getTransaction().commit();
		em.close();
	}



	@Override
	public User setToAdmin(String email) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		User user = em.find(User.class, email);

		PersisterFactory.getInstance().getUserManager().removeFromGroup(user, ROLE.USER);

		PersisterFactory.getInstance().getUserManager().addToGroup(user, ROLE.ADMIN);

		em.getTransaction().commit();

		em.close();
		return user;
	}

	@Override
	public List<Group> getGroups(String email) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User owner = em.find(User.class, email);

		List<Group> l = owner.getGroups();

		em.getTransaction().commit();
		em.close();
		return l;
	}

	@Override
	public void addToGroup(User owner, ROLE groupname) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		addToGroupInner(em, owner, groupname);
		em.getTransaction().commit();
		em.close();
	}

	protected void addToGroupInner(EntityManager em, User owner, ROLE groupname) {
		owner = em.merge(owner);
		Group group = getGroupInner(em, owner.getEmail(), groupname);
		if (group == null) {
			group = new Group(groupname);
			em.persist(group);
		}
		group.setUser(owner);
		owner.getGroups().add(group);
	}

	@Override
	public void removeFromGroup(User owner, ROLE group) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		owner = em.merge(owner);
		for (Group g : owner.getGroups()) {
			if (g.getRole().equals(group)) {
				em.remove(g);
				owner.getGroups().remove(g);
				break;
			}
		}
		em.getTransaction().commit();
		em.close();
	}

	public static String crypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		StringBuffer sb = new StringBuffer("MD5:");
		md.update(password.getBytes("UTF-8"));
		byte[] digest = md.digest();
		for (int i = 0; i < digest.length; i++) {
			String hex = Integer.toHexString(0xff & digest[i]);
			if (hex.length() == 1)
				sb.append('0');
			sb.append(hex);
		}
		return sb.toString();
	}

	@Override
	public List<User> getAll() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<User> l = em.createNamedQuery(User.ALL, User.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return l;
	}

	@Override
	public User getUser(String email) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User p = em.find(User.class, email);
		em.getTransaction().commit();
		em.close();
		return p;
	}

	/**
	 * Key Creation: Create a column verification_key in users table that holds
	 * unique validation key for a User. Use SHA256 hash of your unique
	 * User-name (email-id in this case) with salt as his password. Convert the
	 * hash to base64 and store in verification_key of that User. This will be
	 * unique (for practical purposes, I wouldn't go into probability of
	 * collision).
	 * 
	 * @param email
	 * @param pass
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getVerificationKey(String email, String pass)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update((email + "." + pass).getBytes("UTF-8"));

		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		// sb.append(DatatypeConverter.printBase64Binary(digest));
		for (int i = 0; i < digest.length; i++) {
			String hex = Integer.toHexString(0xff & digest[i]);
			if (hex.length() == 1)
				sb.append('0');
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * Verification: Since we know the verification_key is unique, get the key
	 * from request-parameter and find the matching row. If found, set
	 * verification_key as null (this will also reduce chances of collision if
	 * any) and take User to "successfully-verified page". If not found, take
	 * the User to "already-activated/key-not-found/401 page".
	 */
	@Override
	public boolean activate(String verificationKey) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User owner = em.createNamedQuery(User.BY_VERIFICATIONKEY, User.class)
				.setParameter("verificationKey", verificationKey).getSingleResult();
		if (owner != null) {
			String pass = owner.getPasswd();
			owner.setPasswd(pass.split("-")[1]);
			owner.setVerificationKey(null);
			em.merge(owner);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		em.close();
		return false;
	}

	@Override
	public Group getGroup(String email, ROLE group) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Group g = getGroupInner(em, email, group);
		em.getTransaction().commit();
		em.close();
		return g;
	}

	@Override
	public List<ROLE> getRole(String email) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		List<ROLE> g = getRoleInner(em, email);
		em.getTransaction().commit();
		em.close();
		return g;
	}

	protected List<ROLE> getRoleInner(EntityManager em, String email) {

		try {
			List<ROLE> g = em.createNamedQuery(Group.GET_ROLE, Group.ROLE.class).setParameter("email", email)
					.getResultList();
			return g;
		} catch (NoResultException e) {

		}
		return null;
	}

	protected Group getGroupInner(EntityManager em, String email, ROLE role) {

		try {
			Group g = em.createNamedQuery(Group.BY_USERNAME_ROLE, Group.class).setParameter("email", email)
					.setParameter("role", role).getSingleResult();
			return g;
		} catch (NoResultException e) {

		}
		return null;
	}

	@Override
	public User changePassword(String email, String oldPassword, String newPassword) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, email);
//		Pessoa pessoa = em.find(Pessoa.class, email);
		try {
			user.setPasswd(crypt(newPassword));
//			pessoa.setPasswd(crypt(newPassword));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		em.getTransaction().commit();
		em.close();
		return user;
	}

	@Override
	public User update(User user) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User p = em.merge(user);
		em.getTransaction().commit();
		em.close();
		return p;
	}

	@Override
	public User getUserProfile(String name) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<User> query = em.createNamedQuery("User.getProfile", User.class).setParameter("name", name);
		User p = query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return p;
	}

	@Override
	public User createUserPrivada(String email, String name, String lastName, Date data_nasc, String contacto,
			String sexo, String localidade, String nacionalidade, long bi, long contri) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User p = new User();

		try {
				p.setEmail(email);
				p.setName(name);
				p.setLastName(lastName);
				p.setData_nasc(data_nasc);
				p.setContacto(contacto);
				p.setSexo(sexo);
				p.setLocalidade(localidade);
				p.setNacionalidade(nacionalidade);
				p.setBi(bi);
				p.setContribuinte(contri);
				em.persist(p);
			
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();
		return p;
	}

}
