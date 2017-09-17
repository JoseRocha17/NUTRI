package pt.ipb.nutrimeal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Role
 *
 */
@Entity
@Table(name="AuthGroup")
@NamedQueries({
	@NamedQuery(name=Group.BY_USERNAME, query="SELECT t FROM Group t WHERE t.user.email = :email"),
	@NamedQuery(name=Group.GET_ROLE, query="SELECT g.role FROM Group g WHERE g.user.email = :email"),
	@NamedQuery(name=Group.BY_USERNAME_ROLE, query="SELECT t FROM Group t  WHERE t.user.email = :email AND t.role = :role")
})
public class Group implements Serializable {
	public final static String BY_USERNAME = "pt.ipb.ejetty.entity.Group.BY_USERNAME";
	public static final String GET_ROLE = "pt.ipb.ejetty.entity.Group.GET_ROLE";
	public static final String BY_USERNAME_ROLE = "pt.ipb.ejetty.entity.Group.BY_USERNAME_ROLE";

	public enum ROLE {
		ADMIN, USER
	}

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    ROLE role;

	@ManyToOne
	User user;

	public Group() {
		super();
	}

	public Group(ROLE role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Group) {
			Group other = (Group) obj;
			return other.getRole().equals(getRole());
		}
		return false;
	}
	
	public ROLE getRole() {
		return role;
	}
	
	public void setRole(ROLE role) {
		this.role = role;
	}
	

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
