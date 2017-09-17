package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Table
@NamedQueries({
		@NamedQuery(name="PerfilFisico.getAll", query = "SELECT pf FROM PerfilFisico pf"),
		@NamedQuery(name="PerfilFisico.getPerfil", query = "SELECT pf FROM PerfilFisico pf WHERE pf.user.email=:email")
})
@Entity
public class PerfilFisico implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String nome, email;
	String dia;
	Date data;
	
	@OneToMany(mappedBy = "perfil", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<MetaExercicio> metaexercicio = new ArrayList<MetaExercicio>();
	
	@ManyToOne
	public User user = new User();
	

	public PerfilFisico() {
		super();
	}

	public PerfilFisico(long id, String nome, String dia, Date data, String pessoa) {
		this.id = id;
		this.nome=nome;
		this.dia=dia;
		this.data = data;
		this.user.name=pessoa;
	}
	
	public PerfilFisico(long id, String nome, String dia, Date data, String pessoa, String pessoaEmail, String pessoaLastName) {
		this.id=id;
		this.nome=nome;
		this.dia=dia;
		this.data = data;
		this.user.name=pessoa;
		this.user.email=pessoaEmail;
		this.user.lastName=pessoaLastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<MetaExercicio> getMetaexercicio() {
		return metaexercicio;
	}

	public void setMetaexercicio(List<MetaExercicio> metaexercicio) {
		this.metaexercicio = metaexercicio;
	}

	


}
