package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Table
@NamedQueries({
		@NamedQuery(name="PerfilAlimentar.getAll", query = "SELECT pa FROM PerfilAlimentar pa"),
		@NamedQuery(name="PerfilAlimentar.getPerfil", query = "SELECT pa FROM PerfilAlimentar pa WHERE pa.user.email=:email")
})
@Entity
public class PerfilAlimentar implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String nome, email, dia;
	Date data;
	
	@ManyToOne
	public User user = new User();
	
	@OneToMany(mappedBy = "perfilalimentar", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<Refeicao> refeicao= new ArrayList<Refeicao>();

	public PerfilAlimentar() {
		super();
	}

	public PerfilAlimentar(long id, String nome, String dia, Date data, String pessoa) {
		this.id = id;
		this.nome=nome;
		this.dia=dia;
		this.data = data;
		this.user.name=pessoa;
	}
	
	public PerfilAlimentar(long id, String nome, String dia, Date data, String nomeUser, String pessoaFirstName, String pessoaLastName) {
		this.id = id;
		this.nome=nome;
		this.dia=dia;
		this.data = data;
		this.user.email=nomeUser;
		this.user.name=pessoaFirstName;
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

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
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

	public List<Refeicao> getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(List<Refeicao> refeicao) {
		this.refeicao = refeicao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	

}
