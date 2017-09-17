package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import pt.ipb.nutrimeal.entity.Group;
import pt.ipb.nutrimeal.entity.User;
import pt.ipb.nutrimeal.entity.Group.ROLE;

public interface UserManager {
	
	User create(String email, String name, String lastName, String password);
	User createAdmin(String email, String name, String lastName, String password);
	User createUserPrivada(String email, String name, String lastName, Date data_nasc, String contacto, String sexo,
			String localidade, String nacionalidade, long bi, long contri);

	
	User getUser(String email);
	List<User> getAll();
	User update(User user);
	void delete(User user);
	void delete(String email);
	
	User getUserProfile(String name);
	
	boolean activate(String verificationKey);
	void addToGroup(User user, ROLE role);
	void removeFromGroup(User player, ROLE role);
	Group getGroup(String email, ROLE role);
	List<Group> getGroups(String email);
	
	User setToAdmin(String email);

	
	User changePassword(String email, String oldPassword, String newPassword);
	List<ROLE> getRole(String email);
	

	
}
