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
public class Tipo implements Serializable {
	public final static String BY_EXERCICIO = "pt.ipb.ejetty.entity.Tipo.BY_EXERCICIO";
	public static final String GET_TIPO = "pt.ipb.ejetty.entity.Tipo.GET_TIPO";
	public static final String BY_EXERCICIO_TIPO = "pt.ipb.ejetty.entity.Tipo.BY_EXERCICIO_TIPO";

	public enum TIPO {
		CORRIDA, MUSCULACAO
	}

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    TIPO tipo;

	@ManyToOne
	Exercicio exercicio;

	public Tipo() {
		super();
	}

	public Tipo(TIPO tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Tipo) {
			Tipo other = (Tipo) obj;
			return other.getTipo().equals(getTipo());
		}
		return false;
	}
	
	public TIPO getTipo() {
		return tipo;
	}
	
	public void setTipo(TIPO tipo) {
		this.tipo = tipo;
	}
	

	public Exercicio getExercicio() {
		return exercicio;
	}
	
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}
	
}
